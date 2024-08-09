package lt.techin.vmichailov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.NoSuchElementException;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RegistrationPageTest extends  BaseTest{

    // Registration using valid random login data
    @Test
    void registerAndLoginValidRandomDataAndGenerateCsv(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);


        // Generate user data
        RegistrationDataGenerator.generateValidUserData();

        // Set values for registration
        String randomUserName = RegistrationDataGenerator.getUserName();
        String randomEmail = RegistrationDataGenerator.getEmail();
        String randomPassword = RegistrationDataGenerator.getPassword();

        // Go to registration page
        registrationPage.goToRegistrationPage();

        // Input registration data
        registrationPage.inputUserName(randomUserName);
        registrationPage.inputEmail(randomEmail);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Safe login data to csv file
        List<String[]> validLoginData = new ArrayList<>();
        validLoginData.add(new String[]{randomEmail, randomPassword, randomUserName});
        String filePath = Paths.get("C:", "Users", "orevi", "Desktop", "spring project tests", "SpringProjectSelenium", "src", "test", "resources", "validLoginData.csv").toString();
        File csvFile = new File(filePath);

        registrationPage.writeValidDataToCSV(csvFile, validLoginData);

        // click to finish registration
        registrationPage.clickCreateAccountButton();
        registrationPage.waitForLoginPage();
        Assertions.assertEquals("http://localhost:5173/", driver.getCurrentUrl(), "web addresses after clicking registration button not match");

        // login using this registration credentials
        registrationPage.loginInputEmail(randomEmail);
        registrationPage.loginInputPassword(randomPassword);
        registrationPage.clickToSignIn();

        // confirm login
        accountPage.waitForDashboard();
        Assertions.assertTrue(accountPage.dashboardMessage().toLowerCase().contains(randomUserName.toLowerCase()), "dashboard message does not contain " + randomUserName);
    }

    //Login using generated csv file with valid generated during positive tests
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/validLoginData.csv", numLinesToSkip = 1)
    void loginUsingSavedCredentials(String email, String password, String userName){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // login using data from csv file
        registrationPage.loginInputEmail(email);
        registrationPage.loginInputPassword(password);
        registrationPage.clickToSignIn();

        // confirm login
        accountPage.waitForDashboard();
        Assertions.assertTrue(accountPage.dashboardMessage().toLowerCase().contains(userName.toLowerCase()), "dashboard message does not contain " + userName);
    }

    @Test
    void registrationUsingAlreadyUsedEmail(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);


        // Generate user data
        RegistrationDataGenerator.generateValidUserData();

        // Set values for registration
        String randomUserName = RegistrationDataGenerator.getUserName();
        String randomEmail = RegistrationDataGenerator.getEmail();
        String randomPassword = RegistrationDataGenerator.getPassword();

        // Go to registration page
        registrationPage.goToRegistrationPage();

        // Input registration data
        registrationPage.inputUserName(randomUserName);
        registrationPage.inputEmail(randomEmail);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // click to finish registration
        registrationPage.clickCreateAccountButton();
        registrationPage.waitForLoginPage();
        Assertions.assertEquals("http://localhost:5173/", driver.getCurrentUrl(), "web addresses after clicking registration button not match");

        // login using this registration credentials
        registrationPage.loginInputEmail(randomEmail);
        registrationPage.loginInputPassword(randomPassword);
        registrationPage.clickToSignIn();

        // confirm login
        accountPage.waitForDashboard();
        Assertions.assertTrue(accountPage.dashboardMessage().toLowerCase().contains(randomUserName.toLowerCase()), "dashboard message does not contain " + randomUserName);

        // logout
        accountPage.logoutFromAccount();

        // register using same data
        registrationPage.waitForLoginPage();

        // Go to registration page
        registrationPage.goToRegistrationPage();

        // Input registration data
        registrationPage.inputUserName(randomUserName);
        registrationPage.inputEmail(randomEmail);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // click to finish registration
        registrationPage.clickCreateAccountButton();

        // submit you are still on registration page
        Assertions.assertEquals("http://localhost:5173/registration", driver.getCurrentUrl(), "web addresses after clicking registration button not match");
    }

    // Register using too short username
    @Test
    void registerShortNameRandomDataAndGenerateCsv(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);


        // Generate user data
        RegistrationDataGenerator.generateUserDataShortUserName();

        // Set values for registration
        String randomUserName = RegistrationDataGenerator.getUserName();
        String randomEmail = RegistrationDataGenerator.getEmail();
        String randomPassword = RegistrationDataGenerator.getPassword();

        // Go to registration page
        registrationPage.goToRegistrationPage();

        // Input registration data
        registrationPage.inputUserName(randomUserName);
        registrationPage.inputEmail(randomEmail);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Safe invalid login data to csv file
        List<String[]> invalidLoginData = new ArrayList<>();
        invalidLoginData.add(new String[]{randomUserName,randomEmail, randomPassword, randomPassword});
        String filePath = Paths.get("C:", "Users", "orevi", "Desktop", "spring project tests", "SpringProjectSelenium", "src", "test", "resources", "invalidLoginData.csv").toString();
        File csvFile = new File(filePath);

        registrationPage.writeInvalidDataToCSV(csvFile, invalidLoginData);

        // click to finish registration
        registrationPage.clickCreateAccountButton();
        //registrationPage.waitForLoginPage();
        Assertions.assertNotEquals("http://localhost:5173/", driver.getCurrentUrl(), "web addresses after clicking registration button not match");

    }

    // Register using too long name
    @Test
    void registerLongNameRandomDataAndGenerateCsv(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);


        // Generate user data
        RegistrationDataGenerator.generateUserDataLongUserName();

        // Set values for registration
        String randomUserName = RegistrationDataGenerator.getUserName();
        String randomEmail = RegistrationDataGenerator.getEmail();
        String randomPassword = RegistrationDataGenerator.getPassword();

        // Go to registration page
        registrationPage.goToRegistrationPage();

        // Input registration data
        registrationPage.inputUserName(randomUserName);
        registrationPage.inputEmail(randomEmail);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Safe invalid login data to csv file
        List<String[]> invalidLoginData = new ArrayList<>();
        invalidLoginData.add(new String[]{randomUserName,randomEmail, randomPassword, randomPassword});
        String filePath = Paths.get("C:", "Users", "orevi", "Desktop", "spring project tests", "SpringProjectSelenium", "src", "test", "resources", "invalidLoginData.csv").toString();
        File csvFile = new File(filePath);

        registrationPage.writeInvalidDataToCSV(csvFile, invalidLoginData);

        // click to finish registration
        registrationPage.clickCreateAccountButton();
        Assertions.assertNotEquals("http://localhost:5173/", driver.getCurrentUrl(), "web addresses after clicking registration button not match");

    }

    // Register with empty username
    @Test
    void registerEmptyUserNameAndGenerateCsv(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);


        // Generate user data
        RegistrationDataGenerator.generateUserDataLongUserName();

        // Set values for registration
        String randomUserName = "";
        String randomEmail = RegistrationDataGenerator.getEmail();
        String randomPassword = RegistrationDataGenerator.getPassword();

        // Go to registration page
        registrationPage.goToRegistrationPage();

        // Input registration data
        registrationPage.inputUserName(randomUserName);
        registrationPage.inputEmail(randomEmail);
        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Safe invalid login data to csv file
        List<String[]> invalidLoginData = new ArrayList<>();
        invalidLoginData.add(new String[]{randomUserName,randomEmail, randomPassword, randomPassword});
        String filePath = Paths.get("C:", "Users", "orevi", "Desktop", "spring project tests", "SpringProjectSelenium", "src", "test", "resources", "invalidLoginData.csv").toString();
        File csvFile = new File(filePath);

        registrationPage.writeInvalidDataToCSV(csvFile, invalidLoginData);

        // click to finish registration
        registrationPage.clickCreateAccountButton();
        Assertions.assertNotEquals("http://localhost:5173/", driver.getCurrentUrl(), "web addresses after clicking registration button not match");

    }

    // Register with invalid email inputs from csv file
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/invalid_emails.csv", numLinesToSkip = 1)
    void registerInvalidEmailAndGenerateCsv(String email){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);


        // Generate user data
        RegistrationDataGenerator.generateValidUserData();

        // Set values for registration
        String randomUserName = RegistrationDataGenerator.getUserName();
        String randomPassword = RegistrationDataGenerator.getPassword();

        // Go to registration page
        registrationPage.goToRegistrationPage();

        // Input registration data
        registrationPage.inputUserName(randomUserName);

       // change null email value with empty string
        if (email == null){email = "";}
        registrationPage.inputEmail(email);

        registrationPage.inputPassword(randomPassword);
        registrationPage.inputRepeatPassword(randomPassword);

        // Safe invalid login data to csv file
        List<String[]> invalidLoginData = new ArrayList<>();
        invalidLoginData.add(new String[]{randomUserName,email, randomPassword, randomPassword});
        String filePath = Paths.get("C:", "Users", "orevi", "Desktop", "spring project tests", "SpringProjectSelenium", "src", "test", "resources", "invalidLoginData.csv").toString();
        File csvFile = new File(filePath);

        registrationPage.writeInvalidDataToCSV(csvFile, invalidLoginData);

        // click to finish registration
        registrationPage.clickCreateAccountButton();
        Assertions.assertNotEquals("http://localhost:5173/", driver.getCurrentUrl(), "web addresses after clicking registration button not match");

    }

    // Register with invalid email inputs from csv file
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/invalid_passwords.csv", numLinesToSkip = 1)
    void registerInvalidPasswordAndRepeatPasswordAndGenerateCsv(String password, String repeatPassword){
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Generate user data
        RegistrationDataGenerator.generateValidUserData();

        // Set values for registration
        String randomEmail = RegistrationDataGenerator.getEmail();
        String randomUserName = RegistrationDataGenerator.getUserName();

        // Go to registration page
        registrationPage.goToRegistrationPage();

        // Input registration data
        registrationPage.inputUserName(randomUserName);
        registrationPage.inputEmail(randomEmail);

        // Replace null with empty string
        if (password == null){ password = "";}
        registrationPage.inputPassword(password);
        if (repeatPassword == null){ repeatPassword = "";}
        registrationPage.inputRepeatPassword(repeatPassword);

        // Safe invalid login data to csv file
        List<String[]> invalidLoginData = new ArrayList<>();
        invalidLoginData.add(new String[]{randomUserName,randomEmail, password, repeatPassword});
        String filePath = Paths.get("C:", "Users", "orevi", "Desktop", "spring project tests", "SpringProjectSelenium", "src", "test", "resources", "invalidLoginData.csv").toString();
        File csvFile = new File(filePath);

        registrationPage.writeInvalidDataToCSV(csvFile, invalidLoginData);

        // click to finish registration
        registrationPage.clickCreateAccountButton();
        Assertions.assertNotEquals("http://localhost:5173/", driver.getCurrentUrl(), "web addresses after clicking registration button not match");


    }




    // Regression registration test using invalid data from csv file generated during negative tests
//    @ParameterizedTest
//    @CsvFileSource(files = "src/test/resources/invalidLoginData.csv", numLinesToSkip = 1)
//    void regressiveRegistrationTestInvalidData(String userName, String email, String password, String repeatPassword) {
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//
//        // Go to registration page
//        registrationPage.goToRegistrationPage();
//
//        // Input registration data
//        registrationPage.inputUserName(userName);
//        registrationPage.inputEmail(email);
//        registrationPage.inputPassword(password);
//        registrationPage.inputRepeatPassword(repeatPassword);
//
//        // click to finish registration
//        registrationPage.clickCreateAccountButton();
//        Assertions.assertNotEquals("http://localhost:5173/", driver.getCurrentUrl(), "web addresses after clicking registration button not match");
//
//    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/negative_login_scenarios.csv", numLinesToSkip = 1)
    void loginUsingInvalidCredentials(String email, String password, String userName){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        // login using data from csv file
        try {
        registrationPage.loginInputEmail(email);
        registrationPage.loginInputPassword(password);
        } catch (IllegalArgumentException e) {
            // We are catching IllegalArgumentException because of some of the  data we input is null,so this exception is expected.
        }
        registrationPage.clickToSignIn();

        // confirm not login
        try{
            Assertions.assertFalse(accountPage.dashboardMessage.isDisplayed(),
                    "Dashboard message is displayed, but it should not be.");
        } catch (NoSuchElementException e) {
            // If NoSuchElementException is thrown, it means the element is not present, which is the expected outcome
            Assertions.assertTrue(true, "Dashboard message element is not present, as expected.");
        }
    }


}




