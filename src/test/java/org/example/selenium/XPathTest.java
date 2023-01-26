package org.example.selenium;
import static org.junit.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class XPathTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://manjaro.org/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void checkAmountOfAnchors() {
        List<WebElement> anchors = driver.findElements(By.xpath("//a"));
        System.out.println(anchors.toArray().length);
//        Na dzień 25.01.2023 - stwierdzono 90 tagów <a> na stronie,
        assertEquals(90,anchors.toArray().length);
    }

    @Test
    public void checkNumberOfImages() {
        List<WebElement> images = driver.findElements(By.xpath("//img"));
        System.out.println(images.toArray().length);
        assertEquals(16, images.toArray().length);
    }

    @Test
    public void goToEachLinkAndBack() {
        List<WebElement> anchors = driver.findElements(By.xpath("//a"));
        for (int i = 0; i <= anchors.toArray().length; i++) {
            if(!(anchors.get(i).getText().isEmpty())) {
                anchors.get(i).click();
                driver.navigate().back();
                anchors = driver.findElements(By.xpath("//a"));
            }
        }
        assertEquals(90, anchors.toArray().length);
    }

    @Test
    public void getAmountOfTextInputFields() {
        driver.get("https://manjaro.org/contact/");
        List<WebElement> textFields = driver.findElements(By.xpath("//input[@type='text' or @type='password']"));
        assertEquals(4, textFields.size());
    }
}
