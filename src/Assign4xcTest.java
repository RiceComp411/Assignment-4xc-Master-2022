//import jam.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import java.io.StringReader;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Assign4xcTest {
  
  
  private void algolCheck(String name, String answer, String program) {
    Interpreter interp = new Interpreter(new StringReader(program));
    assertEquals(name, answer, interp.eval().toString());
  }
  
  @Test
  public void testSimpleRef() {
    String testName = "simpleRef";
    try {
      String output = "5";
      String input = "let ref x:=5; in x";
      algolCheck(testName, output, input );
    } catch (VirtualMachineError|Exception e) {
      e.printStackTrace(System.err);
      fail(testName + " threw " + e);
    }
  } //end
  
  @Test
  public void testRef1() {
    String testName = "ref1";
    try {
      String output = "true";
      String input = "let ref x:=5; ref y:=x; in { x <-6; x=y }";
      algolCheck(testName, output, input );
    } catch (VirtualMachineError|Exception e) {
      e.printStackTrace(System.err);
      fail(testName + " threw " + e);
    };
  } //end
  
  @Test
  public void testRef2() {
    String testName = "ref2";
    try {
      String output = "false";
      String input = "let ref x:=5; ref y:=5; in { x <-6; x=y }";
      algolCheck(testName, output, input );
    } catch (VirtualMachineError|Exception e) {
      e.printStackTrace(System.err);
      fail(testName + " threw " + e);
    }
  } //end
  
  
  @Test
  public void testSwap() {
    String testName = "swap";
    try {
      String output = "2500";
      String input = 
        "let ref x := 20;" +
        "    ref y := 5; " +
        "        z := 10; " +
        "     swap := map ref x, ref y to let z := x; in {x <- y; y <- z}; " +
        "in  { x <- (x + y); swap(x,y); swap(x,z); x * y * z}";
      algolCheck(testName, output, input);
    } catch (VirtualMachineError|Exception e) {
      e.printStackTrace(System.err);
      fail(testName + " threw " + e);
    }
  } //end of func
}
