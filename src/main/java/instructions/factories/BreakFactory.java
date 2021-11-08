package instructions.factories;

import instructions.Command;
import instructions.cat1.Break;

public class BreakFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new Break(instruction);
    }
}
