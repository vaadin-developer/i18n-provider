package org.rapidpm.vaadin.api.i18n;

import static java.util.ResourceBundle.getBundle;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.rapidpm.frp.model.Result;

public class PropertyI18NProvider extends VaadinI18NProvider {


  public PropertyI18NProvider() {
    logger().info("I18NProvider Implementation " + this.getClass().getSimpleName() + " was found.. and activated");
  }


  private final Map<Locale, ResourceBundle> activeResourceBundles = new ConcurrentHashMap<>();



  @Override
  public Result<ResourceBundle> loadResourceBundleFor(Locale locale) {
    getBundle()


    return null;
  }

  @Override
  public void removeResourceBundleFor(Locale locale) {

  }

  @Override
  public List<Locale> getProvidedLocales() {
    return null;
  }
}
