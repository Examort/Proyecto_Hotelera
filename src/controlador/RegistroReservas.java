/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import bd.Conexion;
import java.sql.Connection;
import modelo.Reserva;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FreddyMontaño
 */
public class RegistroReservas {

    public boolean agregar(Reserva reserva) {

        Date date, date2;
        
        try {

            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();

            date = reserva.getFechain();
            date2 = reserva.getFechafin();
            System.out.println(date);
            System.out.println(date2);
            String query = "INSERT INTO reserva(idreserva,fechain,fechafin,nrohuespedes,rut) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, reserva.getIdreserva());
            stmt.setDate(2, new java.sql.Date(date.getTime()));
            stmt.setDate(3, new java.sql.Date(date2.getTime()));
            stmt.setInt(4, reserva.getNrohuespedes());
            stmt.setInt(5, reserva.getRut());
            System.out.println(reserva.getIdreserva());

            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar Huesped "+e.getMessage());
            return false;
        } 
//        catch (Exception e) {
//            System.out.println("Error al agregar huesped "+e.getMessage());
//            return false;
//        }                

    }
    
       public boolean actualizar(Reserva reserva){
        
        Date date;
        try {
            
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            date = reserva.getFechain();
            date = reserva.getFechafin();
            
            
            String query = "UPDATE reserva SET fechain=?,fechafin =?,nrohuespedes=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setDate(2, new java.sql.Date(date.getTime()));
            stmt.setDate(3, new java.sql.Date(date.getTime()));
            stmt.setInt(4, reserva.getNrohuespedes());
            
            
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al Actualizar reserva");
            return false;
        } catch(Exception e){
            System.out.println("Error al Actualizar reserva");
            return false;
        }
    }
       
       public boolean eliminar(int idReserva){
        
       
        try {
            
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            
            String query = "DELETE FROM reserva WHERE idReserva=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1,idReserva);
 
            stmt.executeUpdate();
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al eliminar reserva");
            return false;
        } catch(Exception e){
            System.out.println("Error al eliminar reserva");
            return false;
        }
        
    }
       
       
      public Reserva buscarPorReserva(int idReserva){
        Reserva reserva = new Reserva();
        try {
            
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            
            String query = "SELECT * FROM reserva WHERE idReserva=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1,idReserva);
            
            ResultSet rsReserva = stmt.executeQuery();
            
            if (rsReserva.next()) {
                reserva.setIdreserva(rsReserva.getInt("idReserva"));
                reserva.setFechain(rsReserva.getDate("fechain"));
                reserva.setFechafin(rsReserva.getDate("fechafin"));
                reserva.setNrohuespedes(rsReserva.getInt("nrohuespedes"));               
            }
            rsReserva.close();
            stmt.close();
            cnx.close();
            
        } catch (SQLException e) {
            System.out.println("Error SQL al listar reserva por id " + e.getMessage());
        }
        return reserva;
    }  
      
       public List<Reserva> buscarTodasReservas(){
        
        List<Reserva> listaReserva = new ArrayList<>();
        
        
        try {
            
            Conexion con = new Conexion();
            Connection cnx = con.obtenerConexion();
            
            
            String query = "SELECT * FROM reserva order by idreserva";
            PreparedStatement stmt = cnx.prepareStatement(query);
      
            ResultSet rsReserva = stmt.executeQuery();
            
            while (rsReserva.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdreserva(rsReserva.getInt("idreserva"));
                reserva.setFechain(rsReserva.getDate("fechain"));
                reserva.setFechafin(rsReserva.getDate("fechafin"));
                reserva.setNrohuespedes(rsReserva.getInt("nrohuespedes"));
                
                listaReserva.add(reserva);
            }
            rsReserva.close();
            stmt.close();
            cnx.close();
            
        } catch (SQLException e) {
            System.out.println("Error SQL al listar todas las reservas" + e.getMessage());
        }
        return listaReserva;
        
    }
       
       
     public boolean buscarReserva(List<Reserva> listaReserva, int idreserva ){
        
        for (Reserva reserva : listaReserva) {
            if (reserva.equals(idreserva)) {
                return true;
            }
        }
        return false;
    }

}
