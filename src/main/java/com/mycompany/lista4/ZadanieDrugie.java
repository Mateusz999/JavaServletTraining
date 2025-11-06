/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lista4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mateu
 */
public class ZadanieDrugie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
        private int iloscZycia = 10;
        private int losKomputer = 1;
        private int losGracza = 2;
    
    private void renderForm(PrintWriter out, int iloscZycia,int losKomputer, int losGracza) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><style>.container{display:flex;justify-content:center;align-items:center;flex-direction:column;margin-top:50px;}</style>");
            out.println("<title>Servlet ZadanieDrugie</title></head><body>");
            out.println("<div class='container'>");
            out.println("<h1 style='text-align:center;'>Servlet ZadanieDrugie - wykonane na ciasteczkach</h1>");
           

        if(iloscZycia <  1){
             out.println("<p>Wlasnie przegrales</p>");
        }else {
            out.println("<form method='post' action='ZadanieDrugie'>");
            out.println("<input type='number' name='liczba' />");
            out.println("<input type='submit' value='Wyślij'>");
            out.println("</form>");
            out.println("<p>Twoja ilosc zyc wynosi: " + iloscZycia + "</p>");
            out.println("<p>Poprzednia wartosc wylosowana przez komputer: " + losKomputer + "</p>");
            out.println("<p>Poprzednia wartosc wylosowana przez Ciebie: " + losGracza + "</p>");
            out.println("</div></body></html>");
        }

   
}
    
     
    private int getRandom(){
        Random rand = new Random();
        int result = rand.nextInt(6)+1;
        return result;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String zycia = "10", komputer = "1", gracz = "2";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("zycia")) zycia = c.getValue();
                    if (c.getName().equals("komputer")) komputer = c.getValue();
                    if (c.getName().equals("gracz")) gracz = c.getValue();
                }
            } else {
                zycia = "10";
            }

           renderForm(out, Integer.parseInt(zycia), Integer.parseInt(komputer), Integer.parseInt(gracz));

        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String zycia,komputer,gracz;
        
        zycia = "10";

        Cookie[] cookies = request.getCookies();
        
        if(cookies !=null){
            for(Cookie c : cookies){
                if(c.getName().equals("zycia")) zycia = c.getValue(); 
                if(c.getName().equals("komputer")) komputer = c.getValue(); 
                if(c.getName().equals("gracz")) gracz = c.getValue(); 
               
            }
        }
        
        String input = request.getParameter("liczba");
        if (input == null || input.trim().isEmpty()) {
            out.println("<p style='color:red;'>Err: wpisz liczbe od 1 do 6.</p>");
            return;
        } else {
            
        try {
            int gracz_input = Integer.parseInt(input);
            if (gracz_input < 1 || gracz_input > 6) {
                out.println("<p style='color:red;'>Err: liczba musi byc od 1 do 6.</p>");
                return;
            }
        } catch (NumberFormatException e) {
            out.println("<p style='color:red;'>Err: wpisz poprawna liczbe.</p>");
            return;
        }
        }
        int komputer_generator = getRandom();
int gracz_input = Integer.parseInt(request.getParameter("liczba"));

        if(gracz_input != komputer_generator ) {
            int temp_zycie = Integer.parseInt(zycia);
            temp_zycie--;
            zycia = String.valueOf(temp_zycie);
        } else{
           int temp_zycie = Integer.parseInt(zycia);
            temp_zycie++;
            zycia = String.valueOf(temp_zycie); 
        }
        
        Cookie zycia_cookie = new Cookie("zycia",zycia);
        Cookie komputer_cookie = new Cookie("komputer", String.valueOf(komputer_generator));
        Cookie gracz_cookie = new Cookie("gracz",String.valueOf(gracz_input));
        response.addCookie(zycia_cookie);
        response.addCookie(komputer_cookie);
        response.addCookie(gracz_cookie);
        
        renderForm( out,  Integer.parseInt(zycia), komputer_generator,  gracz_input);
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
