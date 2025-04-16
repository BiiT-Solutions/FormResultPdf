package com.biit.form.result.pdf.components;

/*-
 * #%L
 * Think Machine (Core)
 * %%
 * Copyright (C) 2017 Softwaremagico
 * %%
 * This software is designed by Jorge Hortelano Otero. Jorge Hortelano Otero
 * <softwaremagico@gmail.com> Valencia (Spain).
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; If not, see <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.Color;
import java.io.IOException;

public class BaseElement {

    public static final int MAX_WIDTH = 100;
    public static final int SEPARATION = 6;

    public static PdfPCell getCell(String text, int border, int colspan, int align, Color color, BaseFont font, float fontSize) {
        if (text == null) {
            text = "";
        }
        final Phrase content = new Phrase(text, new Font(font, fontSize));
        final PdfPCell cell = new PdfPCell(content);
        cell.setColspan(colspan);
        cell.setBorderWidth(border);
        cell.setHorizontalAlignment(align);
        cell.setBackgroundColor(color);

        return cell;
    }

    public static PdfPCell getCell(Paragraph paragraph, int border, int colspan, int align, Color color) {
        final PdfPCell cell = new PdfPCell(paragraph);
        cell.setColspan(colspan);
        cell.setBorderWidth(border);
        cell.setHorizontalAlignment(align);
        cell.setBackgroundColor(color);

        return cell;
    }

    protected PdfPCell getCell(String text, int border, int colspan, int align, Color color, String font, int fontSize, int fontType) {
        if (text == null) {
            text = "";
        }
        final Paragraph p = new Paragraph(text, FontFactory.getFont(font, fontSize, fontType));
        final PdfPCell cell = new PdfPCell(p);
        cell.setColspan(colspan);
        cell.setBorderWidth(border);
        cell.setHorizontalAlignment(align);
        cell.setBackgroundColor(color);

        return cell;
    }

    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
        final Image img = Image.getInstance(path);
        final PdfPCell cell = new PdfPCell(img, true);
        setCellProperties(cell);
        return cell;
    }

    public static void setCellProperties(PdfPCell cell) {
        cell.setBorder(0);
        cell.setPadding(0);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    }

    public static void setTablePropierties(PdfPTable table) {
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        setCellProperties(table.getDefaultCell());
        table.setWidthPercentage(MAX_WIDTH);
        table.setSpacingBefore(0);
    }

    public static PdfPCell createWhiteSeparator(int height) {
        final PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        setCellProperties(cell);
        cell.setMinimumHeight(height);
        return cell;
    }

    public static PdfPCell createWhiteSeparator() {
        return createWhiteSeparator(SEPARATION);
    }
}
