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
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import py.com.quality.DAO.VendedorDAO;
import py.com.quality.modelos.Usuario;
import py.com.quality.modelos.Vendedor;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "VendedorBuscarIdUsuario", urlPatterns = {"/vendedor/buscar/idusuario"})
public class VendedorBuscarIdUsuario extends HttpServlet {

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

            HttpSession sesion = request.getSession();
            Usuario usuarioLogueado = (Usuario) sesion.getAttribute("usuarioLogueado");

            int id_usuario = usuarioLogueado.getId_usuario();

           VendedorDAO vendedorDAO = new VendedorDAO();
            Vendedor vendedor = vendedorDAO.buscarIdusuario(id_usuario);

            JSONObject obj = new JSONObject();
            obj.put("id_vendedor", vendedor.getId_vendedor());
            obj.put("nombre_vendedor", vendedor.getNombre_vendedor());
            obj.put("id_seccion", vendedor.getSeccion().getId_seccion());
            obj.put("nombre_seccion", vendedor.getSeccion().getNombre_seccion());
            obj.put("fecha_inicioatencion", vendedor.getFecha_inicioatencion());
            obj.put("fecha_finatencion", vendedor.getFecha_finatencion());
            obj.put("id_estadovendedor", vendedor.getEstadovendedor().getId_estadovendedor());
            obj.put("descripcion_estadoatencion", vendedor.getEstadovendedor().getDescripcion_estadovendedor());
            obj.put("id_usuario", vendedor.getUsuario().getId_usuario());

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
