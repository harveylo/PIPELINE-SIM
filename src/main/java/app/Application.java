package app;

import disassembler.Disassembler;
import scoreboard.Scoreboard;
import util.ProgramCounter;

import java.io.*;

public class Application {
    public static void main(String[] args) {
        String inputPath;
        try{
            inputPath = args[0];
        }catch (Exception e){
            System.out.println("No input file specified, please select a input file");
            return;
        }
        try {
            Disassembler.disassemble(inputPath);
        } catch (IOException e) {
            System.out.println("Something wrong with the input file, please check your input");
            System.out.println(e.getMessage());
            return;
        }
        try {
            Disassembler.output();
        } catch (FileNotFoundException e) {
            System.out.println("Something wrong with the disassemble output");
            e.printStackTrace();
        }
        int cycleCount=1;
        ProgramCounter.setPC(256);
        Scoreboard.initialize();
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(System.getProperty("user.dir")+ File.separator+"simulation.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Something wrong with the sim output file");
            e.printStackTrace();
        }
        PrintStream original = System.out;
        System.setOut(ps);
        while(Scoreboard.nextCycle()&&cycleCount<100){
            Scoreboard.printCycle(cycleCount);
            cycleCount++;
        }
        Scoreboard.printCycle(cycleCount);
        System.setOut(original);
    }
}
