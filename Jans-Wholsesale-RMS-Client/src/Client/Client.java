package Client;

import Domain.*;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class Client {
    private ObjectInputStream objIs;
    private ObjectOutputStream objOs;
    private Socket connectionSocket;
    private String action = "";

    public Client(){
        this.createConnection();
        this.configureStreams();
    }

    private void createConnection(){
        try{
            connectionSocket = new Socket("127.0.0.1", 8888);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void configureStreams(){
        try{
            objIs = new ObjectInputStream(connectionSocket.getInputStream());
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public void closeConnection(){
        try {
            objIs.close();
            objOs.close();
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAction(String action){
        this.action = action;
        try{
            objOs.writeObject(action);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendObject(Object obj){
        try {
            objOs.writeObject(obj);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendID(String id){
        try{
            objOs.writeObject(id);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public Object receiveObject(){
        try{
            Object object = objIs.readObject();
            if(object == null) {
                JOptionPane.showMessageDialog(null, "Record could not be found", "NOT FOUND", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return object;

        } catch (ClassNotFoundException | IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void receiveResponse(){
        try{
            Boolean flag = (Boolean) objIs.readObject();
            if(flag) {
                JOptionPane.showMessageDialog(null, "Record added successfully", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (ClassNotFoundException | IOException ex){
            ex.printStackTrace();
        }
    }
}
