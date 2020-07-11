package project1;

public class ALU_Branch {

    public static String SHIFT = "";
    public static int add;

    public static void branch() {
        SHIFT = "0";
        PC.add_address();
        String string = Sign_Extend.s;
        add = (int) ALU.convert(string);
        add = (add) * 4;

        //Shift_left value
        SHIFT = string.substring(2, 32) + "00";

    }

}
