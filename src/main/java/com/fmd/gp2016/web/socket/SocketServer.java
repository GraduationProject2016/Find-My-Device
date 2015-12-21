/**
 * @author mohamed265
 * Created On : Dec 11, 2015 4:09:04 PM
 */
package com.fmd.gp2016.web.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.fmd.gp2016.common.dto.MessageDto;

/**
 * @author mohamed265
 *
 */
public class SocketServer implements Runnable {

	public final int MAX_CLIENT_NUM = 50;
	public ServerThread clients[];
	public ServerSocket server = null;
	public Thread thread = null;
	public int clientCount = 0, port = 13000;

	public SocketServer() {

		clients = new ServerThread[MAX_CLIENT_NUM];

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
				addThread(server.accept());
			} catch (Exception ioe) {
				System.out.println("\nServer accept error: \n");
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

	private int findClient(int ID) {
		for (int i = 0; i < clientCount; i++) {
			if (clients[i].getID() == ID) {
				return i;
			}
		}
		return -1;
	}

	public synchronized void handle(int ID, MessageDto msg) {
		System.out.println(msg);
		// System.out.println("wla eh wla eh");
		// System.out.println(msg);
		// if (msg.content.equals(".bye")) {
		// Announce("signout", "SERVER", msg.sender);
		// remove(ID);
		// } else {
		// if (msg.type.equals("login")) {
		// if (findUserThread(msg.sender) == null) {
		// if (db.checkLogin(msg.sender, msg.content)) {
		// clients[findClient(ID)].username = msg.sender;
		// clients[findClient(ID)].send(new Message("login", "SERVER", "TRUE",
		// msg.sender));
		// Announce("newuser", "SERVER", msg.sender);
		// SendUserList(msg.sender);
		// } else {
		// clients[findClient(ID)].send(new Message("login", "SERVER", "FALSE",
		// msg.sender));
		// }
		// } else {
		// clients[findClient(ID)].send(new Message("login", "SERVER", "FALSE",
		// msg.sender));
		// }
		// } else if (msg.type.equals("message")) {
		// if (msg.recipient.equals("All")) {
		// Announce("message", msg.sender, msg.content);
		// } else {
		// findUserThread(msg.recipient).send(new Message(msg.type, msg.sender,
		// msg.content, msg.recipient));
		// clients[findClient(ID)].send(new Message(msg.type, msg.sender,
		// msg.content, msg.recipient));
		// }
		// } else if (msg.type.equals("test")) {
		// clients[findClient(ID)].send(new Message("test", "SERVER", "OK",
		// msg.sender));
		// } else if (msg.type.equals("signup")) {
		// if (findUserThread(msg.sender) == null) {
		// if (!db.userExists(msg.sender)) {
		// db.addUser(msg.sender, msg.content);
		// clients[findClient(ID)].username = msg.sender;
		// clients[findClient(ID)].send(new Message("signup", "SERVER", "TRUE",
		// msg.sender));
		// clients[findClient(ID)].send(new Message("login", "SERVER", "TRUE",
		// msg.sender));
		// Announce("newuser", "SERVER", msg.sender);
		// SendUserList(msg.sender);
		// } else {
		// clients[findClient(ID)].send(new Message("signup", "SERVER", "FALSE",
		// msg.sender));
		// }
		// } else {
		// clients[findClient(ID)].send(new Message("signup", "SERVER", "FALSE",
		// msg.sender));
		// }
		// } else if (msg.type.equals("upload_req")) {
		// if (msg.recipient.equals("All")) {
		// clients[findClient(ID)]
		// .send(new Message("message", "SERVER", "Uploading to 'All'
		// forbidden", msg.sender));
		// } else {
		// findUserThread(msg.recipient)
		// .send(new Message("upload_req", msg.sender, msg.content,
		// msg.recipient));
		// }
		// } else if (msg.type.equals("upload_res")) {
		// if (!msg.content.equals("NO")) {
		// String IP =
		// findUserThread(msg.sender).socket.getInetAddress().getHostAddress();
		// findUserThread(msg.recipient).send(new Message("upload_res", IP,
		// msg.content, msg.recipient));
		// } else {
		// findUserThread(msg.recipient)
		// .send(new Message("upload_res", msg.sender, msg.content,
		// msg.recipient));
		// }
		// }
		// }
	}

	// public void Announce(String type, String sender, String content) {
	// Message msg = new Message(type, sender, content, "All");
	// for (int i = 0; i < clientCount; i++) {
	// clients[i].send(msg);
	// }
	// }
	//
	// public void SendUserList(String toWhom) {
	// for (int i = 0; i < clientCount; i++) {
	// findUserThread(toWhom).send(new Message("newuser", "SERVER",
	// clients[i].username, toWhom));
	// }
	// }

	public ServerThread findUserThread(String usr) {
		for (int i = 0; i < clientCount; i++) {
			if (clients[i].username.equals(usr)) {
				return clients[i];
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public synchronized void remove(int ID) {
		int pos = findClient(ID);
		if (pos >= 0) {
			ServerThread toTerminate = clients[pos];
			System.out.println("\nRemoving client thread " + ID + " at " + pos);
			if (pos < clientCount - 1) {
				for (int i = pos + 1; i < clientCount; i++) {
					clients[i - 1] = clients[i];
				}
			}
			clientCount--;
			try {
				toTerminate.close();
			} catch (IOException ioe) {
				System.out.println("\nError closing thread: " + ioe);
			}
			toTerminate.stop();
		}
	}

	private void addThread(Socket socket) {
		if (clientCount < clients.length) {
			System.out.println("\nClient accepted: " + socket);
			clients[clientCount] = new ServerThread(this, socket);
			try {
				clients[clientCount].open();
				clients[clientCount].start();
				clientCount++;
			} catch (IOException ioe) {
				System.out.println("\nError opening thread: " + ioe);
			}
		} else {
			System.out.println("\nClient refused: maximum " + clients.length + " reached.");
		}
	}
}

// public SocketServer(int Port) {
//
// clients = new ServerThread[MAX_CLIENT_NUM];
// port = Port;
//
// try {
// server = new ServerSocket(port);
// port = server.getLocalPort();
// System.out.println(
// "Server startet. IP : " + InetAddress.getLocalHost() + ", Port : " +
// server.getLocalPort());
// start();
// } catch (IOException ioe) {
// System.out.println("\nCan not bind to port " + port + ": " +
// ioe.getMessage());
// }
// }
