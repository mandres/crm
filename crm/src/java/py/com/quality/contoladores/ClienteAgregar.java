/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.quality.contoladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import py.com.quality.DAO.ClienteDAO;
import py.com.quality.modelos.Ciudad;
import py.com.quality.modelos.Cliente;
import py.com.quality.modelos.Usuario;
import py.com.quality.utiles.Util;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "ClienteAgregar", urlPatterns = {"/cliente/agregar"})
public class ClienteAgregar extends HttpServlet {

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

            String nombre_cliente = request.getParameter("nombre_cliente");
            String direccion_cliente = request.getParameter("direccion_cliente");
            int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
            Date fecha_nacimiento_cliente = Date.valueOf(Util.dmaToAmd(request.getParameter("fecha_nacimiento_cliente")));
            String mail_cliente = request.getParameter("mail_cliente");
            String telefono_cliente = request.getParameter("telefono_cliente");
            String contacto_cliente = request.getParameter("contacto_cliente");
            String cedula_cliente = request.getParameter("cedula_cliente");
            String ruc_cliente = request.getParameter("ruc_cliente");

            HttpSession sesion = request.getSession();
            Usuario usuarioLogueado = (Usuario) sesion.getAttribute("usuarioLogueado");

            Cliente cliente = new Cliente();

            cliente.setNombre_cliente(nombre_cliente);
            cliente.setDireccion_cliente(direccion_cliente);

            Ciudad ciudad = new Ciudad();
            ciudad.setId_ciudad(id_ciudad);

            cliente.setCiudad(ciudad);
            cliente.setFecha_nacimiento_cliente(fecha_nacimiento_cliente);
            cliente.setMail_cliente(mail_cliente);
            cliente.setTelefono_cliente(telefono_cliente);
            cliente.setContacto_cliente(contacto_cliente);
            cliente.setUsuario_auditoria(usuarioLogueado);

            cliente.setCedula_cliente(cedula_cliente);
            cliente.setRuc_cliente(ruc_cliente);

            ClienteDAO clienteDAO = new ClienteDAO();
            Map agregado = clienteDAO.agregar(cliente);

            JSONObject obj = new JSONObject();
            obj.put("agregado", agregado.get("ok"));
            obj.put("id_cliente", agregado.get("id_cliente"));
            obj.put("mensaje", agregado.get("mensaje"));
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
