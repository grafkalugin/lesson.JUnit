package ru.softwareTesting;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.util.List;
import java.util.Set;

public class WebDriverRule extends TestWatcher implements WebDriver, TakesScreenshot {

	private Capabilities capabilities;

	public WebDriverRule(Capabilities capabilities) {
		this.capabilities = capabilities;
	}

	private WebDriver driver;
	/*
	@Override
		protected void before() throws Throwable {
			System.out.println("Starting a browser");
			driver = new ChromeDriver();
		}

		;

	@Override
		protected void after() {
			System.out.println("Stopping the browser");
			if (driver != null) {
				driver.quit();
			}
		}
		;
		*/


	@Override
	protected void starting(Description description) {
		System.out.println("Getting a browser");
		driver = WebDriverFactory.getDriver(capabilities);
	}

	public void startFreshDriver() {
		System.out.println("Stopping old driver");
		WebDriverFactory.dismissDriver(WebDriverFactory.getDriver(capabilities));
		driver = WebDriverFactory.getDriver(capabilities);
	}

	@Override
	public void close() {
		driver.close();
	}

	@Override
	public WebElement findElement(By arg0) {
		return driver.findElement(arg0);
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		return driver.findElements(arg0);
	}

	@Override
	public void get(String arg0) {
		driver.get(arg0);
	}

	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override
	public Options manage() {
		return driver.manage();
	}

	@Override
	public Navigation navigate() {
		return driver.navigate();
	}

	@Override
	public void quit() {
		driver.quit();
	}

	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		return ((TakesScreenshot) driver).getScreenshotAs(arg0);}
	}

