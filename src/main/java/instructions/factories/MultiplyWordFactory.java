package instructions.factories;

import instructions.Command;
import instructions.cat3.MultiplyWord;

public class MultiplyWordFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new MultiplyWord(instruction);
    }
}
