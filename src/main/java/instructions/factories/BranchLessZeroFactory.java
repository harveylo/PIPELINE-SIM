package instructions.factories;

import instructions.Command;
import instructions.cat1.BranchLessZero;

public class BranchLessZeroFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new BranchLessZero(instruction);
    }
}
