package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	Scanner sc = new Scanner(System.in);
	
	private Socket socket;
	private BufferedWriter out;
	private BufferedReader in;
	
	public void startConnection(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				out.write(sc.nextLine());
				out.newLine();
				out.flush();
				String message = in.readLine();
				System.out.println("Message from Server: " + message);
				if(message.equals("Exit"))
					break;
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				try {
					if(in != null)
						in.close();
					if(out != null)
						out.close();
					if(socket != null)
						socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
