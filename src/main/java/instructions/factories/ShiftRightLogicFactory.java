package instructions.factories;

import instructions.Command;
import instructions.cat1.ShiftRightLogic;

public class ShiftRightLogicFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new ShiftRightLogic(instruction);
    }
}
