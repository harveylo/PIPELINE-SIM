package instructions.cat3;

import disassembler.Disassembler;
import instructions.Command;
import instructions.R3Instruction;
import util.Masks;
import util.ProgramCounter;
import util.Register;

import java.util.LinkedList;
import java.util.List;

public class AddWord implements Command, R3Instruction {
    private final static int category = 3;
    private final static int opCode = 0;
    private final static String name = "ADD";

    public final int rs;
    public final int rt;
    public final int rd;

    private final List<String> parameters;

    public AddWord(int instruction){
        parameters = new LinkedList<>();
        rs = instruction>>>21;
        rt = (instruction& Masks.register2)>>>16;
        rd = (instruction&Masks.register3)>>>11;
        parameters.add("R"+rd);
        parameters.add("R"+rs);
        parameters.add("R"+rt);
        this.instruction = Disassembler.getInstruction(this);
    }

    @Override
    public void run() {
        long tem = (long) Register.getRegisterValue(rs)+(long) Register.getRegisterValue(rt);
        if(!(tem>Integer.MAX_VALUE||tem<Integer.MIN_VALUE)) Register.setRegisterValue(rd,(int)tem);
        ProgramCounter.advancePC(4);
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

    @Override
    public int getRd() {
        return rd;
    }
}
