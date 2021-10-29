package ru.netology.web;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class RegistrationTest {



    @Test
    void shouldOrderDeliveryCard() {


        LocalDate date = LocalDate.now();
        date = date.plusDays(10);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateOfMeeting = date.format(formatters);

        open("http://localhost:9999");
        $("[class='input__control'][placeholder='Город']").setValue("Уфа");
        $("[class='input__control'][placeholder='Дата встречи']").setValue(dateOfMeeting);
        $("[class='input__control'][name='name']").setValue("Потапенко Алексей");
        $("[class='input__control'][name='phone']").setValue("+7 917 472 50 35");
        $("[data-test-id=agreement]").click();
        $(Selectors.byText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible, Duration.ofSeconds(15));




    }
}
