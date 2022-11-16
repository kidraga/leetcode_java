package leetcode.sol.P0468ValidateIPAddress;

public class Solution {
    private static String NEITHER = "Neither";
    private static String IPV4 = "IPv4";
    private static String IPV6 = "IPv6";

    public String validIPAddress(String queryIP) {
        if (queryIP == null) return NEITHER;
        if (isIPv4(queryIP)) {
            return IPV4;
        } else if (isIPv6(queryIP)) {
            return IPV6;
        } else {
            return NEITHER;
        }
    }

    private boolean isIPv4(String queryIP) {
        String[] ipv4 = queryIP.split("\\.", -1);
        if (ipv4.length != 4) return false;
        for (String num : ipv4) {
            // max 3 digits, but not blank
            if (num.length() == 0 || num.length() > 3) return false;
            // cannot start with 0
            if (num.charAt(0) == '0' && num.length() != 1) return false;
            // all digits, not alphabet or special characters
            for (char c : num.toCharArray()) {
                if (!Character.isDigit(c)) return false;
            }
            // cannot larger than 255
            if (Integer.parseInt(num) > 255) return false;
        }
        return true;
    }

    private boolean isIPv6(String queryIP) {
        String[] ipv6 = queryIP.split("\\:", -1);
        if (ipv6.length != 8) return false;
        String hexDigits = "0123456789abcdefABCDEF";
        for (String num : ipv6) {
            // max 4 digits
            if (num.length() == 0 || num.length() > 4) return false;
            for (char c : num.toCharArray()) {
                if (hexDigits.indexOf(c) == -1) return false;
            }
        }
        return true;
    }
}
