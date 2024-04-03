package org.encora.pageObjects;


import org.encora.utils.util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//a[text()='Register']")
    protected WebElement btnIniciarRegistro;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Método para navegar a la página de inicio
    public void navigateToHomePage(String url) {
        System.out.println("URL: " + url);
        driver.get(url);
        System.out.println("Ingreso a la web con éxito!");
    }

    // Click on the "Register" link
    public void clickRegisterLink() {
        util.waitUntilElementIsVisible(driver,btnIniciarRegistro, 45);
        btnIniciarRegistro.click();
        System.out.println("Se encontró el botón de Iniciar Registro y se hizo click en él.");
    }
}
