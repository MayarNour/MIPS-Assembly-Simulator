package project1;

public class Instruction {

    private static int x;
    public static String op_code = "", rs = "", rd = "", rt = "", shamt = "", constant = "", fun_code = "", jump = "",Finstruct="";

    //Returning codes of instruciton 
    public static void codes(String instruct) {

        op_code = instruct.substring(0, 6);
        rs = instruct.substring(6, 11);
        rt = instruct.substring(11, 16);
        rd = instruct.substring(16, 21);
        shamt = instruct.substring(21, 26);
        fun_code = instruct.substring(26, 32);
        constant = instruct.substring(16, 32);
        jump = instruct.substring(6, 32);
        Finstruct = instruct.substring(0, 32);
        
    }
}

