import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	Socket clientSocket;
	InputStream in;
	OutputStream out;
	BufferedReader reader;
	PrintWriter writer;
	String userInput;

	Client() {
		try {
			Socket clientSocket = new Socket("127.0.0.1", 11900);
			in = clientSocket.getInputStream();
			out = clientSocket.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			writer = new PrintWriter(out,true);

		} catch (Exception e) {
			System.out.println("Client error");
		}
	}
	
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	private void send() {
		writer.println(userInput + '\n');
	}
	
	private String receive() {
		String s = null;
		try {
			s = reader.readLine();
		} catch (Exception e) {
			System.out.println("Client cannot recieve.");
		}
		return s;
	}

	private void close() {
		try {
			clientSocket.close();

		} catch (Exception e) {
			System.out.println("Client cannot close.");
		}
	}

	
	
	
	
	public static void main(String[] args) {
		System.out.println("Client start");
		
		Scanner in = new Scanner(System.in);
		Client client = new Client();
		String input = null;
		
		
			System.out.print("Send: ");
			input = in.nextLine();
			client.setUserInput(input);
			client.send();
			
			System.out.print("Received: ");
			System.out.println(client.receive());
		

		client.close();
		in.close();
		System.out.println("Client stop");
	}
	
	
}
