package com.walmart.page.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	public static WebElement element = null;

	/**
	 * Returns the search box element
	 * 
	 * @param driver
	 * @param path
	 * @return
	 */
	public static WebElement locateMainSearch(WebDriver driver, String path) {
		element = driver.findElement(By.xpath(path));
		return element;
	}

	/**
	 * Input item in the search box
	 * 
	 * @param driver
	 * @param item
	 * @param searchXpath
	 */
	public static void mainSearch(WebDriver driver, String item,
			String searchXpath) {
		element = locateMainSearch(driver, searchXpath);
		element.sendKeys(item);
	}

	/**
	 * Returns search icon element
	 * 
	 * @param driver
	 * @param path
	 * @return
	 */
	public static WebElement findSearchButton(WebDriver driver, String path) {
		element = driver.findElement(By.xpath(path));
		return element;
	}

	/**
	 * Click search icon
	 * 
	 * @param driver
	 * @param searchButtonXpath
	 */
	public static void clickSearchButton(WebDriver driver,
			String searchButtonXpath) {
		element = findSearchButton(driver, searchButtonXpath);
		element.click();
	}

	/**
	 * Returns search result result element
	 * 
	 * @param driver
	 * @param path
	 * @return
	 */
	public static String getSearchResult(WebDriver driver, String path) {
		element = driver.findElement(By.xpath(path));
		return element.getText();
	}

	/**
	 * Returns search result text
	 * 
	 * @param driver
	 * @param path
	 * @return
	 */
	public static String verifySearchResult(WebDriver driver, String path) {
		return getSearchResult(driver, path);
	}

}
