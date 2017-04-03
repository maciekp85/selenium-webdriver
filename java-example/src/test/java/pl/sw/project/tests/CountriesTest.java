package pl.sw.project.tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by nishi on 2017-03-26.
 */
public class CountriesTest extends TestBase {

  @Test
  public void testCountries() {
    app.goTo().loginPage();
    app.session().loginAsAdmin( "admin", "admin" );
    app.menu().moveTo("Countries");
    List<String> countries = app.countries().list();
    List<String> sortedCountries = app.countries().sort(countries);
    Assert.assertTrue(countries == sortedCountries);
    boolean sorted = app.countries().checkIfCountriesWithGeoZonesAreSortedInAlphabeticalOrder();
    Assert.assertTrue( sorted );
  }
}
