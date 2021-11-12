package fuctional;

import buffer.PreIssue;
import instructions.Command;
import instructions.factories.CommandFactory;
import scoreboard.Scoreboard;
import util.MemoryCommand;
import util.ProgramCounter;
import util.TypeGetter;

public class IF {
    private static boolean stalled=false;
    public static final String name = "IF";
    private static boolean broke = false;

    public static Command susCommand;
    public static Command exeCommand;


    public static void setStall(boolean flag){
        stalled = flag;
    }

    public static boolean getStall(){
        return stalled;
    }

    public static void fetchInstruction(){
        if (broke) return;
        if(stalled) {
            if(susCommand.getName().equals("NOP")){
                exeCommand = susCommand;
                susCommand = null;
                stalled=false;
            }
            else if (Scoreboard.isBranchOk(susCommand)){
                susCommand.run();
                stalled=false;
                exeCommand = susCommand;
                susCommand = null;
            }
            return;
        }
        if(PreIssue.isFull()) return;
        susCommand = null;
        exeCommand = null;
        Command command1 = MemoryCommand.getCommand(ProgramCounter.getPC());
        if (handleCommand(command1)) return;
        ProgramCounter.advancePC(4);
        if(PreIssue.isFull()) return;
        Command command2 = MemoryCommand.getCommand(ProgramCounter.getPC());
        handleCommand(command2);
        ProgramCounter.advancePC(4);

    }

    private static boolean handleCommand(Command command){
        if(TypeGetter.isBranch(command)){
            if (Scoreboard.isBranchOk(command)) {
                command.run();
                exeCommand = command;
            }
            else {
                susCommand = command;
                stalled = true;
            }
            return true;
        }
        if (command.getName().equals("BREAK")){
            broke = true;
            exeCommand = command;
            Scoreboard.setOver();
            return true;
        }
        if (command.getName().equals("NOP")){
            if(exeCommand!=null&&exeCommand.getName().equals("NOP")){
                stalled = true;
                susCommand = command;
            }
            else
                exeCommand = command;
        }else {
            PreIssue.insert(command);
        }
        return false;
    }



}
