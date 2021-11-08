package disassembler;

import instructions.Command;
import util.CommandMap;
import util.MemoryCommand;
import util.MemoryData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class Disassembler {
    private static final List<String> rawCode = new LinkedList<>();
    private static final List<String> rawData = new LinkedList<>();
    public static void disassemble(String inputPath) throws IOException {
        File inputFile = new File(inputPath);
        InputStreamReader read = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(read);
        String line = null;
        int address = 256;
        while((line=bufferedReader.readLine())!=null){
            long tem = Long.valueOf(line,2);
            int lineCode = (int) tem;
            int opCode = (lineCode&0xFC000000)>>>26;
            int instruction = (lineCode<<6)>>>6;
            Command command = CommandMap.getCommand(opCode,instruction);
            MemoryCommand.putCommand(address,command);
            address+=4;
            rawCode.add(line);
            if (command.getName().equals("BREAK")) break;
        }
        line = null;
        while ((line=bufferedReader.readLine())!=null){
            long tem = Long.valueOf(line,2);
            int lineDate = (int) tem;
            MemoryData.storeData(address,lineDate);
            address+=4;
            rawData.add(line);
        }
    }
    public static void output() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        PrintStream ps = new PrintStream(new FileOutputStream(System.getProperty("user.dir")+File.separator+"disassembly.txt"));
        PrintStream original = System.out;
        System.setOut(ps);
        int address = 256;
        for(String s : rawCode){
            sb.delete(0,sb.length());
            sb.append(s);
            sb.append('\t');
            sb.append(address);
            sb.append('\t');
            Command command = MemoryCommand.getCommand(address);
            String instruction = getInstruction(command);
            sb.append(instruction);
            address+=4;
            System.out.println(sb.toString());
        }
        MemoryData.startAddress = address;
        for(String s: rawData){
            sb.delete(0,sb.length());
            sb.append(s).append('\t').append(address).append('\t').append(MemoryData.loadData(address));
            System.out.println(sb.toString());
            MemoryData.endAddress=address;
            address+=4;
        }
        System.setOut(original);
    }
    public static String getInstruction(Command command){
        StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append(command.getName()).append(' ');
        List<String> list = command.getParameters();
        for(String p:list) commandBuilder.append(p).append(',').append(' ');
        if(list.size()!=0)
            commandBuilder.delete(commandBuilder.length()-2,commandBuilder.length());
        else commandBuilder.delete(commandBuilder.length()-1,commandBuilder.length());
        return commandBuilder.toString();
    }
}
