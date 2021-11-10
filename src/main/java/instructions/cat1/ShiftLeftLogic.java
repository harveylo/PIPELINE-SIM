package instructions.cat1;

import disassembler.Disassembler;
import instructions.Command;
import instructions.R2IInstruction;
import util.Masks;
import util.ProgramCounter;
import util.Register;

import java.util.LinkedList;
import java.util.List;

public class ShiftLeftLogic implements Command, R2IInstruction {
    private static final int category = 1;
    private static final int opCode = 8;
    private static final String name = "SLL";

    private final List<String> parameters;
    public final int rt;
    public final int rd;
    public final int sa;

    public ShiftLeftLogic(int instruction){
        parameters = new LinkedList<>();
        rt = (instruction& Masks.register2)>>>16;
        rd = (instruction&Masks.register3)>>>11;
        sa = (instruction&0x7C0)>>>6;
        parameters.add("R"+rd);
        parameters.add("R"+rt);
        parameters.add("#"+sa);
        this.instruction = Disassembler.getInstruction(this);
    }


    @Override
    public void run() {
        Register.setRegisterValue(rd,Register.getRegisterValue(rt)<<sa);
        
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
        return rt;
    }

    @Override
    public int getRt() {
        return rd;
    }

}
