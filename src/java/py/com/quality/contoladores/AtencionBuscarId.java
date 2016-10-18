/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.quality.contoladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import py.com.quality.DAO.AtencionDAO;
import py.com.quality.modelos.Atencion;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "AtencionBuscarId", urlPatterns = {"/atencion/buscarId"})
public class AtencionBuscarId extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            int id_atencion = Integer.parseInt(request.getParameter("id_atencion"));

            AtencionDAO atencionDAO = new AtencionDAO();

            Atencion atencion = atencionDAO.buscarId(id_atencion);
           
            JSONObject obj = new JSONObject();
            obj.put("id_atencion", atencion.getId_atencion());
            obj.put("id_vendedor", atencion.getVendedor().getId_vendedor());
            obj.put("nombre_vendedor", atencion.getVendedor().getNombre_vendedor());
            obj.put("fechahora_recepcion", atencion.getFechahora_recepcion());
            obj.put("fechahora_asignacion", atencion.getFechahora_asignacion());
            obj.put("fechahora_inicioatencion", atencion.getFechahora_inicioatencion());
            obj.put("fechahora_finatencion", atencion.getFechahora_finatencion());

            out.print(obj);
            out.flush();
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

}
