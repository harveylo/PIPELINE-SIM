package util;

import instructions.Command;
import instructions.factories.*;

import java.util.HashMap;
import java.util.Map;

public class CommandMap {
    private static final Map<Integer, CommandFactory> commandMap=new HashMap<>();
    static {
        commandMap.put(16,new JumpFactory());
        commandMap.put(17,new JumpRegisterFactory());
        commandMap.put(18,new BranchEqualFactory());
        commandMap.put(19, new BranchLessZeroFactory());
        commandMap.put(20,new BranchGreaterZeroFactory());
        commandMap.put(21,new BreakFactory());
        commandMap.put(22,new StoreWordFactory());
        commandMap.put(23,new LoadWordFactory());
        commandMap.put(24,new ShiftLeftLogicFactory());
        commandMap.put(25,new ShiftRightLogicFactory());
        commandMap.put(26,new ShiftRightArithFactory());
        commandMap.put(27,new NoOpFactory());
        commandMap.put(48,new AddWordFactory());
        commandMap.put(49,new SubtractWordFactory());
        commandMap.put(50,new MultiplyWordFactory());
        commandMap.put(51,new AndFactory());
        commandMap.put(52,new OrFactory());
        commandMap.put(53,new ExclusiveOrFactory());
        commandMap.put(54,new NotOrFactory());
        commandMap.put(55,new SetLessThanFactory());
        commandMap.put(56,new AddImmediateFactory());
        commandMap.put(57,new AndImmediateFactory());
        commandMap.put(58,new OrImmediateFactory());
        commandMap.put(59,new ExclusiveOrImmediateFactory());
    }
    public static Command getCommand(int opCode,int instruction){
        return commandMap.get(opCode).newCommand(instruction);
    }
}
