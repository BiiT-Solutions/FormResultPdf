package com.biit.form.result.pdf.file;

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

import com.biit.form.result.pdf.FormAsPdf;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Class with utility methods
 */
public final class FormGeneratorPdf {

    private FormGeneratorPdf() {

    }

    public static InputStream generatePdf(FormAsPdf generator) throws Exception {
        if (generator != null) {
            final byte[] data = generator.generate();
            // convert array of bytes into file
            return new ByteArrayInputStream(data);
        }
        return null;
    }

    public static void generatePdfAsFile(String filename, FormAsPdf generator) throws Exception {
        if (generator != null) {
            final byte[] data = generator.generate();
            // convert array of bytes into file
            try (FileOutputStream fileOuputStream = new FileOutputStream(filename)) {
                fileOuputStream.write(data);
            }
        }
    }
}
