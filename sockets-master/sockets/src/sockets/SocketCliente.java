/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.net.*;

/**
 *
 * @author cristhian
 */
public class SocketCliente extends JFrame {

    float resultado;
    JTextField pantalla;
    JPanel pNumeros, pOperacion;
    int operacion;
    String nombre = "";
    JPanel panel = (JPanel) this.getContentPane();

    public SocketCliente() {
        super();
        setTitle("SocketCliente");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        // JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());

//        pantalla.setBackground(Color.LIGHT_GRAY);
        pantalla = new JTextField("", 20);
        pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
        pantalla.setFont(new Font("Arial", Font.BOLD, 25));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);
        //pantalla.disable();
        panel.add("North", pantalla);

        pOperacion = new JPanel();
        pOperacion.setLayout(new GridLayout(6, 1));
        pOperacion.setBorder(new EmptyBorder(4, 4, 4, 4));

        panel.add("East", pOperacion);

        pNumeros = new JPanel();
        pNumeros.setLayout(new GridLayout(6, 4));
        pNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));
        pNumeros.setVisible(true);
        panel.add("South", pNumeros);

        //JTextField ip = new JTextField("Ip", 20);
        botonNumeros("Conectar");
        validate();
    }

    public void botonNumeros(String numero) {
        JButton btn = new JButton(numero);
        btn.setForeground(Color.DARK_GRAY);

        btn.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                conexion(btn.getText());

            }

        });

        pNumeros.add(btn);
    }

    public void conexion(String tecla) {


      
        
        int puerto = 1234;
       String ip = "127.0.0.1";
       
                 pantalla.setText(" socket " + ip + " " + puerto);
                pantalla.requestFocus();
        if (tecla.equals("Conectar")) {
            try {
                Socket sk = new Socket(ip, puerto);
                InputStreamReader msj =  new InputStreamReader(sk.getInputStream());
                PrintWriter salida = new PrintWriter(
                        new OutputStreamWriter(sk.getOutputStream()), true);
                JOptionPane.showMessageDialog(null, "imgrese su mensaje");
                pantalla.setText("enviando...");
                salida.println("Nombre usuario: " + " " + "agrege su nombre aqui");
                pantalla.setText("recibiendo ... " + msj);
                sk.close();
            } catch (Exception e) {
                pantalla.setText("error: " + e.toString());
            }
        }
    }
}