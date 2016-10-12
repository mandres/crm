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
import py.com.quality.DAO.ClienteDAO;
import py.com.quality.modelos.Cliente;
import py.com.quality.utiles.Util;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "ClienteBuscarId", urlPatterns = {"/cliente/buscarId"})
public class ClienteBuscarId extends HttpServlet {

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
            int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.buscarId(id_cliente);

            JSONObject obj = new JSONObject();
            
            obj.put("id_cliente", cliente.getId_cliente());
            obj.put("codigo_cliente", cliente.getCodigo_cliente());
            obj.put("nombre_cliente", cliente.getNombre_cliente());
            obj.put("direccion_cliente", cliente.getDireccion_cliente());
            obj.put("id_ciudad", cliente.getCiudad().getId_ciudad());
            obj.put("fecha_nacimiento_cliente", Util.sqlDateToString(cliente.getFecha_nacimiento_cliente()));
            obj.put("mail_cliente", cliente.getMail_cliente());
            obj.put("telefono_cliente", cliente.getTelefono_cliente());
            obj.put("contacto_cliente", cliente.getContacto_cliente());
            obj.put("actualizacion_cliente", String.valueOf(cliente.getActualizacion_cliente()));
            obj.put("id_usuario_auditoria", cliente.getUsuario_auditoria().getId_usuario());
            obj.put("id_tipocliente", cliente.getTipocliente().getId_tipocliente());
            obj.put("cedula_cliente", cliente.getCedula_cliente());
            obj.put("ruc_cliente", cliente.getRuc_cliente());

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
