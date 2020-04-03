
package comunicacion_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servidor_UDP {
     public static void main(String[] args) {

        final int PUERTO = 5000;
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Iniciado el servidor UDP");
            
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            
            while (true) {
                
                
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                
                
                socketUDP.receive(peticion);
                System.out.println("Recibo la informacion del cliente");
                
                
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);

                
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();

                mensaje = "¡Hola mundo desde el servidor!";
                buffer = mensaje.getBytes();

                
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                
                System.out.println("Envio la informacion del cliente");
                socketUDP.send(respuesta);
                
            }

        } catch (SocketException ex) {
            Logger.getLogger(Servidor_UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
