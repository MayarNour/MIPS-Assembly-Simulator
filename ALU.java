package project1;

public class ALU {

    public static int mux, valueint;
    public static double RT, RD;
    public static double zeroflag = 0;
    public static double valueofALU;
    public static double RS;
    public static String alucfn;
    public static double second_data = 0;

//Needed Functions for converting 
    //1-Converting from decimal to binary
    public static String dectobin(int x) {
        String s = "";

        if (x > 0) {
            while (x > 0) {
                if (x % 2 == 1) {
                    s = "1" + s;
                }
                if (x % 2 == 0) {
                    s = "0" + s;
                }
                x = x / 2;
            }
            s = "0" + s;
        } else if (x < 0) {
            x = Math.abs(x);
            while (x > 0) {
                if (x % 2 == 1) {
                    s = "1" + s;
                }
                if (x % 2 == 0) {
                    s = "0" + s;
                }
                x = x / 2;
            }
            s = "1" + s;
        } else if (x == 0) {
            s = "0";
        }
        return s;
    }

    //2-converting from string binary to decimal
    public static int b_to_dec(String str) {
        int v = Integer.parseInt(str);
        int sum = 0;

        for (int i = 0; i < str.length(); i++) {
            sum += (v % 10) * Math.pow(2, i);
            v = v / 10;
        }

        return sum;
    }
    //3-Converting from Binary String to decimal

    public static double convert(String xe) {
        double s = 0;
        if (xe.substring(0, 1).equalsIgnoreCase("0")) {
            // positive decimal
            long value = Long.parseLong(xe, 2);
            s = (double) value;
        }
        if (xe.substring(0, 1).equalsIgnoreCase("1")) {
            //Negative decimal
            long val = Long.parseLong(xe, 2);
            int valueint = ((int) val);
            s = (double) valueint;
        }
        return s;
    }

    public static void methods() {
        zeroflag = 0;
        alucfn = ALU_Control.function;
        RS = reg_file.read_reg(Instruction.rs);

        //Values of muxex
        if (Control_Unit.ALUSrc.equalsIgnoreCase("0")) {
            mux = 0;
        } else {
            mux = 1;
        }

        //R-TYPE
        if (mux == 0) {

            //value of rt
            RT = reg_file.read_reg(Instruction.rt);
            second_data = RT;

            //add
            if (alucfn.equalsIgnoreCase("0010")) {
                valueofALU = RS + RT;

            }

            //slt
            if (alucfn.equalsIgnoreCase("0111")) {
                if (RS < RT) {
                    valueofALU = 1;
                } else {
                    valueofALU = 0;
                }
            }

            //Shift-left
            if (alucfn.equalsIgnoreCase("0101")) {
                valueofALU = 0;
                String k = Instruction.shamt;

                for (int i = 0; i < (b_to_dec(k)); i++) {
                    valueofALU += RS * 2;
                }
            }

            //beq
            if (alucfn.equalsIgnoreCase("0110")) {
                RT = reg_file.read_reg(Instruction.rt);
                if ((RS - RT) == 0) {
                    valueofALU = 0;
                    zeroflag = 1;

                } else {
                    valueofALU = RS - RT;
                    zeroflag = 0;
                }

            }
            //nor
            if (alucfn.equalsIgnoreCase("0011")) {

                valueofALU = ~((int) RS | (int) RT);

            }
        }

        //I_type
        if (mux == 1) {

            String se = Sign_Extend.s;
            double cons = convert(se);
            second_data = cons;

            //addi,lw,sw
            if (alucfn.equalsIgnoreCase("0010")) {
                valueofALU = RS + cons;

            }
            //slti
            if (alucfn.equalsIgnoreCase("0111")) {
                if (RS < cons) {
                    valueofALU = 1;
                } else {
                    valueofALU = 0;
                }
            }

        }
    }

}
