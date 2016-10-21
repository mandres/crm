/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.quality.contoladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import py.com.quality.DAO.ProductoDAO;
import py.com.quality.modelos.Dependencia;
import py.com.quality.modelos.Grupo;
import py.com.quality.modelos.Producto;
import py.com.quality.modelos.Talle;
import py.com.quality.modelos.Temporada;
import py.com.quality.modelos.Usuario;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "ProductoModificar", urlPatterns = {"/cproducto/modificar"})
public class ProductoModificar extends HttpServlet {

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
            int id_producto= Integer.parseInt(request.getParameter("id_producto"));
            String nombre_producto =  request.getParameter("nombre_producto");
            String codigo_producto = request.getParameter("codigo_producto");
            int id_grupo = Integer.parseInt(request.getParameter("id_grupo"));
            int id_temporada = Integer.parseInt(request.getParameter("id_temporadda"));
            int id_talle = Integer.parseInt(request.getParameter("id_talle"));
            int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
            int id_dependencia = Integer.parseInt(request.getParameter("id_dependencia"));
            int id_sexo = Integer.parseInt(request.getParameter("id_sexo"));
            int id_edad = Integer.parseInt(request.getParameter("id_edad"));
            
            HttpSession sesion = request.getSession();
            Usuario usuarioLogueado = (Usuario) sesion.getAttribute("usuarioLogueado");

            Producto producto = new Producto();
            
            producto.setId_producto(id_producto);
            producto.setNombre_producto(nombre_producto);
            producto.setCodigo_producto(codigo_producto);
            
            Grupo grupo= new Grupo();
            grupo.setId_grupo(id_grupo);
            
            producto.setId_grupo(grupo);
            
            Temporada temporada = new Temporada();
            temporada.setId_temporada(id_temporada);
         
            producto.setId_temporada(temporada);
            
            Talle talle = new Talle();
            talle.setId_talle(id_talle);
         
            producto.setId_talle(talle);
            
            producto.setId_categoria(id_categoria);
            
            Dependencia dependencia= new Dependencia();
            dependencia.setId_dependencia(id_dependencia);
            
            producto.setId_dependencia(dependencia);
            
            producto.setId_sexo(id_sexo);
            producto.setId_edad(id_edad);

           ProductoDAO productoDAO = new ProductoDAO();
           Map agregado = productoDAO.modificar(producto);

            JSONObject obj = new JSONObject();
            obj.put("modificado", agregado.get("ok"));
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
