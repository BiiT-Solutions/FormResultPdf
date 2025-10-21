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

import com.biit.form.result.FormResult;
import com.biit.form.result.pdf.components.FormResultTableFactory;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.util.Locale;

public class FormAsPdf extends PdfDocument {
    private final FormResult formResult;
    private final String footer;
    private final Locale locale;
    private final boolean showTechnicalName;
    private final boolean disableTranslations;

    public FormAsPdf(FormResult formResult, String footer) {
        this(formResult, footer, true, false);
    }

    public FormAsPdf(FormResult formResult, String footer, boolean showTechnicalName, boolean disableTranslations) {
        super();
        this.formResult = formResult;
        this.footer = footer;
        this.locale = null;
        this.showTechnicalName = showTechnicalName;
        this.disableTranslations = disableTranslations;
    }

    public FormAsPdf(FormResult formResult, String footer, Locale locale) {
        this(formResult, footer, locale, true, false);
    }

    public FormAsPdf(FormResult formResult, String footer, Locale locale, boolean showTechnicalName, boolean disableTranslations) {
        super();
        this.formResult = formResult;
        this.footer = footer;
        this.locale = locale;
        this.showTechnicalName = showTechnicalName;
        this.disableTranslations = disableTranslations;
    }

    @Override
    protected void addEvent(PdfWriter writer) {
        if (formResult != null) {
            final FormPageEvent formPageEvent = new FormPageEvent();
            if (footer != null) {
                formPageEvent.setFooter(footer);
            } else {
                formPageEvent.setFooter(formResult.getLabel());
            }
            writer.setPageEvent(formPageEvent);
        }
    }

    @Override
    protected Rectangle getPageSize() {
        return PageSize.A4;
    }


    @Override
    protected void createPagePDF(Document document) throws InvalidElementException, DocumentException {
        final PdfPTable mainTable = FormResultTableFactory.createElementPdfStructure(formResult, locale, showTechnicalName, disableTranslations);
        document.add(mainTable);
        document.newPage();
    }

    @Override
    protected void addDocumentWriterEvents(PdfWriter writer) {
        // No background.
    }

}
