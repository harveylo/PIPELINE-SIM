package instructions.cat1;

import disassembler.Disassembler;
import instructions.Command;
import util.ProgramCounter;
import util.Register;

import java.util.LinkedList;
import java.util.List;

public class JumpRegister implements Command {
    private static final int category = 1;
    private static final int opCode = 1;
    private static final String name = "JR";


    public int jumpRegister;
    private List<String> parameters;

    public JumpRegister(){
        parameters = new LinkedList<>();
        jumpRegister = 0;
    }
    public JumpRegister(int instruction){
        parameters = new LinkedList<>();
        jumpRegister = instruction>>>21;
        parameters.add("R"+jumpRegister);
        this.instruction = Disassembler.getInstruction(this);
    }

    @Override
    public void run() {
        ProgramCounter.setPC(Register.getRegisterValue(jumpRegister));
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
