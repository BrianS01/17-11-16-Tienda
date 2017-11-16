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
import modelo.Producto;
import util.DbUtil;

public class ProductoDAO
{
    private Connection connection;

    public ProductoDAO()
    {
        connection = DbUtil.getConnection();
    }

    public void addUser(Producto user)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into producto(idProducto,nombre,precio) values (?, ?, ?)");
            preparedStatement.setInt(1, user.getIdProducto());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.setInt(3, user.getPrecio());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteUser(int idProducto)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from producto where idProducto=?");
            // Parameters start with 1
            preparedStatement.setInt(1, idProducto);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateUser(Producto user)
    {
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update producto set idProducto=?, nombre=?, precio=?" + "where idProducto=?");
            preparedStatement.setInt(1, user.getIdProducto());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.setInt(3, user.getPrecio());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Producto> getAllUsers()
    {
        List<Producto> users = new ArrayList<Producto>();
        try
        {
            System.out.println("Llegue hasta aca");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from producto");
            
            while (rs.next())
            {
                Producto user = new Producto();
                user.setIdProducto(rs.getInt("idProducto"));
                user.setNombre(rs.getString("nombre"));
                user.setPrecio(rs.getInt("precio"));
                users.add(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public Producto getUserById(int idProducto)
    {
        Producto user = new Producto();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from producto where idProducto=?");
            preparedStatement.setInt(1, idProducto);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                user.setIdProducto(rs.getInt("idProducto"));
                user.setNombre(rs.getString("nombre"));
                user.setPrecio(rs.getInt("precio"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}