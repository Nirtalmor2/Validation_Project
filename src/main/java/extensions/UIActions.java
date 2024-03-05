package extensions;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonOps;

import java.util.concurrent.TimeUnit;

public class UIActions extends CommonOps {

    @Step("Click On Element")
    public static void click(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }


    @Step("Update Text Element")
    public static void updateText(WebElement elem, String text) {
//        wait.until((ExpectedConditions.visibilityOf(elem)));
        elem.sendKeys(text);
    }


    @Step("Update Text Element As Human")
    public static void updateTextHuman(WebElement elem, String text) {
        wait.until((ExpectedConditions.visibilityOf(elem)));
        for (char ch : text.toCharArray()) {
            Uninterruptibles.sleepUninterruptibly(25, TimeUnit.MILLISECONDS);
            elem.sendKeys(ch + "");
        }
    }

    @Step("Get Text")
    public static String getText(WebElement elem) {
//        wait.until((ExpectedConditions.visibilityOf(elem)));
        return elem.getText();

    }




    @Step("Clear text")
    public static void clear(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.clear();

    }

    public static void selectDropdownOptionByText(WebElement dropdown, String optionText) {
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        Select dropDown = new Select(dropdown);
        dropDown.selectByVisibleText(optionText);
    }


}
