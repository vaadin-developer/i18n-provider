package demo;

import com.vaadin.flow.server.Constants;
import org.apache.commons.cli.ParseException;
import org.rapidpm.vaadin.i18n.provider.I18NPropertiesProvider;
import org.rapidpm.vaadin.nano.CoreUIServiceJava;

public class UIServiceJava extends CoreUIServiceJava {

  private UIServiceJava() {
  }

  public static void main(String[] args) throws ParseException {
    System.setProperty(Constants.SERVLET_PARAMETER_ENABLE_DEV_SERVER, "false");
    System.setProperty("vaadin.compatibilityMode", "false");
    System.setProperty("vaadin.productionMode", "true");

    System.setProperty("vaadin.i18n.provider", I18NPropertiesProvider.class.getName());

    CoreUIServiceJava uiService = new UIServiceJava().executeCLI(args);
    uiService.startup();
  }

}
