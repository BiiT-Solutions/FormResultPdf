package com.biit.form.result.pdf.exceptions;

public class InvalidElementException extends Exception {
    private static final long serialVersionUID = -4027664690256794077L;

    public InvalidElementException(String text) {
        super(text);
    }
}
