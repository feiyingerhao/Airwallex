import calculator.MyCalculator;
import org.junit.Test;

/**
 * Test for [MyCalculator]
 */
public class CalculatorTest {
    @Test
    public void example1() {
        MyCalculator myCalculator = new MyCalculator();
        System.out.println("--------start example1--------");
        myCalculator.doOperation("5 2");
    }

    @Test
    public void example2() {
        MyCalculator myCalculator = new MyCalculator();
        System.out.println("--------start example2--------");
        myCalculator.doOperation("2 sqrt");
        myCalculator.doOperation("clear 9 sqrt");
    }

    @Test
    public void example3() {
        MyCalculator myCalculator = new MyCalculator();
        System.out.println("--------start example3--------");
        myCalculator.doOperation("5 2 -");
        myCalculator.doOperation("3 -");
        myCalculator.doOperation("clear");
    }

    @Test
    public void example4() {
        MyCalculator myCalculator = new MyCalculator();
        System.out.println("--------start example4--------");
        myCalculator.doOperation("5 4 3 2");
        myCalculator.doOperation("undo undo *");
        myCalculator.doOperation("5 *");
        myCalculator.doOperation("undo");
    }

    @Test
    public void example5() {
        MyCalculator myCalculator = new MyCalculator();
        System.out.println("--------start example5--------");
        myCalculator.doOperation("7 12 2 /");
        myCalculator.doOperation("*");
        myCalculator.doOperation("4 /");
    }

    @Test
    public void example6() {
        MyCalculator myCalculator = new MyCalculator();
        System.out.println("--------start example6--------");
        myCalculator.doOperation("1 2 3 4 5");
        myCalculator.doOperation("*");
        myCalculator.doOperation("clear 3 4 -");
    }

    @Test
    public void example7() {
        MyCalculator myCalculator = new MyCalculator();
        System.out.println("--------start example7--------");
        myCalculator.doOperation("1 2 3 4 5");
        myCalculator.doOperation("* * * *");
    }

    @Test
    public void example8() {
        MyCalculator myCalculator = new MyCalculator();
        System.out.println("--------start example8--------");
        myCalculator.doOperation("1 2 3 * 5 + * * 6 5");
    }
}
