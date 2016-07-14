package ru.softwareTesting;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppFixture {

	//protected static WebDriver driver;

	@ClassRule
	public static WebDriverRule driver = new WebDriverRule(DesiredCapabilities.chrome());

	@Rule
	public FreshDriverRule freshDriverRule = new FreshDriverRule(driver);

	//@Rule
	//public TestRule runTwiceRule = new RunTwiceRule();

	@Rule
	public RunSeveralTimesRule runSeveralTimes = new RunSeveralTimesRule(driver);

	@Rule
	public ExternalResource driverRule0 = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			System.out.println("Start method fixture");
			AppTest.tmpDir = System.getProperty("java.io.tmpdir");
		};

		@Override
		protected void after() {
			System.out.println("Stop method fixture");
		};

	};


	@ClassRule
	public static ExternalResource driverRule2 = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			System.out.println("before class fixture");
		};

		@Override
		protected void after() {
			System.out.println("after class fixture");
		};
	};

	//@Rule
	//public RuleChain rules = RuleChain.outerRule(driverRule).around(driverRule0);

}
