import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FillFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void successfulFillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Anastasiya");
        $("#lastName").setValue("Shpakova");
        $("#userEmail").setValue("test1@bk.ru");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("9991002030");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1987");
        $(".react-datepicker__day--022").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("nature2.jpg");
        $("#currentAddress").setValue("Testing 12-35");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Anastasiya Shpakova"));
        $(".table-responsive").shouldHave(text("test1@bk.ru"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("9991002030"));
        $(".table-responsive").shouldHave(text("22 September,1987"));
        $(".table-responsive").shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("nature2.jpg"));
        $(".table-responsive").shouldHave(text("Testing 12-35"));
        $(".table-responsive").shouldHave(text("Haryana Panipat"));
    }

    //{
    //Configuration.holdBrowserOpen = true;
    //}
}

