package ru.softwareTesting;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;

public class AppFixture {


	@Rule
	public ExternalResource driverRule = new ExternalResource() {
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

	@Rule
	public ExternalResource driverRule0 = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			System.out.println("Start method fixture2");
		};

		@Override
		protected void after() {
			System.out.println("Stop method fixture2");
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

	@Rule
	public RuleChain rules = RuleChain.outerRule(driverRule).around(driverRule0);

}
