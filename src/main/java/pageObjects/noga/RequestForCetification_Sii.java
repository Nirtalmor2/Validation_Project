package pageObjects.noga;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RequestForCetification_Sii {

    // דוגמא

    @FindBy(how = How.XPATH, using = "//input[@id='bb6c9b9e-6f65-4645-f2e0-5e1d625452a1']")
    public WebElement mail_field;
    @FindBy(how = How.XPATH, using = "//body/main[@id='content']/main[@class='content-main']/div[@id='skip-nav-content']/div[@class='container ng-scope']/div[@class='umbracoGrid']/div[@class='umb-grid']/div/div[@class='container']/div[@id='umbraco_form_7a5a4444d94246f8813ab3c2fb8d8b24']/form[@class='ng-pristine ng-valid']/div[@class='umbraco-forms-page form-hadracha']/div[@id='1b914166-ab1c-47f8-fcf2-2af234576414']/fieldset/div[@class='row-fluid row']/div[1]/div[2]/div[1]/span[1]")
    public WebElement mail_errorMassage;

    @FindBy(how = How.NAME, using = "__next")
    public WebElement sendButton;



}
