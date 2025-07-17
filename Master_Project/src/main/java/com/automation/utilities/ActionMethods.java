package com.automation.utilities;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/*import com.target.objects.HomePageObjects;
import com.target.objects.PensionPageObjects;*/

public class ActionMethods {

	public void sync(WebDriver driver, WebElement element) {
		try {
			Wait<WebDriver> wait = new WebDriverWait(driver, 100).ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void syncByLocator(WebDriver driver, By locator) {
		try {
			Wait<WebDriver> wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void syncClickable(WebDriver driver, WebElement element) {
		try {
			Wait<WebDriver> wait = (WebDriverWait) new WebDriverWait(driver, 100)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void type(WebElement element, String str) {
		element.clear();
		element.sendKeys(str.toString());

	}

	public void click(WebElement element) {
		element.click();
	}

	public boolean verifyElement(Object element) {
		try {
			if (element instanceof WebElement) {
				if (((WebElement) element).isDisplayed()) {
					return true;
				} else {
					return false;
				}
			} else

			{
				return false;
			}
		} catch (NullPointerException e) {
			System.out.println("Object value null");
			e.printStackTrace();
		} catch (ElementNotVisibleException e) {
			System.out.println("Object not visible");
			e.printStackTrace();
		} catch (MoveTargetOutOfBoundsException e) {
			System.out.println("Object Cannot be scrolled into view");
			e.printStackTrace();
		}
		/*
		 * catch (ElementNotFoundException e) { System.out.println("Object Not Found");
		 * e.printStackTrace(); }
		 */
		catch (NoSuchElementException e) {
			Assert.assertTrue("Object does Not exist", false);
			System.out.println("Object Not exists");
			e.printStackTrace();
		} catch (WebDriverException e) {
			System.out.println("Webdriver Exception: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Unknown Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return false;

	}

	public boolean isElementPrsent(WebElement element) {
		try {
			element.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public void setClipBoardData(String str) {
		StringSelection stringselection = new StringSelection(str);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, null);
	}

	public void selectElementFromDropDown(WebDriver driver, By element, String visibleText) {

		Select dropDown = new Select(driver.findElement(element));
		dropDown.selectByVisibleText(visibleText);

	}

	public void scrollIntoView(WebDriver driver, WebElement webElement) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean containsText(WebDriver driver, String str) {
		try {
			String containsObject = "//*[contains(text(),'" + str + "')]";
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(containsObject)));
			return true;
		} catch (Exception e) {
			System.out.println("Text : " + str + " is not present in the view");
			return false;
		}

	}

	public boolean exactText(WebDriver driver, String str) {

		try {
			String exactobjects = "//*[text()='" + str + "')]";
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(exactobjects)));
			return true;

		} catch (Exception e) {
			System.out.println("Exact Text : " + str + " is not present in the view");
			return false;
		}

	}

	public void scrolldown(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	public boolean redirect(WebDriver driver, String url) {

		String currentURL = driver.getCurrentUrl();
		boolean urlRediection = false;
		if (!currentURL.contains(url)) {
			urlRediection = true;
		}
		return urlRediection;

	}

	public boolean checkTitle(WebDriver driver, String title) {
		try {

			if (driver.getTitle().equalsIgnoreCase(title)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void highlightElement(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;' )",
					element);
			Thread.sleep(3000);
			js.executeScript("arguments[0].setAttribute('style', 'border: 2px white')", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkBrokenLinks(WebDriver driver) {
		try {
			List<WebElement> links = driver.findElements(By.tagName("a"));
			System.out.println("Total links present in the page are  " + links.size());

			for (int i = 0; i < links.size(); i++) {
				WebElement element = links.get(i);
				String url = element.getAttribute("href");
				verifyActiveLink(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyActiveLink(String linkurl) {
		try {
			URL url = new URL(linkurl);
			HttpURLConnection httpconnect = (HttpURLConnection) url.openConnection();
			httpconnect.setConnectTimeout(3000);
			httpconnect.connect();

			if (httpconnect.getResponseCode() == 200) {
				System.out.println(linkurl + "  -" + httpconnect.getResponseMessage());
			}

			if (httpconnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(
						linkurl + "  -" + httpconnect.getResponseMessage() + " " + HttpURLConnection.HTTP_NOT_FOUND);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkTextForParticularElement(WebDriver driver, WebElement element) {
		String text = element.getText();
		return text;
	}

}
