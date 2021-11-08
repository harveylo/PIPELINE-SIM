package instructions.factories;

import instructions.Command;
import instructions.cat1.BranchGreaterZero;

public class BranchGreaterZeroFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new BranchGreaterZero(instruction);
    }
}
