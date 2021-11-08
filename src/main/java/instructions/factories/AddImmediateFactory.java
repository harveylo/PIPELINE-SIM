package instructions.factories;

import instructions.Command;
import instructions.cat3.AddImmediate;

public class AddImmediateFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new AddImmediate(instruction);
    }
}
