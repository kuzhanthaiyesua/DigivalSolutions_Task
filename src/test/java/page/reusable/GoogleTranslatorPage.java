package page.reusable;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class GoogleTranslatorPage {

    WebDriver driver;

    public GoogleTranslatorPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method is used to click on Source language
     *
     * @param language
     */
    public void clickSourceLanguage(String language) {
        String name = this.getClass().getSimpleName();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(.,'" + language + "')]"))); // Give source language name
            List<WebElement> englishButton = driver.findElements(By.xpath("//button[contains(.,'" + language + "')]"));
            englishButton.get(0).click();
        } catch (Exception e) {
            System.out.println("Error in the method " + name + " because of " + e.getMessage());
        }
    }

    /**
     * This method is used to click on Converted language
     *
     * @param language
     */
    public void clickConvertToLanguage(String language) {
        String name = this.getClass().getSimpleName();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(.,'" + language + "')]"))); //Give converted language name
            List<WebElement> englishButton = driver.findElements(By.xpath("//button[contains(.,'" + language + "')]"));
            englishButton.get(1).click();
        } catch (Exception e) {
            System.out.println("Error in the method " + name + " because of " + e.getMessage());
        }
    }

    /**
     * This method is used to clear the text box of source language
     */
    public void clearSourceTextArea() {
        String name = this.getClass().getSimpleName();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(.,'source')]//textarea")));
            driver.findElement(By.xpath("//div[contains(.,'source')]//textarea")).clear();
        } catch (Exception e) {
            System.out.println("Error in the method " + name + " because of " + e.getMessage());
        }
    }

    /**
     * This method is used to input text in text box of Source language
     *
     * @param text
     */
    public void inputSourceTextArea(String text) {
        String name = this.getClass().getSimpleName();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(.,'source')]//textarea")));
            driver.findElement(By.xpath("//div[contains(.,'source')]//textarea")).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Error in the method " + name + " because of " + e.getMessage());
        }
    }

    /**
     * This method is used to get text from the text box of converted language
     *
     * @return
     */
    public String getConvertedText() {
        String name = this.getClass().getSimpleName();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-original-language='en']//span[@lang='es']")));
            return driver.findElement(By.xpath("//div[@data-original-language='en']//span[@lang='es']")).getText();
        } catch (Exception e) {
            System.out.println("Error in the method " + name + " because of " + e.getMessage());
        }
        return "";
    }


}
