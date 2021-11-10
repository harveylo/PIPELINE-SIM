package buffer;

import instructions.Command;

import java.util.Deque;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

public class PostMEM {
    public static class PostMEntry{
        public Command c;
        public int target;
        public int result;
        public PostMEntry(Command command,int t,int r){
            c = command;
            target = t;
            result = r;
        }
    }
    private static final Deque<PostMEntry> que = new LinkedList<>();
    private static int consumed = 0;
    // 数组第一个数字为目标寄存器，第二个数字为结果。
    public static void insert(PostMEntry e){
        que.offer(e);
    }
    public static PostMEntry getResult(){
        consumed++;
        return que.peek();
    }
    public static boolean isEmpty(){
        return que.isEmpty();
    }
    public static Command peek(){
        if(que.isEmpty()) return null;
        return que.peek().c;
    }
    public static void consume(){
        while(consumed>0){
            consumed--;
            que.poll();
        }
    }
}
