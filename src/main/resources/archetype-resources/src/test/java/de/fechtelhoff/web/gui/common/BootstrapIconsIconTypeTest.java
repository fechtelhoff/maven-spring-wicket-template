package de.fechtelhoff.web.gui.common;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.wicket.request.resource.ResourceReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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

	public static final String SPACE = " ";
	public static final String QUOTATION_MARK = "\"";

	@Test
	void test() throws IOException {
		final ResourceReference resourceReference = BootstrapIconsCssResourceReference.getInstance();
		final Optional<Resource> optionalResource = getResource(resourceReference);
		Assertions.assertTrue(optionalResource.isPresent());

		final List<String> cssFileContent = loadResourceContent(optionalResource.get());

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
	 * Ermittelt aus einer Resource Referenz die tatsächliche Resource.
	 */
	private Optional<Resource> getResource(final ResourceReference resourceReference) {
		final String resourceReferenceName = resourceReference.getName();
		try (final ScanResult scanResult = new ClassGraph().enableAllInfo().scan()) {
			return scanResult.getAllResources().stream()
				.filter(resource -> resource.toString().endsWith(resourceReferenceName))
				.findFirst();
		}
	}
}
