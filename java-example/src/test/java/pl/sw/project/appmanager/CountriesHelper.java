package pl.sw.project.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nishi on 2017-03-26.
 */
public class CountriesHelper extends HelperBase {

  public CountriesHelper(WebDriver wd) {
    super(wd);
  }

  public boolean checkIfCountriesWithGeoZonesAreSortedInAlphabeticalOrder() {
    boolean isTrue = false;
    int size = wd.findElements( By.cssSelector( "tr.row" ) ).size();
    List<WebElement> countriesList;
    WebElement country;
    List<String> countriesNames = new ArrayList<>( );
    int zones = 0;

    for (int i = 0; i < size; i++) {
      countriesList = wd.findElements( By.cssSelector( "tr.row" ) );
      country =  countriesList.get( i ).findElement( By.xpath( "td[6]" ));
      zones = Integer.parseInt( country.getText());
      if (zones != 0) {
        countriesList.get( i ).findElement( By.cssSelector( "td a" )).click();
        new WebDriverWait( wd, 1000).until( ExpectedConditions.presenceOfAllElementsLocatedBy( By.cssSelector( "table.dataTable tr" )));
        List<WebElement> countriesListWithZones = wd.findElements( By.cssSelector( "table.dataTable tr" ));
        for (int j = 1; j < countriesListWithZones.size() - 1; j++) {
          countriesNames.add( countriesListWithZones.get( j ).findElement( By.xpath( "td[3]" ) ).getText() );
        }
        List<String> countriesNamesSorted = sort( countriesNames );
        if (countriesNames == countriesNamesSorted) {
          isTrue = true;
        }
        countriesNames.clear();
        countriesNamesSorted.clear();
        wd.navigate().back();
      }
    }

    return isTrue;
  }

  public List<String> list() {
    List<WebElement> countriesList = wd.findElements( By.cssSelector( "tr.row" ) );
    List<String> countriesNames = new ArrayList<>( );
    for (WebElement element: countriesList) {
      countriesNames.add( element.findElement( By.cssSelector( "td a" ) ).getText() );
    }
    return countriesNames;
  }

  public List<String> sort(List<String> countries) {
    Collections.sort( countries );
    return countries;
  }
}
