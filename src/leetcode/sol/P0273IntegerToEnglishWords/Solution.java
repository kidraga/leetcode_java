package leetcode.sol.P0273IntegerToEnglishWords;

public class Solution {
    private int BILLION = 1000000000;
    private int MILLION = 1000000;
    private int THOUSAND = 1000;
    private int HUNDRED = 100;
    private int TEN = 10;

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        int billionDigits = num / BILLION; // billion以上的位数
        int millionDigits = (num - billionDigits * BILLION) / MILLION;
        int thousandDigits = (num - billionDigits * BILLION - millionDigits * MILLION) / THOUSAND;
        int restDigits = num - billionDigits * BILLION - millionDigits * MILLION
                - thousandDigits * THOUSAND; // 剩余的位数

        StringBuilder sb = new StringBuilder();
        if (billionDigits != 0) {
            sb.append(processThreeDigits(billionDigits));
            sb.append(" Billion");
        }
        if (millionDigits != 0) {
            if (sb.length() != 0) {
                // 前面已经有数了，需要加空格
                sb.append(" ");
            }
            sb.append(processThreeDigits(millionDigits));
            sb.append(" Million");
        }
        if (thousandDigits != 0) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(processThreeDigits(thousandDigits));
            sb.append(" Thousand");
        }
        if (restDigits != 0) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            sb.append(processThreeDigits(restDigits));
        }
        return sb.toString();
    }

    private String processThreeDigits(int num) {
        int hundredDigit = num / HUNDRED;
        int rest = num - hundredDigit * HUNDRED;
        StringBuilder sb = new StringBuilder();
        if (hundredDigit != 0 && rest != 0) {
            sb.append(toOneDigitString(hundredDigit));
            sb.append(" Hundred ");
            sb.append(processTwoDigits(rest));
        } else if (hundredDigit == 0 && rest != 0) {
            sb.append(processTwoDigits(rest));
        } else if (hundredDigit != 0 && rest == 0) {
            sb.append(toOneDigitString(hundredDigit));
            sb.append(" Hundred");
        }
        return sb.toString();
    }

    private String processTwoDigits(int num) {
        if (num == 0) return "";
        else if (num < 10) return toOneDigitString(num);
        else if (num < 20) return toTeensString(num);
        else {
            int tenthDigit = num / TEN;
            int rest = num - tenthDigit * TEN;
            if (rest != 0) {
                return toTenthString(tenthDigit) + " " + toOneDigitString(rest);
            } else {
                return toTenthString(tenthDigit);
            }
        }
    }

    private String toOneDigitString(int num) {
        switch (num) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
        }
        return "";
    }

    private String toTeensString(int num) {
        switch (num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
        }
        return "";
    }

    private String toTenthString(int num) {
        switch (num) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
        }
        return "";
    }
}

