package br.com.fotosensores.helmet_detection.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import br.com.fotosensores.helmet_detection.detection.HelmetDetection;

public class UdpServer implements Runnable {

	private DatagramSocket server;
	private int port;
	private InetAddress address;

	public UdpServer(int port) throws SocketException {
		server = new DatagramSocket(port);
	}

	public String receive() throws IOException {

		byte[] buffer = new byte[256];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		server.receive(packet);

		port = packet.getPort();
		address = packet.getAddress();

		String message = new String(packet.getData());

		return message;
	}

	public void send(String message) throws IOException {

		StringBuilder builder = new StringBuilder(message);
		builder.append('\n');

		byte[] buffer = builder.toString().getBytes();

		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
		server.send(packet);
	}

	public void run() {
		while (true) {
			try {
				String msg = receive();
				if (msg != null) {
					send(new HelmetDetection(msg).detector());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}