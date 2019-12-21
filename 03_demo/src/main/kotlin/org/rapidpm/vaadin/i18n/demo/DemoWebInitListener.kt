package org.rapidpm.vaadin.i18n.demo

import com.vaadin.flow.server.ServiceInitEvent
import com.vaadin.flow.server.VaadinServiceInitListener
import org.rapidpm.dependencies.core.logger.HasLogger
import org.rapidpm.vaadin.i18n.provider.I18NPropertiesProvider
import java.io.Serializable

class DemoWebInitListener : VaadinServiceInitListener, HasLogger, Serializable {
  override fun serviceInit(event: ServiceInitEvent) {
    logger().info("setting I18NProvider implementation.. " + I18NPropertiesProvider::class.java.name)
    System.setProperty(VAADIN_I_18_N_PROVIDER, I18NPropertiesProvider::class.java.name)
  }

  companion object {
    const val VAADIN_I_18_N_PROVIDER = "vaadin.i18n.provider"
  }
}