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

    public void deleteUser(int cedula)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from artista where cedula=?");
            // Parameters start with 1
            preparedStatement.setInt(1, cedula);
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

    public Producto getUserById(int cedula)
    {
        Producto user = new Producto();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from artista where cedula=?");
            preparedStatement.setInt(1, cedula);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                user.setCedula(rs.getInt("cedula"));
                user.setNombre(rs.getString("firstname"));
                user.setEdad(rs.getInt("edad"));
                user.setObra(rs.getString("obra"));
                user.setEstilo(rs.getString("estilo"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
