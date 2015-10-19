package main.java.net.therap.facebook.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: rafsan.jani
 * since: 13/10/15.
 */

public class EmailValidator {

    private static EmailValidator emailValidator = new EmailValidator();
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;
    private Matcher matcher;

    private EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public static EmailValidator getInstance() {
        return emailValidator;
    }

    public boolean validate(final String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
