package pl.sw.project.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.sw.project.appmanager.ApplicationManager;

/**
 * Created by nishi on 2017-02-13.
 */
public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite
  public void setUp() {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }
}
