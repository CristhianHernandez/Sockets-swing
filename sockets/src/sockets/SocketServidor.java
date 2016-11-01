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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Alumno
 */
public class SocketServidor extends JFrame {

    double resultado;
    JTextField pantalla;
    JPanel pNumeros, pOperacion;
    int operacion;
    String nombre = "";
    JPanel panel = (JPanel) this.getContentPane();

    public SocketServidor() {
        super();
        setTitle("Servidor");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        panel.setLayout(new BorderLayout());

        pantalla = new JTextField("", 20);
        pantalla.setBorder(new EmptyBorder(10, 10, 10, 10));
        pantalla.setFont(new Font("Arial", Font.BOLD, 25));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(true);
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
        botonOperacion("Conectar");

        validate();
    }

    public void botonOperacion(String tecla) {
        JButton btn = new JButton(tecla);
        btn.setForeground(Color.DARK_GRAY);

        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                try {
                    Conexion(btn.getText());
                } catch (IOException ex) {
                    Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        pOperacion.add(btn);
    }
    public void Conexion(String tecla) throws IOException{
        if(tecla.equals("Conectar"))
        {
                    BufferedReader entrada = null;
        PrintWriter salida = null;

        Socket socket = null;
        //se crea una instancia de ServerSocket que estara atendiendo en el puerto 1234
        ServerSocket serverSocket = new ServerSocket(1234);
        pantalla.setText("Esperando conexion de cliente en el puerto 1234...");
        
        while (true) {
            try {
                //el ServerSocket da el acceso Socket al cliente que lo solicito
                socket = serverSocket.accept();
                
                //se obtiene informacion(IP) del cliente
                pantalla.setText("Conexion establecida desde la IP: " + socket.getInetAddress() +" Con puerto: "+socket.getPort());
                
               //obtengo la entrada y la salida de bytes 
               entrada = new BufferedReader( new InputStreamReader(socket.getInputStream()));
               salida = new PrintWriter( new OutputStreamWriter(socket.getOutputStream()), true);
               //leo el nombre que envia el cliente
               double numero = Double.parseDouble(entrada.readLine()); 
             double resultado = 1;
                 for (double i = 1; i <= numero; i++) {
            resultado *= i;
        }
                
               
               //regreso un saludo como respuesta al cliente
                String saludoServer = "El numero es " + resultado + "!!";
                salida.println(saludoServer);
                socket.close();

            } catch (IOException e) {
                System.out.println(e);
            }
        }
        }
    }

}
