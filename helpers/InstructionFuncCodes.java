package helpers;

import instructions.InstructionType;
import java.util.*;

/**
 * @author Alexandru
 Hashtable mapping Instructions to codes
*/
public class InstructionFuncCodes {

        static Hashtable<InstructionType, String> codes;

	private InstructionFuncCodes(){

	}
        
        public static void init()
        {
            codes = new Hashtable<>();
            codes.put(InstructionType.add, "100000");
            codes.put(InstructionType.addi, "001000");
            codes.put(InstructionType.sub, "100010");
            codes.put(InstructionType.lw, "100011");
            codes.put(InstructionType.lh, "100001");
            codes.put(InstructionType.lhu, "100101");
            codes.put(InstructionType.lb, "100000");
            codes.put(InstructionType.lbu, "100100");
            codes.put(InstructionType.sw, "101011");
            codes.put(InstructionType.sh, "101001");
            codes.put(InstructionType.sb, "101001");
            codes.put(InstructionType.lui, "001111");
            codes.put(InstructionType.sll, "000000");
            codes.put(InstructionType.srl, "000010");
            codes.put(InstructionType.and, "100100");
            codes.put(InstructionType.addi, "001100");
            codes.put(InstructionType.or, "100101");
            codes.put(InstructionType.ori, "001101");
            codes.put(InstructionType.nor, "100111");
            codes.put(InstructionType.beq, "000100");
            codes.put(InstructionType.bne, "000101");
            codes.put(InstructionType.j, "000010");
            codes.put(InstructionType.jal, "000011");
            codes.put(InstructionType.jr, "001000");
            codes.put(InstructionType.slt, "101010");
        }
        
        public static String code(InstructionType type)
        {
            return codes.get(type);
        }
        
        public static String fillWithZeros(String binary, int digits)
        {
            while (binary.length() < digits)
                binary = "0" + binary;
            return binary;
        }
        
	/*
	Instruction	Opcode/Function	Syntax	Operation
	add 	100000	f $d, $s, $t 	$d = $s + $t
	addi 	001000	f $d, $s, i	$d = $s + SE(i)
	addiu 	001001	f $d, $s, i	$d = $s + SE(i)
	and 	100100	f $d, $s, $t 	$d = $s & $t
	andi 	001100	f $d, $s, i	$t = $s & ZE(i)
	div 	011010	f $s, $t 	lo = $s / $t; hi = $s % $t
	divu 	011011	f $s, $t 	lo = $s / $t; hi = $s % $t
	mult 	011000	f $s, $t 	hi:lo = $s * $t
	multu 	011001	f $s, $t 	hi:lo = $s * $t
	nor 	100111	f $d, $s, $t 	$d = ~($s | $t)
	or 	100101	f $d, $s, $t 	$d = $s | $t
	ori 	001101	f $d, $s, i	$t = $s | ZE(i)
	sll 	000000	f $d, $t, a 	$d = $t << a
	sllv 	000100	f $d, $t, $s 	$d = $t << $s
	sra 	000011	f $d, $t, a 	$d = $t >> a
	srav 	000111	f $d, $t, $s 	$d = $t >> $s
	srl 	000010	f $d, $t, a 	$d = $t >>> a
	srlv 	000110	f $d, $t, $s 	$d = $t >>> $s
	sub 	100010	f $d, $s, $t 	$d = $s - $t
	subu 	100011	f $d, $s, $t 	$d = $s - $t
	xor 	100110	f $d, $s, $t 	$d = $s ^ $t
	xori 	001110	f $d, $s, i	$d = $s ^ ZE(i) 
	*/
}
