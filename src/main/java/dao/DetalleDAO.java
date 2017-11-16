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
import modelo.Detalle;
import util.DbUtil;

public class DetalleDAO
{
    private Connection connection;

    public DetalleDAO()
    {
        connection = DbUtil.getConnection();
    }

    public void addUser(Detalle user)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into detalle(idDetalle,nombreCliente,cedula,fecha) values (?, ?, ?, ?)");
            preparedStatement.setInt(1, user.getIdDetalle());
            preparedStatement.setString(2, user.getNombreCliente());
            preparedStatement.setInt(3, user.getCedula());
            preparedStatement.setDate(4, new java.sql.Date(user.getFecha().getTime()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteUser(int idDetalle)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from detalle where idDetalle=?");
            // Parameters start with 1
            preparedStatement.setInt(1, idDetalle);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateUser(Detalle user)
    {
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update detalle set idDetalle=?, nombreCliente=?, cedula=?, fecha=?" + "where idDetalle=?");
            preparedStatement.setInt(1, user.getIdDetalle());
            preparedStatement.setString(2, user.getNombreCliente());
            preparedStatement.setInt(3, user.getCedula());
            preparedStatement.setDate(4, new java.sql.Date(user.getFecha().getTime()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Detalle> getAllUsers()
    {
        List<Detalle> users = new ArrayList<Detalle>();
        try
        {
            System.out.println("Llegue hasta aca");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from detalle");
            
            while (rs.next())
            {
                Detalle user = new Detalle();
                user.setIdDetalle(rs.getInt("idDetalle"));
                user.setNombreCliente(rs.getString("nombreCliente"));
                user.setCedula(rs.getInt("cedula"));
                user.setFecha(rs.getDate("fecha"));
                users.add(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public Detalle getUserById(int idDetalle)
    {
        Detalle user = new Detalle();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from detalle where idDetalle=?");
            preparedStatement.setInt(1, idDetalle);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                user.setIdDetalle(rs.getInt("idDetalle"));
                user.setNombreCliente(rs.getString("nombreCliente"));
                user.setCedula(rs.getInt("cedula"));
                user.setFecha(rs.getDate("fecha"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}