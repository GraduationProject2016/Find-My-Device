/**
 * @author mohamed265
 * Created On : Dec 11, 2015 4:04:22 PM
 */
package com.fmd.gp2016.web.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.fmd.gp2016.common.dto.MessageDto;
import com.fmd.gp2016.common.util.JsonHandler;
import com.fmd.gp2016.common.util.jsf.annotation.SpringApplicationScoped;

/**
 * @author mohamed265
 */
@SpringApplicationScoped
class ServerThread extends Thread {

	public SocketServer server = null;
	public Socket socket = null;
	public int ID = -1;
	public String username = "";
	public ObjectInputStream streamIn = null;
	public ObjectOutputStream streamOut = null;

	public ServerThread(SocketServer _server, Socket _socket) {
		super();
		server = _server;
		socket = _socket;
		ID = socket.getPort();
	}

	public void send(String msg) {
		try {
			streamOut.writeObject(msg);
			streamOut.flush();
		} catch (IOException ex) {
			System.out.println("Exception [SocketClient : send(...)]");
		}
	}

	public int getID() {
		return ID;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		System.out.println("\nServer Thread " + ID + " running.");
		while (true) {
			try {
				String msg = (String) streamIn.readObject();
				server.handle(ID, JsonHandler.getMessageDtoObject(msg));
			} catch (Exception ioe) {
				System.out.println(ID + " ERROR reading: " + ioe.getMessage());
				server.remove(ID);
				stop();
			}
		}
	}

	public void open() throws IOException {
		streamOut = new ObjectOutputStream(socket.getOutputStream());
		streamOut.flush();
		streamIn = new ObjectInputStream(socket.getInputStream());
	}

	public void close() throws IOException {
		if (socket != null)
			socket.close();
		if (streamIn != null)
			streamIn.close();
		if (streamOut != null)
			streamOut.close();
	}
}