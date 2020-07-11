package project1;

public class Mux_branch {

    public static int ANDgate = 0;

    public static void mux_value() {

        Project1.real_offset = PC.address;
        if ((ALU.zeroflag == 0 || Control_Unit.Branch.equalsIgnoreCase("0")) && Control_Unit.Jump.equals("0")) {
            ANDgate = 0;
            if (Instruction.fun_code.equalsIgnoreCase("001000")) {
                Muxes.MuxOut = "0";
                PC.address = (int) ALU.RS;
                ALU_Branch.branch();
                
            } 
            else {

                ALU_Branch.branch();
            }

        } else {
            if (ALU.zeroflag == 1 && Control_Unit.Branch.equals("1") && Control_Unit.Jump.equals("0")) {
                ANDgate = 1;
                ALU_Branch.branch();
                PC.address = PC.address + ALU_Branch.add;

            }

        }

        if (Control_Unit.Jump.equals("1") && Control_Unit.ALUOp.equalsIgnoreCase("xx")) {
            ANDgate = 0;
            ALU_Branch.branch();

            //Converting pc.address to a string 32_bit
            String j_string = ALU.dectobin(PC.address);
            int length = j_string.length();
            for (int i = 0; i < (32 - length); i++) {
                j_string = "0" + j_string;
            }

            //Converting jump to integer *4
            int j_add = (int) ALU.convert(Instruction.jump);
            j_add *= 4;

            //converting it to a string 26 bit
            String sl = ALU.dectobin(j_add);
            int l = sl.length();
            for (int i = 0; i < 26 - l; i++) {
                sl = "0" + sl;

            }
            //concatinate PC.address with instruction.jump
            String j_str = j_string.substring(0, 5);
            j_str = j_str + sl;

            PC.address = (int) ALU.convert(j_str);

        }

      
    }
}
