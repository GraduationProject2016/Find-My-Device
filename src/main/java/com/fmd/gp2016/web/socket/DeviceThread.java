/**
 * @author mohamed265
 * Created On : Dec 11, 2015 4:04:22 PM
 */
package com.fmd.gp2016.web.socket;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import com.fmd.gp2016.common.dto.Command;
import com.fmd.gp2016.common.dto.MessageDto;
import com.fmd.gp2016.common.entity.Device;
import com.fmd.gp2016.common.entity.ServerToClientMessage;
import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.JSONDecoding;
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

	public DeviceThread(SocketServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
		ID = socket.getPort();
		init();
		mapViews = new HashMap();
		mapDto = new HashMap();
	}

	private void init() {
		// System.out.println("I am init");
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
		if (msg.getContent().equals("sign_in")) {
			Device device = server.getDeviceService().getDeviceById(msg.getDeviceId());
			if (device != null) {
				System.out.println("\nServer Thread " + ID + " running. for command");
				setDevice(device);
				DevicePool.addDeviceThread(this);
				readListener();
			}
		} else {
			System.out.println("\nServer Thread " + ID + " running. for file transfar");
			Command command = JsonHandler.getCommandObject(msg.getContent());
			System.out.println(command);
			if (command.getCommand().equals(Constants.FIlE_TRANSFARE + "")) {
				String folderPath = Constants.UPLOAD_PATH + msg.getContentType() + "\\";
				File file = new File(folderPath);
				if (!file.exists())
					file.mkdir();
				folderPath += msg.getDeviceId() + "\\";
				file = new File(folderPath);
				if (!file.exists())
					file.mkdir();
				new Download(folderPath + getStorerdName(command.getParms()[0]), socket, streamIn, streamOut).run();
			}
		}

	}

	private String getStorerdName(String temp) {
		StringBuffer fileName = new StringBuffer(temp);
		String date = new Timestamp(new Date().getTime()).toString();
		date = date.substring(0, date.indexOf('.'));
		date = date.replace(":", "_");
		date = date.replace("-", "_");
		date = date.replace(" ", "_");
		fileName.insert(fileName.indexOf("."), " " + date);
		return fileName.toString();
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

	public void send(String msg, int viewId) {
		try {
			registerThread(viewId);
			streamOut.writeObject(msg);
			streamOut.flush();
		} catch (IOException ex) {
			System.out.println("Exception [SocketClient : send()] for device " + device.getId() + " mesg is " + msg);
			close();
			stop();
		}
	}

	public void readListener() {
		for (ServerToClientMessage scm : server.getDeviceService().getAllMessagesByDevice(device)) {
			send(scm.getContent());
			server.getDeviceService().deleteMessagesByMessage(scm);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (true) {
			try {
				// TODO userid change to view id and -1 mean file upload
				String msg = (String) streamIn.readObject();
				System.out.println("readListener " + msg);
				MessageDto dto = JsonHandler.getMessageDtoObject(msg);
				handleMessage(dto);
			} catch (Exception ioe) {
				System.out.println(ID + " ERROR reading: ");
				ioe.printStackTrace();
				close();
				stop();
			}
		}
	}

	private void handleMessage(MessageDto msg) {
		if (msg.getContentType() > Constants.COMMAND) {
			mapDto.put(msg.getContentType(), msg);
			synchronized (mapViews.get(msg.getContentType())) {
				mapViews.get(msg.getContentType()).notifyAll();
			}
		} else if (msg.getContentType() == Constants.FIlE_TRANSFARE) {

		}
	}

	public MessageDto readOneMessage(int viewId) {
		// System.out.println("read one Message " + (new Date()).toString() + "
		// with view id " + viewId);
		try {
			// registerThread(viewId);
			synchronized (mapViews.get(viewId)) {
				mapViews.get(viewId).wait(6000);
			}

		} catch (InterruptedException e) {
			System.out.println("here in read one message exception");
			e.printStackTrace();
		}
		/// System.out.println("read one Message " + (new Date()).toString());
		MessageDto dtoResult = mapDto.get(viewId);
		clearThread(viewId);
		return dtoResult;
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
			server.getDeviceService().updateLastActiveIn(device);
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