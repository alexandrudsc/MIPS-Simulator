package instructions;

import helpers.InstructionFuncCodes;
import helpers.RegisterMapper;

import java.util.Hashtable;
import java.util.StringTokenizer;

import exceptions.ProgramRunTimeException;
import helpers.InstructionOpcodes;
import java.math.BigInteger;

public class Instruction {

    InstructionType type;
    int format;

    int rs;
    int rt;

    int immediate_value;
    int target_address;

    int rd;
    int shamt;
    int funct;

    boolean immediate;

    public Instruction() {

    }

    public Instruction(String raw_instruction, Hashtable<String, Integer> label_map) {
        decode_instruction(raw_instruction, label_map);
    }

    public InstructionType get_type() {
        return type;
    }

    public int get_rd() {
        return rd;
    }

    public int get_rs() {
        return rs;
    }

    public int get_rt() {
        return rt;
    }

    public int get_shamt() {
        return shamt;
    }

    public int get_format() {
        return format;
    }

    public int get_immediate_value() {
        return immediate_value;
    }

    public void decode_instruction(String raw_instruction, Hashtable<String, Integer> label_map) {
        StringTokenizer st = new StringTokenizer(raw_instruction, ",");
        StringTokenizer st1 = new StringTokenizer(st.nextToken());
        String ins_type = st1.nextToken().trim(), target = st1.nextToken()
                .trim();
        get_class(ins_type);
        
        switch (format) {
            case 0:
                rd = RegisterMapper.map_to_index(target.trim());
                if (type != InstructionType.lui) {
                    rs = RegisterMapper.map_to_index(st.nextToken().trim());
                }
                if (immediate) {
                    immediate_value = Integer.parseInt(st.nextToken().trim());
                } else {
                    rt = RegisterMapper.map_to_index(st.nextToken().trim());
                }
                break;
            case 1:
                rt = RegisterMapper.map_to_index(target.trim());
                if (immediate) {
                    immediate_value = Integer.parseInt(st.nextToken().trim());
                } else {
                    StringTokenizer st2 = new StringTokenizer(st.nextToken().trim(), "(");
                    immediate_value = Integer.parseInt(st2.nextToken().trim());
                    String str = st2.nextToken().trim();
                    rs = RegisterMapper.map_to_index(str.substring(0, str.length() - 1));
                }
                break;
            case 2:
                rt = RegisterMapper.map_to_index(target.trim());
                StringTokenizer st2 = new StringTokenizer(st.nextToken().trim(), "(");
                immediate_value = Integer.parseInt(st2.nextToken().trim());
                String str = st2.nextToken().trim();
                rs = RegisterMapper.map_to_index(str.substring(0, str.length() - 1));
                break;
            case 3:
                rs = RegisterMapper.map_to_index(target.trim());
                rt = RegisterMapper.map_to_index(st.nextToken().trim());
                immediate_value = label_map.get(st.nextToken().trim());
                break;
            case 4:
                immediate_value = label_map.get(target.trim());
                break;
            case 5:
                rs = Integer.parseInt(target.trim());
                break;
        }

    }

    private void get_class(String str) {

        str = str.toLowerCase();
        if (str.startsWith("#")){
            format = -2;
            type = InstructionType.commentariu;
            return;
        }
        
        type = InstructionType.valueOf(str);

        switch (type) {
            case add:
            case and:
            case sub:
            case sll:
            case srl:
            case or:
            case nor:
            case slt:
                format = 0;
                break;
            case addi:
            case andi:
            case ori:
            case lui:
                format = 0;
                immediate = true;
                break;
            case lw:
            case lh:
            case lhu:
            case lb:
            case lbu:
                format = 1;
                break;
            case sw:
            case sh:
            case sb:
                format = 2;
                break;
            case beq:
            case bne:
                format = 3;
                break;
            case j:
            case jal:
                format = 4;
                break;
            case jr:
                format = 5;
                break;
            default:
                format = -1;
                break;

        }

    }

    @Override
    public String toString() {
        String base = "Instructiune de tipul \"" + type + "\" si formatul = " + format + "\n";
        String specific = "";
        switch (format) {
            case 0:
                specific += "   rs index = " + rs + ", rt index= " + rt + ", rd index= " + rd;
                specific += immediate ? " valoare imediata = " + immediate_value : "";
                break;
        }
        String str = base + specific;
        return str;
    }

    public String toBinary() {
        
        if (type == InstructionType.commentariu)
            return "";
        String binary = "";
        String rsBinary = "";
        String rtBinary = "";
        String rdBinary = "";
        String shamtBinary =  "";
        String binaryOpcode = "";

        
        rsBinary = Integer.toBinaryString(rs);
        rsBinary = InstructionFuncCodes.fillWithZeros(rsBinary, 5);
        
        rtBinary = Integer.toBinaryString(rt);
        rtBinary = InstructionFuncCodes.fillWithZeros(rtBinary, 5);
        
        rdBinary = Integer.toBinaryString(rd);
        rdBinary = InstructionFuncCodes.fillWithZeros(rdBinary, 5);
        
        
        shamtBinary = Integer.toBinaryString(shamt);
        shamtBinary = InstructionFuncCodes.fillWithZeros(shamtBinary, 5);
        
        binaryOpcode = Integer.toBinaryString(InstructionOpcodes.code(type));
        binaryOpcode = InstructionFuncCodes.fillWithZeros(binaryOpcode, 6);
        
        binary += binaryOpcode
                + rsBinary 
                + rtBinary
                + rdBinary 
                + shamtBinary 
                + InstructionFuncCodes.code(type);
        return binary;
    }

    public int get_write_register() throws Exception {
        switch (format) {
            case 0:
                return rd;
            case 1:
                return rt;
            case 4:
                return RegisterMapper.map_to_index("ra");
            default:
                throw new ProgramRunTimeException();
        }
    }

}
