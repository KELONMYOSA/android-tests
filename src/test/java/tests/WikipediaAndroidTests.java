package tests;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WikipediaAndroidTests extends TestBase {

    @Test
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем содержание первой ссылки в Wikipedia")
    void firstLinkTest() {
        step("Пропускаем стартовый экран", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
        step("Ищем в поиске \"Saint Petersburg\"", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("Saint Petersburg");
        });
        step("Проверяем первую ссылку", () -> {
                $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(text("Saint Petersburg"));
                $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_description")).shouldHave(text("Federal city in Russia"));
        });
    }
}
