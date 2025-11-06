/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lista4;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mateu
 */
public class ZadanieTrzecie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void renderPage(PrintWriter out,int[] numbers){
        
            StringBuilder svg = new StringBuilder();
            int width = 700;
            int height = 300;
            int barWidth = 50;
            int spacing = 10;

            svg.append("<svg width='" + width + "' height='" + height + "' xmlns='http://www.w3.org/2000/svg'>");
             int xOffset = 0;
            for (int i = 0; i < numbers.length; i++) {
                if(numbers[i]>0){
                    int x = xOffset;
                    xOffset += barWidth + spacing;
                int barHeight = numbers[i]*15;
                int y = height - barHeight;

                svg.append("<rect x='").append(x)
                   .append("' y='").append(y)
                   .append("' width='").append(barWidth)
                   .append("' height='").append(barHeight)
                   .append("' fill='steelblue' />");

                svg.append("<text x='").append(x + barWidth / 2)
                   .append("' y='").append(height - 5)
                   .append("' font-size='12' text-anchor='middle'>")
                   .append(i)
                   .append("</text>");
                xOffset++;
                }
            }

            svg.append("</svg>");


        
        
        
        
            out.println("<!DOCTYPE html>");
            out.println("<html><head><style>.container{display:flex;justify-content:center;align-items:center;flex-direction:column;margin-top:50px;}</style>");
            out.println("<title>Servlet ZadanieTrzecie</title></head><body>");
            out.println("<div class='container'>");
            out.println("<h1 style='text-align:center;'>Servlet ZadanieTrzecie - wykonane na sesji</h1>");
            out.println("<form method='post' action='ZadanieTrzecie'>");
            out.println("<input type='number' name='liczba' />");
            out.println("<input type='submit' value='Wyślij'>");
            out.println("</form>");
            out.println("<h2>Wykres słupowy SVG</h2>");
            
            out.println(svg.toString());

          
            out.println("</div></body></html>");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        HttpSession session = request.getSession();
            int[] numbers = (int[]) session.getAttribute("numbers");

            if (numbers == null) {
                numbers = new int[10];
                session.setAttribute("numbers", numbers);
            }

        
        
        
                response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                renderPage(out,numbers);
            }
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

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession();
    int[] numbers = (int[]) session.getAttribute("numbers");
    if (numbers == null) {
        numbers = new int[10];
        session.setAttribute("numbers", numbers);
    }

    String param = request.getParameter("liczba");
    if (param == null || param.trim().isEmpty()) {
        renderPage(out, numbers);
        return;
    }

    int curr_param;
    try {
        curr_param = Integer.parseInt(param);
        if (curr_param < 0 || curr_param >= numbers.length) {
            renderPage(out, numbers);
            return;
        }
    } catch (NumberFormatException e) {
        renderPage(out, numbers);
        return;
    }

    numbers[curr_param]++;
    session.setAttribute("numbers", numbers); // aktualizacja w sesji
    renderPage(out, numbers);
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

}
