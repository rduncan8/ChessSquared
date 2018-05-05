// borrowed some code from this website: https://www.daniweb.com/programming/software-development/code/448361/multithreaded-simple-client-server-chat-console-program

import java.io.*;
import java.net.*;

public class ChessServer
{            
    public ChessServer() throws IOException
    {        
        ConnectionThread connect = new ConnectionThread();
        Thread thread = new Thread(connect);
        thread.start();
    }
}

class ConnectionThread implements Runnable
{
    public static Socket clientSocket;
    
    @Override
    public void run()
    {
        try
        {
            final int PORT = 444;
            ServerSocket ss = new ServerSocket(PORT);
            clientSocket = ss.accept();
            ServerClientScreen.getTextArea().append("Connection successfull with " +
                    clientSocket.getInetAddress() + "\n" + "You may now start a game of chess." +
                    "\n" + "Server, when ready, press Start Game to begin playing.");
            ServerClientScreen.getStartGameButton().setEnabled(true);
            
            RecieveFromClientThread c = new RecieveFromClientThread();
            Thread thread = new Thread(c);
            thread.start();
            
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

class RecieveFromClientThread implements Runnable
{
    Socket clientSock = null;
    BufferedReader brBufferedReader = null;
	
    public RecieveFromClientThread()
    {
        clientSock = ConnectionThread.clientSocket;
    }
	
    public void run()
    {
        try
        {
            brBufferedReader = new BufferedReader(new InputStreamReader(this.clientSock.getInputStream()));
            String messageString;
            
            while(true)
            {
		while((messageString = brBufferedReader.readLine())!= null)
                {
                    if(messageString.equals("EXIT"))
                    {
                        break; //break to close socket if EXIT
                    }
                    System.out.println("From Client: " + messageString); //print the message from client
                    System.out.println("Please enter something to send back to client..");
		}
		this.clientSock.close();
		System.exit(0);
            }	
	}
	catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

class SendToClientThread implements Runnable
{
    PrintWriter pwPrintWriter;
    Socket clientSock = null;
    
    public static String msgToClientString;
	
    public SendToClientThread()
    {
        clientSock = ConnectionThread.clientSocket;
    }
    
    public static void setMessage(String message)
    {
        msgToClientString = message;
    }
    
    public void run()
    {
        try
        {
            pwPrintWriter =new PrintWriter(new OutputStreamWriter(this.clientSock.getOutputStream()));//get outputstream
		
            while(true)
            {
                //msgToClientString = null;
                //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));//get userinput
			
                //msgToClientString = input.readLine();//get message to send to client
			
                pwPrintWriter.println(msgToClientString); //send message to client with PrintWriter
                pwPrintWriter.flush(); //flush the PrintWriter
                System.out.println("Please enter something to send back to client..");
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }	
    }
}
