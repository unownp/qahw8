package np.qa.lesson9.tests;

import com.codeborne.selenide.Configuration;
import np.qa.lesson9.pages.components.FixedBanComponent;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl="https://demoqa.com";
    }

    FixedBanComponent fixedBanComponent=new FixedBanComponent();

}
