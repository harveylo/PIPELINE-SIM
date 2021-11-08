package buffer;

import instructions.Command;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreIssue {
    private static Deque<Command> instructionPool = new LinkedList<>();
    private static final int MAX_CAP = 4;

    public static boolean isFull(){
        return instructionPool.size()==MAX_CAP;
    }

    public static void insert(Command command){
        if (isFull()) return;
        instructionPool.offer(command);
    }
    public static List<Command> getInstructionPool(){
        return (List<Command>)instructionPool;
    }
}
