package utilities;

import org.openqa.selenium.support.PageFactory;

public class ManagePages extends Base {

    public static void initNoga() {

        // דוגמא
//        homepage = PageFactory.initElements(driver, pageObjects.noga.HomePage.class);

        publicinquiries = PageFactory.initElements(driver, pageObjects.noga.Publicinquiries.class);

        requestForCetificationSii = PageFactory.initElements(driver, pageObjects.noga.RequestForCetification_Sii.class);






    }
}
