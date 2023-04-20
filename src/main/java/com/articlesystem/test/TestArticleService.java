package com.articlesystem.test;

import com.articlesystem.Utils.PageUtils;
import com.articlesystem.dao.ArticleDao;
import com.articlesystem.entity.Article;
import com.articlesystem.service.ArticleService;
import com.articlesystem.service.impl.ArticleServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.List;

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
       article.setArticleUserId(11);
       article.setArticleCreateTime(new Date());
       article.setArticleThumbnail("http://localhost:8080/ArticleSystem/ArticleSystemImg/166844064628251271.jpg");
       //article.setArticleTitle("中华航展，重磅出击，歼击机20，运20");
       article.setArticleContent("本届珠海航展，第五代战斗机歼-20最抢眼。虽然歼-20过去也频频亮相珠海航展，但都是在空中表演完了就飞走了，从没有在地面上做过静态展示，所以，我们观众难以窥见全貌。而此次航展，歼-20首次落地进行静态展示，以供观众近距离参观，满足大家的好奇心和求知欲。\n" +
               "\n" +
               "歼-20是中国第一款第五代战斗机，同时，也是世界第一款拥有双座型的第五代战机，美国和俄罗斯都没有研发第五代战斗机的双座型，这是为啥呢？\n" +
               "\n" +
               "在五代机之前，各国研发的战斗机时，通常都会以单座型为基础，同时研发一款双座型。比如美国的F-16C单座型战斗机，就有与之配套的双座型F-16D。这个双座型F-16D也称教练型，功能上与单座型F-16C一样，只不过多了个座舱，既可用于作战，也可用来培训飞行员，以满足新老飞行员同乘飞行需求。但毕竟多了个座舱，所以，双座型在载荷、航程上，与单座型略有不同。但也有例外，如美国的F-15E就是双座型，但这个双座型就不是教练型，而是战斗轰炸机，前座舱是飞行员座舱，而后座舱是武器控制舱，两个座舱的功能是截然不同的。随着战斗机承担的任务越来越多，如对地攻击、电子战等，一名飞行员难以满足要求，于是，像F-15E这类的双座战斗机就脱颖而出。\n" +
               "\n" +
               "美国在研制五代机的时候，也曾计划研发双座机。然而，五代机同四代机相比，最大的优点就是隐身，而为了隐身，战斗机的所有外挂就要求藏在机身内部，以减少对雷达波的散射。所以，五代机机身内部非常紧凑，如果还要设计双座型，成本将大幅增加。再加上专用教练机和飞行模拟器的发展，使得美俄在研发五代机时，都没有同步研发双座教练型。");
       article.setCategoryId(100000011);

       for (int i = 0; i < 50; i++) {
           article.setArticleTitle(i+"中华航展，重磅出击，歼击机20，运20");
           articleService.insertArticle(article);
       }



   }
   @Test
   public void testGetarticles(){
       PageUtils<Article> pageIngo = articleService.getArticlePageInfo("", 1, 10);
       System.out.print(pageIngo.toString());
   }
    @Test
    public void testGetArticlesByCategoryId(){
        PageUtils<Article> pageIngo = articleService.getArticlePageInfoByCategoryId(100000010,1,5);
        System.out.print(pageIngo.toString());
    }
    @Test
    public void testGetArticlesByArticleId(){
        Article articleByArticleId = articleService.getArticleByArticleId(140);
        System.out.print(articleByArticleId);
    }
    @Test
    public void getArticlePageInfoByUserId(){
        PageUtils<Article> articlePageInfoByUserId = articleService.getArticlePageInfoByUserId(1, 10, 9);
        System.out.print(articlePageInfoByUserId);
    }

    @Test
    public void deleteArticleByArticleId(){

       for(int i=710;i<734;i++){
           articleService.deleteArticleByArticleId(i);
       }

    }

    @Test
    public void getCategoryIdByArticleId(){
        int categoryIdByArticleId = articleService.getCategoryIdByArticleId(560);
        System.out.println(categoryIdByArticleId);
    }

    @Test
    public void getArticleIdsByUserId(){
        List<Integer> articleIdsByUserId = articleService.getArticleIdsByUserId(11);
    }

    @Test
    public void getTenArticle(){
        List<Article> tenArticleRandom = articleService.getTenArticleRandom();
        System.out.println(tenArticleRandom);
    }
}
