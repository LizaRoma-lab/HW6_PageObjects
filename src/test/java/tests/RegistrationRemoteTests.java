package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class RegistrationRemoteTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }


    @Test
    @Tag("demoqa")
    void successfulRegistrationTest() {
        step("Open form", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });
        step("Fill form", () -> {
            $("#firstName").setValue("Veronika");
            $("#lastName").setValue("Romanova");
            $("#userEmail").setValue("nika@romanova.com");
            $(byText("Female")).click();
            $("#userNumber").setValue("79871234567");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption("2005");
            $(".react-datepicker__month-select").selectOption("July");
            $(".react-datepicker__day.react-datepicker__day--022").click();
            $("#subjectsInput").setValue("Math").pressEnter();
            $("#hobbies-checkbox-2").parent().$(byText("Reading")).click();
            $("#uploadPicture").uploadFromClasspath("cat.jfif");
            $("#currentAddress").setValue("Some street 1");
            $("#state").click();
            $(byText("Haryana")).click();
            $("#city").click();
            $(byText("Panipat")).click();
            $("#submit").click();
        });
        step("Verify results", () -> {
            $(".modal-title").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text("Veronika Romanova"));
            $(".table-responsive").shouldHave(text("nika@romanova.com"));
            $(".table-responsive").shouldHave(text("Female"));
            $(".table-responsive").shouldHave(text("7987123456"));
            $(".table-responsive").shouldHave(text("22 July,2005"));
            $(".table-responsive").shouldHave(text("Maths"));
            $(".table-responsive").shouldHave(text("Reading"));
            $(".table-responsive").shouldHave(text("cat.jfif"));
            $(".table-responsive").shouldHave(text("Some street 1"));
            $(".table-responsive").shouldHave(text("Haryana Panipat"));
        });


    }
}

