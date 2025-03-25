package pages.components;

import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String day, String month, String year){
        $(".react-datepicker__year-select").selectOption("2005");
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__day.react-datepicker__day--022").click();

    }
}
