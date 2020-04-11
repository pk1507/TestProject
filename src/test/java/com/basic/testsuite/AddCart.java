package com.basic.testsuite;

import com.basic.utils.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddCart {

    WebDriver driver;

    @Before
    public void setup() {
        //initiating the ChromeDriver
        driver = DriverFactory.getDriver("chrome");
    }

    @After
    public void tearDown() {
        //closes the browsers and quits the session
        driver.quit();
    }

    public String summerDress = "Summer Dresses";

    /*****************************************
     * Test Case 01 locators
     *****************************************/
    public By cart = By.xpath("//b[contains(text(),'Cart')]");
    public By dresses = By.linkText("DRESSES");
    public By sumDressesTxt = By.className("category-name");
    public By summerDresses = By.cssSelector("body.index.hide-left-column.hide-right-column.lang_en:nth-child(2) div.header-container div.container div.row div.sf-contener.clearfix.col-lg-12:nth-child(6) ul.sf-menu.clearfix.menu-content.sf-js-enabled.sf-arrows li:nth-child(2) ul.submenu-container.clearfix.first-in-line-xs li:nth-child(3) > a:nth-child(1)");
    public By prodImg = By.className("product-image-container");
    public By addCartBtn = By.className("button-container");
    public By productConfirmation = By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']//h2[1]");
    public By proceedToCheckOutBtn = By.xpath("//span[contains(text(),'Proceed to checkout')]");
    public By shoppingCartPage = By.id("cart_title");
    public By checkOutBtnInSummaryPage = By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]");
    public By loginForm = By.id("login_form");
    public By authenticationHeader = By.className("page-heading");
    /*******************************************************************
     * Test Case 01
     *******************************************************************/

    @Test
    public void verifyAddCart() throws Throwable{
        launchURL();
        verifyHomePage();
        navigateToSummerDresses();
        addProductToTheCart();
        navigateToShoppingCart();
        validateTappingOnProceedToCheckOutLandsOnSignInPage();
    }

    public void launchURL()
    {
        try {
            //Enter the URL into the address bar
            driver.get("http://automationpractice.com/index.php");
            //Maximizes the Window
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }
    }

    public void verifyHomePage() throws Throwable{
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cart));
        //Validates if page is landed on hompage
        Assert.assertTrue("HomePage is not displayed", driver.findElement(cart).isDisplayed());
    }

    public void navigateToSummerDresses() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dresses));
        //Mouse hover action to hover the mouse on Dresses element and to tap subsequent summer dresses option
        Actions action = new Actions(driver);
        WebElement dressesLink = driver.findElement(dresses);
        action.moveToElement(dressesLink).perform();
        WebElement summerDressesLink = driver.findElement(summerDresses);
        action.click(summerDressesLink).build().perform();

        WebDriverWait wait2 = new WebDriverWait(driver,10);
        wait2.until(ExpectedConditions.presenceOfElementLocated(sumDressesTxt));
        //Validating if Summer Dresses section is displayed or not by validating the header text
        Assert.assertTrue("Summer Dresses section is not displayed",driver.findElement(sumDressesTxt).getText().contains(summerDress) );
    }

    public void addProductToTheCart() throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement compare = driver.findElement(By.xpath("//div[@class='top-pagination-content clearfix']//form[@class='compare-form']"));
        js.executeScript("arguments[0].scrollIntoView();", compare);
        List<WebElement> products = driver.findElements(prodImg);
        Actions action = new Actions(driver);
        action.moveToElement(products.get(1)).perform();
        List<WebElement> addToCartBtn = driver.findElements(addCartBtn);
        action.click(addToCartBtn.get(1)).build().perform();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productConfirmation));
        Assert.assertTrue("confirmation of product added is not displayed", driver.findElement(productConfirmation).getText().contains("Product successfully added to your shopping cart"));
        driver.findElement(proceedToCheckOutBtn).click();
    }

    public void navigateToShoppingCart() throws Throwable {

        WebDriverWait wait2 = new WebDriverWait(driver,10);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartPage));
        Assert.assertTrue("Shopping cart page is not displayed", driver.findElement(shoppingCartPage).getText().contains("SHOPPING-CART SUMMARY"));
    }

    public void validateTappingOnProceedToCheckOutLandsOnSignInPage() throws Throwable {
        driver.findElement(checkOutBtnInSummaryPage).click();
        WebDriverWait wait3 = new WebDriverWait(driver,10);
        wait3.until(ExpectedConditions.visibilityOfElementLocated(authenticationHeader));
        Thread.sleep(4000);
        Assert.assertTrue("Sign in page is not displayed", driver.findElement(authenticationHeader).getText().contains("AUTHENTICATION"));
        Assert.assertTrue("Login form is not displayed", driver.findElement(loginForm).isDisplayed());
    }
}
