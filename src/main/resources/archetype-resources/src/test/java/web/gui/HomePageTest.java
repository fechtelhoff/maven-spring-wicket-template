package ${package}.web.gui;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ${package}.web.WicketTestApplication;

class HomePageTest {

	private WicketTester tester;
	private HomePage homePage;

	@BeforeEach
	void setUp() {
		tester = new WicketTester(new WicketTestApplication());
		homePage = new HomePage();
	}

	@Test
	void homepageRendersSuccessfully() {
		//start and render the test page
		tester.startPage(homePage);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
	}
}

