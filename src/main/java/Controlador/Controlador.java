/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Vista.VistaConsola;
import Vista.VistaMensajes;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class Controlador implements ActionListener {

    private Conexion conexion_bd;
    private boolean desconexionOK;
    private VistaPrincipal vPrincipal = null;
    private VistaConsola vConsola;
    private VistaMensajes vMensajes;

    public Controlador(Conexion conexion_bd) {
        
        this.conexion_bd = conexion_bd;
        vConsola = new VistaConsola();
        vPrincipal = new VistaPrincipal();
        vMensajes = new VistaMensajes();
        addListeners();
        JOptionPane.showMessageDialog(vPrincipal, "Llego al controlador");

        vPrincipal.setLocationRelativeTo(null);
        vPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Cerrar":
                this.desconexionOK = this.desconectar();

                if (this.desconexionOK) {
                    //JOptionPane.showMessageDialog(vPrincipal, "Llego al controlador");
                    this.vMensajes.MensajeInformacion("Desconectado con exito!");
                    this.vPrincipal.dispose();
                    this.vConsola.vistaConsolaLogin("Desconectado con exito!");
                    
                }
                else{
                    
                }
            break;

        }

    }

    private boolean desconectar() {

        boolean resultado = false;

        try {
            this.conexion_bd.desconexion();
            this.vConsola.vistaConsolaLogin("Desonectado de la BD con exito!");
            resultado = true;
        } catch (Exception e) {
            this.vConsola.vistaConsolaLogin("Error al desconectarse de la BD", e.getMessage());
        }

        return resultado;

    }

    private void addListeners() {
        vPrincipal.btn_Cerrar.addActionListener(this);
    }

}
