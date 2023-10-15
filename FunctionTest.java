import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {

    static String inputDIR = "Input/";
    static String fileExtension = ".s";
    static String File = "TEST";
    static String InputString = FileOperator.FileToString(inputDIR + File + fileExtension);

    Assembler as = new Assembler(InputString);

    @Test
    public void AddZeroBitsTest(){
        assertEquals("0001" , Assembler.addZeroBits("1",4));
        assertEquals("0011" , Assembler.addZeroBits("11",4));
        assertEquals("0111" , Assembler.addZeroBits("111",4));
        assertEquals("1111" , Assembler.addZeroBits("1111",4));
    }

    @Test
    public void isIntegerTest(){
        assertTrue(Assembler.isInteger("123"));
        assertFalse(Assembler.isInteger("ABC"));
    }

    @Test
    public void toIntegerTest(){
        assertEquals(123,Assembler.toInteger("123"));
    }

    @Test
    public void twoComplimentTest(){
        // 1 to -1
        assertEquals("1111111111111111", Assembler.twosCompliment("0000000000000001"));
        // 10 to -10
        assertEquals("1111111111110110", Assembler.twosCompliment("0000000000001010"));

        // reverse!!!
        // -1 to 1
        assertEquals("0000000000000001", Assembler.twosCompliment("1111111111111111"));
    }

    @Test
    public void isInstructionTest(){
//        System.out.println(Assembler.);
        assertTrue(Assembler.isInstruction("add"));
        assertTrue(Assembler.isInstruction("nand"));
        assertTrue(Assembler.isInstruction("lw"));
        assertTrue(Assembler.isInstruction("sw"));
        assertTrue(Assembler.isInstruction("beq"));
        assertTrue(Assembler.isInstruction("jalr"));
        assertTrue(Assembler.isInstruction("noop"));
        assertTrue(Assembler.isInstruction("halt"));
        assertTrue(Assembler.isInstruction(".fill"));

        assertFalse(Assembler.isInstruction("mul"));
        assertFalse(Assembler.isInstruction("sub"));
        assertFalse(Assembler.isInstruction("div"));
    }

    @Test
    public void parseLineToDataTest(){
        as.parseLineToData();
        System.out.println(" " + InputString);
        System.out.println("First Line  : " + as.Data_list);
        as.parseLineToData();
        System.out.println("Second Line : " + as.Data_list);
    }

    @Test
    public void computeMachineCode(){
        System.out.println(" " + InputString);
        List<String> bin = as.computeToMachineCode();
        List<String> dec = Assembler.binaryToDecimal(bin);
        System.out.println(bin);
        System.out.println(dec);

        assertEquals(dec, Assembler.binaryToDecimal(bin));
        assertEquals(bin, Assembler.decimalToBinary(dec));
    }

}
