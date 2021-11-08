package instructions.factories;

import instructions.Command;
import instructions.cat3.SubtractWord;

public class SubtractWordFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new SubtractWord(instruction);
    }
}
