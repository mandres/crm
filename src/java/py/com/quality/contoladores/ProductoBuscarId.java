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
import py.com.quality.DAO.ProductoDAO;
import py.com.quality.modelos.Producto;


/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "ProductoBuscarId", urlPatterns = {"/producto/buscarId"})
public class ProductoBuscarId extends HttpServlet {

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
            int id_producto = Integer.parseInt(request.getParameter("id_producto"));

            ProductoDAO productoDAO = new ProductoDAO();
            Producto producto = productoDAO.buscarId(id_producto);

            JSONObject obj = new JSONObject();
            
            obj.put("id_producto", producto.getId_producto());
            obj.put("nombre_producto", producto.getNombre_producto());
            obj.put("codigo_producto", producto.getCodigo_producto());
            obj.put("id_grupo", producto.getId_grupo());
            obj.put("id_temporada", producto.getId_temporada());
            obj.put("id_talle", producto.getId_talle());
            obj.put("id_categoria", producto.getId_categoria());
            obj.put("id_dependdencia", producto.getId_dependencia());
            obj.put("id_sexo", producto.getId_sexo());
            obj.put("id_edad", producto.getId_edad());
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
