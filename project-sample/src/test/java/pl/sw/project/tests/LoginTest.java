package pl.sw.project.tests;

import org.junit.Test;

/**
 * Created by nishi on 2017-02-20.
 */
public class LoginTest extends TestBase {

  @Test
  public void testLogin() {
    app.goTo().loginPage();
    app.session().login("admin", "admin");
  }
}
