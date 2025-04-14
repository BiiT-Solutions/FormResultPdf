package com.biit.form.result.pdf;

import com.biit.form.result.FormResult;
import com.biit.form.result.pdf.exceptions.EmptyPdfBodyException;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.lowagie.text.DocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Test(groups = { "answerWithTranslations" })
public class AnswerWithTranslationTests {
	private static final String FORM_AS_JSON = "answersWithTranslation.json";

	@Test
	public void createPdf() throws IOException, URISyntaxException, EmptyPdfBodyException, DocumentException, InvalidElementException {
		// Load form from json file in resources.
		String text = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(FORM_AS_JSON).toURI())));
		FormResult form = FormResult.fromJson(text);
		Assert.assertNotNull(form);

		// Convert to pdf.
		FormAsPdf pdfDocument = new FormAsPdf(form, "Jorge Hortelano");
		pdfDocument.createFile(System.getProperty("java.io.tmpdir") + File.separator + "AnswerWithTranslation.pdf");
	}
}
