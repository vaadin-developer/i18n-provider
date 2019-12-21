package org.rapidpm.vaadin.api.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.model.Result;
import com.vaadin.flow.i18n.I18NProvider;

public abstract class VaadinI18NProvider implements I18NProvider, HasLogger {

  public static final String THE_ROOT_RESOURCE_BUNDLE_IS_NOT_AVAILABLE = "The ROOT ResourceBundle is not available";

//  public VaadinI18NProvider() {
//    logger().info(VaadinI18NProvider.class.getSimpleName() + " was found..");
//  }


  /**
   * This method is responsible to load the ResourceBundle for the requested Locale.
   * The implementation should be a caching one, because this is used for every translation as well.
   *
   * @param locale
   * @return The requested ResurceBundle or an empty Result if this Locale is not supported.
   */
  public abstract Result<ResourceBundle> loadResourceBundleFor(Locale locale);

  /**
   * This method will give the ROOT ResourceBundle back.
   *
   * @return The ResourceBundle for the Locale ROOT or RuntimeException if not ResourceBundle is available!
   */
  public ResourceBundle defaultResourceBundle() {
    return loadResourceBundleFor(Locale.ROOT)
        .ifAbsent(() -> logger().info(THE_ROOT_RESOURCE_BUNDLE_IS_NOT_AVAILABLE))
        .ifAbsent(() -> {
          throw new RuntimeException(THE_ROOT_RESOURCE_BUNDLE_IS_NOT_AVAILABLE);
        })
        .get();
  }


  /**
   * If you are caching loaded ResourceBundles, you could remove them with this method.
   * With this you are able to reload the translations during a Session
   *
   * @param locale
   */
  public abstract void removeResourceBundleFor(Locale locale);

  /**
   * The attribute params is not used in this implementation.
   * If you need this, you should override this implementation.
   * @param key
   * @param locale
   * @param params
   * @return will return the translated key, or the requested key plus the locale. This could be easily found later.
   */
  @Override
  public String getTranslation(String key , Locale locale , Object... params) {
    logger().fine("VaadinI18NProvider getTranslation.. key : " + key + " - " + locale);
    return loadResourceBundleFor(locale)
        .or(() -> Result.success(defaultResourceBundle()))
        .map(resourceBundle -> {
          if (! resourceBundle.containsKey(key))
            logger().info("missing ressource key (i18n) " + key);
          return (resourceBundle.containsKey(key)) ? resourceBundle.getString(key) : null;
        })
        .getOrElse(() -> key + " - " + locale);
  }

}
