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
    public static boolean is2R(Command command){
        return command.getName().equals("ADDI")|| command.getName().equals("ANDI")|| command.getName().equals("XORI")|| command.getName().equals("ORI")||
        command.getName().equals("SLL")||command.getName().equals("SRA")||command.getName().equals("SRL")||command.getName().equals("LW");
    }
}
