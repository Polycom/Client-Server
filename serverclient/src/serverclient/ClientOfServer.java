package serverclient;
import java.io.*;
import java.net.*;
import java.util.*;
	public class ClientOfServer 
	{
		public static void main(String[] args)
		{
			Socket clientSocket = null;
			String s,i;
			
			try
			{
				Scanner sc=new Scanner(System.in);
				System.out.print("Enter an IP Address : ");
				s=sc.nextLine();
				System.out.print("Enter an Port Number : ");
				i=sc.nextLine();
				clientSocket=new Socket(s,Integer.parseInt(i));				
				PrintWriter socketOut=new PrintWriter(clientSocket.getOutputStream(),true);
				System.out.println("which file has to download?? ");
				String filename=sc.nextLine();
				sc.close();
				socketOut.println(filename);
				InputStream socketIn=clientSocket.getInputStream();
				DataInputStream dataSocketIn=new DataInputStream(clientSocket.getInputStream());				
				int choice=dataSocketIn.readInt();				
				if(choice==0)
						System.out.println("Sorry File is not existed on Server!!");
				else
					{
						File f = new File(filename);
						if(f.exists())						
							System.out.println("File Already Exists.It's overriding now !!!!! ");												
						long size=dataSocketIn.readLong();						
						System.out.println("The no. of bytes :" + size);						
						byte[] bytes = new byte[(int)size];
						int bytesread = socketIn.read(bytes,0,bytes.length);
						FileOutputStream fileOutputStream = new FileOutputStream(args[0]+"\\"+f);
						System.out.println("The no. of bytes :" + bytes.length);						
						int noOfBytes = bytesread;
						do
						{							
							
							bytesread=socketIn.read(bytes,noOfBytes,(bytes.length-noOfBytes));
							if(noOfBytes>=0)
							noOfBytes += bytesread;													
						}while(bytesread>0);
						fileOutputStream.write(bytes,0,noOfBytes);
						fileOutputStream.flush();
						System.out.println("____________________________________________________");
						System.out.println("  File "+filename+" was downloaded  "+"( "+noOfBytes+" )");
						fileOutputStream.close();
						dataSocketIn.close();
						socketIn.close();
						socketOut.close();
					}
							
			}
			catch(Exception e)
			{
				System.out.println(e);
				e.printStackTrace();
			} 
			/*finally 
			{
				try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		}
	}



