/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import instructions.InstructionType;
import java.util.Hashtable;

/**
 *
 * @author Alexandru
 */
public class InstructionOpcodes {

    static Hashtable<InstructionType, Integer> opcodes;

    private InstructionOpcodes() {

    }

    public static void init() {
        opcodes = new Hashtable<>();
        opcodes.put(InstructionType.add,  0x00);
        opcodes.put(InstructionType.addi, 0x08);
        opcodes.put(InstructionType.sub,  0x00);
        opcodes.put(InstructionType.lw,   0x23);
        opcodes.put(InstructionType.lh,   0x25);
        opcodes.put(InstructionType.lhu,  0x25);
        opcodes.put(InstructionType.lb,   0x24);
        opcodes.put(InstructionType.lbu,  0x24);
        opcodes.put(InstructionType.sw,   0x2B);
        opcodes.put(InstructionType.sh,	  0x29);
        opcodes.put(InstructionType.sb,   0x28);
        opcodes.put(InstructionType.lui,  0x0F);
        opcodes.put(InstructionType.sll,  0x00);
        opcodes.put(InstructionType.srl,  0x00);
        opcodes.put(InstructionType.and,  0x00);
        opcodes.put(InstructionType.addi, 0x0C);
        opcodes.put(InstructionType.or,   0x00);
        opcodes.put(InstructionType.ori,  0x0D);
        opcodes.put(InstructionType.nor,  0x00);
        opcodes.put(InstructionType.beq,  0x04);
        opcodes.put(InstructionType.bne,  0x05);
        opcodes.put(InstructionType.j,    0x02);
        opcodes.put(InstructionType.jal,  0x03);
        opcodes.put(InstructionType.jr,   0x00);
        opcodes.put(InstructionType.slt,  0x00);
    }

    public static int code(InstructionType type) {
        return opcodes.get(type);
    }
}
