package util;

import instructions.Command;

public class TypeGetter {
    public static boolean isBranch(Command command){
        String name = command.getName();
        return name.equals("J")||name.equals("JR")||name.equals("BEQ")||name.equals("BLTZ")||name.equals("BGTZ");
    }

    public static boolean finishInIF(Command command){
        String name = command.getName();
        return name.equals("NOP")||isBranch(command)||name.equals("BREAK");
    }

    public static boolean useALU1(Command command){
        return command.getName().equals("SW")||command.getName().equals("LW");
    }
}
