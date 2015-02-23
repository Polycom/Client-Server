package serverclient;
import java.net.*;
import java.io.*;

public class serverThread
{
	public static void main(String args[])throws IOException
	{
		final int PortAddress = 1170;
		ServerSocket serverSocket=null;		
		try
		{
			serverSocket = new ServerSocket(PortAddress);
			System.out.println("Listening on Port  " + PortAddress);	
			while(true)
			{			
				Socket socket = serverSocket.accept();			
				newServer newserver=new newServer(socket,args[0]);
				Thread t=new Thread(newserver);
				t.start();	
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
