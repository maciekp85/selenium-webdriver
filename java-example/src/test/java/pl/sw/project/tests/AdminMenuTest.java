package pl.sw.project.tests;

import org.junit.Test;

/**
 * Created by nishi on 2017-03-06.
 */
public class AdminMenuTest extends TestBase {

  @Test
  public void testAdminMenu() {
    app.goTo().loginPage();
    app.session().login( "admin", "admin" );
    app.menu().walkThroughTheAllElements();
  }
}
