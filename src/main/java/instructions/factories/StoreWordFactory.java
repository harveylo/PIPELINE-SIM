package instructions.factories;

import instructions.Command;
import instructions.cat1.StoreWord;

public class StoreWordFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new StoreWord(instruction);
    }
}
