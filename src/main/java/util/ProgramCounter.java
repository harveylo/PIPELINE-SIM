package util;

public class ProgramCounter {
    private static int pc;

    public static int getPC(){
        return pc;
    }

    public static void setPC(int address){
        pc = address;
    }

    public static void advancePC(int offset){
        pc += offset;
    }
}
