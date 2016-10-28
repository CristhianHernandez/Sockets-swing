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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author cristhian
 */
public class SocketServidor extends JFrame {

    JTextField pantalla;
    JPanel pNumeros, pOperacion;
    int operacion;
    String nombre = "";
    JPanel panel = (JPanel) this.getContentPane();

    public SocketServidor() {
        super();
        setTitle("SocketServidor");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

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
        if (tecla.equals("Conectar")) {
            Vector<String> datosRecibidos = new Vector<String>();

            try {
                ServerSocket s = new ServerSocket(1234);
                pantalla.setText("Esperando conexiones...");

                while (true) {
                    Socket cliente = s.accept();
                    BufferedReader entrada = new BufferedReader(
                            new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(
                            new OutputStreamWriter(cliente.getOutputStream()), true);
                  String datos = "DATOS1";
                    if (datos.equals("DATOS")) {
                        for (int n = 0; n < datosRecibidos.size(); n++) {
                            pantalla.setText(datosRecibidos.get(n));
                        }
                    } else {
                        datosRecibidos.add(0, datos);
                        pantalla.setText("OK");
                    }
                    cliente.close();
                }
            } catch (IOException e) {
                pantalla.setText(e.toString());
            }
        }
       
    }
}
