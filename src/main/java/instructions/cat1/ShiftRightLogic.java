package instructions.cat1;


import disassembler.Disassembler;
import instructions.Command;
import instructions.R2IInstruction;
import util.ProgramCounter;
import util.Register;

import java.util.LinkedList;
import java.util.List;

public class ShiftRightLogic implements Command, R2IInstruction {
    private final static int category = 1;
    private final static int opCode = 9;
    private final static String name = "SRL";

    private final List<String> parameters;
    public final int rt;
    public final int rd;
    public final int sa;

    public ShiftRightLogic(int instruction){
        parameters = new LinkedList<>();
        rt = (instruction&0x1F0000)>>>16;
        rd = (instruction&0xF800)>>>11;
        sa = (instruction&0x7C0)>>>6;
        parameters.add("R"+rd);
        parameters.add("R"+rt);
        parameters.add("#"+sa);
        this.instruction = Disassembler.getInstruction(this);
    }

    @Override
    public void run() {
        Register.setRegisterValue(rd,Register.getRegisterValue(rt)>>>sa);
        
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
    private String instruction;
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
