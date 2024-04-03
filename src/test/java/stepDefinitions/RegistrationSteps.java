package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.E;
import io.cucumber.java.es.Entonces;
import org.encora.pageObjects.HomePage;
import org.encora.pageObjects.RegistrationPage;
import org.encora.utils.SetupDriver;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import io.cucumber.java.es.Y;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class RegistrationSteps {
    private WebDriver driver;
    private String url;
    private HomePage homePage;
    private RegistrationPage registrationPage;


    @Cuando("que abro la pagina de Parabank")
    public void i_am_on_the_parabank_home_page() throws InterruptedException, IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("src/test/resources/application.properties");
        prop.load(file);

        url = prop.getProperty("url.cert");
        driver = SetupDriver.getDriver();
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage.navigateToHomePage(url);
        Thread.sleep(1000);
    }

    @Cuando("presiono el boton Iniciar Registro")
    public void iClickOnTheRegisterLink() {
        homePage.clickRegisterLink();
    }

    @Y("relleno el formulario con los datos del cliente a registrar")
    public void rellenarFormularioRegistro(DataTable datos) {
        registrationPage.verificarExisteFormulario();
        registrationPage.ingresarDatosCliente(datos);
    }

    @Y("presiono el boton Registrar")
    public void presionoBotonRegistrar() {
        registrationPage.clickRegisterButton();
    }

    @Y("cierro sesion con la cuenta creada")
    public void presionoLogOut() {
        registrationPage.clickLogOutButton();
    }


    @Entonces("deberia ver el mensaje de exito {string}")
    public void iShouldSeeTheSuccessMessage(String expectedMessage) {
         String actualMessage = registrationPage.getSuccessMessage();
         Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Y("navego de regreso a la pagina de inicio de Parabank")
    public void regresoInicio() {
        homePage.navigateToHomePage(url);
    }

    @E("inicio sesion con la nueva cuenta creada")
    public void inicioSesion() {
        registrationPage.ingresoUsuario();
        registrationPage.ingresoPassword();
        registrationPage.clickLoginButton();
    }
}