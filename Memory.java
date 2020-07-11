package project1;

import java.util.*;

public class Memory {

    public static double d;

    public static String[] List = new String[1000];

    //Function converting unsigned
    public static double convert1(String xe) {
        double s = 0;

        // positive decimal
        long value = Long.parseLong(xe, 2);
        s = (double) value;

        return s;
    }

    //Function extending string to 32-bits
    public static String Extend(int length, String s) {
        String st = s;

        for (int i = 0; i < (32 - length); i++) {
            if (st.substring(0, 1).equals("0")) {
                st = "0" + st;
            } else if (st.substring(0, 1).equals("1")) {
                st = "1" + st;
            }
        }
        return st;

    }

    public static void init() {

        for (int i = 0; i < 1000; i++) {
            List[i] = "0";

        }
        reg_file.write_reg("11101", List.length);
    }

    public static void Memoryfunction() {

        int valofalu = (int) ALU.valueofALU;

        //ay 7aga msh memory
        if (Control_Unit.Memread.equalsIgnoreCase("0") && Control_Unit.MemWrite.equalsIgnoreCase("0")) {
            d = ALU.valueofALU;
        }

        //load-word
        //lb
        if (Control_Unit.Memread.equalsIgnoreCase("1")) {
            String first = "", second = "", third = "", fourth = "";

            if (Instruction.op_code.equalsIgnoreCase("100000")) {

                for (int i = 0; i < List.length; i++) {
                    if (i == valofalu) {

                        first = List[i];
                        System.out.println("Value stored " + List[i]);

                    }
                }
                first = Extend(first.length(), first);
                d = ALU.convert(first);

            } //lbu
            else if (Instruction.op_code.equalsIgnoreCase("100100")) {

                for (int i = 0; i < List.length; i++) {
                    if (i == valofalu) {
                        first = List[i];
                    }
                }

                //Converting to 32_bits
                for (int i = 0; i < (32 - first.length()); i++) {
                    first = "0" + first;
                }
                d = convert1(first);

            } else {

                for (int i = 0; i < List.length; i++) {
                    if (i == valofalu) {

                        first = List[i];

                        second = List[i + 1];

                        third = List[i + 2];

                        fourth = List[i + 3];
                    }

                }

                String final_val = first + second + third + fourth;
                d = ALU.convert(final_val);
            }
        }
        //Store word 
        if (Control_Unit.MemWrite.equalsIgnoreCase("1")) {

            //converting value of rt into string 32_bits
            double rt = reg_file.read_reg(Instruction.rt);
            String mem_value = ALU.dectobin((int) rt);
            mem_value = Extend(mem_value.length(), mem_value);
            if (Instruction.op_code.equalsIgnoreCase("101000")) {

                List[valofalu] = mem_value.substring(24, 32);
            } else {

                //storing fl memory 
                List[(int) valofalu] = mem_value.substring(0, 8);
                List[(int) (valofalu + 1)] = mem_value.substring(8, 16);
                List[(int) (valofalu + 2)] = mem_value.substring(16, 24);
                List[(int) (valofalu + 3)] = mem_value.substring(24, 32);
            }
        }
    }

}
