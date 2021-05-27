package com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Info {
    public void info(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCountry(locale)).append("\n");
        stringBuilder.append(getLanguage(locale)).append("\n");
        stringBuilder.append(getCurrency(locale)).append("\n");
        stringBuilder.append(getWeekDays(locale)).append("\n");
        stringBuilder.append(getMonths(locale)).append("\n");
        stringBuilder.append(getToday(locale)).append("\n");
        System.out.println(stringBuilder.toString());
    }

    private String getCountry(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Country: ");
        stringBuilder.append(locale.getDisplayCountry(Locale.US));
        stringBuilder.append(" (");
        stringBuilder.append(locale.getDisplayCountry(locale));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private String getLanguage(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Language: ");
        stringBuilder.append(locale.getDisplayLanguage(Locale.US));
        stringBuilder.append(" (");
        stringBuilder.append(locale.getDisplayLanguage(locale));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private String getCurrency(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        Currency currency = Currency.getInstance(locale);
        stringBuilder.append("Currency: ");
        stringBuilder.append(currency.getCurrencyCode());
        stringBuilder.append(" (");
        stringBuilder.append(currency.getDisplayName());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private String getWeekDays(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] weekDays = DateFormatSymbols.getInstance(locale).getWeekdays();

        stringBuilder.append("Week Days: ");
        stringBuilder.append(weekDays[1]);
        for (int i = 2; i < weekDays.length; i++)
            stringBuilder.append(", ").append(weekDays[i]);
        return stringBuilder.toString();
    }

    private String getMonths(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] months = DateFormatSymbols.getInstance(locale).getMonths();

        stringBuilder.append("Months: ");
        stringBuilder.append(months[0]);
        for (int i = 1; i < months.length - 1; i++)
            stringBuilder.append(", ").append(months[i]);
        return stringBuilder.toString();
    }

    private String getToday(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Today: ");
        stringBuilder.append(DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(new Date()));
        stringBuilder.append(" (");
        stringBuilder.append(DateFormat.getDateInstance(DateFormat.LONG, locale).format(new Date()));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}