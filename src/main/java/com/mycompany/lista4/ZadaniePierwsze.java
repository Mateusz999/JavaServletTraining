/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lista4;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mateu
 */
public class ZadaniePierwsze extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private int wartoscZadania = 0;
    
    
    private void renderForm(PrintWriter out, int wartoscZadania) {
    out.println("<!DOCTYPE html>");
    out.println("<html><head><style>.container{display:flex;justify-content:center;align-items:center;flex-direction:column;margin-top:50px;}</style>");
    out.println("<title>Servlet ZadaniePierwsze</title></head><body>");
    out.println("<div class='container'>");
    out.println("<h1 style='text-align:center;'>Servlet ZadaniePierwsze - wykonane na ciasteczkach</h1>");
    out.println("<form method='post' action='ZadaniePierwsze'>");
    out.println("<input type='number' name='liczba' />");
    out.println("<input type='submit' value='Wyślij'>");
    out.println("</form>");
    out.println("<p>Podana wartość wynosi: " + wartoscZadania + "</p>");
    out.println("</div></body></html>");
}

    
    
    
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            renderForm(out,wartoscZadania);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        
        
        res.setContentType("text/html");
        
        PrintWriter out = res.getWriter();
        
        String num = req.getParameter("liczba");
        String lastNumber = "0";
        Cookie[] cookies = req.getCookies();
        if(cookies != null ){
            for(Cookie c : cookies ){
                if(c.getName().equals("liczba")){ lastNumber = c.getValue(); break; }
            }
        }
        
   
        try{
            if (num == null || num.trim().isEmpty()) {
                out.println("Błąd: pole liczba jest puste.");
                return;
               }
            int numer = Integer.parseInt(num);
             if(lastNumber != null) wartoscZadania = Integer.parseInt(lastNumber);

             wartoscZadania+=numer;
             Cookie cookie = new Cookie("liczba",String.valueOf(wartoscZadania));
             res.addCookie(cookie);
            renderForm(out,wartoscZadania);
            
        } catch(NumberFormatException  e){
            out.println("Błąd: nie podano liczby.");
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


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
   

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
