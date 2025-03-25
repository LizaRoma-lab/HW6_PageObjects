import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {

        registrationPage.openPage()
                .setFirstName("Veronika")
                .setLastName("Romanova")
                .setEmail("nika@romanova.com")
                .setGender("Female")
                .setUserNumber("79871234567")
                .setDateOfBirth("22","July","2005")
                .setSubjects("Math")
                .setHobbies("Reading")
                .setPicture("cat.jfif")
                .setAddress("Some street 1")
                .setState("Haryana")
                .setCity("Panipat");

        registrationPage.checkResult("Student Name", "Veronika Romanova")
                .checkResult("Student Email", "nika@romanova.com")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "7987123456")
                .checkResult("Date of Birth", "22 July,2005")
                .checkResult("Subjects", "Maths")
                .checkResult("Hobbies", "Reading")
                .checkResult("Picture", "cat.jfif")
                .checkResult("Address", "Some street 1")
                .checkResult("State and City", "Haryana Panipat");
    }

    @Test
    void successfulMinRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Veronika")
                .setLastName("Romanova")
                .setGender("Female")
                .setUserNumber("79871234567")
                .setSubmit();

        registrationPage.checkResult("Student Name", "Veronika Romanova")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "7987123456")
                .checkResult("Date of Birth", "25 March,2025");
    }

    @Test
    void unsuccessfulRegistrationWithoutGenderTest() {

        registrationPage.openPage()
                .setFirstName("Veronika")
                .setLastName("Romanova")
                .setUserNumber("79871234567")
                .setSubmit();

        registrationPage.visibleFirstName();
    }

}
