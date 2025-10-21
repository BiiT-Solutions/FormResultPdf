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

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * iText page event class. This formats the page to have a header and a footer
 * with page count and titles.
 */
public class FormPageEvent extends PdfPageEventHelper {

    private static final int TEMPLATE_WIDTH = 30;
    private static final int TEMPLATE_HEIGHT = 16;
    private static final int COLUMN_WIDTH = 24;
    private static final int TOTAL_WIDTH = 527;
    private static final int FIXED_EIGHT = 20;
    private static final int XPOS = 34;

    private String footer;
    private PdfTemplate total;

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(TEMPLATE_WIDTH, TEMPLATE_HEIGHT);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        final PdfPTable table = new PdfPTable(3);

        try {
            table.setWidths(new int[]{COLUMN_WIDTH, COLUMN_WIDTH, 2});
            table.setTotalWidth(TOTAL_WIDTH);
            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(FIXED_EIGHT);
            table.getDefaultCell().setBorder(Rectangle.TOP);
            table.addCell(footer);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(String.format("Page %d of", writer.getPageNumber()));
            final PdfPCell cell = new PdfPCell(Image.getInstance(total));
            cell.setBorder(Rectangle.TOP);
            table.addCell(cell);
            table.writeSelectedRows(0, -1, XPOS, document.bottom(), writer.getDirectContent());
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
    }

}
