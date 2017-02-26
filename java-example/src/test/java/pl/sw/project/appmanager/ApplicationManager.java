package pl.sw.project.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by nishi on 2017-02-13.
 */
public class ApplicationManager {

  private WebDriver wd;
  private NavigationHelper navigationHelper;
  private SessionHelper sessionHelper;

  public void init() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait( 0, TimeUnit.SECONDS );
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper( wd );
  }

  public void stop() {
    wd.quit();
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public SessionHelper session() {
    return sessionHelper;
  }
}