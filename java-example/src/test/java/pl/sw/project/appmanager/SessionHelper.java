package pl.sw.project.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by nishi on 2017-02-20.
 */
public class SessionHelper extends HelperBase {

  protected WebDriver wd;

  public SessionHelper(WebDriver wd) {
    super(wd);
  }

  public void login(String userName, String password) {
    type( By.name("username"), userName);
    type(By.name( "password" ), password);
    click(By.name( "login" ));
  }

}
