package com.biit.form.result.pdf.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.biit.form.entity.TreeObject;
import com.biit.form.result.CategoryResult;
import com.biit.form.result.FormResult;
import com.biit.form.result.QuestionWithValueResult;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.biit.form.result.pdf.style.Theme;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class FormResultTableFactory extends BaseElement {
	private final static int CONTENT_WIDTH = 300;
	private final static int TITLE_HEIGHT = 80;

	public static PdfPTable getFormStructureTable(FormResult formResult) throws InvalidElementException {
		float[] widths = { 1f };
		PdfPTable table = new PdfPTable(widths);
		setTablePropierties(table);

		table.addCell(createElementPdfStructure(formResult));

		return table;
	}

	private static PdfPCell createElementPdfStructure(TreeObject element) throws InvalidElementException {
		float[] widths = { 1f };
		PdfPTable table = new PdfPTable(widths);
		setTablePropierties(table);

		table.addCell(createElementLine(element));

		for (TreeObject child : element.getAllNotHiddenChildren()) {
			table.addCell(createElementPdfStructure(child));
		}

		PdfPCell cell = new PdfPCell();
		setCellProperties(cell);

		cell.addElement(table);
		return cell;
	}

	private static PdfPCell createElementLine(TreeObject element) throws InvalidElementException {
		if (element instanceof FormResult) {
			return createLine((FormResult) element);
		} else if (element instanceof CategoryResult) {
			return createLine((CategoryResult) element);
		} else if (element instanceof QuestionWithValueResult) {
			return createLine((QuestionWithValueResult) element);
		} else {
			throw new InvalidElementException("Element '" + element + "' cannot be represented in the PDF. Not defined.");
		}
	}

	private static PdfPCell createLine(FormResult form) {
		return createElementLine(form, Theme.getFormFont(), Theme.FORM_FONT_SIZE, CONTENT_WIDTH);
	}

	private static PdfPCell createLine(CategoryResult category) {
		return createElementLine(category, Theme.getCategoryFont(), Theme.CATEGORY_FONT_SIZE, CONTENT_WIDTH);
	}

	private static PdfPCell createLine(QuestionWithValueResult question) {
		float[] widths = { 1f };
		PdfPTable table = new PdfPTable(widths);
		setTablePropierties(table);

		// Add questions
		table.addCell(createElementLine(question, Theme.getQuestionFont(), Theme.QUESTION_FONT_SIZE, CONTENT_WIDTH));

		// Add answers
		List<String> answers = new ArrayList<>(question.getAnswers());
		Collections.sort(answers);

		for (String answer : answers) {
			table.addCell(createElementLine(answer, Theme.getAnswerFont(), Theme.ANSWER_FONT_SIZE, CONTENT_WIDTH));
		}

		PdfPCell cell = new PdfPCell();
		setCellProperties(cell);

		cell.addElement(table);
		return cell;
	}

	protected static PdfPCell createElementLine(TreeObject element, BaseFont font, int fontSize, int maxColumnWidth) {
		return createElementLine(element.getLabel(), font, fontSize, maxColumnWidth);
	}

	protected static PdfPCell createElementLine(String text, BaseFont font, int fontSize, int maxColumnWidth) {
		PdfPCell cell = getCell(text, 0, 1, Element.ALIGN_LEFT, Color.WHITE, Theme.getFormFont(), fontSize);
		cell.setMinimumHeight(TITLE_HEIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}

}
