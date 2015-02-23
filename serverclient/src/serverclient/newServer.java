
package serverclient;
import java.io.*;
import java.net.*;
public class newServer implements Runnable
{
	Socket socket;
	String pathName;
	public newServer(Socket socket,String path)
	{
		this.socket=socket;	
		pathName=path;
	}	
	public void run()
	{
		try
		{			
			InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
			BufferedReader Bufferedreader = new BufferedReader(inputStreamReader);
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			String fileName = Bufferedreader.readLine();
			System.out.println("Requested a file named " + "\"" + fileName + "\"");
			String publicRoot =pathName;
			File requestedFile = new File(publicRoot+"\\"+fileName);			
			if((requestedFile.exists())==true)
			{
				int i=1;
				dataOutputStream.writeInt(i);
				System.out.println("Requested File Exists");				
				byte[] arrayOfFile = new byte[(int)requestedFile.length()];
				FileInputStream fileInputStream = new FileInputStream(requestedFile);
				long length=(long)arrayOfFile.length;
			    dataOutputStream.writeLong((long)arrayOfFile.length);			    
			    System.out.println("Sending " + fileName + " of size (" + arrayOfFile.length + "bytes )");
			    fileInputStream.read(arrayOfFile,0,(int)length);
			    dataOutputStream.write(arrayOfFile,0,(int)length);
			    dataOutputStream.flush();
			    fileInputStream.close();
			    System.out.println("Completed....!!!");		
			 }
			else
			{
				System.out.println(" \""+fileName+"\""  + " is Not Available at " + publicRoot);
				dataOutputStream.writeInt(0);
			}
			//socket.close();
         }		
		catch (IOException e)
		{
			 e.printStackTrace();
        }		
	}

	/*public static void main(String[] args) 
	{
		 final int PortAddress = 1170;
		 ServerSocket serverSocket=null;
		try
		{
			serverSocket = new ServerSocket(PortAddress);
			System.out.println("Listening on Port  " + PortAddress);
         	Socket socket = serverSocket.accept();	
			InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
			BufferedReader Bufferedreader = new BufferedReader(inputStreamReader);
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			String fileName = Bufferedreader.readLine();
			System.out.println("Requested a file named " + "\"" + fileName + "\"");
			String publicRoot = "C:\\Users\\mgiddaluri\\";
			File requestedFile = new File(publicRoot+ fileName);			
			if((requestedFile.exists())==true)
			{
				int i=1;
				dataOutputStream.writeInt(i);
				System.out.println("Requested File Exists");				
				byte[] arrayOfFile = new byte[(int)requestedFile.length()];
				FileInputStream fileInputStream = new FileInputStream(requestedFile);
				long length=(long)arrayOfFile.length;
			    dataOutputStream.writeLong((long)arrayOfFile.length);			    
			    System.out.println("Sending " + fileName + " of size (" + arrayOfFile.length + "bytes )");
			    fileInputStream.read(arrayOfFile,0,(int)length);
			    dataOutputStream.write(arrayOfFile,0,(int)length);
			    dataOutputStream.flush();
			    fileInputStream.close();
			    System.out.println("Completed....!!!");
			 }
			else
			{
				System.out.println(" \""+fileName+"\""  + " is Not Available at " + publicRoot);
				dataOutputStream.writeInt(0);
			}
			//socket.close();
         }		
		catch (IOException e)
		{
			 e.printStackTrace();
        }
		try
		{
			Thread.sleep(1000000000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
	}*/
}
