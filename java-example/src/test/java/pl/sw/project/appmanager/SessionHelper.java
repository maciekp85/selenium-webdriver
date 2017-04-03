package pl.sw.project.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by nishi on 2017-02-20.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd) {
    super( wd );
  }

  public void loginAsAdmin(String userName, String password) {
    type( By.name( "username" ), userName );
    type( By.name( "password" ), password );
    click( By.name( "login" ) );
  }

  public void loginAsCustomer(String email, String password) {
    type( By.name( "email" ), email );
    type( By.name( "password" ), password );
    click( By.name( "login" ) );
    new WebDriverWait( wd, 1000 ).until( ExpectedConditions.visibilityOf( wd.findElement( By.cssSelector( "div.notice" ) ) ) ).getText().equals( "You are now logged in as" );
  }

  public void logout() {
    new WebDriverWait( wd, 1000 ).until( ExpectedConditions.visibilityOfAllElements( wd.findElements( By.xpath( "//a[contains(text(),'Logout')]" ) ) ) );
    wd.findElements( By.xpath( "//a[contains(text(),'Logout')]" ) ).get( 0 ).click();
    new WebDriverWait( wd, 1000 ).until( ExpectedConditions.visibilityOf( wd.findElement( By.cssSelector( "div.notice" ) ) ) ).getText().equals( "You are now logged out." );
  }
}