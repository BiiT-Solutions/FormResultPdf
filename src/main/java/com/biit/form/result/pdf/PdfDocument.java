package com.biit.form.result.pdf;

/*-
 * #%L
 * Form Result PDF Conversor
 * %%
 * Copyright (C) 2022 - 2025 BiiT Sourcing Solutions S.L.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import com.biit.form.result.pdf.exceptions.EmptyPdfBodyException;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class PdfDocument {
    private static final String DEFAULT_DOCUMENT_NAME = "Form document PDF";
    private static final String DEFAULT_DOCUMENT_SUBJECT = "Form document PDF";
    private static final String DOCUMENT_AUTHOR = "BiiT Sourcing Solutions S.L.";
    private static final String DOCUMENT_CREATOR = "BiiT Sourcing Solutions S.L.";

    private static final int MARGIN_RIGHT = 30;
    private static final int MARGIN_LEFT = 30;
    private static final int MARGIN_TOP = 30;
    private static final int MARGIN_BOTTON = 60;

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
        final Document document = generateDocument();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final PdfWriter writer = PdfWriter.getInstance(document, baos);
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
