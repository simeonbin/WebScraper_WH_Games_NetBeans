
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simeon
 */
public class LoginWHTest {    
 WebDriver driver2;   
 String urlWHGames = "https://games.williamhill.com/#!/";

@Before
public void setup() throws MalformedURLException, UnknownHostException  {
    
    ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile myProfile = allProfiles.getProfile("SimBin");
        myProfile.setAcceptUntrustedCertificates(true);
        myProfile.setAssumeUntrustedCertificateIssuer(false);
        driver2 = new FirefoxDriver(myProfile);
        
     WebDriverWait wait = new WebDriverWait(driver2, 15);
     driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver2.navigate().to(urlWHGames);
        driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
}

@Test
public void testAuthenticationFailureWhenProvidingBadCredentials(){
     WebDriverWait wait;
     WebElement username;
    
    // Click on 'Login' button
        driver2.findElement(By.xpath("html/body/div[2]/div/div/div[2]/wf-header/header/div/div[2]/wf-user-button/div/a/span")).click();
           
    // Enter UserName
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[1]/input")).click();
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[1]/input")).sendKeys("");
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[1]/input")).sendKeys("simeonbin");
    
        wait = new WebDriverWait(driver2, 60);
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//*[@id='loginForm']/div[1]/input"), "simeonbin"));
   
       // Enter Password
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[4]/input")).sendKeys("");
        driver2.findElement(By.xpath("//*[@id='loginForm']/div[4]/input")).sendKeys("password");
        wait = new WebDriverWait(driver2, 60);        
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//*[@id='loginForm']/div[4]/input"), "password"));
       
        // Click on 'Login' button
        WebElement login = driver2.findElement(By.xpath("//*[@id='loginForm']/button"));
        wait = new WebDriverWait(driver2, 15);
        login.click();        
        wait = new WebDriverWait(driver2, 15);
        
        String errorMsg = driver2.findElement(By.xpath(" //*[@id='loginForm']/div[7]")).getText();
        System.out.println(errorMsg);

        assertTrue(errorMsg.startsWith("Sorry"));
    
        driver2.close();
    }   
    
}
