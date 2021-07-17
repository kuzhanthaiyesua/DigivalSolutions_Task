package test.functional;

import browser.utils.BrowserUtils;
import config.reader.ConfigReader;
import excel.reader.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.reusable.GoogleTranslatorPage;

public class TestScript extends BrowserUtils {
    static WebDriver driver;
    static GoogleTranslatorPage googletranslatepage;
    static ConfigReader configreader = new ConfigReader();
    static ExcelReader excelReader = new ExcelReader();

    @BeforeClass
    public static void launchBrowser() {
        String browser = configreader.getProperty("browser");
        driver = startBrowser(browser);
        googletranslatepage = new GoogleTranslatorPage(driver);
    }

    @BeforeMethod
    public void launchURL() {
        //Step 1
        //Launch any browser and navigate to google translate url  "https://translate.google.com/"
        driver.get(configreader.getProperty("url"));
    }

    @Test
    public void validateEnglishToSpanishTranslate() throws Exception {

        //Step 2
        //Select source language as "English" and converted language as "Spanish"
        googletranslatepage.clickSourceLanguage("English");
        googletranslatepage.clickConvertToLanguage("Spanish");

        //Step 3
        /*
        Pass below listed strings in the source language textbox and verify that the strings are converted to Spanish
            Testing
            Solutions
            Translate
            Definitions
            Automation
            Selenium
            Sample task
         */
        String[] englishText = excelReader.getTexts("Sheet1", "English");
        int totalRows = englishText.length;
        String[] actualText = new String[totalRows];
        for (int actual = 0; actual < englishText.length; actual++) {
            googletranslatepage.clearSourceTextArea();
            Thread.sleep(2000);
            googletranslatepage.inputSourceTextArea(englishText[actual]);
            Thread.sleep(2000);
            actualText[actual] = googletranslatepage.getConvertedText();
        }

        //Step 4
        //Verify that converted Spanish strings are correct with the testdata from Excelsheet and update the result
        String[] expectedText = excelReader.getTexts("sheet1", "Spanish");
        for (int compare = 0; compare < actualText.length; compare++) {
            int resultRow = compare + 1;
            int resultColumn = 2;
            if (actualText[compare].equals(expectedText[compare])) {
                excelReader.updateResult(resultRow, resultColumn, "sheet1", "Pass");
            } else {
                excelReader.updateResult(resultRow, resultColumn, "sheet1", "Fail");
            }
        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
