package buffer;

import instructions.Command;

import java.util.Deque;
import java.util.LinkedList;

public class PreALU2 {
    private static final Deque<Command> instructionPoll = new LinkedList<>();
    private static final int MAX_CAP = 2;

    public static boolean isFull(){
        return instructionPoll.size()==MAX_CAP;
    }
    public static void insertCommand(Command command){
        instructionPoll.offer(command);
    }
    public static Command popCommand(){
        return instructionPoll.poll();
    }
}
