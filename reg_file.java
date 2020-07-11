package project1;

public class reg_file {

    public static double[] regsfile = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //value of register 
    public static String[] regnames = {"$0", "$at", "$v0", "$v1", "$a0", "$a1", "$a2", "$a3", "$t0", "$t1", "$t2", "$t3", "$t4",
        "$t5", "$t6", "$t7", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$t8", "$t9", "$k0", "$k1", "$gp", "$sp",
        "$fp", "$ra"};
    public static String[] regstr = {"00000", "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010", "01011",
        "01100", "01101", "01110", "01111", "10000", "10001", "10010", "10011", "10100", "10101", "10110", "10111", "11000", "11001", "11010",
        "11011", "11100", "11101", "11110", "11111"};

    public static void Print() {
        for (int i = 0; i < 32; i++) {
            System.out.println(regsfile[i]);
        }
    }

    //Getting register name 
    public String getname(int offset) {
        String name = regnames[offset];
        return name;

    }

    //Read data of register
    public static double read_reg(String reg) {

        int offset = 0;
        //int offset = b_to_dec(reg);
        for (int i = 0; i <= 31; i++) {
            if (reg.equals(regstr[i])) {
                offset = i;
                break;
            }
        }

        return regsfile[offset];
    }

    //Write data in register
    public static void write_reg(String reg, double value) {

        int offset = 0;
        for (int i = 0; i <= 31; i++) {
            if (reg.equals(regstr[i])) {
                offset = i;
                break;
            }
        }

        regsfile[offset] = value;
    }
}
