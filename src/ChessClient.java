// borrowed some code from this website: https://www.daniweb.com/programming/software-development/code/448361/multithreaded-simple-client-server-chat-console-program

import java.awt.Color;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class ChessClient
{
    public static Thread t;
    
    public ChessClient()
    {
        ConnectThread connect = new ConnectThread();
        t = new Thread(connect);
        t.start();
    }
}

class ConnectThread implements Runnable
{
    public static Socket sock;
    
    @Override
    public void run()
    {
        try
        {            
            sock = new Socket(ServerClientScreen.getTextField().getText(), 444);
            ServerClientScreen.getTextArea().append("Connection Successfull with "
                    + sock.getInetAddress() + "\n You may now start a game of chess."
                    + "\n Server, when ready, press Start Game to begin playing.");
             
            SendThread sendThread = new SendThread();
            Thread thread = new Thread(sendThread);thread.start();
            RecieveThread recieveThread = new RecieveThread();
            Thread thread2 =new Thread(recieveThread);thread2.start();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Connection not successful, IP "
                    + "address was entered wrong.");
            ServerClientScreen.getTextField().setText("");
            ServerClientScreen.getConnectButton().setEnabled(true);

        }
    }  
}

class RecieveThread implements Runnable
{
    Socket socket = null;
    BufferedReader recieve = null;
    private boolean hasGameStarted = false;
	
    public RecieveThread()
    {
        this.socket = ConnectThread.sock;
    }
    
    public void run()
    {
        try
        {    
            recieve = new BufferedReader(new InputStreamReader(this.socket.getInputStream())); //get inputstream
            String msgRecieved = null;
            while((msgRecieved = recieve.readLine())!= null)
            {
                System.out.println("From Server: " + msgRecieved);
                System.out.println("Please enter something to send to server..");
                
                if (msgRecieved.equals("1"))
                {
                    if (!hasGameStarted)
                    {
                        ChessBoard chessBoard = new ChessBoard(new RealPlayer(Color.BLACK), false);
                        hasGameStarted = true;
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

class SendThread implements Runnable
{
    Socket socket = null;
    PrintWriter print = null;
    BufferedReader brinput = null;
	
    public SendThread()
    {
        this.socket = ConnectThread.sock;
    }
    
    public void run()
    {
        try
        {
            if(socket.isConnected())
            {
                System.out.println("Client connected to " + socket.getInetAddress() + " on port " + socket.getPort());
                this.print = new PrintWriter(socket.getOutputStream(), true);	
		while(true)
                {
                    System.out.println("Type your message to send to server..type 'EXIT' to exit");
                    brinput = new BufferedReader(new InputStreamReader(System.in));
                    String msgtoServerString=null;
                    msgtoServerString = brinput.readLine();
                    this.print.println(msgtoServerString);
                    this.print.flush();
		
                    if(msgtoServerString.equals("EXIT"))
                        break;
                }
            socket.close();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
