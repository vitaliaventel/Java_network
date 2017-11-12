package ua.kpi.leshchenko.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import ua.kpi.leshchenko.model.Product;
import ua.kpi.leshchenko.server.JabberServer;

public class JabberClient {
	public static void main(String[] args) throws IOException {
		InetAddress addr = InetAddress.getByName(null);
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, JabberServer.PORT);
		try {
			System.out.println("socket = " + socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
					true);
			int option;
			do {
				System.out.println("Please choose one of available options: ");
				System.out.println("1 - POST request, 2 - GET request, 3 - END");
				Scanner scanner = new Scanner(System.in);
				option = scanner.nextInt();

				switch (option) {
				case 1:
					out.println("POST");
					out.println(scanner.nextLine());
					System.out.println(in.readLine());
					break;
				case 2:
					out.println("GET");
					out.println(scanner.nextLine());
					System.out.println(in.readLine());
					break;
				default:
					System.out.println("No such option!");
				}
			} while (option != 3);
			out.println("END");
		} finally {
			System.out.println("closing...");
			socket.close();
		}
	}
}
