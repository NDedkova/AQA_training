import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BeforeAndAfter {

    @BeforeEach
    public void setUp() {

        Configuration.startMaximized = true;
    }

    @AfterEach
    public void tearDown(){

        Selenide.closeWebDriver();
    }
}
