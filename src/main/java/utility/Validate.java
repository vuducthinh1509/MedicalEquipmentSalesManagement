package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    public static boolean validateNumber(String number){
        // Regex to check valid password.
        String regex = "^-?\\d+\\.?\\d*$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (number == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(number);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }
    public static boolean validatePassword(String password){
        // Regex to check valid password.
        String regex = "^(?=\\S+$).{3,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    public static boolean validatePhoneVN(String phone){
        // Regex to check valid password.
        String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (phone == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(phone);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }
}
