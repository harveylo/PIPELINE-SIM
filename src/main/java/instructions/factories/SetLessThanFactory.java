package instructions.factories;

import instructions.Command;
import instructions.cat3.SetLessThan;

public class SetLessThanFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new SetLessThan(instruction);
    }
}
