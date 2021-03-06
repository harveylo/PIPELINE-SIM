package instructions.cat1;

import disassembler.Disassembler;
import instructions.Command;
import instructions.R2IInstruction;
import util.MemoryData;
import util.ProgramCounter;
import util.Register;

import java.util.LinkedList;
import java.util.List;

public class StoreWord implements Command, R2IInstruction {
    private static final int category = 1;
    private static final int opCode = 6;
    private static final String name = "SW";

    private final List<String> parameters;
    private final short offset;
    public final int base;
    public final int rt;

    public StoreWord(int instruction) {
        parameters = new LinkedList<>();
        base = instruction>>>21;
        rt = (instruction&0x1F0000)>>>16;
        offset = (short)(instruction&0xFFFF);
        parameters.add("R"+rt);
        parameters.add(offset+"(R"+base+")");
        this.instruction = Disassembler.getInstruction(this);
    }

    @Override
    public void run() {
        int address = Register.getRegisterValue(base)+offset;
        Register.setRegisterValue(rt,address);
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
        return base;
    }

    @Override
    public int getRt() {
        return rt;
    }
}
