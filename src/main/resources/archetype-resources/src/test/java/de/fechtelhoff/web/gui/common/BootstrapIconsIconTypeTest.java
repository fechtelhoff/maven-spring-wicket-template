package de.fechtelhoff.web.gui.common;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import de.fechtelhoff.web.gui.WicketTestApplication;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ScanResult;

/**
 * Dieser Test erzeugt aus der über WebJars eingebunden Resource der Bootstrap-Icons automatische
 * die Java Variablendeklarationen für die Icon-Types. Der Output muss manuell in die Klasse
 * <pre>BootstrapIconsIconType</pre> eingefügt werden.
 * <p/>
 * Dies sollte immer dann ausgeführt werden, wenn eine neue Version der Bootstrap-Icons eingebunden wird.
 */
@Disabled
class BootstrapIconsIconTypeTest {

	private static final String SPACE = " ";
	private static final String QUOTATION_MARK = "\"";

	@SuppressWarnings({"FieldCanBeLocal", "unused"})
	private WicketTester tester;

	@BeforeEach
	void setUp() {
		tester = new WicketTester(new WicketTestApplication());
	}

	@Test
	void test() throws IOException {
		final ResourceReference resourceReference = BootstrapIconsCssResourceReference.getInstance();

		final List<String> cssFileContent = getResourceContent(resourceReference);
		Assertions.assertNotNull(cssFileContent);
		Assertions.assertFalse(cssFileContent.isEmpty());

		final List<String> cssElements = cssFileContent.stream()
			.filter(e -> e.startsWith(".bi-"))
			.map(this::getCssName)
			.sorted()
			.toList();

		final List<String> javaCodeLines = cssElements.stream()
			.map(this::generateJavaCode)
			.toList();

		javaCodeLines.forEach(System.out::println);
	}

	/**
	 * Erstellt die Java Code Zeile für die Variablendeklaration.
	 */
	@SuppressWarnings("StringBufferReplaceableByString")
	private String generateJavaCode(final String cssElement) {
		return new StringBuilder()
			.append("public static final BootstrapIconsIconType")
			.append(SPACE)
			.append(getVariableName(cssElement))
			.append(SPACE)
			.append("= new BootstrapIconsIconType(")
			.append(QUOTATION_MARK)
			.append(cssElement)
			.append(QUOTATION_MARK)
			.append(");")
			.toString();
	}

	/**
	 * Erstellt aus einem CSS Tag Namen den Namen einer Java Variablen.
	 */
	private String getVariableName(final String cssName) {
		return cssName.replace("-", "_").toUpperCase();
	}

	/**
	 * Separiert aus der Eingabezeile den CSS Tag Namen.
	 */
	private String getCssName(final String inputLine) {
		return inputLine.substring(0, inputLine.indexOf(":")).substring(1);
	}

	/**
	 * Läd den Inhalt einer Resource als String-Liste.
	 */
	private List<String> loadResourceContent(final Resource resource) throws IOException {
		if (Objects.nonNull(resource)) {
			final String content = resource.getContentAsString();
			return Arrays.asList(content.split("\n"));
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * Ermittelt aus einer Resource Referenz die tatsächliche Resource und läd den Inhalt als String-Liste.
	 */
	private List<String> getResourceContent(final ResourceReference resourceReference) throws IOException {
		final String resourceReferenceName = resourceReference.getName();
		try (final ScanResult scanResult = new ClassGraph().enableAllInfo().scan()) {
			final Resource resource = scanResult.getAllResources().stream()
				.filter(r -> r.toString().endsWith(resourceReferenceName))
				.findFirst()
				.orElse(null);
			return loadResourceContent(resource);
		}
	}
}
