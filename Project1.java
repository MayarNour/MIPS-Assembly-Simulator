package project1;

import java.util.*;

public class Project1 {

    public static int real_offset;

    public static void output() {
        Control_Unit.ControlUnit();
        Sign_Extend.SE();
        ALU_Control.ALU_C();
        ALU.methods();
        Memory.Memoryfunction();
        Muxes.mix();
        Mux_branch.mux_value();

    }

    public static void printfn() {
        System.out.println("Wires: ");
        System.out.println("---------------------");
        System.out.println("PC output: " + real_offset);
        System.out.println("PC+4 adder output : " + (real_offset + 4));
        System.out.println("Instruction Memory Output: " + Instruction.Finstruct);
        System.out.println("Opcode field of instruction: " + ALU.b_to_dec(Instruction.op_code));
        System.out.println("Rs field of instruction: " + ALU.b_to_dec(Instruction.rs));
        System.out.println("Rt field of instruction: " + ALU.b_to_dec(Instruction.rt));
        System.out.println("Rd field of instruction: " + ALU.b_to_dec(Instruction.rd));
        System.out.println("RegDestination Mux output: " + ALU.b_to_dec(Muxes.MuxOut));
        System.out.println("Offset field of instruction: " + Instruction.constant);
        System.out.println("Sign-extender Output: " + Sign_Extend.s);
        System.out.println("Shift-2 output: " + ALU_Branch.SHIFT);
        System.out.println("Target address adder: " + PC.address);
        System.out.println("Function code of instruction: " + ALU.b_to_dec(Instruction.fun_code));
        System.out.println("Read data 1: " + ALU.RS);
        System.out.println("Read data 2: " + ALU.RT);
        System.out.println("ALU second input: " + ALU.second_data);
        System.out.println("ALU output: " + ALU.valueofALU);
        System.out.println("Zero flag:" + ALU.zeroflag);
        if ((Control_Unit.Memread.equals("0") && Control_Unit.MemWrite.equals("0") || Control_Unit.MemWrite.equals("1"))) {
            System.out.println("Data memory output: UNKNOWN ");
        } else if (Control_Unit.Memread.equals("1")) {
            System.out.println("Data memory output: " + Memory.d);
        }

        System.out.println("MemtoReg Mux output: " + Memory.d);
        System.out.println("PC input: " + PC.address);
        System.out.println("AND gate output: " + Mux_branch.ANDgate);
        System.out.println("-----------------------------------------------------");
        System.out.println("Control Unit Signals: ");
        System.out.println("---------------------");
        System.out.println("RegDest: " + Control_Unit.Regdst);
        System.out.println("Branch: " + Control_Unit.Branch);
        System.out.println("MemRead: " + Control_Unit.Memread);
        System.out.println("MemtoReg: " + Control_Unit.Memtoreg);
        System.out.println("ALUOp: " + Control_Unit.ALUOp);
        System.out.println("MemWrite: " + Control_Unit.MemWrite);
        System.out.println("ALUSrc: " + Control_Unit.ALUSrc);
        System.out.println("RegWrite: " + Control_Unit.RegWrite);
        System.out.println("ALU control output: " + ALU_Control.function);
        System.out.println("-----------------------------------------------------");

    }

    public static void main(String[] args) {

        GUI g = new GUI();
        g.setVisible(true);

        instruct_mem.cin_instruct_arr();

        System.out.println("Please enter address: ");
        Scanner sc = new Scanner(System.in);
        PC.address = sc.nextInt();

        System.out.println("Please enter number of instructions : ");
        int num_instructions = sc.nextInt();

        real_offset = PC.address;

        for (int i = 0; i < num_instructions; i++) {
            System.out.println("Instruction number " + (i + 1));
            instruct_mem.instruct_arr[i] = sc.next();

        }

        PC.address = real_offset;

        int clock = 1;
        Memory.init();
        while (PC.address <= ((num_instructions - 1) * 4)) {
            System.out.println("Clock Cycle " + clock + " is : ");

            Instruction.codes(instruct_mem.instruct_arr[(PC.address) / 4]);
            output();

            printfn();
            clock++;
            System.out.println("Register File: ");
            System.out.println("---------------------");
            for (int i = 0; i < 32; i++) {
                System.out.print(reg_file.regnames[i] + " : ");
                System.out.println(reg_file.regsfile[i]);

            }
            System.out.println("-----------------------------------------------------");

            System.out.println("Memory: \n" + "----------------");
            if (Control_Unit.MemWrite.equals("1")) {
                for (int i = 0; i < Memory.List.length; i++) {
                    if (i == ALU.valueofALU) {
                        if (Instruction.op_code.equals("101011")) {
                            String memory = Memory.Extend(Memory.List[i].length(), Memory.List[i]);
                            System.out.println("Address: " + (i));
                            System.out.println("Value: " + (int) ALU.convert(memory));
                            memory = Memory.Extend(Memory.List[i + 1].length(), Memory.List[i + 1]);
                            System.out.println("Address: " + (i + 1));
                            System.out.println("Value: " + (int) ALU.convert(memory));
                            memory = Memory.Extend(Memory.List[i + 2].length(), Memory.List[i + 2]);
                            System.out.println("Address: " + (i + 2));
                            System.out.println("Value: " + (int) ALU.convert(memory));
                            memory = Memory.Extend(Memory.List[i + 3].length(), Memory.List[i + 3]);
                            System.out.println("Address: " + (i + 3));
                            System.out.println("Value: " + (int) ALU.convert(memory));
                        } else if (Instruction.op_code.equals("101000")) {
                            String memory = Memory.Extend(Memory.List[i].length(), Memory.List[i]);
                            System.out.println("Address: " + (i));
                            System.out.println("Value: " + (int) ALU.convert(memory));
                        }
                    }

                }
            } else {
                System.out.println("Nothing changes ");
            }
            System.out.println("-----------------------------------------------------");
        }

    }
}
