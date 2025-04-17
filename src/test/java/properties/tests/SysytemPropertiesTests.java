package properties.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static java.lang.String.format;

public class SysytemPropertiesTests {

    @Test
    void systemProperties1Test() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser");
        System.out.println(browser);
    }

    @Test
    void systemProperties2Test() {
        System.setProperty("browser", "mozilla");
        String browser = System.getProperty("browser");
        System.out.println(browser);

    }

    @Test
    @Tag("property")
    void systemProperties3Test() {
        String browser = System.getProperty("browser", "mozilla");
        System.out.println(browser);
        //gradle property_test
        //mozilla

        //gradle property_test -Dbrowser=opera
        //opera
    }

    @Test
    @Tag("hello")
    void systemProperties4Test() {
        String name = System.getProperty("name", "default student");
        String message = format("Hello, %s!", name);
        System.out.println(message);
    }
}