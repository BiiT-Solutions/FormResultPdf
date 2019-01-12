package com.biit.form.result.pdf;

import com.biit.form.result.FormResult;
import com.biit.form.result.pdf.components.FormResultTableFactory;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class FormAsPdf extends PdfDocument {
	private FormResult formResult;

	public FormAsPdf(FormResult formResult) {
		super();
		this.formResult = formResult;
	}

	@Override
	protected void addEvent(PdfWriter writer) {
		if (formResult != null) {
			FormPageEvent formPageEvent = new FormPageEvent();
			formPageEvent.setHeader(formResult.getLabel());
			writer.setPageEvent(formPageEvent);
		}
	}

	@Override
	protected Rectangle getPageSize() {
		return PageSize.A4;
	}

	@Override
	protected void createPagePDF(Document document) throws InvalidElementException, DocumentException {
		PdfPTable mainTable = FormResultTableFactory.createElementPdfStructure(formResult);
		document.add(mainTable);
		document.newPage();
	}

	@Override
	protected void addDocumentWriterEvents(PdfWriter writer) {
		// No background.
	}

}
