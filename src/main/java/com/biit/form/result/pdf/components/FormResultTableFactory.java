package com.biit.form.result.pdf.components;

import java.awt.Color;

import com.biit.form.entity.TreeObject;
import com.biit.form.result.FormResult;
import com.biit.form.result.pdf.style.Theme;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class FormResultTableFactory extends BaseElement {
	private final static int TITLE_HEIGHT = 80;

	public static PdfPTable getFormStructureTable(FormResult formResult) {
		float[] widths = { 1f, 1f, 1f };
		PdfPTable table = new PdfPTable(widths);
		setTablePropierties(table);

		createFormTitleTable(formResult);

		for (TreeObject element : formResult.getAllNotHiddenChildren()) {

		}

		return table;
	}

	private static PdfCell createFormTitleTable(TreeObject element) {
		float[] widths = { 1f };
		PdfPTable table = new PdfPTable(widths);
		setTablePropierties(table);

		table.addCell(createElementLine(element, Theme.TITLE_FONT_SIZE, 100));
	}

	protected static PdfPCell createElementLine(TreeObject element, int fontSize, int maxColumnWidth) {
		PdfPCell cell = getCell(element.getLabel(), 0, 1, Element.ALIGN_LEFT, Color.WHITE, Theme.getTitleFont(), fontSize);
		cell.setMinimumHeight(TITLE_HEIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}

}
