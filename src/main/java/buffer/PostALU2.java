package buffer;

import instructions.Command;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostALU2 {
    public static class PostA2Entry{
        public Command c;
        public int target;
        public int result;
        public PostA2Entry(Command command,int t,int r){
            c = command;
            target = t;
            result = r;
        }
    }
    private static Deque<PostA2Entry> que = new LinkedList<>();
    private static int consumed = 0;
    public static void insert(PostA2Entry e){
        que.offer(e);
    }
    public static PostA2Entry getEntry(){
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
