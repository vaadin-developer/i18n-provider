package junit.org.rapidpm.vaadin.i18n.provide;

import com.vaadin.flow.i18n.I18NProvider;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.i18n.provider.I18NPropertiesProvider;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class I18NPropertiesProviderTest {

  @Test
  void getTranslation() {
    final I18NProvider i18NProvider = new I18NPropertiesProvider();

    final List<Locale> providedLocales = i18NProvider.getProvidedLocales();
    assertEquals(5, providedLocales.size());
    //assume that there is a key : provider.test=german/english/default
    assertEquals("default-en", i18NProvider.getTranslation("provider.test", Locale.ENGLISH, null));
    assertEquals("default-de", i18NProvider.getTranslation("provider.test", Locale.GERMAN, null));
    assertEquals("default", i18NProvider.getTranslation("provider.test", Locale.CHINA, null));
  }
}