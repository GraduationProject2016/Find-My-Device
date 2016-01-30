/**
 * @author mohamed265
 * Created On : Dec 11, 2015 4:09:04 PM
 */
package com.fmd.gp2016.web.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.dto.MessageDto;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.jsf.annotation.SpringApplicationScoped;

/**
 * @author mohamed265
 */
@SpringApplicationScoped
@Named
public class SocketServer implements Runnable {

	@Autowired
	private DeviceService deviceService;
	private ServerSocket server = null;
	private Thread thread = null;
	private int port = 13000;

	public SocketServer() {
		try {
			server = new ServerSocket(port);
			port = server.getLocalPort();
			System.out.println(
					"Server startet. IP : " + InetAddress.getLocalHost() + ", Port : " + server.getLocalPort());
			start();
		} catch (IOException ioe) {
			System.out.println("Can not bind to port : " + port + "\nRetrying");
		}
	}

	public void run() {
		while (thread != null) {
			try {
				System.out.println("\nWaiting for a client ...");
				new DeviceThread(this, server.accept());
			} catch (Exception ioe) {
				System.out.println("\nServer accept error: \n");
				ioe.printStackTrace();
			}
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	public synchronized void handle(int ID, MessageDto msg) {
		System.out.println(msg);
		// System.out.println("wla eh wla eh");
		// System.out.println(msg);
		// .bye
		// login
		// message
		// signup

	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	// private void addThread(Socket socket) {
	// // if (clientCount < clients.length) {
	// System.out.println("\nClient accepted: " + socket);
	// DevicePool.addDeviceThread(new DeviceThread(this, socket));
	// // clientCount++;
	// // } else {
	// // System.out.println("\nClient refused: maximum " + clients.length + "
	// // reached.");
	// // }
	// }

	// public void Announce(String type, String sender, String content) {
	// Message msg = new Message(type, sender, content, "All");
	// for (int i = 0; i < clientCount; i++) {
	// clients[i].send(msg);
	// }
	// }

}
