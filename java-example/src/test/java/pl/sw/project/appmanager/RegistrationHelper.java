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
public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(WebDriver wd) {
    super(wd);
  }

  public void createNewCustomer(String name, String surname, String address, String zip, String city, String email, String phone, String password) {
    List<WebElement> rows = wd.findElements( By.cssSelector("tbody tr") );
    List<WebElement> cells = rows.get( 4 ).findElements( By.tagName( "td" ) );
    cells.get( 0 ).click();
    new WebDriverWait( wd, 1000 ).until( ExpectedConditions.textToBePresentInElementLocated( By.tagName( "h1" ), "Create Account"  ));
    type( By.name( "firstname" ), name );
    type( By.name( "lastname" ), surname );
    type( By.name( "address1" ), address );
    type( By.name( "postcode" ), zip );
    type( By.name( "city" ), city );
    type( By.name( "email" ), email );
    type( By.name( "phone" ), phone );
    type( By.name( "password" ), password );
    type( By.name( "confirmed_password" ), password );
    click( By.name( "create_account" ) );
  }
}
