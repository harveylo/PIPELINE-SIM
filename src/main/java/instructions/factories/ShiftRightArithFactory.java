package instructions.factories;

import instructions.Command;
import instructions.cat1.ShiftRightArith;

public class ShiftRightArithFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new ShiftRightArith(instruction);
    }
}
