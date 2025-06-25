package com.biit.form.result.pdf.components;

import com.biit.form.entity.TreeObject;
import com.biit.form.result.CategoryResult;
import com.biit.form.result.FormResult;
import com.biit.form.result.QuestionWithValueResult;
import com.biit.form.result.RepeatableGroupResult;
import com.biit.form.result.SystemFieldResult;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.biit.form.result.pdf.logger.PdfExporterLog;
import com.biit.form.result.pdf.style.Theme;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FormResultTableFactory extends BaseElement {
    private static final String DEFAULT_LANGUAGE = "EN";
    private static final int CONTENT_WIDTH = 300;
    private static final String ANSWER_TAB = "    ";
    private static final int GROUP_SEPARATOR_MIN_HEIGHT = 10;
    private static final int ANSWERS_SEPARATOR_MIN_HEIGHT = 6;
    private static final int CATEGORY_SEPARATOR_MIN_HEIGHT = 20;

    private static final String LOCALIZATION_SYSTEM_FIELD = "localization";
    private static String[] localizationLanguages;

    private static boolean showTechnicalNames;
    private static boolean disableTranslations;

    public static PdfPTable createElementPdfStructure(TreeObject element, Locale locale, boolean showTechnicalName, boolean disableTranslation)
            throws InvalidElementException {
        if (locale != null) {
            localizationLanguages = new String[]{locale.getLanguage().toLowerCase()};
        }
        showTechnicalNames = showTechnicalName;
        disableTranslations = disableTranslation;
        return createElementPdfStructure(element);
    }

    public static PdfPTable createElementPdfStructure(TreeObject element) throws InvalidElementException {
        final float[] widths = {1f};
        final PdfPTable table = new PdfPTable(widths);

        if (localizationLanguages == null) {
            element.getAllChildrenInHierarchy(SystemFieldResult.class).forEach(child -> {
                if (Objects.equals(child.getName(), LOCALIZATION_SYSTEM_FIELD) && child.getValue() != null) {
                    localizationLanguages = child.getValue().split(",");
                }
            });
        }

        setTablePropierties(table);
        createElementPdfStructure(table, element);
        return table;
    }

    private static void createElementPdfStructure(PdfPTable table, TreeObject element) throws InvalidElementException {
        addCell(table, element);

        if (element != null) {
            for (TreeObject child : element.getAllNotHiddenChildren()) {
                table.addCell(createElementPdfStructure(child));
            }
        }
    }

    private static void addCell(PdfPTable table, TreeObject element) throws InvalidElementException {
        if (element instanceof FormResult formResult) {
            addCell(table, formResult);
        } else if (element instanceof CategoryResult categoryResult) {
            addCell(table, categoryResult);
        } else if (element instanceof QuestionWithValueResult questionWithValueResult) {
            addCell(table, questionWithValueResult);
            final PdfPCell separator = createBigWhiteSeparator();
            table.addCell(separator);
        } else if (element instanceof RepeatableGroupResult repeatableGroupResult) {
            addCell(table, repeatableGroupResult);
        } else if (element instanceof SystemFieldResult) {
            PdfExporterLog.debug(FormResultTableFactory.class.getName(), "Ignoring System Fields.");
        } else if (element == null) {
            PdfExporterLog.debug(FormResultTableFactory.class.getName(), "Ignoring null elements.");
        } else {
            throw new InvalidElementException("Element '" + element + "' cannot be represented in the PDF. Not defined.");
        }
    }

    private static void addCell(PdfPTable table, FormResult form) {
        table.addCell(createElementCell(form, Theme.getFormFont(), Theme.FORM_FONT_SIZE, CONTENT_WIDTH));
    }

    private static void addCell(PdfPTable table, CategoryResult category) {
        table.addCell(createWhiteSeparator(CATEGORY_SEPARATOR_MIN_HEIGHT));
        table.addCell(createElementCell(category, Theme.getCategoryFont(), Theme.CATEGORY_FONT_SIZE, CONTENT_WIDTH));
    }

    private static void addCell(PdfPTable table, RepeatableGroupResult group) {
        table.addCell(createWhiteSeparator(GROUP_SEPARATOR_MIN_HEIGHT));
        table.addCell(createElementCell(group, Theme.getGroupFont(), Theme.GROUP_FONT_SIZE, CONTENT_WIDTH));
    }

    private static void addCell(PdfPTable table, QuestionWithValueResult question) {
        // Add questions
        table.addCell(createElementCell(question, Theme.getQuestionFont(), Theme.QUESTION_FONT_SIZE, CONTENT_WIDTH));

        // Add answers
        final List<Pair<String, String>> answers = new ArrayList<>();
        if (question.getQuestionValues() != null) {
            for (int i = 0; i < question.getQuestionValues().size(); i++) {
                boolean translated = false;
                if (!disableTranslations && localizationLanguages != null) {
                    for (String language : localizationLanguages) {
                        final String translatedLabel = question.getAnswerLabelTranslations() != null
                                && question.getAnswerLabelTranslations().get(question.getQuestionValues().get(i)) != null
                                && question.getAnswerLabelTranslations().get(question.getQuestionValues().get(i)).get(language.toUpperCase()) != null
                                ? question.getAnswerLabelTranslations().get(question.getQuestionValues().get(i)).get(language.toUpperCase()) : null;
                        if (translatedLabel != null) {
                            answers.add(new Pair<>(question.getQuestionValues().get(i), translatedLabel));
                            translated = true;
                        }
                        if (translated || Objects.equals(DEFAULT_LANGUAGE, language)) {
                            //Language has been found. Stop searching.
                            //A selected one or the default one (English).
                            break;
                        }
                    }
                }
                if (!translated) {
                    answers.add(new Pair<>(question.getQuestionValues().get(i), question.getAnswerLabels() != null
                            && i < question.getAnswerLabels().size() ? question.getAnswerLabels().get(i) : null));
                }
            }
        }

        answers.sort(Comparator.comparing(Pair::getFirst));

        for (Pair<String, String> answer : answers) {
            if (answer != null) {
                final String text = createAnswerText(answer);
                table.addCell(createElementCell(text, Theme.getAnswerFont(), Theme.ANSWER_FONT_SIZE, CONTENT_WIDTH));
                table.addCell(createWhiteSeparator(ANSWERS_SEPARATOR_MIN_HEIGHT));
            }
        }
    }


    private static String createAnswerText(Pair<String, String> answer) {
        final StringBuilder text = new StringBuilder();
        text.append(ANSWER_TAB);
        if (showTechnicalNames) {
            text.append(answer.getFirst());
        }
        if (answer.getSecond() != null && !answer.getSecond().trim().isEmpty() && !Objects.equals(answer.getFirst(), answer.getSecond())) {
            if (showTechnicalNames) {
                text.append(" (");
            }
            text.append(answer.getSecond());
            if (showTechnicalNames) {
                text.append(")");
            }
        }
        text.append("\n");
        return text.toString();
    }


    protected static PdfPCell createElementCell(TreeObject element, BaseFont font, int fontSize, int maxColumnWidth) {
        if (!disableTranslations && localizationLanguages != null) {
            for (String language : localizationLanguages) {
                if (Objects.equals(DEFAULT_LANGUAGE, language)) {
                    //English is the default language.
                    break;
                }
                //If translation on the required language exists, use it.
                final String translation = element.getLabelTranslations().get(language.toUpperCase());
                if (translation != null) {
                    return createElementCell(translation, font, fontSize, maxColumnWidth);
                }
            }
        }
        return createElementCell(element.getLabel(), font, fontSize, maxColumnWidth);
    }


    private static String createTreeObjectTechnicalNameText(String technicalName, String label) {
        final StringBuilder text = new StringBuilder();
        if (showTechnicalNames) {
            text.append(technicalName);
        }
        if (showTechnicalNames) {
            text.append(" (");
        }
        text.append(label);
        if (showTechnicalNames) {
            text.append(")");
        }
        return text.toString();
    }


    protected static PdfPCell createElementCell(String text, BaseFont font, int fontSize, int maxColumnWidth) {
        final PdfPCell cell = getCell(text, 0, 1, Element.ALIGN_LEFT, Color.WHITE, font, fontSize);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }


    public static PdfPCell createBigWhiteSeparator() {
        return createWhiteSeparator(GROUP_SEPARATOR_MIN_HEIGHT);
    }


    public static PdfPCell createWhiteSeparator(int height) {
        return BaseElement.createWhiteSeparator(height);
    }

}
