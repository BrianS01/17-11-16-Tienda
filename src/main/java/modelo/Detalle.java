/*
 *      Author ::: Brian Sterling
 *     Program ::: Bases de Datos
 *  Credential ::: SIST0008-G01:SIV
 */

package modelo;

import java.util.Date;

public class Detalle
{
    private int idDetalle;
    private int cedula;
    private String nombreCliente;
    private Date fecha;

    public int getIdDetalle()
    {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle)
    {
        this.idDetalle = idDetalle;
    }

    public int getCedula()
    {
        return cedula;
    }

    public void setCedula(int cedula)
    {
        this.cedula = cedula;
    }

    public String getNombreCliente()
    {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente)
    {
        this.nombreCliente = nombreCliente;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    @Override
    public String toString()
    {
        return "Detalle{" + "idDetalle=" + idDetalle + ", cedula=" + cedula + ", nombreCliente=" + nombreCliente + ", fecha=" + fecha + '}';
    }
}