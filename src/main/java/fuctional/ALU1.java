package fuctional;

import buffer.PreALU1;
import buffer.PreMEM;
import instructions.Command;
import instructions.R2IInstruction;
import util.Register;

public class ALU1 {
    public static final String name = "ALU1";

    public static void compute(){
        Command c = PreALU1.popCommand();
        R2IInstruction r2 = (R2IInstruction) c;
        if(c.getName().equals("LW")){
            int preChange = Register.getRegisterValue(r2.getRt()); //保存 运行之前目标寄存器的值。
            c.run();
            int address = Register.getRegisterValue(r2.getRt()); //执行之后目标寄存器中的值是将要读取到寄存器的目标地址
            Register.setRegisterValue(r2.getRt(),preChange); // 还原目标寄存器的值。
            PreMEM.insertResult(new int[]{0,r2.getRt(),address});
        }
        else{
            int result = Register.getRegisterValue(r2.getRt());  //取得目标值，同时也是rt改变之前的值。
            c.run();
            int address = Register.getRegisterValue(r2.getRt());
            Register.setRegisterValue(r2.getRt(),result);
            PreMEM.insertResult(new int[]{1,address,result});
        }
    }

}
