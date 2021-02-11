/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;

import com.dao.ArticleDAOLocal;
import com.model.Article;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ArticleController", urlPatterns = {"/article"})
public class ArticleController extends HttpServlet {

    @EJB private ArticleDAOLocal articleDAO;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("form")){
            this.ArticleForm(request, response);
        }else if(action.equalsIgnoreCase("list")){
            this.AllArticles(request, response);
        }else{
            request.getRequestDispatcher("articles_list.jsp").forward(request, response);
        }
        
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void AllArticles (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Article> list = articleDAO.getAllArticles();
        request.setAttribute("list", list);
        request.getRequestDispatcher("articles_list.jsp").forward(request, response);
    }
    
    protected void ArticleForm (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codeStr = request.getParameter("code");
        int code = codeStr.equals("") ? 0 : Integer.parseInt(codeStr);
        String designation = request.getParameter("designation");
        String prixStr = request.getParameter("prix");
        double prix = prixStr.equals("") ? 0 : Double.parseDouble(prixStr);
        String operation = request.getParameter("operation");
        
        Article article = new Article(code,designation,prix);
       
        if (operation.equalsIgnoreCase("Add")) {
            articleDAO.addArticle(article);
        } else if (operation.equalsIgnoreCase("Edit")) {
            articleDAO.editArticle(article);
        } else if (operation.equalsIgnoreCase("Delete")) {
            articleDAO.deleteArticle(code);
        } else if (operation.equalsIgnoreCase("Search")) {
            article = articleDAO.getArticle(code);
        }

        request.setAttribute("article", article);
        request.getRequestDispatcher("articles_form.jsp").forward(request, response);
    }
}
