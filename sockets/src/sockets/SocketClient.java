package sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class SocketClient {

    public static void main(String[] args) {
//        para conectarns desde otros equipos hay que agregar cambiar 
//        localhost por la ip del servidor
        SocketCliente sk = new SocketCliente();
        sk.setVisible(true);
        /*
        String ip = "192.168.9.105";
        int puerto = 1234;
        System.out.println(" socket " + ip + " " + puerto);
        
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        
        try {
            //el cliente crea un socket para solicitar una conexion al SocketServer
            socketCliente = new Socket(ip, puerto);
            System.out.println("Enviando solicitud de conexion...");
            
            //obtengo la entrada y la salida de bytes 
            entrada = new BufferedReader( new InputStreamReader(socketCliente.getInputStream()));
            salida = new PrintWriter( new OutputStreamWriter(socketCliente.getOutputStream()), true);
            
            //añado el nombre que envia el cliente
            //salida.println("Emmanuel Oropeza");
            salida.println("100");
            //leo la respuesta del server 
            System.out.println("recibiendo ... " + entrada.readLine());
            
            //cerramos la conexion
            socketCliente.close();
        } catch (Exception e) {
            System.out.println("error: " + e.toString());
        }
*/
    }

}
