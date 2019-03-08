package com.example.paresh.test.main.activity.re.recycler_test.util_Validation;

public class validation {
    public static boolean isValidname(String name) {
        if (name.isEmpty() && name.length() >= 3) {
            return true;
        }
        return false;
    }

    public static boolean isvalidaddress(String address) {
        if (address.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email) {

//        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            return true;
        }
        return false;
    }

}
