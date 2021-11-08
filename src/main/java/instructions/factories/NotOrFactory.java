package instructions.factories;

import instructions.Command;
import instructions.cat3.NotOr;

public class NotOrFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new NotOr(instruction);
    }
}
