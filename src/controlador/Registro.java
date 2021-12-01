/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import bd.Conexion;
import modelo.Huesped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FreddyMontaño
 */
public class Registro {

    public boolean agregar(Huesped huesped) {

        try {

            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "INSERT INTO huesped(rut,nombre,apellidop,apellidom,telefono,correo,direccion) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, huesped.getRut());
            stmt.setString(2, huesped.getNombre());
            stmt.setString(3, huesped.getApellidoP());
            stmt.setString(4, huesped.getApellidoM());
            stmt.setInt(5, huesped.getTelefono());
            stmt.setString(6, huesped.getCorreo());
            stmt.setString(7, huesped.getDireccion());

            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar Huesped");
            return false;
        } catch (Exception e) {
            System.out.println("Error al agregar huesped");
            return false;
        }
    }

    public boolean actualizar(Huesped huesped) {

        try {

            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "UPDATE huesped SET nombre=?,apellidop=?,apellidom=?,telefono=?,correo=?,direccion=? WHERE rut=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, huesped.getNombre());
            stmt.setString(2, huesped.getApellidoP());
            stmt.setString(3, huesped.getApellidoM());
            stmt.setInt(4, huesped.getTelefono());
            stmt.setString(5, huesped.getCorreo());
            stmt.setString(6, huesped.getDireccion());
            stmt.setInt(7, huesped.getRut());

            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al Actualizar datos huesped");
            return false;
        } catch (Exception e) {
            System.out.println("Error al Actualizar huesped");
            return false;
        }
    }

    public boolean eliminar(int rut) {

        try {

            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "DELETE FROM huesped WHERE rut=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, rut);

            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al eliminar datos");
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar datos");
            return false;
        }

    }

    public Huesped buscarPorId(int rut) {
        Huesped huesped = new Huesped();
        try {

            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "SELECT * FROM huesped WHERE rut=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, rut);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                huesped.setRut(rs.getInt("rut"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setApellidoP(rs.getString("apellidop"));
                huesped.setApellidoM(rs.getString("apellidom"));
                huesped.setTelefono(rs.getInt("telefono"));
                huesped.setCorreo(rs.getString("correo"));
                huesped.setDireccion(rs.getString("direccion"));
            }
            rs.close();
            stmt.close();
            cnx.close();

        } catch (SQLException e) {
            System.out.println("Error SQL al listar el huesped" + e.getMessage());
        }
        return huesped;
    }

    public List<Huesped> buscarTodos() {

        List<Huesped> lista = new ArrayList<>();

        try {

            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            String query = "SELECT * FROM huesped";
            PreparedStatement stmt = cnx.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Huesped huesped = new Huesped();
//                stmt.setInt(1, huesped.getRut());
//                stmt.setString(2, huesped.getNombre());
//                stmt.setString(3, huesped.getApellidoP());
//                stmt.setString(4, huesped.getApellidoM());
//                stmt.setInt(5, huesped.getTelefono());
//                stmt.setString(6, huesped.getCorreo());
//                stmt.setString(7, huesped.getDireccion());
                huesped.setRut(rs.getInt("rut"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setApellidoP(rs.getString("apellidoP"));
                huesped.setApellidoM(rs.getString("apellidoM"));
                huesped.setTelefono(rs.getInt("telefono"));
                huesped.setCorreo(rs.getString("correo"));
                huesped.setDireccion(rs.getString("direccion"));
                
                lista.add(huesped);
            }
            rs.close();
            stmt.close();
            cnx.close();

        } catch (SQLException e) {
            System.out.println("Error SQL al listar todos los huespedes " + e.getMessage());
        }
        return lista;

    }

    public boolean buscarHuesped(List<Huesped> lista, String nombre) {

        for (Huesped huesped : lista) {
            if (huesped.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

}
