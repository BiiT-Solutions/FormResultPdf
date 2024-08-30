package com.biit.form.result.pdf.components;

import com.biit.form.entity.TreeObject;
import com.biit.form.result.CategoryResult;
import com.biit.form.result.FormResult;
import com.biit.form.result.QuestionWithValueResult;
import com.biit.form.result.RepeatableGroupResult;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.biit.form.result.pdf.style.Theme;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class FormResultTableFactory extends BaseElement {
    private final static int CONTENT_WIDTH = 300;
    private final static String ANSWER_TAB = "    ";
    private static final int BIG_SEPARATOR_MIN_HEIGHT = 20;

    public static PdfPTable createElementPdfStructure(TreeObject element) throws InvalidElementException {
        float[] widths = {1f};
        PdfPTable table = new PdfPTable(widths);
        setTablePropierties(table);

        createElementPdfStructure(table, element);

        return table;
    }

    private static void createElementPdfStructure(PdfPTable table, TreeObject element) throws InvalidElementException {
        addCell(table, element);

        for (TreeObject child : element.getAllNotHiddenChildren()) {
            table.addCell(createElementPdfStructure(child));
        }
    }

    private static void addCell(PdfPTable table, TreeObject element) throws InvalidElementException {
        if (element instanceof FormResult) {
            addCell(table, (FormResult) element);
        } else if (element instanceof CategoryResult) {
            addCell(table, (CategoryResult) element);
        } else if (element instanceof QuestionWithValueResult) {
            addCell(table, (QuestionWithValueResult) element);
            final PdfPCell separator = createBigWhiteSeparator();
            table.addCell(separator);
        } else if (element instanceof RepeatableGroupResult) {
            addCell(table, (RepeatableGroupResult) element);
        } else if (element == null) {
            //Skip null elements.
        } else {
            throw new InvalidElementException("Element '" + element + "' cannot be represented in the PDF. Not defined.");
        }
    }

    private static void addCell(PdfPTable table, FormResult form) {
        table.addCell(createElementCell(form, Theme.getFormFont(), Theme.FORM_FONT_SIZE, CONTENT_WIDTH));
    }

    private static void addCell(PdfPTable table, CategoryResult category) {
        table.addCell(createWhiteSeparator(20));
        table.addCell(createElementCell(category, Theme.getCategoryFont(), Theme.CATEGORY_FONT_SIZE, CONTENT_WIDTH));
    }

    private static void addCell(PdfPTable table, RepeatableGroupResult group) {
        table.addCell(createWhiteSeparator(10));
        table.addCell(createElementCell(group, Theme.getGroupFont(), Theme.GROUP_FONT_SIZE, CONTENT_WIDTH));
    }

    private static void addCell(PdfPTable table, QuestionWithValueResult question) {
        // Add questions
        table.addCell(createElementCell(question, Theme.getQuestionFont(), Theme.QUESTION_FONT_SIZE, CONTENT_WIDTH));

        // Add answers
        final List<Pair<String, String>> answers = new ArrayList<>();
        if (question.getQuestionValues() != null) {
            for (int i = 0; i < question.getQuestionValues().size(); i++) {
                answers.add(new Pair<>(question.getQuestionValues().get(i), question.getAnswerLabels() != null
                        && i < question.getAnswerLabels().size() ? question.getAnswerLabels().get(i) : null));
            }
        }

        answers.sort(Comparator.comparing(p -> p.first));

        for (Pair<String, String> answer : answers) {
            if (answer != null) {
                table.addCell(createElementCell(ANSWER_TAB + answer.first + (answer.second != null && !answer.second.trim().isEmpty()
                                && !Objects.equals(answer.first, answer.second) ? " (" + answer.second + ")" : ""),
                        Theme.getAnswerFont(), Theme.ANSWER_FONT_SIZE, CONTENT_WIDTH));
            }
        }
    }

    protected static PdfPCell createElementCell(TreeObject element, BaseFont font, int fontSize, int maxColumnWidth) {
        return createElementCell(element.getLabel(), font, fontSize, maxColumnWidth);
    }

    protected static PdfPCell createElementCell(String text, BaseFont font, int fontSize, int maxColumnWidth) {
        PdfPCell cell = getCell(text, 0, 1, Element.ALIGN_LEFT, Color.WHITE, font, fontSize);
        // cell.setMinimumHeight(TITLE_HEIGHT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    public static PdfPCell createBigWhiteSeparator() {
        return createWhiteSeparator(BIG_SEPARATOR_MIN_HEIGHT);
    }

    public static PdfPCell createWhiteSeparator(int height) {
        final PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        setCellProperties(cell);
        cell.setMinimumHeight(height);
        return cell;
    }

}
