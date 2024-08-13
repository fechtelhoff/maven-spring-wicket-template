package ${package}.web;

import org.apache.wicket.protocol.http.WebApplication;
import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.CookieThemeProvider;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import de.agilecoders.wicket.webjars.WicketWebjars;
import de.agilecoders.wicket.webjars.settings.WebjarsSettings;

@ApplicationInitExtension
public class WicketApplicationConfig implements WicketApplicationInitConfiguration {

	@Override
	public void init(final WebApplication webApplication) {
		initializeCsp(webApplication);
		initializeBootstrap(webApplication);
		initializeWebJars(webApplication);

		mountPages(webApplication);
	}

	private void initializeCsp(final WebApplication webApplication) {
		webApplication.getCspSettings().blocking().disabled();
	}

	private void initializeBootstrap(final WebApplication webApplication) {
		final IBootstrapSettings bootstrapSettings = new BootstrapSettings();
		final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Cerulean);
		bootstrapSettings.setThemeProvider(themeProvider);
		bootstrapSettings.setActiveThemeProvider(new CookieThemeProvider());
		Bootstrap.install(webApplication, bootstrapSettings);
	}

	private void initializeWebJars(final WebApplication webApplication) {
		final WebjarsSettings webjarsSettings = new WebjarsSettings();
		webjarsSettings.useCdnResources(false);
		WicketWebjars.install(webApplication, webjarsSettings);
	}

	private void mountPages(final WebApplication webApplication) {
		WicketApplicationPrettyUrlConfig.getPageClassByPrettyUrlMap().forEach(webApplication::mountPage);
	}
}
