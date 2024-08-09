package lt.techin.vmichailov;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    @FindBy(css = ".fRZdu.sc-eDPFhE")
    WebElement signUpLink;

    // Registration form
    @FindBy(css = "input[name='name']")
    WebElement userNameInput;

    @FindBy(css = "input[name='email']")
    WebElement emailInput;

    @FindBy (css = "input[name='password']")
    WebElement passwordInput;

    @FindBy (css = "input[name='repeatPassword']")
    WebElement repeatPasswordInput;

    @FindBy (css = ".fCZwOC.sc-hZDzol")
    WebElement createAnAccountButton;

    // Login form
    @FindBy(css = "input[name='login']")
    WebElement loginEmailInput;

    @FindBy(css = "input[name='password']")
    WebElement loginPasswordInput;

    @FindBy(css = ".sc-cPiJYC")
    WebElement signInButton;



    // Go to registration page

    public void goToRegistrationPage(){
        signUpLink.click();
    }
    // Wait for registration page
    public void waitForRegistrationPage(){
        wait.until(ExpectedConditions.urlToBe("http://localhost:5173/registration"));
    }
    // Registration form methods
    // Send first name
    public void inputUserName(String userName) {
        userNameInput.clear();
        userNameInput.sendKeys(userName);
    }
     // Send email
     public void inputEmail(String email) {
         emailInput.clear();
         emailInput.sendKeys(email);
     }
    // Send password
    public void inputPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    // Repeat password
    public void inputRepeatPassword(String password) {
        repeatPasswordInput.clear();
        repeatPasswordInput.sendKeys(password);
    }
    // Click to create account
    public void clickCreateAccountButton(){
        createAnAccountButton.click();
    }

    public void writeValidDataToCSV(File file, List<String[]> validLoginData) {
        boolean fileExists = file.exists();

        try (FileWriter out = new FileWriter(file, true); // Open in append mode
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withRecordSeparator("\n"))) {

            // Add header only if the file does not exist or is empty
            if (!fileExists || Files.size(file.toPath()) == 0) {
                printer.printRecord("email", "password", "userName");
            }

            for (String[] record : validLoginData) {
                printer.printRecord((Object[]) record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInalidDataToCSV(File file, List<String[]> invalidLoginData) {
        boolean fileExists = file.exists();

        try (FileWriter out = new FileWriter(file, true); // Open in append mode
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withRecordSeparator("\n"))) {

            // Add header only if the file does not exist or is empty
            if (!fileExists || Files.size(file.toPath()) == 0) {
                printer.printRecord( "userName","email", "password", "repeatPassword");
            }

            for (String[] record : invalidLoginData) {
                printer.printRecord((Object[]) record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForLoginPage(){
        wait.until(ExpectedConditions.urlToBe("http://localhost:5173/"));
    }

    // Login inputs

    // Send email
    public void loginInputEmail(String email) {
        loginEmailInput.clear();
        loginEmailInput.sendKeys(email);
    }
    // Send password
    public void loginInputPassword(String password) {
        loginPasswordInput.clear();
        loginPasswordInput.sendKeys(password);
    }
    // Sign In
    public void clickToSignIn(){
        signInButton.click();
    }


}
