package buffer;

import instructions.Command;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreALU2 {
    private static final Deque<Command> instructionPool = new LinkedList<>();
    private static int consumed = 0;
    private static final int MAX_CAP = 2;

    public static boolean isFull(){
        return instructionPool.size()==MAX_CAP;
    }
    public static void insertCommand(Command command){
        instructionPool.offer(command);
    }
    public static Command getCommand(){
        consumed++;
        return instructionPool.peek();
    }
    public static boolean isEmpty(){return instructionPool.isEmpty();}
    public static List<Command> getPool(){
        return (List<Command>) instructionPool;
    }
    public static void consume(){
        while(consumed>0) {
            consumed--;
            instructionPool.poll();
        }
    }
}
