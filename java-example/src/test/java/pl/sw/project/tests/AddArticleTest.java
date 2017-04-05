package pl.sw.project.tests;

import org.junit.Test;

import java.util.Random;

/**
 * Created by nishi on 2017-04-03.
 */
public class AddArticleTest extends TestBase {

  @Test
  public void testRegistrationUser() {
    app.goTo().loginPage();
    app.session().loginAsAdmin("admin", "admin" );
    app.menu().moveTo( "Catalog" );
    Random random = new Random(  );
    int number = random.nextInt( 1000 );
    String name = "testname" + number;
    app.article().addNewProduct(name);
  }
}
