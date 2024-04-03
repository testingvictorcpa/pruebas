package org.encora.pageObjects;

import io.cucumber.datatable.DataTable;
import org.encora.utils.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private WebDriver driver;

    // Locators
    private By firstNameField = By.name("customer.firstName");
    private By lastNameField = By.name("customer.lastName");
    private By addressField = By.name("customer.address.street");
    private By cityField = By.name("customer.address.city");
    private By stateField = By.name("customer.address.state");
    private By zipCodeField = By.name("customer.address.zipCode");
    private By phoneNumberField = By.name("customer.phoneNumber");
    private By ssnField = By.name("customer.ssn");
    private By usernameField = By.name("customer.username");
    private By passwordField = By.name("customer.password");
    private By confirmPasswordField = By.name("repeatedPassword");
    private By registerButton = By.xpath("//input[@value='Register']");
    @FindBy(xpath = "//td[contains(., 'First Name')]")
    protected WebElement txtFirstName;

    @FindBy(xpath = "//h1[contains(@class, 'title') and contains(text(), 'Welcome')]")
    protected WebElement txtTitulo;

    @FindBy(xpath = "//div[@id='rightPanel']/p")
    protected WebElement txtMensajeObtenido;

    @FindBy(xpath = "//form[@name='login']//input[@name='username']")
    protected WebElement usernameLoginField;

    @FindBy(xpath = "//form[@name='login']//input[@name='password']")
    protected WebElement passwordLoginField;

    @FindBy(xpath = "//form[@name='login']//input[@type='submit']")
    protected WebElement botonLogin;

    @FindBy(xpath = "//a[text()='Log Out']")
    protected WebElement btnLogOut;

    // Accesos
    private String username;
    private String password;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verificarExisteFormulario(){
        util.waitUntilElementIsVisible(driver,txtFirstName,25);
    }

    public void ingresarDatosCliente(DataTable datos) {
        try {
            String nombre = util.getValueFromDataTable(datos, "nombre");
            String apellido = util.getValueFromDataTable(datos, "apellido");
            String direccion = util.getValueFromDataTable(datos, "direccion");
            String ciudad = util.getValueFromDataTable(datos, "ciudad");
            String estado = util.getValueFromDataTable(datos, "estado");
            String zipCode = util.getValueFromDataTable(datos, "zipCode");
            String telefono = util.getValueFromDataTable(datos, "telefono");
            String SSN = util.getValueFromDataTable(datos, "SSN");

            enterFirstName(nombre);
            enterLastName(apellido);
            enterAddress(direccion);
            enterCity(ciudad);
            enterState(estado);
            enterZipCode(zipCode);
            enterPhoneNumber(telefono);
            enterSSN(SSN);

            username=enterUsername();
            password=enterPassword();


        } catch (Exception e) {
            // Manejar la excepción aquí
            System.out.println("Ocurrió una excepción al ingresar los datos del cliente.");
            e.printStackTrace();
        }
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {

        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void enterCity(String city) {
        driver.findElement(cityField).sendKeys(city);
    }

    public void enterState(String state) {
        driver.findElement(stateField).sendKeys(state);
    }

    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void enterSSN(String ssn) {
        driver.findElement(ssnField).sendKeys(ssn);
    }

    public String enterUsername() {
        String username = generateRandomWord(10);
        driver.findElement(usernameField).sendKeys(username);
        System.out.println("Usuario generado: "+username);
        return username;
    }

    public static String generateRandomWord(int length) {
        // Definir los caracteres posibles (letras y números)
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Generar la palabra aleatoria
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public String enterPassword() {
        String password = generateRandomWord(10);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        System.out.println("Password generado: "+password);
        return password;
    }


    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickLogOutButton() {
        util.waitUntilElementIsVisible(driver,btnLogOut,25);
        btnLogOut.click();
    }


    public String getSuccessMessage(){
        util.waitUntilElementIsVisible(driver,txtTitulo,25);
        util.waitUntilElementIsVisible(driver,txtMensajeObtenido,25);
        String mensaje = txtMensajeObtenido.getText();
        System.out.println("Mensaje obtenido: " + mensaje);
        return mensaje;

    }

    public void ingresoUsuario() {
        util.waitUntilElementIsVisible(driver,usernameLoginField,25);
        usernameLoginField.sendKeys(username);
    }

    public void ingresoPassword() {
        util.waitUntilElementIsVisible(driver,passwordLoginField,25);
        passwordLoginField.sendKeys(password);
    }

    public void clickLoginButton() {
        util.waitUntilElementIsVisible(driver,botonLogin,25);
        botonLogin.click();
        System.out.println("Inicio de Sesión realizado con el usuario: "+username);
        System.out.println("Usuario generado: "+username);
        System.out.println("Password generado: "+password);
    }
}