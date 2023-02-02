package Demo;

import Utils.ManagerExtentReports;
import Utils.UtilsFunction;
import Utils.ScreenRecorderUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGuru99Bank {
    WebDriver driver;
    UtilsFunction FO;
    ManagerExtentReports manager;
    String titrePage;

    @Parameters({"browserName","url"})
    @BeforeTest
    public void setUp(String browserName, String url) throws Exception {
        System.out.println("Chargement du navigateur");
        FO = new UtilsFunction();

        driver = FO.selectBrowser(browserName);
        manager = new ManagerExtentReports();
        System.out.println("Demarrage de la contruction du rapport");

        System.out.println("Demarrage de l'enregistrement vidéo");
        ScreenRecorderUtil.startRecord("ENREGISTREMENT_GURU99_BANK");

        System.out.println("Démarrage du navigateur");
        driver.get(url);
    };

    @Parameters({"login","password"})
    @Test
    public void loginSession(String login, String password){
        manager.initializeReport("loginSession");
        try{
            driver.findElement(By.name("uid")).sendKeys(login);
            manager.GetStatus("Pass","Username Succes");
            driver.findElement(By.name("password")).sendKeys(password);
            manager.GetStatus("Pass","Password Succes");
            driver.findElement(By.name("btnLogin")).click();
            manager.GetStatus("Pass","Click Succes");
            titrePage = driver.getTitle();
            if (titrePage.contains("Manager HomePage")){
                System.out.println("Connexion effectuée avec succès");
                manager.GetStatus("Pass","Connexion effectuée avec succès");
            }else{
                System.out.println("Connexion échouée");
                manager.GetStatus("Fail","Connexion échouée");

            }
        }catch (Throwable t){
            manager.GetStatus("Fail",t.getMessage());
        }

    }

    @AfterTest
    public void TearDown() throws Exception {
        System.out.println("Arrêt de l'enregistrement vidéo");
        ScreenRecorderUtil.stopRecord();

        System.out.println("Arrêt de la journalisation");
        manager.CloseReporter();

        System.out.println("Fermeture du navigateur");
        driver.quit();
    }

}
