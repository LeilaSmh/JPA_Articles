package com.dao;

import com.model.Article;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author Dell
 */
@Stateless
public class ArticleDAO implements ArticleDAOLocal{
    @PersistenceUnit
        EntityManagerFactory emf;
    @PersistenceContext
        private EntityManager em;
    
    public ArticleDAO(){
        emf = Persistence.createEntityManagerFactory("ArticlesPU");
        em = emf.createEntityManager();
    }
    
    @Override
    public void addArticle(Article article) {
        em.persist(article);
    }
    @Override
    public void editArticle(Article article) {
        em.merge(article);
    }

    @Override
    public void deleteArticle(int code){
        Article article = em.find(Article.class,code);
        em.remove(article);
    }

    @Override
    public Article getArticle(int code) {
        return em.find(Article.class, code);
    }

    @Override
    public List<Article> getAllArticles() {
       return em.createNamedQuery("Article.getAll").getResultList();
    }
}