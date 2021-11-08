package instructions.factories;

import instructions.Command;
import instructions.cat1.JumpRegister;

public class JumpRegisterFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new JumpRegister(instruction);
    }
}
