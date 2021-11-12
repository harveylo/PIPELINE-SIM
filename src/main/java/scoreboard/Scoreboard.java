package scoreboard;

import buffer.*;
import functional.*;
import instructions.Command;
import instructions.R2IInstruction;
import instructions.R3Instruction;
import instructions.cat1.*;
import util.MemoryData;
import util.Register;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Scoreboard {
//    private static final Map<Command,String> instructionStatus = new HashMap<>();
    private static final Map<Integer,String> registerStatus = new HashMap<>();
//    private static final Map<String,FunctionUnitStatus> functionalStatus = new HashMap<>();
    private static final List<Integer> toReleaseList = new LinkedList<>();
    private static boolean over = false;

    public static void setOver(){
        over = true;
    }


    public static void initialize(){
        for(int i = 0;i<32;i++){
            registerStatus.put(i, Register.VACANT);
        }
//        functionalStatus.put(IF.name,new FunctionUnitStatus());
//        functionalStatus.put(Issue.name,new FunctionUnitStatus());
//        functionalStatus.put(ALU1.name,new FunctionUnitStatus());
//        functionalStatus.put(ALU2.name,new FunctionUnitStatus());
//        functionalStatus.put(MEM.name,new FunctionUnitStatus());
//        functionalStatus.put(WB.name,new FunctionUnitStatus());
    }
    public static void toWrite(int index,String fu){
        registerStatus.put(index,fu);
    }
    public static boolean nextCycle(){
        Register.initialBuffer();
        WB.writeBack();
        MEM.MEMAccess();
        ALU1.compute();
        ALU2.compute();
        Issue.issue();
        IF.fetchInstruction();
        Register.updateByBuffer();
        Register.updateByBuffer();
        PostALU2.consume();
        PostMEM.consume();
        PreALU1.consume();
        PreALU2.consume();
        PreIssue.consume();
        PreMEM.consume();
        releaseRegisters();
        return !over;
    }
    public static void toRelease(int register){
        toReleaseList.add(register);
    }

    public static void releaseRegisters(){
        for (int i : toReleaseList){
            registerStatus.put(i,Register.VACANT);
        }
        toReleaseList.clear();
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

    public static void printCycle(int cycle){
        System.out.println("--------------------");
        System.out.println("Cycle:"+cycle);
        System.out.println();
        System.out.println("IF Unit:");
        System.out.println("\tWaiting Instruction: "+(IF.susCommand==null?"":"["+IF.susCommand.getInstruction()+"]"));
        System.out.println("\tExecuted Instruction: "+(IF.exeCommand==null?"":"["+IF.exeCommand.getInstruction()+"]"));
        System.out.println("Pre-Issue Queue:");
        List<Command> commandList = PreIssue.getInstructionPool();
        printQueue(commandList,4);
        System.out.println("Pre-ALU1 Queue:");
        commandList = PreALU1.getPool();
        printQueue(commandList,2);
        System.out.println("Pre-MEM Queue:"+(PreMEM.isEmpty()?"":" ["+PreMEM.peek().getInstruction()+"]"));
        System.out.println("Post-MEM Queue:"+(PostMEM.isEmpty()?"":" ["+PostMEM.peek().getInstruction()+"]"));
        System.out.println("Pre-ALU2 Queue:");
        commandList = PreALU2.getPool();
        printQueue(commandList,2);
        System.out.println("Post-ALU2 Queue:"+(PostALU2.isEmpty()?"":" ["+PostALU2.peek().getInstruction()+"]"));
        System.out.println();
        System.out.println("Registers");
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<4;i++){
            sb.delete(0,sb.length());
            sb.append('R').append(i*8<10?"0":"").append(i*8).append(':');
            for(int j = i*8;j<(i+1)*8;j++){
                sb.append('\t').append(Register.getRegisterValue(j));
            }
            System.out.println(sb.toString());
        }
        System.out.println();
        System.out.println("Data");
        int count = MemoryData.endAddress-MemoryData.startAddress+4;
        count/=32;
        int address = MemoryData.startAddress;
        for(int i = 0;i<count;i++){
            sb.delete(0,sb.length());
            sb.append(address).append(':');
            for(int j =0 ;j<8;j++){
                sb.append('\t').append(MemoryData.loadData(address));
                address+=4;
            }
            System.out.println(sb.toString());
        }
    }
    private static void printQueue(List<Command> commandList,int size){
        for(int i = 0;i<size;i++){
            System.out.println("\tEntry "+i+":"+(i+1>commandList.size()?"":" ["+commandList.get(i).getInstruction()+"]"));
        }
    }
}


//class FunctionUnitStatus{
//    boolean busy = false;
//    String op;
//    int fi;
//    int fj;
//    int fk;
//    String qj;
//    String qk;
//    boolean rj;
//    boolean rk;
//}