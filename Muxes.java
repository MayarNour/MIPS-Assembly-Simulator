package project1;

public class Muxes {

    public static double z = 0;
    public static double f = 0;
    public static String MuxOut = "";

    public static void mix() {

        MuxOut = "0";
        if (Control_Unit.RegWrite.equalsIgnoreCase("1")) {
            if (Control_Unit.Memtoreg.equalsIgnoreCase("1")) {
                z = Memory.d;

                //I_type (rt)
                if (Control_Unit.Regdst.equalsIgnoreCase("0")) {
                    MuxOut = Instruction.rt;

                    //Handling register zero 
                    if (Instruction.rt.equalsIgnoreCase("00000")) {
                        reg_file.write_reg(Instruction.rt, 0);
                    } else {

                        reg_file.write_reg(Instruction.rt, z);
                    }
                }

            }

            if (Control_Unit.Memtoreg.equalsIgnoreCase("0")) {
                f = ALU.valueofALU;

                if (Control_Unit.Regdst.equalsIgnoreCase("0")) {
                    MuxOut = Instruction.rt;

                    if (Instruction.rt.equalsIgnoreCase("00000")) {
                        reg_file.write_reg(Instruction.rt, 0);
                    } else {

                        reg_file.write_reg(Instruction.rt, f);

                    }
                }

                //R_type (rd)
                if (Control_Unit.Regdst.equalsIgnoreCase("1")) {
                    MuxOut = Instruction.rd;

                    if (Instruction.rd.equalsIgnoreCase("00000")) {
                        reg_file.write_reg(Instruction.rd, 0);
                    } else {

                        reg_file.write_reg(Instruction.rd, f);
                    }
                }

            }
            if (Control_Unit.Memtoreg.equals("x") && Control_Unit.Jump.equals("1")) {
                MuxOut = ("11111");
                reg_file.write_reg("11111", PC.address);

            }

        }

    }

}
