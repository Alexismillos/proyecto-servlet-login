/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyectopruservlet.loginServlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author USUARIO
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          /* out.println(request.getParameter("Usuario"));
            out.println(request.getParameter("Password"));*/
          
         String usuarios=request.getParameter("usuarios");
         String password=request.getParameter("password");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.getLogger(loginServlet.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            String url = "jdbc:mysql://localhost:3306/proyecto1";
        
        //Punto 5 Crear objetos de tipo conexion con Driver Maneger
        Connection conexion;
        Statement st;
        ResultSet rs;
        
            try {
                conexion=DriverManager.getConnection(url, "root","Millonarios25.");
                
                st=conexion.createStatement();
                
                rs=st.executeQuery("SELECT * FROM usuarios WHERE Nomusu='"+usuarios+"'AND passus='"+password+"'");
                if(rs.next()){
                    request.getSession().setAttribute("Nomusu", usuarios);
                    response.sendRedirect("panel.jsp");
                }else{
                     response.sendRedirect("index.html");
                }
                    
            } catch (SQLException ex) {
                System.getLogger(loginServlet.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
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
    }
}
// </editor-fold>


