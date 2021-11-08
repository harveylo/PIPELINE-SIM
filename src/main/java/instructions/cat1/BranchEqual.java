package instructions.cat1;

import disassembler.Disassembler;
import instructions.Command;
import util.Masks;
import util.ProgramCounter;
import util.Register;

import java.util.LinkedList;
import java.util.List;

public class BranchEqual implements Command {
    private static final int category = 1;
    private static final int opCode = 2;
    private static final String name = "BEQ";
    private String instruction = null;

    private List<String> parameters;

    public int rs;
    public int rt;
    public int offset;

    public BranchEqual(){
        parameters = new LinkedList<>();
    }
    public BranchEqual(int instruction){
        parameters = new LinkedList<>();
        rs = instruction>>>21;
        rt = (instruction& Masks.register2)>>>16;
        short tem = (short)(instruction&0xffff);
        offset = (int)tem<<2;
        parameters.add("R"+rs);
        parameters.add("R"+rt);
        parameters.add("#"+offset);
        this.instruction = Disassembler.getInstruction(this);
    }

    @Override
    public void run() {
        if(Register.getRegisterValue(rs)==Register.getRegisterValue(rt)){
            ProgramCounter.advancePC(4+offset);
        }else ProgramCounter.advancePC(4);
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

    @Override
    public String getInstruction() {
        return instruction;
    }
}
