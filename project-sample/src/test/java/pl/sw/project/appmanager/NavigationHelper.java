package pl.sw.project.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by nishi on 2017-02-13.
 */
public class NavigationHelper {

  protected WebDriver wd;

  public NavigationHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void googlePage() {
    wd.get( "https://www.google.pl/" );
  }
}
