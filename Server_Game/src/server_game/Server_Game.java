/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server_game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

/**
 *
 * @author Ade Ilham Fajri
 */

class ClientThread extends Thread{
    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket client_socket = null;
    private final ClientThread[] threads; //"threads" bukan "thread"
    private int max_clients;
    
    public ClientThread(Socket client_socket, ClientThread[] threads){
        this.client_socket = client_socket;
        this.threads = threads;
        max_clients = threads.length;
    }
    
    @Override
    public void run(){
        int max_clients = this.max_clients;
        ClientThread[] threads = this.threads;
        
        
        try{
            //input output streams
            is = new DataInputStream(client_socket.getInputStream());
            os = new PrintStream(client_socket.getOutputStream());
            String input;
            String [] splitinput;
            input = is.readLine();
            
            os.println("Bismillah...");
            
            
            for(int i=0; i< max_clients; i++){
                if(threads[i] == this){
                    threads[i] = null;
                }
            }
            
            is.close();
            os.close();
            client_socket.close();
        }catch(Exception e) {
            //.....
        }
    }
}

public class Server_Game {

    //Instansiasi ServerSocket dan Socket
    private static ServerSocket server_socket = null;
    private static Socket client_socket = null;
    
    private static final int max_clients = 2;
    private static final ClientThread[] threads = new ClientThread[max_clients];
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        int port_number = 3250;
        
        if(args.length < 1){
            System.out.println("Selamat Datang di server Game Juara Satu !");
        }else{
//            port_number = Integer.valueOf(args[0].intValue());
        }
        
        //instansiasi server socket
        try{
            server_socket = new ServerSocket(port_number);
        }catch(Exception e){
            System.out.println(e);
        }
        
        //Ciptakan sebuah client socket untuk setiap koneksi, lalu langsung passing ke new client thread
        while(true){
            
            try{
                client_socket = server_socket.accept();
                int i = 0;
                for(i=0; i < max_clients; i++){
                    if(threads[i] == null){ //jika threads ke i belum ada, maka instansiasi
                        (threads[i]  = new ClientThread(client_socket, threads)).start();
                        break;
                    } 
                }
                
                if(i == max_clients){
                    PrintStream os = new PrintStream(client_socket.getOutputStream());
                    os.println("Server too busy");
                    os.close();
                    client_socket.close();
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }    
    }
}
