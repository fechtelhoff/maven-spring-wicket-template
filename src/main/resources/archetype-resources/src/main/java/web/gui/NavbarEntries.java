package ${package}.web.gui;

import org.apache.wicket.markup.html.WebPage;

public enum NavbarEntries {

	HOME("Home", HomePage.class),
	SECOND("Second", SecondPage.class),
	/**/;

	private final String label;
	private final Class<? extends WebPage> page;

	NavbarEntries(final String label, final Class<? extends WebPage> page) {
		this.label = label;
		this.page = page;
	}

	public String getLabel() {
		return label;
	}

	public Class<? extends WebPage> getPage() {
		return page;
	}
}
