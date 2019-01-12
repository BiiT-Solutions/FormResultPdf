package com.biit.form.result.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.biit.form.result.pdf.exceptions.EmptyPdfBodyException;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public abstract class PdfDocument {
	private final static String DEFAULT_DOCUMENT_NAME = "Form document PDF";
	private final static String DEFAULT_DOCUMENT_SUBJECT = "Form document PDF";
	private final static String DOCUMENT_AUTHOR = "BiiT Sourcing Solutions S.L.";
	private final static String DOCUMENT_CREATOR = "BiiT Sourcing Solutions S.L.";

	private final static int MARGIN_RIGHT = 30;
	private final static int MARGIN_LEFT = 30;
	private final static int MARGIN_TOP = 30;
	private final static int MARGIN_BOTTON = 60;

	private PdfWriter writer;

	protected Document addMetaData(Document document) {
		document.addTitle(DEFAULT_DOCUMENT_NAME);
		document.addAuthor(DOCUMENT_AUTHOR);
		document.addCreator(DOCUMENT_CREATOR);
		document.addSubject(DEFAULT_DOCUMENT_SUBJECT);
		document.addCreationDate();
		return document;
	}

	protected void generatePDF(Document document, PdfWriter writer) throws EmptyPdfBodyException, InvalidElementException, DocumentException {
		addMetaData(document);
		document.open();
		createPagePDF(document);
		document.close();
	}

	protected void addEvent(PdfWriter writer) {
		writer.setPageEvent(new FormPageEvent());
	}

	protected Document generateDocument() {
		return new Document(getPageSize(), MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTON);
	}

	protected abstract void addDocumentWriterEvents(PdfWriter writer);

	public final byte[] generate() throws EmptyPdfBodyException, DocumentException, InvalidElementException {
		Document document = generateDocument();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		// TableFooter event = new TableFooter();
		// writer.setPageEvent(event);
		addEvent(writer);
		generatePDF(document, writer);
		return baos.toByteArray();
	}

	public void createFile(String path) throws IOException, EmptyPdfBodyException, DocumentException, InvalidElementException {
		if (!path.endsWith(".pdf")) {
			path += ".pdf";
		}

		try (FileOutputStream fos = new FileOutputStream(path)) {
			fos.write(generate());
		}
	}

	public PdfWriter getWriter() {
		return writer;
	}

	protected abstract Rectangle getPageSize();

	protected abstract void createPagePDF(Document document) throws InvalidElementException, DocumentException;
}
