package ru.softwareTesting;


import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RunSeveralTimesRule implements TestRule {

	private WebDriverRule driverRule;

	public RunSeveralTimesRule(WebDriverRule driverRule) {
		this.driverRule = driverRule;
	}

	@Override
	public Statement apply(Statement base, Description desc) {
		return new RunSeveralTimesStatement(base, desc);
	}

	public class RunSeveralTimesStatement extends Statement {

		private final Statement base;
		private Description desc;

		public RunSeveralTimesStatement(Statement base, Description desc) {
			this.base = base;
			this.desc = desc;
		}

		@Override
		public void evaluate() throws Throwable {



			RunSeveralTimes sevTimes = desc.getAnnotation(RunSeveralTimes.class);
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
