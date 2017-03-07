package pl.sw.project.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by nishi on 2017-03-07.
 */
public class ArticleHelper extends HelperBase {

  public ArticleHelper(WebDriver wd) {
    super(wd);
  }

  public void checkNumberOfStickers() {
    List<WebElement> articleSets = wd.findElements( By.cssSelector("ul.listing-wrapper.products"));
    for (int i = 0; i < articleSets.size(); i++) {
      List<WebElement> articlesListOnSet = articleSets.get( i ).findElements( By.cssSelector( "li" ) );
      for (int j = 0; j < articlesListOnSet.size(); j++) {
        WebElement article = articlesListOnSet.get( j );
        int stickersNumberInArticle = article.findElements( By.cssSelector( "div.image-wrapper > div.sticker" ) ).size();
        Assert.assertTrue(stickersNumberInArticle == 1 );
      }
    }
  }
}
