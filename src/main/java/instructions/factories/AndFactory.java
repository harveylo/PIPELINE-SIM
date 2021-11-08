package instructions.factories;

import instructions.Command;
import instructions.cat3.And;

public class AndFactory implements CommandFactory{

    @Override
    public Command newCommand(int instruction) {
        return new And(instruction);
    }
}
