package buffer;

import instructions.Command;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreIssue {
    private static final Deque<Command> instructionPool = new LinkedList<>();
    private static final Deque<Command> shouldRemove = new LinkedList<>();
    private static final int MAX_CAP = 4;

    public static boolean isFull(){
        return instructionPool.size()==MAX_CAP;
    }

    public static void insert(Command command){
        if (isFull()) return;
        instructionPool.offer(command);
    }
    public static List<Command> getInstructionPool(){
        return (List<Command>) instructionPool;
    }

    public static void toRemove(List<Command> list){
        for (Command command : list) {
            shouldRemove.offer(command);
        }
    }

    public static void consume(){
        List<Command> list = getInstructionPool();
        while(!shouldRemove.isEmpty()){
            list.remove(shouldRemove.poll());
        }
    }
}
