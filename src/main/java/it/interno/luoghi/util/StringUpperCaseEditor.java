package it.interno.luoghi.util;

import java.beans.PropertyEditorSupport;

public class StringUpperCaseEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        return getValue().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (text == null)
            throw new IllegalArgumentException("Value cannot be null");

        this.setValue(text.toUpperCase());
    }
}
