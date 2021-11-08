package util;

import java.util.HashMap;
import java.util.Map;

public class MemoryData {

    public static int startAddress;
    public static int endAddress;
    private static final Map<Integer,Integer> dataMap=new HashMap<>();

    public static void storeData(int address,int data){
        dataMap.put(address,data);
    }

    public static int loadData(int address){
        return dataMap.getOrDefault(address,0);
    }


}
