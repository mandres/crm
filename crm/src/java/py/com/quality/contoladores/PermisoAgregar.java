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
import py.com.quality.DAO.PermisoDAO;
import py.com.quality.modelos.Formulario;
import py.com.quality.modelos.Permiso;
import py.com.quality.modelos.Rol;
import py.com.quality.modelos.Usuario;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "PermisoAgregar", urlPatterns = {"/permiso/agregar"})
public class PermisoAgregar extends HttpServlet {

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
            int id_rol = Integer.parseInt(request.getParameter("id_rol"));
            int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));
            boolean agregar_permiso = Boolean.valueOf(request.getParameter("agregar"));
            boolean modificar_permiso = Boolean.valueOf(request.getParameter("modificar"));
            boolean eliminar_permiso = Boolean.valueOf(request.getParameter("eliminar"));
            boolean listar_permiso = Boolean.valueOf(request.getParameter("listar"));

            HttpSession sesion = request.getSession();
            Usuario usuarioLogueado = (Usuario) sesion.getAttribute("usuarioLogueado");

            Rol rol = new Rol();
            rol.setId_rol(id_rol);

            Formulario formulario = new Formulario();
            formulario.setId_formulario(id_formulario);

            Permiso permiso = new Permiso();
            permiso.setRol(rol);
            permiso.setFormulario(formulario);
            permiso.setAgregar_permiso(agregar_permiso);
            permiso.setModificar_permiso(modificar_permiso);
            permiso.setEliminar_permiso(eliminar_permiso);
            permiso.setListar_permiso(listar_permiso);
            permiso.getUsuario_auditoria().setId_usuario(usuarioLogueado.getId_usuario());

            PermisoDAO permisoDAO = new PermisoDAO();
            boolean agregado = permisoDAO.agregar(permiso);

            JSONObject obj = new JSONObject();
            obj.put("agregado", agregado);

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
