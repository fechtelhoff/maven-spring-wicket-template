package de.fechtelhoff.web.gui;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import de.agilecoders.wicket.webjars.WicketWebjars;
import de.agilecoders.wicket.webjars.settings.WebjarsSettings;

public class WicketTestApplication extends WebApplication {

	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public void init() {
		super.init();
		initializeWebJars();
	}

	private void initializeWebJars() {
		final WebjarsSettings settings = new WebjarsSettings();
		settings.useCdnResources(false);
		WicketWebjars.install(this, settings);
	}
}
