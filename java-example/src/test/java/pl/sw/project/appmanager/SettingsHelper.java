package pl.sw.project.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by nishi on 2017-04-03.
 */
public class SettingsHelper extends HelperBase {

  public SettingsHelper(WebDriver wd) {
    super(wd);
  }

  public void cancelCaptcha() {
    new WebDriverWait( wd, 1000 ).until( ExpectedConditions.presenceOfElementLocated( By.cssSelector("ul.docs li#doc-security") ) );
    wd.findElement( By.cssSelector( "ul.docs li#doc-security" ) ).click();
    List<WebElement> rows = wd.findElements( By.cssSelector( "tr.row" ) );
    List<WebElement> cells = rows.get( 5 ).findElements( By.tagName( "td" ) );
    cells.get( 2 ).findElement( By.tagName( "a" ) ).click();
    wd.findElements( By.tagName( "label" ) ).get( 1 ).click();
    wd.findElement( By.tagName( "button" ) ).click();
    new WebDriverWait( wd, 1000 ).until( ExpectedConditions.visibilityOf( wd.findElement( By.cssSelector( "div.notice" ) ))).getText().equals( "Changes were successfully saved." );
  }
}
