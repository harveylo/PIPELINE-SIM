package instructions.factories;

import instructions.Command;
import instructions.cat3.ExclusiveOr;

public class ExclusiveOrFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new ExclusiveOr(instruction);
    }
}
