package com.fmd.gp2016.web.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Download implements Runnable {

	public String saveTo = "";
	public InputStream In;
	public FileOutputStream Out;

	public Download(String saveTo, Socket socket) {
		try {
			// System.out.println("Download constract");
			In = socket.getInputStream();
			this.saveTo = saveTo;
		} catch (IOException ex) {
			System.out.println("Exception [Download : Download(...)]");
		}
	}

	@Override
	public void run() {
		int p = 0;
		try {
			// System.out.println("Download run " + saveTo);
			Out = new FileOutputStream(saveTo);

			byte[] buffer = new byte[1024];
			int count ;

			while ((count = In.read(buffer)) >= 0) {
				// System.out.println("Download " + count);
				Out.write(buffer, 0, count);
				p++;
			}
			
			Out.flush();

			// sui.jTextArea1.append("[Application > Me] : Download
			// complete\n");

			if (Out != null) {
				Out.close();
			}
			if (In != null) {
				In.close();
			}

		} catch (Exception ex) {
			System.out.println("Exception [Download : run(...)]");
			ex.printStackTrace();
		}
		System.out.println("p " + p);
	}
}