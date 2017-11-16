/*
 *      Author ::: Brian Sterling
 *     Program ::: Bases de Datos
 *  Credential ::: SIST0008-G01:SIV
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import util.DbUtil;

public class EmpleadoDAO
{
    private Connection connection;

    public EmpleadoDAO()
    {
        connection = DbUtil.getConnection();
    }

    public void addUser(Empleado user)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into empleado(idEmpleado,nombre) values (?, ?)");
            preparedStatement.setInt(1, user.getIdEmpleado());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteUser(int idEmpleado)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from empleado where idEmpleado=?");
            // Parameters start with 1
            preparedStatement.setInt(1, idEmpleado);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateUser(Empleado user)
    {
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update empleado set idEmpleado=?, nombre=?" + "where idEmpleado=?");
            preparedStatement.setInt(1, user.getIdEmpleado());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Empleado> getAllUsers()
    {
        List<Empleado> users = new ArrayList<Empleado>();
        try
        {
            System.out.println("Llegue hasta aca");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from empleado");
            
            while (rs.next())
            {
                Empleado user = new Empleado();
                user.setIdEmpleado(rs.getInt("idEmpleado"));
                user.setNombre(rs.getString("nombre"));
                users.add(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public Empleado getUserById(int idProducto)
    {
        Empleado user = new Empleado();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from empleado where idEmpleado=?");
            preparedStatement.setInt(1, idProducto);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                user.setIdEmpleado(rs.getInt("idEmpleado"));
                user.setNombre(rs.getString("nombre"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}