package by.tms.insta.validation;

import by.tms.insta.exceptions.RequestException;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {

    public static final String PASSWORD_REX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    // min 8 char : 1 uppercase + 1 lowercase + 1 number + 1 special character
    public static final String EMAIL_REX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";


    public static String isNotEmptyField(String string) {

        if (string.isEmpty()) {
            throw new RequestException("must be completed", 400);
        } else
            return string;

    }

    public static String passwordValidation(String password) {

        String notEmptyPassword = isNotEmptyField(password);
        Pattern pattern = Pattern.compile(PASSWORD_REX);
        Matcher matcher = pattern.matcher(notEmptyPassword);

        if (!matcher.matches()) {
            throw new RequestException("password is not valid", 400);
        } else
            return password;
    }

    public static String emailValidation(String email) {

        String notEmptyEmail = isNotEmptyField(email);
        Pattern pattern = Pattern.compile(EMAIL_REX);
        Matcher matcher = pattern.matcher(notEmptyEmail);

        if (!matcher.matches()) {
            throw new RequestException("email is not valid", 400);
        } else
            return email;
    }

}
