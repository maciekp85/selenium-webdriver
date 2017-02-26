package pl.sw.project.appmanager;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by nishi on 2017-02-13.
 */
public class ApplicationManager {

  private WebDriver wd;
  private NavigationHelper navigationHelper;
  private SessionHelper sessionHelper;
  private WebDriverWait wait;

  public void init() {

    // Chrome browser
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait( 0, TimeUnit.SECONDS );

    // Firefox browser - ESR version (without MARIONETTE, old schema)
/*
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability( FirefoxDriver.MARIONETTE, false );
    wd = new FirefoxDriver(
            new FirefoxBinary( new File( "C:\\Program Files (x86)\\Mozilla Firefox\\ESR\\firefox.exe" )),
            new FirefoxProfile(), caps);
    System.out.println( ((HasCapabilities) wd).getCapabilities());
    wd.manage().timeouts().implicitlyWait( 0, TimeUnit.SECONDS );
*/

    // Firefox browser - Nightly version (with MARIONETTE, new schema)
/*
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability( FirefoxDriver.MARIONETTE, true );
    wd = new FirefoxDriver(
            new FirefoxBinary( new File( "C:\\Program Files (x86)\\Mozilla Firefox\\Nightly\\firefox.exe" )),
            new FirefoxProfile(), caps);
    System.out.println( ((HasCapabilities) wd).getCapabilities());
    wait = new WebDriverWait(wd, 10 );
*/

    // Internet Explorer browser - IE 11 version (old schema)
/*    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability( InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true );
    caps.setCapability( InternetExplorerDriver.IGNORE_ZOOM_SETTING, true );
//    caps.setCapability( InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true );  // DO NOT USE!!!
    wd = new InternetExplorerDriver(caps);
    System.out.println( ((HasCapabilities) wd).getCapabilities());
    wait = new WebDriverWait(wd, 10 );*/

    // CREATE HELPERS OBJECTS
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper( wd );
  }

  public void stop() {
    wd.close();
    wd.quit();
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public SessionHelper session() {
    return sessionHelper;
  }
}
