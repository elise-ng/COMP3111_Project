package comp3111.webscraper;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.testfx.assertions.api.Assertions.assertThat;

public class RefineTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new WebScraperApplication().start(stage);
        stage.requestFocus();
    }

    @Test
    public void testRefineWithoutSearch() {
        clickOn("#textFieldKeyword").write("PS4");
        clickOn(lookup("Refine").queryButton());
        assertThat(lookup("#textAreaConsole").queryAs(TextArea.class)).hasText("Please perform a search first before refining");
    }

    @Test
    public void testRefine() {
        clickOn("#textFieldKeyword").write("PS4");
        clickOn(lookup("Go").queryButton());
        try {
            WaitForAsyncUtils.waitFor(60, TimeUnit.SECONDS,
                    () -> !lookup("#textAreaConsole").queryAs(TextArea.class).getText().isEmpty());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        lookup("#textFieldKeyword").queryTextInputControl().clear();
        clickOn("#textFieldKeyword").write("Pro");
        clickOn(lookup("Refine").queryButton());
        ObservableList list = lookup("#tableView").queryTableView().getItems();
        for(Object item : list) {
            Assert.assertTrue(((Item) item ).getTitle().contains("Pro"));
        }
    }
}
