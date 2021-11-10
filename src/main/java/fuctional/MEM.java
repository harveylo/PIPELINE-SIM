package fuctional;

import buffer.PostMEM;
import buffer.PreMEM;
import instructions.Command;
import util.MemoryData;

public class MEM {
    public static final String name = "MEM";

    public static void MEMAccess(){
        if (PreMEM.isEmpty()) return;
        PreMEM.PreMEntry entry = PreMEM.getResult();
        if(entry.c.getName().equals("LW")){
            int result = MemoryData.loadData(entry.result);
            PostMEM.insert(new PostMEM.PostMEntry(entry.c, entry.target,result));
        }else {
            MemoryData.storeData(entry.target,entry.result);
        }
    }
}
