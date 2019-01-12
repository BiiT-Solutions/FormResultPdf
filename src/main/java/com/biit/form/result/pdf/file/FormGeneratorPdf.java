package com.biit.form.result.pdf.file;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.biit.form.result.pdf.FormAsPdf;

/**
 * Class with utility methods
 */
public class FormGeneratorPdf {

	public static InputStream generatePdf(FormAsPdf generator) throws Exception {
		if (generator != null) {
			byte[] data = generator.generate();
			// convert array of bytes into file
			InputStream inputStream = new ByteArrayInputStream(data);
			return inputStream;
		}
		return null;
	}

	public static void generatePdfAsFile(String filename, FormAsPdf generator) throws Exception {
		if (generator != null) {
			byte[] data = generator.generate();
			// convert array of bytes into file
			FileOutputStream fileOuputStream = new FileOutputStream(filename);
			fileOuputStream.write(data);
			fileOuputStream.close();
		}
	}
}
