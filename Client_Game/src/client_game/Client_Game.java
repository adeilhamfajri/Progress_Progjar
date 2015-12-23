/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client_game;

import java.io.DataInputStream;
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
        
        int post_number = "3250"; //port ke server
        
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
