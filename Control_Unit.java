package project1;

public class Control_Unit {

    public static String Regdst = "";
    public static String Branch = "";
    public static String Memread = "";
    public static String Memtoreg = "";
    public static String ALUOp = "";
    public static String MemWrite = "";
    public static String ALUSrc = "";
    public static String RegWrite = "";
    public static String Jump = "";

    public static void ControlUnit() {
        String opcode = Instruction.op_code;

        //R_type(add,nor,sll,slt,jr);
        if (opcode.equalsIgnoreCase("000000")) {
            Regdst = "1";
            Branch = "0";
            Memread = "0";
            Memtoreg = "0";
            ALUOp = "10";
            MemWrite = "0";
            ALUSrc = "0";
            RegWrite = "1";
            Jump = "0";
        }

        //LW or lb or lbu
        if (opcode.equalsIgnoreCase("100011") || opcode.equalsIgnoreCase("100000") || opcode.equalsIgnoreCase("100100")) {
            Regdst = "0";
            Branch = "0";
            Memread = "1";
            Memtoreg = "1";
            ALUOp = "00";
            MemWrite = "0";
            ALUSrc = "1";
            RegWrite = "1";
            Jump = "0";
        }

        //sw or sb
        if (opcode.equalsIgnoreCase("101011") || opcode.equalsIgnoreCase("101000")) {
            Regdst = "x";
            Branch = "0";
            Memread = "0";
            Memtoreg = "x";
            ALUOp = "00";
            MemWrite = "1";
            ALUSrc = "1";
            RegWrite = "0";
            Jump = "0";
        }

        //beq
        if (opcode.equalsIgnoreCase("000100")) {
            Regdst = "x";
            Branch = "1";
            Memread = "0";
            Memtoreg = "x";
            ALUOp = "01";
            MemWrite = "0";
            ALUSrc = "0";
            RegWrite = "0";
            Jump = "0";
        }

        //addi
        if (opcode.equalsIgnoreCase("001000")) {
            Regdst = "0";
            Branch = "0";
            Memread = "0";
            Memtoreg = "0";
            ALUOp = "00";
            MemWrite = "0";
            ALUSrc = "1";
            RegWrite = "1";
            Jump = "0";
        }

        //slti
        if (opcode.equalsIgnoreCase("001010")) {
            Regdst = "0";
            Branch = "0";
            Memread = "0";
            Memtoreg = "0";
            ALUOp = "11";
            MemWrite = "0";
            ALUSrc = "1";
            RegWrite = "1";
            Jump = "0";
        }

        //Jump
        if (opcode.equalsIgnoreCase("000010")) {

            Regdst = "x";
            Branch = "0";
            Memread = "0";
            Memtoreg = "x";
            ALUOp = "xx";
            MemWrite = "0";
            ALUSrc = "x";
            RegWrite = "0";
            Jump = "1";

        }

        //JAL        
        if (opcode.equalsIgnoreCase("000011")) {
            Regdst = "x";
            Branch = "0";
            Memread = "0";
            Memtoreg = "x";
            ALUOp = "xx";
            MemWrite = "0";
            ALUSrc = "x";
            RegWrite = "1";
            Jump = "1";
        }
    }
}
