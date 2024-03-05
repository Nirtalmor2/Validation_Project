package pageObjects.noga;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Publicinquiries {


    @FindBy(how = How.ID, using = "communication-fullname")
    public WebElement firstName_field;
    @FindBy(how = How.CSS, using = "span[id='communication-fullname-errore']")
    public WebElement firstName_errorMassage;

    @FindBy(how = How.ID, using = "communication-phone")
    public WebElement phone_field;

    @FindBy(how = How.CSS, using = "//span[@id='communication-phone-errore']")
    public WebElement phone_errorMassage;


    @FindBy(how = How.ID, using = "communication-email")
    public WebElement mail_field;

    @FindBy(how = How.CSS, using = "#communication-email-errore")
    public WebElement mail_errorMassage;




    @FindBy(how = How.ID, using = "communication-submit")
    public WebElement sendButton;











}
