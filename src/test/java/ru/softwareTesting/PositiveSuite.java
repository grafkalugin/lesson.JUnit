package ru.softwareTesting;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses({
		AppTest.class
})
@RunWith(Categories.class)
@IncludeCategory({MyCategories.PositiveTest.class, MyCategories.NegativeTest.class})
@ExcludeCategory(MyCategories.BrokenTest.class)
public class PositiveSuite {
}
