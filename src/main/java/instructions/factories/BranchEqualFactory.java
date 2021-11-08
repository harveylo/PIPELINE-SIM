package instructions.factories;

import instructions.Command;
import instructions.cat1.BranchEqual;

public class BranchEqualFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new BranchEqual(instruction);
    }
}
