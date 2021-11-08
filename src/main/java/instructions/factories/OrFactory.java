package instructions.factories;

import instructions.Command;
import instructions.cat3.Or;

public class OrFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new Or(instruction);
    }
}
