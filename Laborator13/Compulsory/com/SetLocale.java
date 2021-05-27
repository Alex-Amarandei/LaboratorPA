package com;

import java.util.Locale;

public class SetLocale {
    public void execute(Locale locale) {
        Locale.setDefault(locale);
    }
}
