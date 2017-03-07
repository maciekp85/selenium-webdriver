package pl.sw.project.tests;

import org.junit.Test;

/**
 * Created by nishi on 2017-03-07.
 */
public class NumberOfStickersTest extends TestBase {

  @Test
  public void testNumberOfStickers() {
    app.goTo().mainPage();
    app.article().checkNumberOfStickers();
  }
}
