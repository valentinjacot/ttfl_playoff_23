package ch.seleniumConsulting.selenium.Aufgabe;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Allgemein {

    public static final String EVENT_BASE_URL = "https://selenium-consulting.ch/event/itByMW22/";
    public static final String TEAM_NAME = "Tarvos/";
    public static final int NUMBER_TEST_RUN = 1;
    public static String downloadFilepath = "C:\\Users\\vjacot\\Documents\\2. Trainings\\seleniumIntensivtraining-Workspace\\data";


    @Test
    public void ChromeTest() {

        // Driver Setup
        WebDriverManager.chromedriver().setup();
        //  Starten des Webdrivers ohne Chrome Extensions (Falls keine Adminrechte kommt sonst ein Popup)
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        try {
            // Auf die Seite Aufgabe Init 1 von Dragonia
            webDriver.get(EVENT_BASE_URL);

            Assert.assertEquals(webDriver.getTitle(), "Scoreboard");
        } finally {
            // Webdriver schliessen
            webDriver.quit();
        }
    }

    @Test
    public void FirefoxTest() {

        // Driver Setup
        WebDriverManager.firefoxdriver().setup();
        WebDriver webDriver = new FirefoxDriver();
        try {
            // Auf die Seite Aufgabe Init 1 von Dragonia
            webDriver.get(EVENT_BASE_URL);

            Assert.assertEquals(webDriver.getTitle(), "Scoreboard");
        } finally {
            // Webdriver schliessen
            webDriver.quit();
        }
    }

    @Test
    public void EdgeTest() {

        // Driver Setup
        WebDriverManager.edgedriver().setup();
        WebDriver webDriver = new EdgeDriver();
        try {
            // Auf die Seite Aufgabe Init 1 von Dragonia
            webDriver.get(EVENT_BASE_URL);

            Assert.assertEquals(webDriver.getTitle(), "Scoreboard");
        } finally {
            // Webdriver schliessen
            webDriver.quit();
        }
    }

    public static WebDriver getChromeDriver(){
        WebDriverManager.chromedriver().setup();
        //  Starten des Webdrivers ohne Chrome Extensions (Falls keine Adminrechte kommt sonst ein Popup)
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        //chromeOptions.addArguments("headless","--blink-settings=imagesEnabled=false");
        //chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        return webDriver;
    }
    public static WebDriver getChromeDriverDwld(){
        WebDriverManager.chromedriver().setup();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver= new ChromeDriver(options);
        return driver;

    }

    public static WebDriver getEdgeDriver(){
        WebDriverManager.edgedriver().setup();
        WebDriver webDriver = new EdgeDriver();
        return webDriver;
    }
    public static WebDriver getFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions ffopt= new FirefoxOptions();
        ffopt.addPreference("permissions.default.image",2);
        WebDriver webDriver = new FirefoxDriver(ffopt);
        return webDriver;
    }

}
