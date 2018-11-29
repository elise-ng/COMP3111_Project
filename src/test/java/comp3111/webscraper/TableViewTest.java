package comp3111.webscraper;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TableViewTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new WebScraperApplication().start(stage);
        stage.setAlwaysOnTop(true);
    }

    @Test
    public void testTableCellOpenWebsiteOnClick() {
        clickOn("#textFieldKeyword").write("PS4");
        clickOn(lookup("Go").queryButton());
        try {
            WaitForAsyncUtils.waitFor(60, TimeUnit.SECONDS,
                    () -> !lookup("#textAreaConsole").queryAs(TextArea.class).getText().isEmpty());
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        clickOn((Node) lookup(".tab-pane > .tab-header-area > .headers-region > .tab").nth(2).query());
        moveTo((Node) lookup("#tableColumnURL").query());
        moveBy(0, 25);
        clickOn();
    }
}
