package com.biit.form.result.pdf.file;

import com.biit.form.result.pdf.FormAsPdf;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Class with utility methods
 */
public class FormGeneratorPdf {

    public static InputStream generatePdf(FormAsPdf generator) throws Exception {
        if (generator != null) {
            byte[] data = generator.generate();
            // convert array of bytes into file
            return new ByteArrayInputStream(data);
        }
        return null;
    }

    public static void generatePdfAsFile(String filename, FormAsPdf generator) throws Exception {
        if (generator != null) {
            byte[] data = generator.generate();
            // convert array of bytes into file
            try (FileOutputStream fileOuputStream = new FileOutputStream(filename)) {
                fileOuputStream.write(data);
            }
        }
    }
}
