package ru.netology.web;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RegistrationTest {


    public String getDate(int days, String formattedDate) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(formattedDate));

    }

    @Test
    void shouldOrderDeliveryCard() {

        String DateOfMeeting = getDate(20, "dd.MM.yyyy");

        open("http://localhost:9999");
        $("[class='input__control'][placeholder='Город']").setValue("Уфа");
        $("[class='input__control'][placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[class='input__control'][placeholder='Дата встречи']").setValue(DateOfMeeting);
        $("[class='input__control'][name='name']").setValue("Потапенко Юрий");
        $("[class='input__control'][name='phone']").setValue("+7 917 472 50 35");
        $("[data-test-id=agreement]").click();
        $(Selectors.byText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("div.notification__content").shouldBe(exactText("Встреча успешно запланирована на " + DateOfMeeting));


    }
}
