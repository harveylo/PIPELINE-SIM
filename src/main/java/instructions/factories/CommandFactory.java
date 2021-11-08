package instructions.factories;

import instructions.Command;

public interface CommandFactory {
    public Command newCommand(int instruction);
}
