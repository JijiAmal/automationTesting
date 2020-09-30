package AjioPageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AjioPageFactory {
    private final WebDriver driver;

    public String expectedUrl = null;
    public String productUrl = "https://www.ajio.com/search/?text=Chains";
    public String actualUrl = "https://www.ajio.com/login";
    public String homeUrl = "https://www.ajio.com/";
    public String bagItemCount = null;
    public String actualTitle = null;

    @FindBy(xpath = "//input[@name='username']")
    WebElement username;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitButton;
    @FindBy(xpath = "//*[@id='pwdInput']")
    WebElement password;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement loginButton;
    @FindBy(xpath = "//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/form/div/button/span")
    WebElement searchButton;
    @FindBy(xpath = "//*[@type ='text']")
    WebElement searchBox;

    @FindBy(xpath = "//div[@class='pdp-addtocart-button']")
    WebElement addToCartbtn;
    @FindBy(xpath = "//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[7]/div[1]/div[1]/a/div")
    WebElement goToBag;
    @FindBy(xpath = "//*[@id=\"dCartWrapper\"]/div[2]/div[2]/div[1]/div[2]/div/div[1]/div[1]/a/img")
    WebElement itemInBag;
    @FindBy(xpath = "//*[@id=\"products\"]/div[3]/div[1]/div/div[13]/a/div/div[1]/img")
    WebElement selectedItem1;
    @FindBy(xpath = "//*[@id=\"products\"]/div[3]/div[1]/div/div[35]/a/div/div[1]/img")
    WebElement selectedItem2;
    @FindBy(xpath = "//*[@id=\"products\"]/div[3]/div[1]/div/div[37]/a/div/div[1]/img")
    WebElement selectedItem3;
    @FindBy(xpath = "//*[@id=\"products\"]/div[3]/div[1]/div/div[38]/a/div/div[1]/img")
    WebElement selectedItem4;

    @FindBy(xpath = "//div[@class='delete-btn']")
    WebElement deletebtn;
    @FindBy(xpath = "//*[@id=\"modalId\"]/div/div/div[2]/div[2]/div[2]")
    WebElement deleteRadiobtn;
    @FindBy(xpath = "//*[@id=\"dCartWrapper\"]/div[2]/div[2]/div[1]/div[1]/div[1]/span[2]")
    WebElement bagItem;
    @FindBy(xpath = "//*[@class=\"voucher-wrapper\"]")
     List<WebElement>  couponSummary;
    @FindBy(xpath = "//*[@type='radio']")
    WebElement couponSavingsbtn;
    @FindBy(xpath ="/html/body/div[1]/div/div/div[1]/div/div[2]/div[2]/div[2]/div[3]/div/div[2]/div/button")
    WebElement couponApplybtn;
    @FindBy(xpath = "//*[@id=\"dCartWrapper\"]/div[2]/div[2]/div[2]/div[3]/div/div[2]/div")
    List<WebElement>  couponSavingPopup;
    public int bagTotalNew = 0;


    @FindBy(xpath="//*[@id=\"dCartWrapper\"]/div[2]/div[2]/div[2]/div[2]/button")
    WebElement shippingbtn;

    @FindBy(xpath="//*[@id=\"dAccountWrapper\"]/div[1]/div/div[2]/div/div[2]")
    WebElement addressbtn;

    @FindBy(xpath="//input[@name='addressPoc']")
    WebElement addressPoc;
    @FindBy(xpath="//input[@name='phone']")
    WebElement mobNumber;

    @FindBy(xpath="//*[@name=\"postalCode\"]")
    WebElement postalCode;


    @FindBy(xpath="//*[@name='line2']")
    WebElement streetName;

    @FindBy(xpath="//*[@name='line1']")
    WebElement buildingName;


    @FindBy(xpath="//input[@name='landmark']")
    WebElement landmarkName;

    @FindBy(xpath="//*[@id=\"addAddressForm\"]/div[2]/button[2]")
    WebElement submitBtn;


    @FindBy(xpath="//*[@id=\"dAccountWrapper\"]/div[1]/div/div[2]/div/div[3]")
    WebElement addressChangeBtn;


    @FindBy(xpath="//*[@id=\"modalId\"]/div/div/div[2]/div[2]/div/div/div[2]/span[1]")
    WebElement editAddressBtn;

    @FindBy(xpath="//*[@id=\"dAccountWrapper\"]/div[1]/div/div[2]/div/div[2]/div/div/div[1]")
    List<WebElement >viewAddress;

    @FindBy(xpath="//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/div[1]/a/div")
    WebElement closetbtn;
    @FindBy(xpath="//*[@id=\"capsule-products\"]/div/div[1]/div/div[1]/a/div/div[1]/img")
    WebElement item1;
    @FindBy(xpath="//*[@id=\"capsule-products\"]/div/div[1]/div/div[2]/a/div/div[1]/img")
    WebElement item2;
    @FindBy(xpath="//*[@id=\"capsule-products\"]/div/div[1]/div/div[3]/a/div/div[1]/img")
    WebElement item3;


    public AjioPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void Login(String userName, String Password) throws InterruptedException {
        //set the username field's value
        username.sendKeys(userName);
        //press the submit button to continue
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        password.sendKeys(Password);
        Thread.sleep(30000);
        loginButton.click();

    }

    public void urlVerify() {
        expectedUrl = driver.getCurrentUrl();
    }

    public void verifyHomepageTitle() {
        System.out.println("Verifying home page title");
        actualTitle = driver.getTitle();


    }

    public void SearchItem(String productType) throws InterruptedException {

        driver.manage().window().maximize();
        driver.get( homeUrl );

        // locate the search box and enter the product
        searchBox.sendKeys(productType);

        // locate the search button and click

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        searchButton.click();
        Thread.sleep(20000);

        // check whether the search product is displayed
        WebElement searchedItem = driver.findElement(By.xpath("//*[@id=\"products\"]/div[1]/h1/div[2]"));
        searchedItem.isDisplayed();

    }

    public int bagTotal = 0;

    //Using for loop, it  tries for 3 times
    //loop execution stops on finding the element
    public void addToBagException() {
        for (int i = 0; i <= 2; i++) {
            try {
                addToCartbtn.click();
                break;

            } catch (org.openqa.selenium.StaleElementReferenceException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }

    public void goToBagException() {
        for (int i = 0; i <= 2; i++) {
            try {
                goToBag.click();
                break;

            } catch (org.openqa.selenium.StaleElementReferenceException ex) {

                System.out.println(ex.getMessage());
            }
        }

    }

    public void AddItemToBag() throws InterruptedException {

        driver.manage().window().maximize();
        driver.get(productUrl);

        // Select the item
        selectedItem1.click();
        //Switch to new window

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        //Add item to cart
        Thread.sleep(6000);
        addToCartbtn.click();

        //Go to Bag
        Thread.sleep(6000);
        goToBagException();
        //Check whether item is displayed in Bag
        Thread.sleep(6000);
        itemInBag.isDisplayed();

        //Verify if the item is displayed
        if (itemInBag.isDisplayed()) {
            System.out.println("SelectedItem1 is present in the cart : Test Pass");
        } else {
            System.out.println("SelectedItem1 is not found : Test Fail");
        }
    }


    public void viewBagOnAddingMultipleItems() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get(homeUrl);
        closetbtn.click();

        // Select the second item
         item1.click();

        //Switch to new window
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        //Add item to cart
        addToCartbtn.click();


        //Go to Bag
        Thread.sleep(6000);
        goToBag.click();


        //Check whether item is displayed in Bag
        Thread.sleep(6000);
        itemInBag.isDisplayed();
        if (itemInBag.isDisplayed()) {
            System.out.println("SelectedItem2 is present in the cart : Test Pass");
        } else {
            System.out.println("SelectedItem2 is not present in the cart : Test Fail");
        }

        //Select third item and add to cart
        driver.get("https://www.ajio.com/wishlist");
        item2.click();
        Thread.sleep(6000);

        ArrayList<String> newWindow = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newWindow.get(3));
        addToBagException();

        Thread.sleep(6000);
        driver.switchTo().window(newWindow.get(3));
        goToBagException();
        Thread.sleep(6000);
        driver.switchTo().window(newWindow.get(3));
        driver.navigate().refresh();
        itemInBag.isDisplayed();
        System.out.println("SelectedItem3 is present in the cart: Test Pass");
        driver.get("\"https://www.ajio.com/wishlist\"");
        Thread.sleep(6000);
        item3.click();
        Thread.sleep(6000);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        addToBagException();

        Thread.sleep(6000);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        goToBagException();
        Thread.sleep(6000);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.navigate().refresh();
        itemInBag.isDisplayed();
        System.out.println("SelectedItem4 is present in the cart: Test Pass");


    }

    public void verifyBagCount() throws AssertionError, InterruptedException {
        driver.get("https://www.ajio.com/cart");
        driver.navigate().refresh();
        List<WebElement> selected_Items = driver.findElements(By.xpath("//*[@class='card-offer']"));
        bagTotal = selected_Items.size();
        Thread.sleep(6000);
        // Now using Iterator we will iterate all elements

        // this will check whether list has some element or not
        for (WebElement item : selected_Items) {

            // Iterate one by one
            // get the text
            String label = item.getText();

            // print the text
            System.out.println("Item label is " + label);
        }
        System.out.println("Total number of items selected : " + "(" + bagTotal + " items)");

        bagItemCount = bagItem.getText();
        System.out.println("Total number of items in MyBag : " + bagItemCount);
        Assert.assertEquals("(" + bagTotal + " items)", bagItemCount);

    }

    public void viewOrderSummary() {

        List<WebElement> order_summary = driver.findElements(By.xpath("//*[@class='price-summary']"));

        // this will check whether list has some element or not
        for (WebElement orderSmry : order_summary) {

            // Iterate one by one
            // get the text
            String orderDetails = orderSmry.getText();

            // print the text
            System.out.println(orderDetails);
        }

    }

    public void deleteItem() {
        driver.get("https://www.ajio.com/cart");
        deletebtn.click();
        deleteRadiobtn.click();
        driver.navigate().refresh();
        List<WebElement> selected_Items_updated = driver.findElements(By.xpath("//*[@class='card-offer']"));
        bagTotalNew = selected_Items_updated.size();

    }
    public void couponDetails() throws InterruptedException {
        driver.get("https://www.ajio.com/cart");
        // this will check whether list has some element or not
        for (WebElement item : couponSummary) {

            // Iterate one by one
            // get the text
            String label = item.getText();

            // print the text
            System.out.println("Coupon details: " + label);
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        couponSavingsbtn.click();
        Thread.sleep(20000);
        couponApplybtn.click();
        for (WebElement items : couponSavingPopup) {

            // Iterate one by one
            // get the text
            String labels = items.getText();

            // print the text
            System.out.println("Coupon Savings message: " + labels);
        }

    }

    public void addNewAddress(String PostalCode,String StreetName,String BuildingName,String LandmarkName) throws InterruptedException {
        driver.get("https://www.ajio.com/cart");
        shippingbtn.click();
        Thread.sleep(3000);
        addressbtn.click();
        postalCode.sendKeys(PostalCode);
        Thread.sleep(3000);
        streetName.sendKeys(StreetName);
        Thread.sleep(3000);
        buildingName.sendKeys(BuildingName );
        Thread.sleep(3000);
        landmarkName.sendKeys(LandmarkName);
        Thread.sleep(3000);
        submitBtn.click();
        for (WebElement item : viewAddress) {

            // Iterate one by one
            // get the text
            String label = item.getText();

            // print the text
            System.out.println("Coupon details: " + label);
        }

    }

    public void editAddress(String PostalCode,String StreetName,String BuildingName,String LandmarkName) throws InterruptedException {
        driver.get("https://www.ajio.com/shipping");
        addressChangeBtn.click();
        Thread.sleep(3000);
        editAddressBtn.click();
        Thread.sleep(3000);
        postalCode.clear();
        postalCode.sendKeys(PostalCode);
        Thread.sleep(3000);
        streetName.clear();
        streetName.sendKeys(StreetName);
        Thread.sleep(3000);
        buildingName.clear();
        buildingName.sendKeys(BuildingName );
        Thread.sleep(3000);
        landmarkName.clear();
        landmarkName.sendKeys(LandmarkName);
        Thread.sleep(3000);
        submitBtn.click();
        Thread.sleep(3000);
        for (WebElement item : viewAddress) {

            // Iterate one by one
            // get the text
            String label = item.getText();

            // print the text
            System.out.println("Coupon details: " + label);
        }

    }

}
