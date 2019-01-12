package com.biit.form.result.pdf.style;

import java.awt.Color;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;

public class Theme {
	public final static String LOGO_IMAGE = "fading-suns.png";
	public final static String RIGHT_CORNER_IMAGE = "rightCorner.png";
	public final static String LEFT_CORNER_IMAGE = "leftCorner.png";
	public final static String MAIN_TITLE_IMAGE = "pageTitle.png";
	public final static String QUESTION_FONT_NAME = "DejaVuSansCondensed.ttf";
	public final static String LINE_FONT_ITALIC_NAME = "DejaVuSansCondensed-Oblique.ttf";
	public final static String CATEGORY_FONT_NAME = "DejaVuSansCondensed-Oblique.ttf";
	public final static String GROUP_FONT_NAME = "DejaVuSansCondensed-Oblique.ttf";
	public final static String LINE_BOLD_FONT_NAME = "DejaVuSansCondensed-Bold.ttf";
	public final static String FORM_FONT_NAME = "Roman Antique.ttf";
	public final static String ANSWER_FONT_NAME = "ArchitectsDaughter.ttf";

	public final static int FORM_FONT_SIZE = 18;
	public final static int CATEGORY_FONT_SIZE = 16;
	public final static int GROUP_FONT_SIZE = 14;
	public final static int QUESTION_FONT_SIZE = 12;
	public final static int ANSWER_FONT_SIZE = 10;

	public final static int DEFAULT_MARGIN = 3;

	private static BaseFont footerFont;
	private static BaseFont lineItalicFont;
	private static BaseFont lineBoldFont;
	private static BaseFont formFont;
	private static BaseFont categoryFont;
	private static BaseFont groupFont;
	private static BaseFont questionFont;
	private static BaseFont answerFont;

	public static BaseFont getFormFont() {
		if (formFont == null) {
			Font font = FontFactory.getFont("/" + FORM_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, Color.BLACK);
			formFont = font.getBaseFont();
		}
		return formFont;
	}

	public static BaseFont getCategoryFont() {
		if (categoryFont == null) {
			Font font = FontFactory.getFont("/" + CATEGORY_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.ITALIC, Color.BLACK);
			categoryFont = font.getBaseFont();
		}
		return categoryFont;
	}

	public static BaseFont getGroupFont() {
		if (groupFont == null) {
			Font font = FontFactory.getFont("/" + GROUP_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.ITALIC, Color.BLACK);
			groupFont = font.getBaseFont();
		}
		return groupFont;
	}

	public static BaseFont getQuestionFont() {
		if (questionFont == null) {
			Font font = FontFactory.getFont("/" + QUESTION_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, Color.BLACK);
			questionFont = font.getBaseFont();
		}
		return questionFont;
	}

	public static BaseFont getAnswerFont() {
		if (answerFont == null) {
			Font font = FontFactory.getFont("/" + ANSWER_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, Color.BLACK);
			answerFont = font.getBaseFont();
		}
		return answerFont;
	}

	public static BaseFont getLineItalicFont() {
		if (lineItalicFont == null) {
			Font font = FontFactory.getFont("/" + LINE_FONT_ITALIC_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.ITALIC, Color.BLACK);
			lineItalicFont = font.getBaseFont();
		}
		return lineItalicFont;
	}

	public static BaseFont getLineFontBold() {
		if (lineBoldFont == null) {
			Font font = FontFactory.getFont("/" + LINE_BOLD_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.BOLD, Color.BLACK);
			lineBoldFont = font.getBaseFont();
		}
		return lineBoldFont;
	}

	public static BaseFont getFooterFont() {
		if (footerFont == null) {
			Font font = FontFactory.getFont("/" + FORM_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.5f, Font.NORMAL, Color.BLACK);
			footerFont = font.getBaseFont();
		}
		return footerFont;
	}

	public static int getHandWrittingFontSize(int originalSize) {
		return originalSize - 1;
	}
}
