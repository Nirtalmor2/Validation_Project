package Sanity;

import com.google.common.util.concurrent.Uninterruptibles;
import jdk.jfr.Description;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.WebFlows;

import java.util.concurrent.TimeUnit;

@Listeners(utilities.Listeners.class)
public class NogaWeb extends CommonOps {


    @Test(description = "בדיקת ולידציות")
    @Description("בדיקת ולידציות")
    public void test01_Validation() {
        driver.get(getData("URL_Noga"));
        Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
        WebElement mailField = publicinquiries.mail_field;
        WebElement sendButton = publicinquiries.sendButton;
        WebElement mailErrorMessage = publicinquiries.mail_errorMassage;
        WebFlows.validation("Validation Results", "publicinquiries.xlsx", mailField, sendButton, mailErrorMessage);
    }
}