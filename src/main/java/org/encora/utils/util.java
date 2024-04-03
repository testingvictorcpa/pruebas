package org.encora.utils;

import io.cucumber.datatable.DataTable;
import jdk.jshell.spi.ExecutionControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class util {

    public static void waitUntilElementIsVisible(WebDriver driver, WebElement webElement, int timeOutOnSeconds) {
        try {
            (new WebDriverWait(driver, ((long) timeOutOnSeconds))).until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            // Manejar la excepción aquí
            System.out.println("El elemento no se hizo visible dentro del tiempo de espera especificado.");
            e.printStackTrace();
        }
    }

    public static String getValueFromDataTable(DataTable dataTable, String title) throws ExecutionControl.NotImplementedException {
        if (dataTable.getTableConverter().getClass().getSimpleName().equalsIgnoreCase("DataTableTypeRegistryTableConverter")) {
            return (String)((Map)dataTable.asMaps(String.class, String.class).get(0)).get(title);
        } else if (dataTable.getTableConverter().getClass().getSimpleName().equalsIgnoreCase("NoConverterDefined")) {
            return (String)((Map)dataTable.asMaps().get(0)).get(title);
        } else {
            throw new ExecutionControl.NotImplementedException("DataTable getTableConverter() not implemented.");
        }
    }

}