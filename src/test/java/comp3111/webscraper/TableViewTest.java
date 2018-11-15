package comp3111.webscraper;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.testfx.assertions.api.Assertions.assertThat;

public class TableViewTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new WebScraperApplication().start(stage);
        stage.requestFocus();
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
