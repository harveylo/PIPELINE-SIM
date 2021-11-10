package util;

public class Register {
    private static final int[] registers = new int[32];
    private static final int[] buffer = new int[32];
    public final static String VACANT= "vacant";
    public static int getRegisterValue(int index){
        return registers[index];
    }
    public static void setRegisterValue(int index,int value){
        registers[index] = value;
    }
    public static int getBufferValue(int index) {return buffer[index];}
    public static void setBufferValue(int index,int value){
        buffer[index]=value;
    }
    public static void initialBuffer(){
        System.arraycopy(registers, 0, buffer, 0, registers.length);
    }
    public static void updateByBuffer(){
        System.arraycopy(buffer, 0, registers, 0, buffer.length);
    }

}
