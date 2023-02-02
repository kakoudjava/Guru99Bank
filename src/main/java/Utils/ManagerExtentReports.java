package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManagerExtentReports {

    ExtentHtmlReporter reporter;
    ExtentTest test;
    WebDriver webDriver;
    static ExtentReports report;
    UtilsFunction UF = new UtilsFunction();

    public ManagerExtentReports() {
        DateFormat format = new SimpleDateFormat("HH_mm_ss");
        Date date = new Date();
        reporter = new ExtentHtmlReporter(UF.mainPath()+"/report/rapport"+format.format(date)+".html");
        report = new ExtentReports();
        report.attachReporter(reporter);
    }

    public void initializeReport(String nameReport){
        test = report.createTest(nameReport);
    }

    public void GetStatus(String Status,String Msg){
       switch (Status){
           case "Fail":
               test.fail(Msg);
               break;
           default:
               test.pass(Msg);

       }
    }

    public void CloseReporter(){
        report.flush();
        reporter.stop();
    }

}
