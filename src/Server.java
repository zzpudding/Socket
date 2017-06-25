import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	private static final BufferedReader severSocket = null;
	ServerSocket serverSocket;
	Socket listeningSocket;
	InputStream in;
	OutputStream out;
	BufferedReader reader;
	PrintWriter writer;

	Server() {
		try {
			ServerSocket serverSocket = new ServerSocket(11900);
			Socket listeningSocket = serverSocket.accept();
			in = listeningSocket.getInputStream();
			out = listeningSocket.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			writer = new PrintWriter(out, true);
			
		} catch (Exception e) {
			System.out.println("Server error");
		}
	}

	private String receive() {
		String s = null;
		try {
			
			s = reader.readLine();
		} catch (Exception e) {
			System.out.println("Server cannot recieve.");
		}
		return s;
	}

	private void send() {
		String s = null;
		try {
			s = reader.readLine();
		} catch (Exception e) {
			System.out.println("Server cannot sent.");
		}
		writer.println();
	}

	private void close() {
		try {
			in.close();
			out.close();
			reader.close();
		} catch (Exception e) {
			System.out.println("Server Cannot close.");
		}
	}

	public static void main(String[] args) {
		System.out.println("Server start");
		Server server = new Server();
		
			System.out.print("Received: ");
			System.out.println(server.receive());
			server.send();
		

	}
}
