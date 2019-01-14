package com.biit.form.result.pdf;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.biit.form.result.FormResult;
import com.biit.form.result.pdf.exceptions.EmptyPdfBodyException;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.lowagie.text.DocumentException;

@Test(groups = { "eminForm" })
public class EminTest {
	private final static String FORM_AS_JSON = "EminForm.json";

	@Test
	public void createEminPdf() throws IOException, URISyntaxException, EmptyPdfBodyException, DocumentException, InvalidElementException {
		// Load form from json file in resources.
		String text = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(FORM_AS_JSON).toURI())));
		FormResult form = FormResult.fromJson(text);
		Assert.assertNotNull(form);

		// Convert to pdf.
		FormAsPdf pdfDocument = new FormAsPdf(form);
		pdfDocument.createFile(System.getProperty("java.io.tmpdir") + File.separator + "Emin.pdf");
	}
}
