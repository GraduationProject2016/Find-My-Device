package com.fmd.gp2016.web.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Download implements Runnable {

	public String saveTo = "";
	private InputStream is;
	// private ObjectInputStream ois;
	private OutputStream oos;
	public FileOutputStream Out;
	public Socket socket;

	public Download(String saveTo, Socket socket) {
		try {
			this.saveTo = saveTo;
			this.socket = socket;
			System.out.println("Download constract");
			// oos = new ObjectOutputStream(socket.getOutputStream());
			// oos.flush();
			oos = socket.getOutputStream();
			System.out.println("Download Constractr");
			is = socket.getInputStream();
			// ois = new ObjectInputStream(socket.getInputStream());
			// System.out.println("Download Constractr " + ";" + ((String)
			// ois.readObject()));
		} catch (IOException ex) {
			System.out.println("Exception [Download : Download(...)]");
			ex.printStackTrace();
		}
	}

	@Override
	public void run() {
		int p = 0, t, nFail = 0;
		try {
			System.out.println("Download run " + saveTo);
			Out = new FileOutputStream(saveTo);
			byte[] buffer = new byte[1024];
			int count;

			// System.out.println("run 1");
			// socket.getInputStream().read();
			// System.out.println("run 11");
			// //ois = new ObjectInputStream(socket.getInputStream());
			oos.write(1);
			oos.flush();
			// System.out.println("run 2");
			while ((t = is.read()) >= 0 && (count = is.read(buffer)) >= 0) {
				// System.out.println("Download " + count);
				if (t == p) {
					p = (p==255?0:p+1);
					Out.write(buffer, 0, count);
					oos.write(1);
					
				} else {
					nFail++;
					oos.write(2);
					
					
				}
				oos.flush();
			}

			Out.flush();

			if (oos != null)
				oos.close();

			if (is != null)
				is.close();

			if (Out != null) {
				Out.close();
			}

		} catch (Exception ex) {
			System.out.println("Exception [Download : run(...)]");
			ex.printStackTrace();
		}
		System.out.println("p " + p + " nFail " + nFail);
	}
}