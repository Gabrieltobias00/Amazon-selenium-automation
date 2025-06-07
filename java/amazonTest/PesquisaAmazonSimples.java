package amazonTest;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class PesquisaAmazonSimples {
    public static void main(String[] args) {
        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com.br");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement barraBusca = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

            // Verificacao  do campo tem autocomplete habilitado
            String autocomplete = barraBusca.getAttribute("autocomplete");
            if (!"off".equalsIgnoreCase(autocomplete)) {
                System.out.println("Autocomplete está desativado: " + autocomplete);
            } else {
                System.out.println("Autocomplete está ativado.");
            }

            // Digita o termo e espera sugestões
            barraBusca.sendKeys("iphone");

            // Espera as sugestões aparecerem
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-suggestion")));

            List<WebElement> sugestoes = driver.findElements(By.cssSelector(".s-suggestion"));
            if (!sugestoes.isEmpty()) {
                System.out.println("Sugestões encontradas:");
                for (WebElement sugestao : sugestoes) {
                    System.out.println("- " + sugestao.getText());
                }
            } else {
                System.out.println("Nenhuma sugestão encontrada.");
            }

        } catch (Exception e) {
            System.out.println("Erro durante o teste: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}