package instructions.cat3;

import disassembler.Disassembler;
import instructions.Command;
import instructions.R2IInstruction;
import util.Masks;
import util.ProgramCounter;
import util.Register;

import java.util.LinkedList;
import java.util.List;

public class ExclusiveOrImmediate implements Command, R2IInstruction {
    private final static int category = 3;
    private final static int opCode = 11;
    private final static String name = "XORI";

    public final int rs;
    public final int rt;
    private final int immediate;

    private final List<String> parameters;

    public ExclusiveOrImmediate(int instruction){
        parameters = new LinkedList<>();
        rs = instruction>>>21;
        rt = (instruction& Masks.register2)>>>16;
        immediate = instruction&0xFFFF;
        parameters.add("R"+rt);
        parameters.add("R"+rs);
        parameters.add("#"+immediate);
        this.instruction = Disassembler.getInstruction(this);
    }

    @Override
    public void run() {
        Register.setRegisterValue(rt,Register.getRegisterValue(rs)^immediate);
        
    }

    @Override
    public int getCat() {
        return category;
    }

    @Override
    public int getOpCode() {
        return opCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getParameters() {
        return parameters;
    }
    private String instruction = null;
    @Override
    public String getInstruction() {
        return instruction;
    }
    @Override
    public int getRs() {
        return rs;
    }

    @Override
    public int getRt() {
        return rt;
    }
}
