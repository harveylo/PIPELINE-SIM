package buffer;

import instructions.Command;

import java.util.Deque;
import java.util.LinkedList;

public class PreMEM {
    private static final Deque<int[]> que = new LinkedList<>();
    //数组第一个数字0代表LW，1代表SW
    //如果是LW，第二个数字代表目标寄存器，第三个数字代表目标地址
    //如果是SW，第二个数字代表目标地址，第三个数字表示读出来的寄存器的值，即目标数值
    public static void insertResult(int[] r){
        que.offer(r);
    }

    public static int[] popResult(){
        return que.poll();
    }
}
