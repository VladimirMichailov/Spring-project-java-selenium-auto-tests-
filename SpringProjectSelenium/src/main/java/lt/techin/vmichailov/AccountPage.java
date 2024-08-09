package lt.techin.vmichailov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BasePage{
    public AccountPage(WebDriver driver) {
        super(driver);
    }
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @FindBy (css = ".sc-fxwsqt")
    WebElement dashboardMessage;

    @FindBy (css ="[alt='Account icon']")
    WebElement accountIcon;

    @FindBy(css = ".sc-jXbVAB")
    WebElement logOutPopUpMessage;








    // wait for dashboard
    public void waitForDashboard(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector(".sc-fxwsqt")));
    }
    // Get text from dashboard
    public String dashboardMessage(){
        return dashboardMessage.getText();
    }
    // log out from account
    public void logoutFromAccount(){
        accountIcon.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector(".sc-jXbVAB")));
        logOutPopUpMessage.click();
    }
}
