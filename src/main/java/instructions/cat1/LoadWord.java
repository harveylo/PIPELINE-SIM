package instructions.cat1;


import disassembler.Disassembler;
import instructions.Command;
import util.MemoryData;
import util.ProgramCounter;
import util.Register;

import java.util.LinkedList;
import java.util.List;

public class LoadWord implements Command {
    private final static int category = 1;
    private final static int opCode = 7;
    private final static String name = "LW";

    private final List<String> parameters;
    public final int base;
    public final int rt;
    private final short offset;

    public LoadWord(int instruction){
        parameters = new LinkedList<>();
        base = instruction>>>21;
        rt = (instruction&0x1F0000)>>16;
        offset = (short)(instruction&0xFFFF);
        parameters.add("R"+rt);
        parameters.add(offset+"(R"+base+")");
        this.instruction = Disassembler.getInstruction(this);
    }

    @Override
    public void run() {
        Register.setRegisterValue(rt, MemoryData.loadData(Register.getRegisterValue(base)+offset));
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
}
