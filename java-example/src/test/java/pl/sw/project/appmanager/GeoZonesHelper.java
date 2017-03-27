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
 * Created by nishi on 2017-03-27.
 */
public class GeoZonesHelper extends HelperBase {

  public GeoZonesHelper(WebDriver wd) {
    super(wd);
  }

  public List<WebElement> list() {
    return wd.findElements( By.cssSelector("tr.row"));
  }

  public boolean checkIfZonesAreSortedInAlphabeticalOrder() {
    boolean isTrue = false;
    int size = list().size();
    List<WebElement> zones;
    List<String> zoneNames = new ArrayList<>(  );
    for (int i = 0; i < size; i++ ) {
      zones = list();
      zones.get( i ).findElement( By.xpath( "td[3]/a" ) ).click();
      new WebDriverWait( wd, 1000).until( ExpectedConditions.presenceOfAllElementsLocatedBy( By.cssSelector( "table.dataTable tr" )));
      List<WebElement> zonesList = wd.findElements( By.cssSelector( "table.dataTable tr" ));
      for (int j = 1; j < zonesList.size() - 1; j++) {
        if (!zonesList.get( j ).findElement( By.xpath( "td[3]/select" )).isEnabled()) {
          zoneNames.add( zonesList.get( j ).findElement( By.xpath( "td[3]/select" )).getText());
        } else {
          zoneNames.add( zonesList.get( j ).findElement( By.xpath( "td[3]/select/option[@selected='selected']" )).getText());
        }
      }
      List<String> zonesNamesSorted = sort( zoneNames );
      if (zoneNames == zonesNamesSorted) {
        isTrue = true;
      }
      zoneNames.clear();
      zonesNamesSorted.clear();
      wd.navigate().back();
    }
    return isTrue;
  }

  public List<String> sort(List<String> zones) {
    Collections.sort( zones );
    return zones;
  }
}
