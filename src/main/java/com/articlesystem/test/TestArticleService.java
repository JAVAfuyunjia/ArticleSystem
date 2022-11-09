package com.articlesystem.test;

import com.articlesystem.dao.ArticleDao;
import com.articlesystem.dao.UserDao;
import com.articlesystem.entity.Article;
import com.articlesystem.entity.User;
import com.articlesystem.service.ArticleService;
import com.articlesystem.service.UserService;
import com.articlesystem.service.impl.ArticleServiceImpl;
import com.articlesystem.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.Date;

/**
 * @author 云佳
 * @create 2022-10-25 19:57
 * @只管耕耘，莫问收获。
 */
public class TestArticleService {
   ArticleDao articleDao  = new ArticleDao();
   ArticleService articleService = new ArticleServiceImpl();

   @Test
   public void testInertArticle(){
       Article article = new Article();
       article.setArticleUserId(5);
       article.setArticleCreateTime(new Date());
       article.setArticleThumbnail("localhost:99999");
       article.setArticleTitle("标题3");
       article.setArticleContent("内容3");
       article.setCategoryId(10008);
//       Integer primaryKey = articleDao.insert(article);
//       System.out.println(primaryKey);
       articleService.insertArticle(article);



   }


}
