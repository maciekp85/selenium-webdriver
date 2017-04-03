package pl.sw.project.tests;

import org.junit.Test;

import java.util.Random;

/**
 * Created by nishi on 2017-04-03.
 */
public class RegistrationUserTest extends TestBase {

  @Test
  public void testRegistrationUser() {
    app.goTo().loginPage();
    app.session().loginAsAdmin("admin", "admin" );
    app.menu().moveTo( "Settings" );
    app.settings().cancelCaptcha();
    app.goTo().mainPage();
    String name = "Maciej";
    String surname = "Piotrowski";
    String address = "ul. Testowa 3";
    String zip = "31-875";
    String city = "Cracow";
    Random random = new Random( );
    int number = random.nextInt( 100 );
    String email = "maciekp" + number + "@abc.pl";
    String phone = "+48111222333";
    String password = "password";
    app.registration().createNewCustomer(name, surname, address, zip, city, email, phone, password);
    app.session().logout();
    app.session().loginAsCustomer( email, password );
    app.session().logout();
  }
}
