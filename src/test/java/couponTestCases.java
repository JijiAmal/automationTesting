import AjioPageObjectModel.AjioPageFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class couponTestCases {
    WebDriver driver = AjioPageObjectModel.Driver.openBrowser("chrome", "https://www.ajio.com/login");
    public static ExtentReports reports = new ExtentReports();
    public static ExtentSparkReporter extentSparkReporter;
    ExtentTest extentTest;
    public AjioPageFactory ajioPageFactory = new AjioPageFactory(driver);
    public String orderDetails =null;
    @BeforeSuite
    public void beforeSuiteSetup() {
        String path = System.getProperty("user.dir");
        extentSparkReporter = new ExtentSparkReporter(path + "\\report\\reports.html");
        reports.attachReporter(extentSparkReporter);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

        reports.flush();
    }
    @Test
    public void  couponSavingTest() throws IOException, InterruptedException {

        extentTest = reports.createTest("couponSavingTest");
        ajioPageFactory.Login("jijimol91@gmail.com", "Admin@1234");
        ajioPageFactory.AddItemToBag();
        List<WebElement>  order_summary = driver.findElements(By.xpath("//*[@class='price-summary']")) ;
        Iterator<WebElement> orderSummary =order_summary.iterator();

        // this will check whether list has some element or not
        while ( orderSummary.hasNext()) {

            // Iterate one by one
            WebElement orderSmry = orderSummary.next();

            // get the text
            orderDetails = orderSmry.getText();


            // print the text
            System.out.println(orderDetails);
            System.out.println("The total value of order is: "+order_summary.get(order_summary.size()-1));
            orderSmry.isDisplayed();
        }



        WebElement couponRadioBtn = driver .findElement(By.xpath("//*[@class='price-value bold-font']"));
        couponRadioBtn.click();

        WebElement couponApplyBtn = driver .findElement(By.xpath("//*[@class='rilrtl-button button apply-button']"));
        couponRadioBtn.click();
        WebElement orderTotalNew = order_summary.get(order_summary.size());
        System.out.println("The total value of order is: "+orderTotalNew);
        driver.navigate().refresh();
        WebElement  amountSavings = driver .findElement(By.xpath("//*[@class='amount-savings']"));
        System.out.println("The total value of order is: "+amountSavings.getText());
        WebElement  amountSavingsMsg = driver .findElement(By.xpath("//*[@class='you-save-text']"));
        System.out.println("The total value of order is: "+amountSavingsMsg.getText());
        extentTest.log(Status.INFO, "coupon amount is saved");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }
}
