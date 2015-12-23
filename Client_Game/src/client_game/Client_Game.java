/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client_game;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


/**
 *
 * @author bila
 */
public class Client_Game implements Runnable {

    private static Socket client_socket = null; //atribut "client_sokcet"
    private static DataInputStream is = null; //atribut "is"
    private static PrintStream os = null; // atribut "os"
    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    
    public static void main(String[] args) {
        
        int port_number = 3250; //port ke server
        
        String host = "10.151.32.30";
        
        if (args.length < 2){
            System.out.println("Sedang terhubung ke server " + host);
        }else{
            host = args[0];
        }
        
        //akses server socket
        try{
            client_socket = new Socket(host, port_number);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            
            os = new PrintStream(client_socket.getOutputStream());
            is = new DataInputStream(client_socket.getInputStream());
            
        }catch(Exception e){
            System.err.println("Don't know about host " + host);
        }
        
    
    
        
        if(client_socket != null && os != null && is != null){
            
            try{
                
                //ciptakan thread baru utk membaca pesan dari server
                new Thread(new Client_Game()).start();
                
                while(!closed){
                    os.println(inputLine.readLine().trim());
                }
                
                //close semuanya
                
                os.close();
                is.close();
                client_socket.close();
            }catch(Exception e){
                System.err.println("IOException : " + e);
            }
        }
    }
    @Override
    public void run() {
        System.out.println("Ini void run");
    }
}
