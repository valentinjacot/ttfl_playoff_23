package ch.seleniumConsulting.selenium.Aufgabe;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ch.seleniumConsulting.selenium.Aufgabe.Allgemein.getChromeDriver;
import static ch.seleniumConsulting.selenium.Aufgabe.Allgemein.getFirefoxDriver;

public class trashTalk {

    @Test
    public void login(){
        LocalDate date = LocalDate.now();
        //System.out.println(date);
        String file_noms="noms_"+date.toString()+".csv";
        String file_picks="picks_"+date.toString()+".csv";

        WebDriver driver=getChromeDriver();
       // WebDriverManager.chromedriver().setup();
        //ChromeOptions chromeOptions = new ChromeOptions();
        //WebDriver driver = new ChromeDriver(chromeOptions);
        try{
            driver.get("https://fantasy.trashtalk.co/login/");
            if(!driver.findElements(By.xpath("//*[@id=\"sd-cmp\"]/div[2]/div[1]/div/div/div/div/div/div[2]/div[1]/button[1]/span")).isEmpty()){
                driver.findElement(By.xpath("//*[@id=\"sd-cmp\"]/div[2]/div[1]/div/div/div/div/div/div[2]/div[1]/button[1]/span")).click();
            }
            driver.findElement(By.xpath("//*[@id=\"contact-form\"]//input[@name=\"email\"]")).sendKeys("vjd.jako@gmail.com");
            driver.findElement(By.xpath("//*[@id=\"contact-form\"]//input[@name=\"password\"]")).sendKeys("mom58vol");

            new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"counter-number\"]/b[text()[contains(.,\"PetitPoney\")]]")));
            List<String> TeamNames = new ArrayList<>(Arrays.asList("PetitPoney","GREENCITY","EKIP667","Duplex","Saltbae","OrlandoFR","Mpotes","ALMR","Av+Parrain","LesGerard","GatineTeam","CAOM","TTFL+BDL","FC+NBAIX","NEURCHI10","TeamBaba"));
            List<String> noms = new ArrayList<String>();
            List<String> picks = new ArrayList<String>();
            noms.add(date.toString());
            picks.add(date.toString());
            for (String teamName:TeamNames) {
                driver.get("https://fantasy.trashtalk.co/?tpl=equipe&team="+teamName);
//                new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[text()[contains(.,\""+teamName.toUpperCase()+"\")]]")));
                noms.add("Equipe:"+teamName);
                picks.add("Equipe:"+teamName);
                for (int i = 1; i < 11; i++) {
                    noms.add(driver.findElement(By.xpath("//*[@id=\"classementTeamTabme\"]/tbody/tr["+i+"]/td[2]/b/a")).getAttribute("innerHTML"));
                    picks.add(driver.findElement(By.xpath("//*[@id=\"classementTeamTabme\"]/tbody/tr[" + i + "]/td[5]")).getAttribute("innerHTML"));
                }
            }
            System.out.println(picks);
            System.out.println(noms);

            FileWriter file = new FileWriter(file_noms);
            PrintWriter write = new PrintWriter(file);
            for (String name: noms){
                write.println(name);
            }
            write.close();

            file = new FileWriter(file_picks);
            write = new PrintWriter(file);
            for (String pick: picks){
                write.println(pick);
            }

            write.close();
        } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
            try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
            driver.quit();
        }



    }
}
