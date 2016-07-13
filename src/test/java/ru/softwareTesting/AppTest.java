package ru.softwareTesting;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(DataProviderRunner.class)
public class AppTest extends AppFixture implements MyCategories
{
	public static String tmpDir;
	public static String pathToFile;

/*	@Rule
	public TemporaryFolder tmpdir2 = new TemporaryFolder();

	@Test
	public void testSomething() {
		System.out.println("Временная папка: " + tmpdir2.getRoot());
	}
*/
	@DataProvider
	public static Object[][] users2()  throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				AppTest.class.getResourceAsStream("/user.data")));

		List<Object[]> userData = new ArrayList<Object[]>();
		String line = in.readLine();
		while (line != null) {
			userData.add(line.split(";"));
			line = in.readLine();
		}

		in.close();

		return (Object[][]) userData.toArray(new Object[][]{});
	}
	@DataProvider
	public static Object[][] users() {
		List<Object[]> data = new ArrayList<Object[]>();
		for (int i = 0; i < 10; i++) {
			data.add(new Object[]{
					generateRandomName(), generateRandomPassword()
			});
		}
		return (Object[][]) data.toArray(new Object[][]{});
	}

	private static Object generateRandomPassword() {
		return "password" + new Random().nextInt();
	}

	private static Object generateRandomName() {
		return "user" + new Random().nextInt();
	}

    @Test
    @Category({PositiveTest.class})
    @UseDataProvider("users")
    public void createFileTest(String name, String name2) throws IOException {
        pathToFile = tmpDir + name + name2 + ".txt";
	    System.out.println(pathToFile);
        File file2 = new File(pathToFile);
	    System.out.println(file2);
        boolean result;
	    result = file2.createNewFile();
	    System.out.println("Файл2:"+file2);
	    System.out.println(result);
        assertThat("Filed creating - createFileTest", result, is(true));
	    file2.delete();
    }

    @Test
    @Category({PositiveTest.class, BrokenTest.class})
    public void deleteFileTest() throws IOException{
        pathToFile = tmpDir + "test.txt";
        File file = new File(pathToFile);
        file.createNewFile();
        boolean result = file.delete();
        assertThat("Ошибка удаления файла.", result, is(true));
    }

    @Test
    @Category({NegativeTest.class})
    @UseDataProvider("users2")
    public void negativeCreateDoubleFileTest(String name, String name2) throws IOException {
        pathToFile = tmpDir + name + name2 + ".txt";
        File file = new File(pathToFile);
        boolean result;
        boolean result2;
        file.delete();
        result = file.createNewFile();
        result2 = file.createNewFile();
        SoftAssertions s = new SoftAssertions();
        s.assertThat(result).isEqualTo(true); //"Ошибка создания фикстуры для теста negativeCreateDoubleFileTest"
        s.assertThat(result2).isEqualTo(false); //"Ошибка. Созданы 2 файла с одинаковым именем."
        s.assertAll();

    }

    @Ignore
    @Test
    public void testSoftAssert() {
        SoftAssertions s = new SoftAssertions();
        s.assertThat(2 * 2).isEqualTo(4);
        s.assertThat(2 * 2).isEqualTo(4);
        s.assertAll();
    }

	@Test
	public void assumeTest(){
		Assume.assumeThat(2 + 3, is(5));
	}

	@Test
	public void test1() {
		driver.get("http://seleniumhq.org/");
	}

	@Test
	@NeedsFreshDriver
	public void test2() {
		driver.get("http://selenium2.ru/");
	}
}
