package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	Scanner sc = new Scanner(System.in);
	private ServerSocket serverSocket;
	
	public void start(int port) {
		System.out.println("Server startting!");
		try {
			this.serverSocket = new ServerSocket(port);
			while(true) {
				new ClientHander(serverSocket.accept()).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(serverSocket != null)
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public class ClientHander extends Thread{
		private Socket socket;
		private BufferedReader in;
		private BufferedWriter out;
		private FileOutputStream file;
		private DataOutputStream dat;
		
		public ClientHander(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				file = new FileOutputStream("data.text");
				dat = new DataOutputStream(file);
				System.out.println("Client connected!");
				while(true) {
					String messenger = in.readLine();
					dat.writeUTF(messenger.toString() + "\r");
					
					System.out.println("Messenger from client: " + messenger);
					if(messenger.equals("Exit"))
						break;
					String s = sc.nextLine();
					dat.writeUTF(s.toString() + "\r");
					out.write(s);
					out.newLine();
					out.flush();
					
				}
				
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
						if(file != null)
							file.close();
						if(dat != null)
							dat.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
			
		}
	}

}
