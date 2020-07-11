package project1;

public class ALU_Control {

    public static String function = "";
    public static String a;
    public static String f;

    public static void ALU_C() {
        //function code
        f = Instruction.fun_code;
        //OPCode
        a = Control_Unit.ALUOp;

        //R_type
        if (a.equals("10")) {
            if (f.equalsIgnoreCase("100000"))//add
            {
                function = "0010";

            }

            if (f.equalsIgnoreCase("101010"))//SLT
            {
                function = "0111";
            }

            if (f.equalsIgnoreCase("100111"))//nor
            {
                function = "0011";
            }

            if (f.equalsIgnoreCase("000000"))//sll
            {
                function = "0101";
            }
        }

        if (a.equalsIgnoreCase("00")) {

            //addi
            if (Control_Unit.MemWrite.equalsIgnoreCase("0") && Control_Unit.Memread.equalsIgnoreCase("0")) {
                function = "0010";
            }

            //lw,sw,lb,lbu,sb
            function = "0010";

        }

        //slti
        if (a.equalsIgnoreCase("11")) {
            {
                function = "0111";
            }
        }
        
        //beq  
        if (a.equalsIgnoreCase("01")) {
            function = "0110";
        }

        //Jump and JAL
        if (a.equalsIgnoreCase("xx")) {
            function = "0000";
        }

    }
}

