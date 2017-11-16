/*
 *      Author ::: Brian Sterling
 *     Program ::: Bases de Datos
 *  Credential ::: SIST0008-G01:SIV
 */

package controlador;
        
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductoDAO;
import modelo.Producto;

public class EmpleadoControlador extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/empleado.jsp";
    private static String LIST_USER = "/listarEmpleado.jsp";
    private ProductoDAO dao;

    public EmpleadoControlador()
    {
        super();
        dao = new ProductoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String forward = "";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("delete"))
        {
            int userId = Integer.parseInt(request.getParameter("idEmpleado"));
            dao.deleteUser(userId);
            forward = LIST_USER;
            request.setAttribute("empleado", dao.getAllUsers());
        }
        else if (action.equalsIgnoreCase("edit"))
        {
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("idEmpleado"));
            Producto user = dao.getUserById(userId);
            request.setAttribute("user", user);
        }
        else if (action.equalsIgnoreCase("listUser"))
        {
            forward = LIST_USER;
            request.setAttribute("empleado", dao.getAllUsers());
        }
        else
        {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Producto user = new Producto();
        user.setNombre(request.getParameter("nombre"));
        String idProducto = request.getParameter("idEmpleado");
        
        if (idProducto == null || idProducto.isEmpty())
        {
            dao.addUser(user);
        }
        else
        {
            user.setIdProducto(Integer.parseInt(idProducto));
            dao.updateUser(user);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("empleado", dao.getAllUsers());
        view.forward(request, response);
    }
}