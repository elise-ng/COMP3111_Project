package comp3111.webscraper;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.assertions.api.Assertions.assertThat;

public class RefineTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new WebScraperApplication().start(stage);
    }

    @Test
    public void testRefineWithoutSearch() {
        clickOn("#textFieldKeyword").write("PS4");
        clickOn(lookup("Refine").queryButton());
        assertThat(lookup("#textAreaConsole").queryAs(TextArea.class)).hasText("Please perform a search first before refining");
    }
}
