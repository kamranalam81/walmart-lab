package com.walmart.page.item;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.walmart.page.search.SearchPage;

public class ItemPage extends SearchPage {

	public static WebElement element = null;
	
	/**
	 * Return Item element of the search result
	 * @param driver
	 * @param path
	 * @return
	 */
	public static WebElement findItemOnPage(WebDriver driver, String path) {
		element = driver.findElement(By.xpath(path));
		return element;
	}
	
	/**
	 * Click Item on the search result
	 * @param driver
	 * @param resultXpath
	 */
	public static void clickItem(WebDriver driver, String resultXpath) {
		element = findItemOnPage(driver, resultXpath);
		element.click();
	}
	
	/**
	 * Returns Image location
	 * @param driver
	 * @param path
	 * @return
	 */
	public static WebElement getImageDetails(WebDriver driver, String path) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		element = driver.findElement(By.xpath(path));
		return element;
	}
	
	/**
	 * Returns image status: enable/disable
	 * @param driver
	 * @param path
	 * @return
	 */
	public static boolean verifyImageDetails(WebDriver driver, String path) {
		element = getImageDetails(driver, path);
		return element.isDisplayed();
	}

	/**
	 * Returns Item title displayed on UI
	 * @param driver
	 * @param path
	 * @return
	 */
	public static WebElement getItemTitle(WebDriver driver, String path) {
		element = driver.findElement(By.xpath(path));
		return element;
	}
	
	/**
	 * Returns label of the field
	 * @param driver
	 * @param path
	 * @return
	 */
	public static String verifyLabel(WebDriver driver, String path) {
		element= getItemTitle(driver, path);
		return element.getText();
	}
	
	/**
	 * Returns title of the field
	 * @param driver
	 * @param path
	 * @return
	 */
	public static String verifyItemTitle(WebDriver driver, String path) {
		element=  getItemTitle(driver, path);
		return element.getText();
	}
	
}
