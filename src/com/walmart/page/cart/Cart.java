package com.walmart.page.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {

	public static WebElement element = null;

	/**
	 * Check qty donot below one
	 * @param driver
	 * @param decreaseQtyXpath
	 * @return
	 */
	public static String verifyNonNegative(WebDriver driver,
			String decreaseQtyXpath) {
		 element= checkButtonState(driver, decreaseQtyXpath);
		 return element.getAttribute("disabled");
	}
	
	/**
	 * Disabled when cart it empty
	 * @param driver
	 * @param path
	 * @return
	 */
	public static WebElement checkButtonState(WebDriver driver, String path) {
		element = driver.findElement(By.xpath(path));
		return element;
	}

	
    /**
     * Returns cart element
     * @param driver
     * @param addToCartXpath
     * @return
     */
	public static WebElement clickAddButton(WebDriver driver,
			String addToCartXpath) {
		element = driver.findElement(By.xpath(addToCartXpath));
		return element;
	}

	/**
     * Returns cart quantity
     * @param driver
     * @param addToCartXpath
     * @return
     */
	public static WebElement cartQty(WebDriver driver, String path) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		element = driver.findElement(By.xpath(path));
		return element;
	}

	/**
     * Add item to the cart and returns the text on the checkout page
     * @param driver
     * @param addToCartXpath
     * @return
     */
	public static String verifyPurchase(WebDriver driver, String addToCartXpath,
			String checkoutXpath) {
		String updateText = "";
		element = clickAddButton(driver, addToCartXpath);
		element.click();

		// switch frame
		driver.switchTo().activeElement();
		element = cartQty(driver, checkoutXpath);
		updateText = element.getText();
		// reset frame
		driver.switchTo().defaultContent();
		return updateText;
	}
}
