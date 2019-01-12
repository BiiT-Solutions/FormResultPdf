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
	public final static String LINE_FONT_NAME = "DejaVuSansCondensed.ttf";
	public final static String LINE_FONT_ITALIC_NAME = "DejaVuSansCondensed-Oblique.ttf";
	public final static String TABLE_SUBTITLE_FONT_NAME = "DejaVuSansCondensed-Oblique.ttf";
	public final static String LINE_BOLD_FONT_NAME = "DejaVuSansCondensed-Bold.ttf";
	public final static String TITLE_FONT_NAME = "Roman Antique.ttf";
	public final static String HANDWRITTING_FONT_NAME = "ArchitectsDaughter.ttf";

	public final static int TITLE_FONT_SIZE = 18;

	public final static int DEFAULT_MARGIN = 3;

	private static BaseFont footerFont;
	private static BaseFont lineFont;
	private static BaseFont lineItalicFont;
	private static BaseFont lineBoldFont;
	private static BaseFont titleFont;
	private static BaseFont tableSubtitleFont;
	private static BaseFont handwrittingFont;

	public static BaseFont getFooterFont() {
		if (footerFont == null) {
			Font font = FontFactory.getFont("/" + TITLE_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.5f, Font.NORMAL, Color.BLACK);
			footerFont = font.getBaseFont();
		}
		return footerFont;
	}

	public static BaseFont getLineFont() {
		if (lineFont == null) {
			Font font = FontFactory.getFont("/" + LINE_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, Color.BLACK);
			lineFont = font.getBaseFont();
		}
		return lineFont;
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

	public static BaseFont getTitleFont() {
		if (titleFont == null) {
			Font font = FontFactory.getFont("/" + TITLE_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, Color.BLACK);
			titleFont = font.getBaseFont();
		}
		return titleFont;
	}

	public static BaseFont getSubtitleFont() {
		if (tableSubtitleFont == null) {
			Font font = FontFactory.getFont("/" + TABLE_SUBTITLE_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.ITALIC, Color.BLACK);
			tableSubtitleFont = font.getBaseFont();
		}
		return tableSubtitleFont;
	}

	public static BaseFont getHandwrittingFont() {
		if (handwrittingFont == null) {
			Font font = FontFactory.getFont("/" + HANDWRITTING_FONT_NAME, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, Color.BLACK);
			handwrittingFont = font.getBaseFont();
		}
		return handwrittingFont;
	}

	public static int getHandWrittingFontSize(int originalSize) {
		return originalSize - 1;
	}
}
