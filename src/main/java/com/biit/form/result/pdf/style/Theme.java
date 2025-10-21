package com.biit.form.result.pdf.style;

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

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;

import java.awt.Color;

public final class Theme {
    public static final String FORM_FONT_NAME = "DejaVuSansCondensed-Bold.ttf";
    public static final String CATEGORY_FONT_NAME = "DejaVuSans-Bold.ttf";
    public static final String GROUP_FONT_NAME = "DejaVuSansCondensed-Bold.ttf";
    public static final String QUESTION_FONT_NAME = "DejaVuSansCondensed.ttf";
    public static final String ANSWER_FONT_NAME = "DejaVuSans-ExtraLight.ttf";

    public static final float FORM_SIZE = 0.8f;
    public static final float FOOTER_SIZE = 0.5f;

    public static final int FORM_FONT_SIZE = 18;
    public static final int CATEGORY_FONT_SIZE = 14;
    public static final int GROUP_FONT_SIZE = 11;
    public static final int QUESTION_FONT_SIZE = 11;
    public static final int ANSWER_FONT_SIZE = 10;

    public static final int DEFAULT_MARGIN = 3;

    private static BaseFont footerFont;
    private static BaseFont formFont;
    private static BaseFont categoryFont;
    private static BaseFont groupFont;
    private static BaseFont questionFont;
    private static BaseFont answerFont;

    private Theme() {

    }

    public static BaseFont getFormFont() {
        if (formFont == null) {
            final Font font = FontFactory.getFont("/" + FORM_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FORM_SIZE, Font.NORMAL, Color.BLACK);
            formFont = font.getBaseFont();
        }
        return formFont;
    }

    public static BaseFont getCategoryFont() {
        if (categoryFont == null) {
            final Font font = FontFactory.getFont("/" + CATEGORY_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FORM_SIZE, Font.ITALIC, Color.BLACK);
            categoryFont = font.getBaseFont();
        }
        return categoryFont;
    }

    public static BaseFont getGroupFont() {
        if (groupFont == null) {
            final Font font = FontFactory.getFont("/" + GROUP_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FORM_SIZE, Font.ITALIC, Color.BLACK);
            groupFont = font.getBaseFont();
        }
        return groupFont;
    }

    public static BaseFont getQuestionFont() {
        if (questionFont == null) {
            final Font font = FontFactory.getFont("/" + QUESTION_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FORM_SIZE, Font.NORMAL, Color.BLACK);
            questionFont = font.getBaseFont();
        }
        return questionFont;
    }

    public static BaseFont getAnswerFont() {
        if (answerFont == null) {
            final Font font = FontFactory.getFont("/" + ANSWER_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FORM_SIZE, Font.NORMAL, Color.DARK_GRAY);
            answerFont = font.getBaseFont();
        }
        return answerFont;
    }

    public static BaseFont getFooterFont() {
        if (footerFont == null) {
            final Font font = FontFactory.getFont("/" + FORM_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FOOTER_SIZE, Font.NORMAL, Color.BLACK);
            footerFont = font.getBaseFont();
        }
        return footerFont;
    }

    public static int getHandWrittingFontSize(int originalSize) {
        return originalSize - 1;
    }
}
