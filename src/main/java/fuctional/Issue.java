package fuctional;

import buffer.PreIssue;
import instructions.Command;
import util.TypeGetter;

import java.util.LinkedList;
import java.util.List;

public class Issue {
    public static final String name = "Issue";


    public static void issue(){
        List<Command> issuable = PreIssue.getInstructionPool();

    }

//    private static void issueALU1(List<Command> issuable){
//        int index = 0;
//        for(Command c : issuable){
//            if (TypeGetter.useALU1(c)){
//                if ()
//            }
//            index++;
//        }
//    }

}
