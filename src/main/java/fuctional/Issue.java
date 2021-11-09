package fuctional;

import buffer.PreALU1;
import buffer.PreALU2;
import buffer.PreIssue;
import instructions.Command;
import instructions.R2IInstruction;
import instructions.R3Instruction;
import scoreboard.Scoreboard;
import util.TypeGetter;

import java.util.LinkedList;
import java.util.List;

public class Issue {
    public static final String name = "Issue";
    public static void issue(){
        int[] wannaUse = new int[32];    //记录是否有指令试图读取寄存器，用以检查WAR
        List<Command> issuable = PreIssue.getInstructionPool();
        boolean hasPreStore = false;
        boolean issuedALU2=false;
        boolean issuedALU1 = false;
        List<Command> shouldRemove = new LinkedList<>();  //被发射的指令放入此列表，后续使用一个循环从PreIssue中删除对应指令
        for(Command c : issuable){
            if (c.getName().equals("SW")){
                R2IInstruction r2 = (R2IInstruction) c;
                wannaUse[r2.getRs()]++;
                wannaUse[r2.getRt()]++;
            }else if(TypeGetter.is2R(c)){
                R2IInstruction r2 = (R2IInstruction) c;
                wannaUse[r2.getRs()]++;
            }else {
                R3Instruction r3 = (R3Instruction) c;
                wannaUse[r3.getRs()]++;
                wannaUse[r3.getRt()]++;
            }
            if (TypeGetter.useALU1(c)&&!issuedALU1&&!PreALU1.isFull()){
                R2IInstruction r2 = (R2IInstruction) c;
                if (c.getName().equals("LW")&&Scoreboard.isIssuableR2I(c)&&!hasPreStore&&(wannaUse[r2.getRt()]==0||(wannaUse[r2.getRt()]==1&&r2.getRt()==r2.getRs()))){
                    Scoreboard.toWrite(r2.getRs(),"ALU1");
                    wannaUse[r2.getRs()]--;
                    PreALU1.insertCommand(c);
                    issuedALU1=true;
                    shouldRemove.add(c);
                }else if(c.getName().equals("SW")){
                    if (!hasPreStore&&Scoreboard.isIssuableR2I(c)){
                        wannaUse[r2.getRs()]--;
                        wannaUse[r2.getRt()]--;
                        PreALU1.insertCommand(c);
                        issuedALU1=true;
                        shouldRemove.add(c);
                    }else hasPreStore = true;
                }
            }else if(!issuedALU2&&!PreALU2.isFull()){
                if(TypeGetter.is2R(c)){
                    R2IInstruction r2 = (R2IInstruction) c;
                    if(Scoreboard.isIssuableR2I(c)&&(wannaUse[r2.getRt()]==0||(wannaUse[r2.getRt()]==1&&r2.getRt()==r2.getRs()))){
                        Scoreboard.toWrite(r2.getRt(),"ALU2");
                        wannaUse[r2.getRs()]--;
                        PreALU2.insertCommand(c);
                        issuedALU2=true;
                        shouldRemove.add(c);
                    }
                }
                else{
                    R3Instruction r3 = (R3Instruction) c;
                    if(Scoreboard.isIssuableR3(c)&&(
                            wannaUse[r3.getRd()]==0||
                                    (wannaUse[r3.getRd()]==1&&(r3.getRd()==r3.getRt()||r3.getRd()==r3.getRs()))||
                                    (wannaUse[r3.getRd()]==2&&(r3.getRd()==r3.getRs()&&r3.getRd()==r3.getRt()))
                            )){
                        Scoreboard.toWrite(r3.getRd(),"ALU2");
                        wannaUse[r3.getRt()]--;
                        wannaUse[r3.getRs()]--;
                        PreALU2.insertCommand(c);
                        issuedALU2=true;
                        shouldRemove.add(c);
                    }
                }
            }
            if(issuedALU1&&issuedALU2) break;
        }
        for(Command c : shouldRemove){
            issuable.remove(c);
        }
    }
    

}
