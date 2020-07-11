package project1;

public class Sign_Extend {

    public static String s = "";

    public static void SE() {
        String str = Instruction.constant;

        if (str.substring(0, 1).equalsIgnoreCase("0")) {
            s = "0000000000000000" + str;

        }

        if (str.substring(0, 1).equalsIgnoreCase("1")) {
            s = "1111111111111111" + str;
        }

    }
}
