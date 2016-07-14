package ru.softwareTesting;


import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class UnstableRule implements TestRule {

	private WebDriverRule driverRule;

	public UnstableRule(WebDriverRule driverRule) {
		this.driverRule = driverRule;
	}

	@Override
	public Statement apply(Statement base, Description desc) {
		return new UnstableStatement(base, desc);
	}

	public class UnstableStatement extends Statement {

		private final Statement base;
		private Description desc;

		public UnstableStatement(Statement base, Description desc) {
			this.base = base;
			this.desc = desc;
		}

		@Override
		public void evaluate() throws Throwable {



			Unstable sevTimes = desc.getAnnotation(Unstable.class);
			if (sevTimes != null) {

				int numOfSevTimes = sevTimes.value();
				for (int i = 0; i < numOfSevTimes; i++) {
					//Assume.assumeFalse();
					try {
						base.evaluate();
						break;
					} catch (Throwable t) {
						int attCount = i + 1;
						System.out.println("Failed on "+ attCount +" attempt: " + desc);
						if(i+1==numOfSevTimes)
							Assert.fail("Method " + desc + " absolutely failed. Count of attempt is " +numOfSevTimes+".");
					}
				}
			}







		}

	}

}
