package buffer;

import instructions.Command;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreMEM {
    public static class PreMEntry{
        public Command c;
        public int target;
        public int result;
        public PreMEntry(Command c,int target,int result){
            this. c = c;
            this.target = target;
            this.result = result;
        }
    }
    private static final Deque<PreMEntry> que = new LinkedList<>();
    private static int consumed = 0;
    //如果是LW，第二个数字代表目标寄存器，第三个数字代表目标地址
    //如果是SW，第二个数字代表目标地址，第三个数字表示读出来的寄存器的值，即目标数值
    public static void insertResult(PreMEntry r){
        que.offer(r);
    }

    public static PreMEntry getResult(){
        consumed++;
        return que.peek();
    }
    public static boolean isEmpty(){return que.isEmpty();}
    public static Command peek(){
        if(que.isEmpty()) return null;
        return que.peek().c;
    }
    public static void consume(){
        while (consumed>0) {
            consumed--;
            que.poll();
        }
    }
}
