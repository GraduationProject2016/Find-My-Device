/**
 * @author mohamed265
 * Created On : Dec 11, 2015 4:04:22 PM
 */
package com.fmd.gp2016.web.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

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
	private HashMap<Integer, Thread> mapViews;
	private HashMap<Integer, MessageDto> mapDto;
	private static Object obj = new Object();

	public DeviceThread(SocketServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
		ID = socket.getPort();
		init();
		mapViews = new HashMap();
		mapDto = new HashMap();
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
		MessageDto msg = readSignin();
		if (msg == null)
			return;
		System.out.println("Msg " + msg);
		Device device = server.getDeviceService().getDeviceById(msg.getDeviceId());
		if (device != null) {
			System.out.println("\nServer Thread " + ID + " running.");
			setDevice(device);
			DevicePool.addDeviceThread(this);
			readListener();
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
				// TODO userid change to view id and -1 mean file upload
				String msg = (String) streamIn.readObject();
				System.out.println("in read lisnter " + msg);
				// server.handle(ID, JsonHandler.getMessageDtoObject(msg));
				MessageDto dto = JsonHandler.getMessageDtoObject(msg);
				System.out.println("dto " + dto);
				if (dto.getUserId() != -1) {
					mapDto.put(dto.getUserId(), dto);
					synchronized (obj) {
						mapViews.get(dto.getUserId()).notify();
					}
				} else {

				}
			} catch (Exception ioe) {
				System.out.println(ID + " ERROR reading: ");
				ioe.printStackTrace();
				close();
				stop();
			}
		}
	}

	public MessageDto readOneMessage(int viewId) {
		System.out.println("read one Message " + (new Date()).toString());
		try {
			registerThread(viewId);

			synchronized (obj) {
				wait(6000);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("read one Message " + (new Date()).toString());
		clearThread(viewId);
		return mapDto.get(viewId);
		// return null;
		// System.out.println("Thread id is: " + Thread.currentThread().getId()
		// + " in read one message ");
		// String msg = null;
		// try {
		// System.out.println("read only one ");
		// msg = (String) streamIn.readObject();
		// System.out.println("read only one " + msg);
		// } catch (Exception ioe) {
		// System.out.println(ID + " ERROR reading: " + ioe.getMessage());
		// close();
		// stop();
		// }
		// return JsonHandler.getMessageDtoObject(msg);

	}

	public MessageDto readSignin() {
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

	public void registerThread(int viewId) {
		// System.out.println("Thread id is: " + Thread.currentThread().getId()
		// + " in register view ");
		mapViews.put(viewId, Thread.currentThread());
		mapDto.put(viewId, null);
	}

	public void clearThread(int viewId) {
		// System.out.println("Thread id is: " + Thread.currentThread().getId()
		// + " in register view ");
		mapViews.remove(viewId);
		mapDto.remove(viewId);
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