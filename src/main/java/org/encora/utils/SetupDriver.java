package org.encora.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.PageLoadStrategy;

public class SetupDriver {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/application.properties");
            prop.load(file);

            String browser = prop.getProperty("webdriver.browser");
            String driverPath = prop.getProperty("webdriver.path");
            String headless = prop.getProperty("webdriver.headless");
            String headlessDimension = prop.getProperty("webdriver.headless.dimension");
            String pageLoadStrategy = prop.getProperty("webdriver.pageload");

            switch (browser.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", driverPath);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setPageLoadStrategy(pageLoadStrategy.equals("EAGER") ? PageLoadStrategy.EAGER : PageLoadStrategy.NORMAL);
                    if (headless.equals("on")) {
                        chromeOptions.addArguments("--headless", headlessDimension);
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", driverPath);
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setPageLoadStrategy(pageLoadStrategy.equals("EAGER") ? PageLoadStrategy.EAGER : PageLoadStrategy.NORMAL);
                    if (headless.equals("on")) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                // Agrega otros casos para otros navegadores si es necesario
                default:
                    throw new IllegalArgumentException("Navegador no compatible: " + browser);
            }
            driver.manage().window().maximize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}