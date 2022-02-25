import java.util.StringTokenizer;
import junit.framework.TestCase;
import java.io.*;

public class Assign3Test extends TestCase {

  public Assign3Test (String name) { super(name); }
  
  /**
   * The following 9 check methods create an interpreter object with the
   * specified String as the program, invoke the respective evaluation
   * method (valueValue, valueName, valueNeed, etc.), and check that the 
   * result matches the (given) expected output.  
   */
 
  private void evalCheck(String name, String answer, String program) {
      Interpreter interp = new Interpreter(new StringReader(program));
      assertEquals(name, answer, interp.rightEval().toString());
  }

  public void testNumberP() {
    try {
      String output = "number?";
      String input = "number?";
      evalCheck("numberP", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("numberP threw " + e);
    }
  } //end of func 

  public void testMathOp() {
    try {
 String output = "18";
      String input = "2 * 3 + 12";
      evalCheck("mathOp", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("mathOp threw " + e);
    }
  } //end of func
  
  public void testParseException() {
    try {
      String output = "haha";
      String input = " 1 +";
      evalCheck("parseException", output, input );

         fail("parseException did not throw ParseException exception");
      } catch (ParseException e) {   
         //e.printStackTrace();
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("parseException threw " + e);
    }
  } //end of func

  public void testEvalException() {
    try {
      String output = "mojo";
      String input = "1 + number?";
      evalCheck("evalException", output, input );

         fail("evalException did not throw EvalException exception");
      } catch (EvalException e) {   
         //e.printStackTrace();
      
    } catch (Exception e) {
      e.printStackTrace();
      fail("evalException threw " + e);
    }
  } //end of func 

  public void testAppend() {
    try {
      String output = "(1 2 3 1 2 3)";
      String input = "let Y    := map f to              let g := map x to f(map z1,z2 to (x(x))(z1,z2));     in g(g);  APPEND := map ap to            map x,y to               if x = empty then y else cons(first(x), ap(rest(x), y)); l      := cons(1,cons(2,cons(3,empty))); in (Y(APPEND))(l,l)";
      evalCheck("append", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("append threw " + e);
    }
  } //end of func
  
  public void testLetRec() {
    try {
      String output = "(1 2 3 1 2 3)";
      String input = "let append :=       map x,y to          if x = empty then y else cons(first(x), append(rest(x), y));    l      := cons(1,cons(2,cons(3,empty))); in append(l,l)";
      evalCheck("letRec", output, input );

    } catch (Exception e) {
      e.printStackTrace();
      fail("letRec threw " + e);
    }
  } //end of func
  
  public void testForwardRef() {
    try {    
      String output = "Error";
      String input = "let a := a; in a";
      evalCheck("[0.50] forwardRef", output, input);
      fail("[0.50] forwardRef did not throw EvalException");
    } 
    catch (EvalException e) {  /* Success! badLet1 threw a ParseException;  */  }
    catch (Exception e) { 
//      e.printStackTrace();
      fail("forwardRef threw Exception " + e + " rather than an EvalException"); 
    }
  }
}





