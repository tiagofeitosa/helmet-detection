package br.com.fotosensores.helmet_detection;

import java.net.SocketException;

import br.com.fotosensores.helmet_detection.connection.UdpServer;

public class App {
	public static void main(String[] args) {

		try {
			Thread thread = new Thread(new UdpServer(7005));
			thread.setName("helmet-detection");
			thread.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}
}
