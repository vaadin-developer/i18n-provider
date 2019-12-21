package org.rapidpm.vaadin.i18n.provider;

import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.Transformations;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.Locale.*;
import static java.util.ResourceBundle.getBundle;
import static java.util.stream.Collectors.toList;

public class ResourceBundleService
    implements HasLogger {

  public static final String RESOURCE_BUNDLE_NAME = "labels";

  public static final Locale AUSTRALIAN  = new Builder().setLanguage("en")
                                                        .setRegion("AU")
                                                        .build();
  public static final Locale NEW_ZEALAND = new Builder().setLanguage("en")
                                                        .setRegion("NZ")
                                                        .build();

  private static final ResourceBundle RESOURCE_BUNDLE_ROOT  = getBundle(RESOURCE_BUNDLE_NAME, ROOT);
  private static final ResourceBundle RESOURCE_BUNDLE_DE    = getBundle(RESOURCE_BUNDLE_NAME, GERMAN);
  private static final ResourceBundle RESOURCE_BUNDLE_EN    = getBundle(RESOURCE_BUNDLE_NAME, ENGLISH);
  private static final ResourceBundle RESOURCE_BUNDLE_EN_US = getBundle(RESOURCE_BUNDLE_NAME, US);
  private static final ResourceBundle RESOURCE_BUNDLE_EN_AU = getBundle(RESOURCE_BUNDLE_NAME, AUSTRALIAN);
  private static final ResourceBundle RESOURCE_BUNDLE_EN_NZ = getBundle(RESOURCE_BUNDLE_NAME, NEW_ZEALAND);

  private static Map<Locale, ResourceBundle> persistenceStorage = new ConcurrentHashMap<>();

  public ResourceBundleService() {
    postProcess();
  }

  //don´t remove from the Set itself -> will be reflected to the map
  public Supplier<Stream<Locale>> providedLocalesAsSupplier() {
    return this::providedLocalesAsStream;
  }

  public Stream<Locale> providedLocalesAsStream() {
    return persistenceStorage.keySet()
                             .stream()
                             .filter((l) -> !l.equals(ROOT));
  }

  public List<Locale> providedLocalesAsList() {
    return providedLocalesAsStream().collect(toList());
  }


  public Function<Locale, ResourceBundle> resourceBundleToUse() {
    return (locale) -> convertLocale().andThen(loadResourceBundle())
                                      .apply(locale);
  }


  private Function<Locale, Locale> convertLocale() {
    return Transformations.<Stream<Locale>, Locale, Locale>curryBiFunction()
        .apply(selectLocaleToUse())
        .apply(providedLocalesAsStream());
  }

  private BiFunction<Stream<Locale>, Locale, Locale> selectLocaleToUse() {
    return (availableLocales, requestedLocale) -> availableLocales.filter((l) -> l.equals(requestedLocale))
                                                                  .findFirst()
                                                                  .orElse(ROOT);
  }

  private Function<Locale, ResourceBundle> loadResourceBundle() {
    return (locale) -> persistenceStorage.get(locale);
  }

  //LifeCycle dependent
  private void postProcess() {
    persistenceStorage.put(ROOT, RESOURCE_BUNDLE_ROOT);
    persistenceStorage.put(GERMAN, RESOURCE_BUNDLE_DE);
    persistenceStorage.put(ENGLISH, RESOURCE_BUNDLE_EN);
    persistenceStorage.put(US, RESOURCE_BUNDLE_EN_US);
    persistenceStorage.put(AUSTRALIAN, RESOURCE_BUNDLE_EN_AU);
    persistenceStorage.put(NEW_ZEALAND, RESOURCE_BUNDLE_EN_NZ);
  }


}
