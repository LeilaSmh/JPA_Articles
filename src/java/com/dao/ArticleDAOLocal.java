/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import com.model.Article;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dell
 */
@Local
public interface ArticleDAOLocal {

    void addArticle(Article article);

    void editArticle(Article article);

    void deleteArticle(int code);

    Article getArticle(int code);

    List<Article> getAllArticles();
    
}