/**
 * Copyright © 2018 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rapidpm.vaadin.i18n.provider;

import com.vaadin.flow.i18n.I18NProvider;
import org.rapidpm.dependencies.core.logger.HasLogger;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Locale.ROOT;
import static java.util.Objects.isNull;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;

public class I18NPropertiesProvider
    implements I18NProvider, HasLogger {

  public static final String                NULL_KEY              = "###-NULL-KEY-###";
  public static final String                EMPTY_KEY             = "###-EMPTY-KEY-###";

  private final ResourceBundleService resourceBundleService = new ResourceBundleService();

  public I18NPropertiesProvider() {
    logger().info("I18NPropertiesProvider was found..");
  }

  @Override
  public List<Locale> getProvidedLocales() {
    logger().info("getProvidedLocales.. ");
    return resourceBundleService.providedLocalesAsList();
  }

  @Override
  //TODO add custom translations
  public String getTranslation(String key, Locale locale, Object... params) {
    final Locale localeInput = locale != null
                               ? locale
                               : ROOT;
    final ResourceBundle resourceBundle = resourceBundleService.resourceBundleToUse()
                                                               .apply(localeInput);

    return match(matchCase(() -> failure("###-" + key + "-###-" + locale)),
                 matchCase(() -> isNull(key), () -> failure(NULL_KEY)),
                 matchCase(key::isEmpty, () -> failure(EMPTY_KEY)),
                 matchCase(() -> resourceBundle.containsKey(key),
                           () -> success(resourceBundle.getString(key))))
        .ifFailed(msg -> logger().warning(msg))
        .getOrElse(() -> "###-KEY_NOT_FOUND-" + key + " - " + locale + "-###");
  }
}
