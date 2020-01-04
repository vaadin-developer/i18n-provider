# i18n-provider
An I18N Provider Implementation for Vaadin Flow

## Supported Locals 
The supported locals fixed right now.

```java
  private static final ResourceBundle RESOURCE_BUNDLE_ROOT  = getBundle(RESOURCE_BUNDLE_NAME, ROOT);
  private static final ResourceBundle RESOURCE_BUNDLE_DE    = getBundle(RESOURCE_BUNDLE_NAME, GERMAN);
  private static final ResourceBundle RESOURCE_BUNDLE_EN    = getBundle(RESOURCE_BUNDLE_NAME, ENGLISH);
  private static final ResourceBundle RESOURCE_BUNDLE_EN_US = getBundle(RESOURCE_BUNDLE_NAME, US);
  private static final ResourceBundle RESOURCE_BUNDLE_EN_AU = getBundle(RESOURCE_BUNDLE_NAME, AUSTRALIAN);
  private static final ResourceBundle RESOURCE_BUNDLE_EN_NZ = getBundle(RESOURCE_BUNDLE_NAME, NEW_ZEALAND);
```
 
## Heroku deployment
This project is prepared for a Heroku deployment