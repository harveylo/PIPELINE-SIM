package scoreboard;

import fuctional.*;
import instructions.Command;
import instructions.cat1.BranchEqual;
import instructions.cat1.BranchGreaterZero;
import instructions.cat1.BranchLessZero;
import instructions.cat1.JumpRegister;
import util.Register;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
    private static final Map<Command,String> instructionStatus = new HashMap<>();
    private static final Map<Integer,String> registerStatus = new HashMap<>();
    private static final Map<String,FunctionUnitStatus> functionalStatus = new HashMap<>();
    private static boolean over = false;

    public static void setOver(){
        over = true;
    }

    public static void initialize(){
        for(int i = 0;i<32;i++){
            registerStatus.put(i, Register.VACANT);
        }
        functionalStatus.put(IF.name,new FunctionUnitStatus());
        functionalStatus.put(Issue.name,new FunctionUnitStatus());
        functionalStatus.put(ALU1.name,new FunctionUnitStatus());
        functionalStatus.put(ALU2.name,new FunctionUnitStatus());
        functionalStatus.put(MEM.name,new FunctionUnitStatus());
        functionalStatus.put(WB.name,new FunctionUnitStatus());
    }
    public void nextCycle(){

    }

    public static boolean isBranchOk(Command command){
        if (command.getName().equals("J")) return true;
        if (command.getName().equals("JR")){
            JumpRegister jr = (JumpRegister)command;
            return registerStatus.get(jr.jumpRegister).equals(Register.VACANT);
        }
        if(command.getName().equals("BEQ")){
            BranchEqual be = (BranchEqual) command;
            return registerStatus.get(be.rs).equals(Register.VACANT)&&registerStatus.get(be.rt).equals(Register.VACANT);
        }
        if(command.getName().equals("BLTZ")){
            BranchLessZero bl = (BranchLessZero) command;
            return registerStatus.get(bl.rs).equals(Register.VACANT);
        }
        BranchGreaterZero bg = (BranchGreaterZero) command;
        return registerStatus.get(bg.rs).equals(Register.VACANT);
    }

//    public static boolean isIssuableLW(Command command){
//
//    }
}


class FunctionUnitStatus{
    boolean busy = false;
    String op;
    int fi;
    int fj;
    int fk;
    String qj;
    String qk;
    boolean rj;
    boolean rk;
}