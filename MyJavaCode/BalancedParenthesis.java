// Java program to count swaps required to balance string

public class BalancedParenthesis {
    // Function to calculate swaps required
    public static int swapCount(String s) {
        String strWithoutBalancedParenthesis = replaceBalancedParenthesis(s);
        System.out.println(strWithoutBalancedParenthesis);
        int openBraces = 0;
        int closedBraces = 0;
        int result = -1;
        int len = strWithoutBalancedParenthesis.length();
        for (int i = 0; i < len; i++) {
            if (strWithoutBalancedParenthesis.charAt(i) == '(')
                openBraces++;
            else if (strWithoutBalancedParenthesis.charAt(i) == ')')
                closedBraces++;
        }
        if (openBraces == closedBraces) {
            if (openBraces % 2 == 1)
                result = openBraces / 2 + 1;
            else
                result = openBraces / 2;
        }

        return result;
    }

    private static String replaceBalancedParenthesis(String str) {
        while (str.contains("()")) {
            str = str.replace("()", "");
        }
        return str;
    }

    // Driver code
    public static void main(String[] args) {
        String s = ")))))(((((";
        int finalResult = swapCount(s);
        System.out.println("The minimm number of swap required is: " + finalResult);
    }
}
