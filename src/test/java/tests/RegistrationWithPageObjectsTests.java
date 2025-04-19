package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;


public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();


    @Test
    void successfulRegistrationTest() {

        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Veronika")
                .setLastName("Romanova")
                .setEmail("nika@romanova.com")
                .setGender("Female")
                .setUserNumber("79871234567")
                .setDateOfBirth("22", "July", "2005")
                .setSubjects("Math")
                .setHobbies("Reading")
                .setPicture("cat.jfif")
                .setAddress("Some street 1")
                .setState("NCR")
                .setCity("Delhi");

        registrationPage.checkResult("Student Name", "Veronika Romanova")
                .checkResult("Student Email", "nika@romanova.com")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "7987123456")
                .checkResult("Date of Birth", "22 July,2005")
                .checkResult("Subjects", "Maths")
                .checkResult("Hobbies", "Reading")
                .checkResult("Picture", "cat.jfif")
                .checkResult("Address", "Some street 1")
                .checkResult("State and City", "NCR Delhi");
    }

    @Test
    void successfulMinRegistrationTest() {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Veronika")
                .setLastName("Romanova")
                .setGender("Female")
                .setUserNumber("79871234567")
                .setSubmit();

        registrationPage.checkResult("Student Name", "Veronika Romanova")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "7987123456")
                .checkResult("Date of Birth", "19 April,2025");
    }

    @Test
    void unsuccessfulRegistrationWithoutGenderTest() {

        registrationPage.openPage()
                .removeBanners()
                .setFirstName("Veronika")
                .setLastName("Romanova")
                .setUserNumber("79871234567")
                .setSubmit();

        registrationPage.visibleFirstName();
    }

}
