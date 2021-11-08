package instructions.factories;

import instructions.Command;
import instructions.cat3.ExclusiveOrImmediate;

public class ExclusiveOrImmediateFactory implements CommandFactory {
    @Override
    public Command newCommand(int instruction) {
        return new ExclusiveOrImmediate(instruction);
    }
}
