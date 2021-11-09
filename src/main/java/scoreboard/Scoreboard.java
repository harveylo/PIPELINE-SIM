package scoreboard;

import fuctional.*;
import instructions.Command;
import instructions.R2IInstruction;
import instructions.R3Instruction;
import instructions.cat1.*;
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
    public static void toWrite(int index,String fu){
        registerStatus.put(index,fu);
    }
    public static void releaseR(int index){
        registerStatus.put(index,Register.VACANT);
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

    public static boolean isIssuableR2I(Command command){   //检查R2类型指令在issue时的WAW和RAW
        R2IInstruction r2i = (R2IInstruction) command;
        return registerStatus.get(r2i.getRs()).equals(Register.VACANT)&&registerStatus.get(r2i.getRt()).equals(Register.VACANT);
    }

    public static boolean isIssuableR3(Command command){ //检查R3类型指令在issue时的WAW和RAW
        R3Instruction r3 = (R3Instruction) command;
        return registerStatus.get(r3.getRd()).equals(Register.VACANT)&&registerStatus.get(r3.getRs()).equals(Register.VACANT)&&registerStatus.get(r3.getRt()).equals(Register.VACANT);
    }
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