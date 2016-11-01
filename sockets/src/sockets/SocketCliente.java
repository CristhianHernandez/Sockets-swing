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
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Alumno
 */
public class SocketCliente extends JFrame {
    double resultado;
    JTextField pantalla;
    JPanel pNumeros, pOperacion;
    int operacion;
    String nombre = "";
    JPanel panel = (JPanel) this.getContentPane();

    public SocketCliente() {
        super();
        setTitle("Cliente");
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
                Conexion(btn.getText());
            }
            
        });
        pOperacion.add(btn);
    }
    public void Conexion(String tecla){
        if(tecla.equals("Conectar"))
        {
                   String ip = "192.168.9.105";
        int puerto = 1234;
        System.out.println(" socket " + ip + " " + puerto);
        
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        
        try {
            //el cliente crea un socket para solicitar una conexion al SocketServer
            socketCliente = new Socket(ip, puerto);
            pantalla.setText("Enviando solicitud de conexion...");
            pantalla.setText("");
            //obtengo la entrada y la salida de bytes 
            entrada = new BufferedReader( new InputStreamReader(socketCliente.getInputStream()));
            salida = new PrintWriter( new OutputStreamWriter(socketCliente.getOutputStream()), true);
            
            //añado el nombre que envia el cliente
            //salida.println("Emmanuel Oropeza");
            salida.println("100");
            //leo la respuesta del server 
            pantalla.setText("recibiendo ... " + entrada.readLine());
            
            //cerramos la conexion
            socketCliente.close();
        } catch (Exception e) {
            pantalla.setText("error: " + e.toString());
        }
        }
    }

}
