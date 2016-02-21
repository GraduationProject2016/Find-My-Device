/**
 * Create On: Dec 9, 2015 8:04:07 AM
 * @author mohamed265
 */
package com.fmd.gp2016.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;

import com.fmd.gp2016.common.util.Constants;

/**
 * @author mohamed265
 */
public class FileDownload {

	private DefaultStreamedContent downloadObject;

	public FileDownload() {
	}

	public void download(String filePath) throws Exception {
		System.out.println("here");
		File file = new File(filePath);
		InputStream input = new FileInputStream(file);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		setDownloadObject(
				new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	}

	public String delete(String filePath) {
		new File(filePath).delete();
		return "";
	}

	public DefaultStreamedContent getDownloadObject() {
		System.out.println("GET = " + downloadObject.getName());
		return downloadObject;
	}

	public void setDownloadObject(DefaultStreamedContent downloadObject) {
		this.downloadObject = downloadObject;
	}

}
