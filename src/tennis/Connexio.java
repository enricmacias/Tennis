/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tennis;

import java.net.*;
import javax.swing.*;
import java.io.*;
/**
 *
 * @author Dani i KiKe
 */
public class Connexio
{
    public static final int SERVER = 0;
    public static final int CLIENT = 1;

    Socket _socket;
    DataInputStream _dis;
    DataOutputStream _dos;
    boolean _isConnected;

    public Connexio()
    {

    }

    public void connect(String ip, int port)
    {
        try
        {
            _socket = new Socket(ip,port);

            _dis = new DataInputStream(_socket.getInputStream());
            _dos = new DataOutputStream(_socket.getOutputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        _isConnected = true;
    }

    public void waitForConnection(int port)
    {
        try
        {
            ServerSocket ss = new ServerSocket(port);
            _socket = ss.accept();

            _dis = new DataInputStream(_socket.getInputStream());
            _dos = new DataOutputStream(_socket.getOutputStream());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        _isConnected = true;
    }

    public String read()
    {
        try
        {
            String msg = _dis.readUTF();
            return msg;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String msg)
    {
        try
        {
            _dos.writeUTF(msg);
            _dos.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void disconnect()
    {
        try
        {
            _dos.writeUTF("exit");
            _dos.flush();

            _dis.close();
            _dos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        _isConnected = false;
    }

    public boolean isConnected()
    {
        return _isConnected;
    }
}
