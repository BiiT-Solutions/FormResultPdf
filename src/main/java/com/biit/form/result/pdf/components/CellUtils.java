package com.biit.form.result.pdf.components;

/*-
 * #%L
 * Think Machine (Core)
 * %%
 * Copyright (C) 2017 Softwaremagico
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

import com.lowagie.text.pdf.BaseFont;

public final class CellUtils {

    private CellUtils() {

    }

    public static String getSubStringFitsIn(String originalText, BaseFont font, int fontSize, float width) {
        String text = originalText;

        while (!fitsIn(text, font, fontSize, width) && !text.isEmpty()) {
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    public static boolean fitsIn(String text, BaseFont font, int fontSize, float width) {
        return font.getWidthPoint(text, fontSize) < width;
    }
}
