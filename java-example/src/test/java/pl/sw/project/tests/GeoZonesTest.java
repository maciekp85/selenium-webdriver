package pl.sw.project.tests;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nishi on 2017-03-26.
 */
public class GeoZonesTest extends TestBase {

  @Test
  public void testCountries() {
    app.goTo().loginPage();
    app.session().login( "admin", "admin" );
    app.menu().moveTo("Geo Zones");
    boolean sorted = app.geo().checkIfZonesAreSortedInAlphabeticalOrder();
    Assert.assertTrue( sorted );
  }
}
