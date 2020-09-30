import AjioPageObjectModel.AjioPageFactory;
import AjioPageObjectModel.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class AJIOTestExecution {

    private final String path = System.getProperty("user.dir");
    WebDriver driver = Driver.openBrowser("chrome", "https://www.ajio.com/login");

    public AjioPageFactory ajioPageFactory = new AjioPageFactory(driver);
    public static ExtentReports reports = new ExtentReports();
    public static ExtentSparkReporter extentSparkReporter;
    ExtentTest extentTest;
    public String orderDetails =null;

    @BeforeSuite
    public void beforeSuiteSetup() {
        extentSparkReporter = new ExtentSparkReporter(path + "\\report\\report.html");
        reports.attachReporter(extentSparkReporter);
    }


    @BeforeTest
    public void launchBrowser(){
        System.out.println("Launching Chrome Browser");
    }
    @Test(priority = 1)
    public void verifyURL() throws IOException {

        extentTest = reports.createTest("URL_Verification");
        ajioPageFactory.urlVerify();
        Assert.assertEquals(ajioPageFactory.expectedUrl,ajioPageFactory.actualUrl);
        extentTest.log(Status.PASS, "URL Verified");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());
    }

    @Test(priority = 2)
    public void LoginTest() throws IOException, InterruptedException {

        extentTest = reports.createTest("LoginTest");
        ajioPageFactory.Login( "jijimol91@gmail.com", "Admin@1234" );
        extentTest.log(Status.PASS, "Login Passed");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }

    @Test(priority = 3)
    public void HomePageTitleVerification() throws IOException {

        extentTest = reports.createTest("VerifyHomePageTitle");
        ajioPageFactory.verifyHomepageTitle();
        Assert.assertTrue(ajioPageFactory.actualTitle.contains("AJIO"));
        extentTest.log(Status.PASS, "Home Page Title Verified");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }

    @Test(priority = 4)
    public void SearchFunctionTest() throws IOException {

        extentTest = reports.createTest("SearchFunctionTest");
        try {
            ajioPageFactory.SearchItem("Chains");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        extentTest.log(Status.PASS, "Search Function Test Passed ");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }

    @Test(priority = 5)
    public void addItemToBagTest() throws IOException, InterruptedException {

        extentTest = reports.createTest("AddItemToBagTest");
        ajioPageFactory.AddItemToBag();
        extentTest.log(Status.PASS, "Added item successfully ");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }

    @Test(priority = 6)
    public void multipleItemToBagTest() throws IOException, InterruptedException {

        extentTest = reports.createTest("AddMultipleItemToBagTest");
        ajioPageFactory.viewBagOnAddingMultipleItems();



        extentTest.log(Status.INFO, "Items are present in the Bag");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }

    @Test(priority = 7)
    public void bagCountVerification() throws IOException, AssertionError {

        extentTest = reports.createTest("VerifyBagCount");
        try {
            ajioPageFactory.verifyBagCount();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        extentTest.log(Status.INFO, "Bag count matches total count");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }
    @Test(priority = 8)
    public void  viewOrderSummary() throws IOException, AssertionError {

        extentTest = reports.createTest("ViewOrderSummary ");
        ajioPageFactory.viewOrderSummary();
        extentTest.log(Status.INFO, "Order Summary is displayed");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }
    @Test(priority = 9)
    public void itemDeleteVerification() throws IOException, AssertionError {

        extentTest = reports.createTest("VerifyItemDelete");
        ajioPageFactory.deleteItem();
        System.out.println("Bag count before deletion: " + ajioPageFactory.bagTotal);
        System.out.println("Bag count after deletion: " + ajioPageFactory.bagTotalNew);
        Assert.assertEquals(ajioPageFactory.bagTotalNew,ajioPageFactory.bagTotal-1);
        extentTest.log(Status.INFO, "Bag count reduced by one");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }

    @Test(priority = 10)
    public void couponSummaryDisplay() throws IOException, InterruptedException {

        extentTest = reports.createTest("CouponDisplayTest");
        ajioPageFactory.couponDetails();

        extentTest.log(Status.INFO, "Get all the coupon details");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());
    }

    @Test(priority = 11)
    public void addNewShippingAdress() throws IOException, AssertionError {

        extentTest = reports.createTest("AddNewShippingAddress");
        try {
            ajioPageFactory.addNewAddress("691009","V N Nagar","8","Thevally");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        extentTest.log(Status.INFO, "Added New Shipping Address");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }
    @Test(priority = 12)
    public void editShippingAdress() throws IOException, AssertionError {

        extentTest = reports.createTest("EditShippingAddress");
        try {
            ajioPageFactory.editAddress("691009","Palace Nagar","53","Thevally");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        extentTest.log(Status.INFO, "Edited Shipping Address");
        extentTest.addScreenCaptureFromPath(AjioPageObjectModel.Driver.takeScreenshot());

    }
    @AfterTest
    public void terminateBrowser(){
        driver.close();
    }
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        reports.flush();
    }


}
