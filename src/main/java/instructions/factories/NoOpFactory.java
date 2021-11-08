package instructions.factories;

import instructions.Command;
import instructions.cat1.NoOp;

public class NoOpFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new NoOp(instruction);
    }
}
