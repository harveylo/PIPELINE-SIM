package fuctional;

import buffer.PostALU2;
import buffer.PreALU2;
import instructions.Command;
import instructions.R2IInstruction;
import instructions.R3Instruction;
import util.Register;
import util.TypeGetter;

public class ALU2 {
    public static final String name = "ALU2";

    public static void compute(){
        if (PreALU2.isEmpty()) return;
        Command c = PreALU2.getCommand();
        if (TypeGetter.is2R(c)){
            R2IInstruction r2 = (R2IInstruction) c;
            int backup = Register.getRegisterValue(r2.getRt());
            c.run();
            int result = Register.getRegisterValue(r2.getRt());
            Register.setRegisterValue(r2.getRt(),backup);
            PostALU2.insert(new PostALU2.PostA2Entry(c,r2.getRt(),result));
        }else {
            R3Instruction r3 = (R3Instruction) c;
            int backup = Register.getRegisterValue(r3.getRd());
            c.run();
            int result = Register.getRegisterValue(r3.getRd());
            Register.setRegisterValue(r3.getRd(),backup);
            PostALU2.insert(new PostALU2.PostA2Entry(c,r3.getRd(),result));
        }
    }
}
