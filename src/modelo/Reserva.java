/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Freddy Montaño Pinto
 */
public class Reserva {
    
    private int idreserva;
    private Date fechain;
    private Date fechafin;
    private int nrohuespedes;
    private int rut;

    public Reserva() {
    }

    public Reserva(int idreserva, Date fechain, Date fechafin, int nrohuespedes, int rut) {
        this.idreserva = idreserva;
        this.fechain = fechain;
        this.fechafin = fechafin;
        this.nrohuespedes = nrohuespedes;
        this.rut = rut;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public Date getFechain() {
        return fechain;
    }

    public void setFechain(Date fechain) {
        this.fechain = fechain;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public int getNrohuespedes() {
        return nrohuespedes;
    }

    public void setNrohuespedes(int nrohuespedes) {
        this.nrohuespedes = nrohuespedes;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

   

    
}
