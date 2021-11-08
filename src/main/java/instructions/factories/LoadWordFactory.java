package instructions.factories;


import instructions.Command;
import instructions.cat1.LoadWord;

public class LoadWordFactory implements CommandFactory{
    @Override
    public Command newCommand(int instruction) {
        return new LoadWord(instruction);
    }
}
