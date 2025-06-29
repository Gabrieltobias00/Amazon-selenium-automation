package amazonTest;

import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class TesteMenuResponsivo {
    public static void main(String[] args) {
        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com.br");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Espera o botão "Todos" pelo span da classe hm-icon-label com texto "Todos"
            WebElement botaoTodos = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@class, 'hm-icon-label') and text()='Todos']")));

            System.out.println("Botão 'Todos' encontrado.");

            // Clica no botão para abrir o menu lateral
            botaoTodos.click();

            // Espera o menu lateral ficar visível
            WebElement menuLateral = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("hmenu-content"))); // id comum do menu lateral da Amazon

            System.out.println("Menu lateral aberto.");

            // Lista algumas opções dentro do menu lateral
            List<WebElement> opcoes = menuLateral.findElements(By.tagName("a")); // geralmente links no menu

            if (!opcoes.isEmpty()) {
                System.out.println("Opções encontradas no menu lateral:");
                for (int i = 0; i < Math.min(10, opcoes.size()); i++) {  // imprime até 10 opções
                    System.out.println("- " + opcoes.get(i).getText());
                }
            } else {
                System.out.println("Nenhuma opção encontrada no menu lateral.");
            }

        } catch (Exception e) {
            System.out.println("Erro durante o teste do menu 'Todos': " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}