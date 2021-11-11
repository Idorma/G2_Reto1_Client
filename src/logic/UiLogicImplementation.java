/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import classes.MessageType;
import classes.Signable;
import classes.User;
import classes.UserInfo;
import exceptions.ConnectException;
import exceptions.SignInException;
import exceptions.SignUpException;
import exceptions.UpdateException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author idoia
 */

public class UiLogicImplementation implements Signable{
  
    @Override
    public User signIn(User message) throws ConnectException, SignInException, UpdateException {
        
        Socket socket = null;
        ObjectOutputStream out;
        ObjectInputStream in;
        UserInfo userInfo = new UserInfo(message, MessageType.SIGNIN_REQUEST);
       
        try {
            socket = new Socket("127.0.0.1",5001);
            System.out.println("Cliente iniciado");
            
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());//recibir mensajes
            
            
            out.writeObject(userInfo);
            userInfo = (UserInfo) in.readObject();
            
            if (userInfo.getMessage() == MessageType.SIGNIN_EXCEPTION) {
                throw new SignInException("Los parametros introducidos no corresponden a ningún cliente");
            }
        } catch (IOException ex) {
            Logger.getLogger(UiLogicImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UiLogicImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
       
        return userInfo.getUser();
    }

    @Override
    public User signUp(User message) throws ConnectException, SignUpException, UpdateException {
        Socket socket = null;
        ObjectOutputStream out;
        ObjectInputStream in;
        UserInfo userInfo = new UserInfo(message, MessageType.SIGNUP_REQUEST);
        try {
            socket = new Socket("127.0.0.1",5001);
            System.out.println("Cliente iniciado");
            
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());//recibir mensajes
            
            
            out.writeObject(userInfo);
            userInfo = (UserInfo) in.readObject();
            if (userInfo.getMessage() == MessageType.SIGNUP_EXCEPTION) {
                throw new SignUpException("El email y/o el nombre de usuario introducidos ya corresponden a un cliente");
            }
        } catch (IOException ex) {
            Logger.getLogger(UiLogicImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UiLogicImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userInfo.getUser();
    }
    
    
}

