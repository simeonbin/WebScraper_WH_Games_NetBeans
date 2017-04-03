import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Simeon on 3/31/2017.
 */
public class WebScraper_WH {    
    static ArrayList<WebElement> gameEntries = new ArrayList<>(400);
    static String[] hr = new String[400];
    
    
    static int numOfGames = 375;

private static void joinWH(WebDriver driver2) {

// Find the link to sign-up/registration form
        WebElement link = driver2.findElement(By.xpath("//*[@id='signupForm']/header/p"));
        // Click the link
        link.click();
        
        //Click and Select the 'Mr' on 'Title' Form wrapper
        driver2.findElement(By.xpath("//*[@id='form-wrapper']/div[2]/fieldset[1]/div[1]/div/div/a[1]")).click();
        
        //Set FirstName Textbox
        driver2.findElement(By.xpath("//*[@id='input-firstName']")).sendKeys("Simeon");
        //Set LastName Textbox
        driver2.findElement(By.xpath("//*[@id='input-lastName']")).sendKeys("Biniatidis");
        
        //Set DOB DD MM YYYY Textboxes
        driver2.findElement(By.xpath("//*[@id='dobSelectorDay']")).sendKeys("23");
        driver2.findElement(By.xpath("//*[@id='dobSelectorMonth']")).sendKeys("11");
        driver2.findElement(By.xpath("//*[@id='dobSelectorYear']")).sendKeys("1971");
        
        //Set Mobile #
        driver2.findElement(By.xpath("//*[@id='input-mobile']")).sendKeys("+30-6944-205702");

        //Set Address OR PostCode Textbox
        driver2.findElement(By.xpath("//*[@id='input-search']")).sendKeys("54633");
        
        // Find the email form field
        WebElement email = driver2.findElement(By.xpath("//*[@id='input-email']"));        
        // Type the random email to the form
        email.sendKeys("simeonbin@gmail.com");
        
        //Set Username Textbox
        driver2.findElement(By.xpath("//*[@id='input-username']")).sendKeys("simeonbin");
      
        // Find the password form field
        WebElement password = driver2.findElement(By.xpath("//*[@id='input-password']"));
        // Type a password to the form. This needs not be unique.
        password.sendKeys("password");
        
        // Find 'Security Question' dropdown listbox
        Select dropdown1 = new Select(driver2.findElement(By.xpath("//*[@id='input-challenge']")));
        // I select its option 'My first car' 
        dropdown1.selectByVisibleText("My first car");
        // Set the Answer Textbox
        driver2.findElement(By.xpath("//*[@id='input-response']")).sendKeys("Oldsmobile");
        
       // Find 'Currency' dropdown listbox
        Select dropdown2 = new Select(driver2.findElement(By.xpath("//*[@id='input-challenge']")));
        // I select its option 'EUR - Euro' 
        dropdown2.selectByVisibleText("EUR - Euro");
        
        //Click the 'Set a deposit limit' link
        driver2.findElement(By.xpath("//*[@id='form-wrapper']/div[3]/fieldset/div[5]/div/a/span")).click();
        
        //Click and Select the 'Daily' on 'Choose a time period' Form wrapper
        driver2.findElement(By.xpath("//*[@id='form-wrapper']/div[3]/fieldset/div[5]/div/div/div/div[2]/div/div/a[1]")).click();
        
        // Find 'Your maximum daily deposit limit' dropdown listbox
        Select dropdown3 = new Select(driver2.findElement(By.xpath("//*[@id='input-ddl']")));
        // I select its option 'EUR - Euro' 
        dropdown2.selectByVisibleText("€65");
        
        // Submit the sign up form
        // Click on 'Agree and Join' button
        driver2.findElement(By.xpath("//*[@id='button-createAccount']")).submit();
        
        // Check the sign up succeeded by checking that the randomized
        // email appears in the website's header bar.
        (new WebDriverWait(driver2, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver2) {
                WebElement header = driver2.findElement(By.xpath("//*[@id='input-email']"));
                return header.getText().contains("simeonbin@gmail.com");
            }
        });
}

    private static void loginWH(WebDriver driver2) {
        WebDriverWait wait;
        
        // Click on 'Login' button
        driver2.findElement(By.xpath("html/body/div[2]/div/div/div[2]/wf-header/header/div/div[2]/wf-user-button/div/a/span")).click();
        
        // Enter UserName       
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[1]/input")).click();
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[1]/input")).sendKeys("");
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[1]/input")).sendKeys("simeonbin");
        wait = new WebDriverWait(driver2, 15);
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//*[@id='loginForm']/div[1]/input"), "simeonbin"));
 
        // Enter Password        
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[4]/input")).sendKeys("");
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[4]/input")).sendKeys("password");
        wait = new WebDriverWait(driver2, 60);        
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//*[@id='loginForm']/div[4]/input"), "password"));
        
        // Click on 'Sign In' button
        driver2.findElement(By.xpath("//*[@id='loginForm']/button")).click();

    }
        int countNumOfGames (WebDriver driver2) throws NoSuchElementException {
             WebElement game;            
                String[] hrefArray = new String[500];
                int gameCount = 1;
                String   hrefGame;
                String iStr;
                String gamesXPath1 = "html/body/div[2]/div/div/div[2]/div[3]/section/div/div/div/div[";
                String gamesXPath2 = "]/figure/a[1]";
                String gamesCountXPath = "html/body/div[2]/div/div/div[2]/div[3]/section/div/div/div";   

             game = driver2.findElement(By.xpath(gamesXPath1 + String.valueOf(1) + gamesXPath2));    
                 hrefGame = game.getAttribute("href");
                 hrefArray[0]= hrefGame;

               while (hrefGame != null) {
                    try {
                        gameCount++;
                        iStr = String.valueOf(gameCount);
                        game = driver2.findElement(By.xpath(gamesXPath1 + iStr + gamesXPath2)) ; 
                        hrefGame = game.getAttribute("href"); 
                        if (hrefGame != null) hrefArray[gameCount-1] = hrefGame;
                    }
                    catch (Exception e) {                
                        hrefGame = null;
                    }
                }        
               gameCount--;
               return gameCount;
        }
        
        
    private static void printGameInfo(WebDriver driver2) throws NoSuchElementException {
        WebElement game;            
        String[] hrefArray = new String[500];
        int gameCount = 1;
        String   hrefGame;
        String iStr;
        String gamesXPath1 = "html/body/div[2]/div/div/div[2]/div[3]/section/div/div/div/div[";
        String gamesXPath2 = "]/figure/a[1]";
        String gamesCountXPath = "html/body/div[2]/div/div/div[2]/div[3]/section/div/div/div";        
       
        for (int i = 1; i <= numOfGames; i++) {
            iStr = String.valueOf(i);
            try {
                game = driver2.findElement(By.xpath(gamesXPath1 + iStr + gamesXPath2));
                gameEntries.add(game);
            }
            catch (Exception e) {                  
                continue;
            }
        }

        for (int i=0; i < numOfGames;  i++) {
            try {
                hr[i] = gameEntries.get(i).getAttribute("href");
            }
            catch (Exception e) {               
                hr[i]= "Empty";
                continue;
            }
        }
        for (int i=0; i < numOfGames;  i++) {
            System.out.println("Game Entries: " + hr[i]);
        //    System.out.println("Game Entries: " + hrefArray[i]);
        }
        
        String  numOfGamesStr = String.valueOf (gameEntries.size());
        System.out.println();        
        System.out.println("There are " + numOfGamesStr + " Games in WH Games" );
    }

    public static void main(String[] args) throws IOException {

        String urlWHGames = "https://games.williamhill.com/#!/";
        String urlWHGamesAZ = "https://games.williamhill.com/#!/lobby/az";
        WebDriverWait wait;
        String valueOfCookie;

        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile myProfile = allProfiles.getProfile("SimBin");
        myProfile.setAcceptUntrustedCertificates(true);
        myProfile.setAssumeUntrustedCertificateIssuer(false);
        WebDriver driver2 = new FirefoxDriver(myProfile);    

        driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver2, 15);
        
        driver2.navigate().to(urlWHGames);

        Cookie stack = driver2.manage().getCookieNamed("STACK");
        if (stack != null) valueOfCookie = stack.getValue();

        driver2.navigate().to(urlWHGamesAZ);

        wait.until(ExpectedConditions.urlMatches(urlWHGamesAZ));
        wait = new WebDriverWait(driver2, 30);
        driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        printGameInfo(driver2);

    }
}

