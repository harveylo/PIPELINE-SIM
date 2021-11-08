package instructions.factories;

import instructions.Command;
import instructions.cat1.Jump;

public class JumpFactory implements CommandFactory {
    @Override
    public Command newCommand(int instruction) {
        return new Jump(instruction);
    }
}
