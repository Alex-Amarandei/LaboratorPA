package com;

import java.util.Arrays;
import java.util.Locale;

public class DisplayLocales {
    public void execute() {
        System.out.println("Default locale: " + Locale.getDefault());
        System.out.println("Available locales:");
        Arrays.stream(Locale.getAvailableLocales())
                .forEach(locale -> System.out.println(locale.getDisplayCountry() + "\t" + locale.getDisplayLanguage(locale)));
    }
}
