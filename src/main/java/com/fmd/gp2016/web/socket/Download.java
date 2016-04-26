package com.fmd.gp2016.web.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.fmd.gp2016.common.dto.Acknowledgement;
import com.fmd.gp2016.common.dto.FilePart;

public class Download implements Runnable {

	public String saveTo = "";
	private ObjectInputStream is;
	private ObjectOutputStream oos;
	public FileOutputStream Out;
	public Socket socket;

	public Download(String saveTo, Socket socket, ObjectInputStream is, ObjectOutputStream oos) {
		this.saveTo = saveTo;
		this.socket = socket;
		// oos = new ObjectOutputStream(socket.getOutputStream());
		// oos.flush();
		// System.out.println("Download Constractr");
		// is = new ObjectInputStream(socket.getInputStream());
		this.is = is;
		this.oos = oos;
		System.out.println("Download constract");
	}

	@Override
	public void run() {
		int nFail = 0;
		// Integer t, p = new Integer(0);
		int p = 0;
		try {
			System.out.println("Download run " + saveTo);
			Out = new FileOutputStream(saveTo);
			while (true) {
				try {

					FilePart filePart = FilePart.toFilePart((String) is.readObject());
					// System.out.println("p " + p + " t " +
					// filePart.getPartNum());
					if (filePart.getPartNum() == p) {
						p++;
						oos.writeObject(Acknowledgement.getPositiveAcknowledgement().toJsonString());
						Out.write(filePart.getPart(), 0, filePart.getCount());
					} else {
						oos.writeObject(Acknowledgement.getNagativeAcknowledgement().toJsonString());
						nFail++;
					}
					oos.flush();
				} catch (Exception e) {
					break;
				}
			}
			oos.flush();
			Out.flush();

			cleanUp();

		} catch (Exception ex) {
			System.out.println("Exception [Download : run(...)]");
			ex.printStackTrace();
		}
		System.out.println("p " + p + " nFail " + nFail);
	}

	private void cleanUp() throws IOException {
		if (oos != null)
			oos.close();

		if (is != null)
			is.close();

		if (Out != null) {
			Out.close();
		}

		if (socket != null) {
			socket.close();
		}
	}
}