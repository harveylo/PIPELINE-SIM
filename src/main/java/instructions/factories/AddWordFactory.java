package instructions.factories;

import instructions.Command;
import instructions.cat3.AddWord;

public class AddWordFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new AddWord(instruction);
    }
}
