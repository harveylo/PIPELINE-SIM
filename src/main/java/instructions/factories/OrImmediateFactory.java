package instructions.factories;

import instructions.Command;
import instructions.cat3.OrImmediate;

public class OrImmediateFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new OrImmediate(instruction);
    }
}
