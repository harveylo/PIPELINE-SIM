package instructions.factories;

import instructions.Command;
import instructions.cat1.ShiftLeftLogic;

public class ShiftLeftLogicFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new ShiftLeftLogic(instruction);
    }
}
