package functional;

import buffer.PostALU2;
import buffer.PostMEM;
import scoreboard.Scoreboard;
import util.Register;

public class WB {
    public static final String name = "WB";
    public static void writeBack(){
        if(!PostMEM.isEmpty()){
            PostMEM.PostMEntry e = PostMEM.getResult();
            Register.setBufferValue(e.target,e.result);
            Scoreboard.toRelease(e.target);
        }if(!PostALU2.isEmpty()){
            PostALU2.PostA2Entry e = PostALU2.getEntry();
            Register.setBufferValue(e.target,e.result);
            Scoreboard.toRelease(e.target);
        }
    }
}
