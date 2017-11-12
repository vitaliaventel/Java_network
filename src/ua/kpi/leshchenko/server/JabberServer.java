package ua.kpi.leshchenko.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import ua.kpi.leshchenko.model.Product;

public class JabberServer {
	public static final int PORT = 8081;

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		ServerController controller = new ServerController();
		System.out.println("Started: " + s);
		try {
			Socket socket = s.accept();
			try {
				System.out.println("Connection accepted: " + socket);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
						true);
				while (true) {

					String str = in.readLine();
					if (str.equals("END")) {
						break;
					}
					System.out.println("Echoing: " + str);
					String messageBody = in.readLine();
					System.out.println("Echoing: " + messageBody);
					if (str.equals("POST")) {
						out.println(messageBody);
						Product product = controller.jsonToProduct(messageBody);
						controller.addToStorage(product);
						System.out.println("Current storage = " + controller.getProductStorage());
					} else if (str.equals("GET")) {
						out.println(messageBody);
						Product product = controller.getProductById(Long.valueOf(messageBody.trim()));
						out.println("RESPONSE: " + controller.productToJson(product));
					}

				}
			} finally {
				System.out.println("closing...");
				socket.close();
			}
		} finally {
			s.close();
		}
	}
}
