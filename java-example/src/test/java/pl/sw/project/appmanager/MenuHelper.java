package pl.sw.project.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by nishi on 2017-03-06.
 */
public class MenuHelper extends HelperBase {

  public MenuHelper(WebDriver wd) {
    super(wd);
  }

  public void walkThroughTheAllElements() {
    int numberElementsInMenu = wd.findElements( By.cssSelector("ul#box-apps-menu li") ).size();
    int numberSubmenuElement = 0;
    for (int i = 0; i < numberElementsInMenu; i++){
      WebElement menuElement = wd.findElements( By.cssSelector("ul#box-apps-menu li") ).get( i + numberSubmenuElement );
      String menuText = menuElement.getText();
      menuElement.click();
      numberSubmenuElement = wd.findElements( By.cssSelector("ul#box-apps-menu ul li") ).size();
      for (int j = 1; j <= numberSubmenuElement; j++) {
        WebElement submenuElement = wd.findElement( By.cssSelector("ul#box-apps-menu ul li:nth-child(" + j + ")") );
        String textFromSubmenu = submenuElement.getText();
        submenuElement.click();
        String h1Text = wd.findElement( By.cssSelector( "h1" ) ).getText();
        if(menuText.equals( "Settings" )) {
          Assert.assertEquals( "Settings", h1Text );
        } else if (textFromSubmenu.equals( "Background Jobs" ) ) {
          Assert.assertEquals( "Job Modules", h1Text );
        } else if(menuText.equals( "Modules" ) ) {
          Assert.assertEquals( textFromSubmenu + " Modules", h1Text );
        } else if(textFromSubmenu.equals( "Scan Files" )) {
          Assert.assertEquals( textFromSubmenu + " For Translations", h1Text );
        } else {
          Assert.assertEquals( textFromSubmenu, h1Text );
        }
      }
    }
  }
}
