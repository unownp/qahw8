package np.qa.lesson9.tests;

import np.qa.lesson9.pages.RegistrationFormPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormParametrizedTest extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @ParameterizedTest
    @CsvSource(value = {
            "Male",
            "Female",
            "Other"
    })
    void practiceFormCsvSourceTest(String gender) {
        registrationFormPage.openPage()
                .setFirstName(("Ololo"))
                .setLastName("Pyshpysh")
                .setEmail("realne@chake.com")
                .setGender(gender)
                .setUserNumber(1234567890)
                .calendarComponent.setDate(1991, "July", 1);

        registrationFormPage.setSubject("Hindi")
                .setHobbies("Sports")
                .uploadFile("src/test/resources/scale_1200.jpg")
                .setAddress("voruyubivay")
                .setState("Uttar")
                .setCity("Agra");

        $("#submit").click();

        checkTableFromGender(gender);

    }

    @ParameterizedTest
    @MethodSource("commonDateOfBirth")
    void practiceFormMethodSourceTest(int day,String month,int year) {
        registrationFormPage.openPage()
                .setFirstName(("Ololo"))
                .setLastName("Pyshpysh")
                .setEmail("realne@chake.com")
                .setGender("Male")
                .setUserNumber(1234567890)
                .calendarComponent.setDate(year, month, day);

        registrationFormPage.setSubject("Hindi")
                .setHobbies("Sports")
                .uploadFile("src/test/resources/scale_1200.jpg")
                .setAddress("voruyubivay")
                .setState("Uttar")
                .setCity("Agra");

        $("#submit").click();

        checkTableFromBirthDate(year,month,day);

    }

    void checkTableFromGender(String gender) {
        $("#example-modal-sizes-title-lg").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Ololo"), text("Pyshpysh"), text("realne@chake.com"),
                text(gender), text("1234567890"),
                text("01 July,1991"),
                text("Hindi"), text("Sports"),
                text("scale_1200.jpg"), text("voruyubivay"),
                text("Uttar Pradesh Agra"));

    }

    void checkTableFromBirthDate(int year,String month,int day) {
        $("#example-modal-sizes-title-lg").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Ololo"), text("Pyshpysh"), text("realne@chake.com"),
                text("Male"), text("1234567890"),
                text(day+" "+month+","+year),
                text("Hindi"), text("Sports"),
                text("scale_1200.jpg"), text("voruyubivay"),
                text("Uttar Pradesh Agra"));

    }

    static Stream<Arguments> commonDateOfBirth() {
        return Stream.of(
                Arguments.of(12, "July", 1990),
                Arguments.of(11, "August", 2001),
                Arguments.of(22, "November", 1984)
        );
    }
}
