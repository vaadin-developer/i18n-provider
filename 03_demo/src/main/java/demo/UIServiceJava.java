package demo;

import com.vaadin.flow.server.Constants;
import org.apache.commons.cli.ParseException;
import org.rapidpm.vaadin.nano.CoreUIServiceJava;

public class UIServiceJava extends CoreUIServiceJava {

  private UIServiceJava() {
  }

  public static void main(String[] args) throws ParseException {
    System.setProperty(Constants.SERVLET_PARAMETER_ENABLE_DEV_SERVER, "false");
    System.setProperty("vaadin.compatibilityMode", "false");
    CoreUIServiceJava uiService = new UIServiceJava().executeCLI(args);
    uiService.startup();
  }

}
