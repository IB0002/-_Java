import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два числа");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])){
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
       else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])){
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
       else{
            throw new Exception("Числа должны быть в однои формате");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman ) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Roman.convertToArabian(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String experssion) {
        if (experssion.contains("+")) return "+";
        else if (experssion.contains("-")) return "-";
        else if (experssion.contains("*")) return "*";
        else if (experssion.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

   static class Roman {
       static String[] romanArray = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
               "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
               "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
               "XL", "XLI", "XLI", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L"};

       public static boolean isRoman(String val) {
           for (int i = 0; i < romanArray.length; i++) {
               if (val.equals(romanArray[i])) {
                   return true;
               }
           }
           return false;
       }

       public static int convertToArabian(String roman) {
           for (int i = 0; i < romanArray.length; i++) {
               if (roman.equals(romanArray[i])) {
                   return i;
               }
           }
           return -1;
       }
       public static String convertToRoman(int arabian) {
           return romanArray[arabian];}
   }

}
