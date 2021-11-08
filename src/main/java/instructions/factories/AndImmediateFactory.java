package instructions.factories;

import instructions.Command;
import instructions.cat3.AndImmediate;

public class AndImmediateFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return  new AndImmediate(instruction);
    }
}
