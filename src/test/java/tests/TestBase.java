package tests;

import com.codeborne.selenide.Configuration;
import drivers.BrowserstackSelenideDriver;
import drivers.EmulatorMobileDriver;
import helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static config.Project.config;
import static helpers.AllureAttachments.getSessionId;

public class TestBase {

    private static final String DEVICE_HOST = config.deviceHost();

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        switch (DEVICE_HOST) {
            case "browserstack":
                Configuration.browser = BrowserstackSelenideDriver.class.getName();
                break;
            case "local":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                break;
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();

        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.pageSource();

        closeWebDriver();
        if (DEVICE_HOST.equals("browserstack")) {
            AllureAttachments.video(sessionId);
        }
    }
}
