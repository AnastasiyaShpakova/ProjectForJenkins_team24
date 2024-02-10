import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@Tag("FirstTestForJenkins")
public class FillFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @Test
    void successfulFillFormTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть форму", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");

            $(".fc-button-label").shouldHave(text("Consent"));
            $(".fc-button-label").click();
        });

        step("Заполнить форму тестовыми данными", () -> {
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
        });
        step("Проверить результаты заполнения формы", () -> {
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
        });

        //{
        //Configuration.holdBrowserOpen = true;
        //}
    }
}
