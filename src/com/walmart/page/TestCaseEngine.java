package com.walmart.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.walmart.page.cart.Cart;
import com.walmart.page.item.ItemPage;
import com.walmart.page.search.SearchPage;

import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class TestCaseEngine {
	private WebDriver driver;
	private String baseUrl;
	private static Document document;

	@BeforeClass
	public void setUp() throws Exception {
		// System.setProperty("webdriver.gecko.driver", "geckodriver");
		
		System.setProperty("webdriver.chrome.driver",
				"chromedriver");
		driver = new ChromeDriver();

		baseUrl = "https://www.walmart.ca/";

		// Maximize the browser's window
		driver.manage().window().maximize();

		driver.get(baseUrl);

		File inputFile = new File("ObjectRepository.xml");
		SAXReader saxReader = new SAXReader();
		document = saxReader.read(inputFile);
	}

	// verify the search result
	@Test(enabled = true, priority = 1)
	public void search_item_main_page() {
		String sXpath = document.selectSingleNode(
				"//walmart/searchPage/searchXpath").getText();
		String sbXpath = document.selectSingleNode(
				"//walmart/searchPage/searchButtonXpath").getText();
		String srXpath = document.selectSingleNode(
				"//walmart/searchPage/searchResultXpath").getText();

		String actualSearchResult = "";
		String product = "PRD1GJ5OCB8NJM1";
		String searchXpath = sXpath;
		String searchButtonXpath = sbXpath;
		String searchResultXpath = srXpath;
		String expectedSearchResult = "Intex 28131EH 12 ft. x 30 in. Easy Set Pool";

		SearchPage.mainSearch(driver, product, searchXpath);
		SearchPage.clickSearchButton(driver, searchButtonXpath);
		actualSearchResult = SearchPage.verifySearchResult(driver,
				searchResultXpath);
		Assert.assertEquals(expectedSearchResult, actualSearchResult);

	}

	// verify search item is clickable and lands to the item description
	@Test(enabled = true, priority = 2)
	public void click_item_on_search_result() {
		String tXpath = document.selectSingleNode(
				"//walmart/searchPage/titleXpath").getText();
		String iOSPXpath = document.selectSingleNode(
				"//walmart/searchPage/itemOnSearchPageXpath").getText();
		String actualTitle = "";
		String expectedTitle = "Intex 28131EH 12 ft. x 30 in. Easy Set Pool";
		String titleXpath = tXpath;
		String itemOnSearchPageXpath = iOSPXpath;

		ItemPage.clickItem(driver, itemOnSearchPageXpath);
		actualTitle = ItemPage.verifyItemTitle(driver, titleXpath);
		Assert.assertEquals(expectedTitle, actualTitle);

	}

	// verify image displayed
	@Test(enabled = true, priority = 3)
	public void check_item_image_displayed() {
		String iXpath = document.selectSingleNode(
				"//walmart/itemPage/imageXpath").getText();
		boolean actualImageClass;
		boolean expectedImageClass = true;

		actualImageClass = ItemPage.verifyImageDetails(driver, iXpath);
		Assert.assertEquals(expectedImageClass, actualImageClass);
	}

	// verify label displayed
	@Test(enabled = true, priority = 4)
	public void verify_add_to_cart_label() {
		String iXpath = document.selectSingleNode(
				"//walmart/itemPage/addToCartXpath").getText();
		String actualAddtoCartLabel;
		String expectedAddtoCartLabel = "Add to cart";

		actualAddtoCartLabel = ItemPage.verifyLabel(driver, iXpath);
		Assert.assertEquals(expectedAddtoCartLabel, actualAddtoCartLabel);
	}

	// verify label displayed
	@Test(enabled = true, priority = 5)
	public void validate_find_in_store_label() {
		String fIsXpath = document.selectSingleNode(
				"//walmart/itemPage/findInStoreXpath").getText();
		String actualFindInStoreLabel;
		String expectedFindInStoreLabel = "Find in-store";

		actualFindInStoreLabel = ItemPage.verifyLabel(driver, fIsXpath);
		Assert.assertEquals(expectedFindInStoreLabel, actualFindInStoreLabel);
	}

	// verify remove quantity is disabled
	@Test(enabled = true, priority = 6)
	public void validate_cart_quantity_not_negative() {
		String dQXpath = document
				.selectSingleNode("//walmart/cart/decreaseQty").getText();
		String disabled = "";

		disabled = Cart.verifyNonNegative(driver, dQXpath);
		Assert.assertEquals("true", disabled);
	}

	// verify add to cart
	@Test(enabled = true, priority = 7)
	public void add_item_to_cart() {
		String aTcXpath = document.selectSingleNode(
				"//walmart/cart/addToCartXpath").getText();
		String cXpath = document.selectSingleNode(
				"//walmart/cart/checkoutXpath").getText();
		String actualCartCount = "";
		String expectedCartCount = "1 item";

		actualCartCount = Cart.verifyPurchase(driver, aTcXpath, cXpath);
		Assert.assertEquals(expectedCartCount, actualCartCount);
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.close();
	}

}
