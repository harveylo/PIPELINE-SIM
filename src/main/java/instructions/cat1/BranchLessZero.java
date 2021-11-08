package instructions.cat1;

import disassembler.Disassembler;
import instructions.Command;
import util.ProgramCounter;
import util.Register;

import java.util.LinkedList;
import java.util.List;

public class BranchLessZero implements Command {
    private static final int category = 1;
    private static final int opCode = 3;
    private static final String name = "BLTZ";

    private final List<String> parameters;

    public final int rs;
    public final int offset;

    public BranchLessZero(int instruction){
        parameters = new LinkedList<>();
        rs = instruction>>>21;
        short tem = (short)(instruction&0xffff);
        offset = (int)tem<<2;
        parameters.add("R"+rs);
        parameters.add("#"+offset);
        this.instruction = Disassembler.getInstruction(this);
    }

    @Override
    public void run() {
        if(Register.getRegisterValue(rs)<0) ProgramCounter.advancePC(offset+4);
        else ProgramCounter.advancePC(4);
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
}
