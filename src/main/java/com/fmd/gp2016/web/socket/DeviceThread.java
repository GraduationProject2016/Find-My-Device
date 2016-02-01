/**
 * @author mohamed265
 * Created On : Dec 11, 2015 4:04:22 PM
 */
package com.fmd.gp2016.web.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.fmd.gp2016.common.dto.MessageDto;
import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.util.JsonHandler;

/**
 * @author mohamed265
 */
public class DeviceThread extends Thread {

	private SocketServer server = null;
	private Socket socket = null;
	private int ID = -1;
	private ObjectInputStream streamIn = null;
	private ObjectOutputStream streamOut = null;
	private Device device;

	public DeviceThread(SocketServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
		ID = socket.getPort();
		init();
	}

	private void init() {
		System.out.println("I am post construct");
		try {
			openConnections();
			start();
		} catch (IOException ioe) {
			System.out.println("\nError opening thread: " + ioe);
		}
	}

	@SuppressWarnings("deprecation")
	public void run() {
		MessageDto msg = readOneMessage();
		if (msg == null)
			return;
		System.out.println("Msg " + msg);
		Device device = server.getDeviceService().getDeviceById(msg.getDeviceId());
		if (device != null) {
			System.out.println("\nServer Thread " + ID + " running.");
			setDevice(device);
			DevicePool.addDeviceThread(this);
			//readListener();
		}

	}

	public void send(String msg) {
		try {
			streamOut.writeObject(msg);
			streamOut.flush();
		} catch (IOException ex) {
			System.out.println("Exception [SocketClient : send()] for device " + device.getId() + " mesg is " + msg);
			close();
			stop();
		}
	}

	public void readListener() {
		while (true) {
			try {
				System.out.println("in read listner");
				// TODO sinout mes
				String msg = (String) streamIn.readObject();
				// server.handle(ID, JsonHandler.getMessageDtoObject(msg));
			} catch (Exception ioe) {
				System.out.println(ID + " ERROR reading: " + ioe.getMessage());
				close();
				stop();
			}
		}
	}

	public MessageDto readOneMessage() {
		String msg = null;
		try {
			System.out.println("read only one ");
			msg = (String) streamIn.readObject();
			System.out.println("read only one " + msg);
		} catch (Exception ioe) {
			System.out.println(ID + " ERROR reading: " + ioe.getMessage());
			close();
			stop();
		}
		return JsonHandler.getMessageDtoObject(msg);
	}

	public void openConnections() throws IOException {
		streamOut = new ObjectOutputStream(socket.getOutputStream());
		streamOut.flush();
		streamIn = new ObjectInputStream(socket.getInputStream());
	}

	public void close() {
		try {
			if (socket != null)
				socket.close();
			if (streamIn != null)
				streamIn.close();
			if (streamOut != null)
				streamOut.close();
		} catch (IOException e) {
			System.out.println("Error Closeing thread id " + ID);
			e.printStackTrace();
		} finally {
			DevicePool.removeDeviceThread(this);
		}
	}

	public int getID() {
		return ID;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
}