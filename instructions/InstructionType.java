package instructions;

public enum InstructionType {
	add,                                            // 1
        addi, sub,                                      // 2 3
	lw, lh, lhu, lb, lbu, sw, sh, sb, lui,          // 4 5 6 7 8 9 10 11 12
	sll, srl, and, andi, or, ori, nor,              // 13 14 15 16 17 18 19
	beq, bne, j, jal, jr,                           // 20 21 22 23 24
	slt                                             // 25
}
