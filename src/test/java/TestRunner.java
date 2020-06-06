import org.testng.TestNG;

public class TestRunner {

    public static void main(String[] args) {
        TestNG testSuite = new TestNG();
        testSuite.setTestClasses(new Class[]{GoDevTestSuite.class});

        testSuite.run();
    }
}
