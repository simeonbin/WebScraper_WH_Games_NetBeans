
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import Webscraper_WH.countNumOfGames(WebDriver driver2);

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simeon
 */
public class NumOfWHGamesTest {
   
 WebDriver driver2;
   
 String urlWHGames = "https://games.williamhill.com/#!/";
 String urlWHGamesAZ = "https://games.williamhill.com/#!/lobby/az";
 int numOfGames = 375;

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
        driver2.navigate().to(urlWHGamesAZ);

        wait.until(ExpectedConditions.urlMatches(urlWHGamesAZ));

        wait = new WebDriverWait(driver2, 30);
        driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@Test
    public void testCountNumOfGames () throws NoSuchElementException {
    
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
              // return gameCount;
               int actualNumGames = gameCount;
            int expectedNumGames = numOfGames;
                assertEquals(actualNumGames, expectedNumGames);
    }
}   
           
