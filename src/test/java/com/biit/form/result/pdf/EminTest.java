package com.biit.form.result.pdf;

import com.biit.form.result.FormResult;
import com.biit.form.result.pdf.exceptions.EmptyPdfBodyException;
import com.biit.form.result.pdf.exceptions.InvalidElementException;
import com.lowagie.text.DocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Test(groups = {"eminForm"})
public class EminTest {
    protected static final String OUTPUT_FOLDER = System.getProperty("java.io.tmpdir") + File.separator + "PdfTests";
    private final static String FORM_AS_JSON = "Emin Formulario On-line.json";

    @BeforeClass
    public void prepareFolder() throws IOException {
        Files.createDirectories(Paths.get(OUTPUT_FOLDER));
    }

    protected boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    @Test
    public void createEminPdf() throws IOException, URISyntaxException, EmptyPdfBodyException, DocumentException, InvalidElementException {
        // Load form from json file in resources.
        String text = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(FORM_AS_JSON).toURI())));
        FormResult form = FormResult.fromJson(text);
        Assert.assertNotNull(form);

        // Convert to pdf.
        FormAsPdf pdfDocument = new FormAsPdf(form, "Jorge Hortelano");
        pdfDocument.createFile( OUTPUT_FOLDER + File.separator + "Emin.pdf");
    }

    @AfterClass
    public void removeFolder() {
        Assert.assertTrue(deleteDirectory(new File(OUTPUT_FOLDER)));
    }
}
