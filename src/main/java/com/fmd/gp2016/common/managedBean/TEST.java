/**
 * @author mohamed265
 * Created On : Feb 15, 2016 12:26:19 AM
 */
package com.fmd.gp2016.common.managedBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.json.JSONException;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import com.fmd.gp2016.common.service.DeviceService;
import com.fmd.gp2016.common.util.file.UserFiles;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;

/**
 * @author mohamed265
 *
 */
@Named("test")
@SpringViewScoped
public class TEST extends BaseBean {
	private UserFiles userFiles;

	@Autowired
	private DeviceService deviceService;

	private DefaultStreamedContent downloadObject;

	@PostConstruct
	public void init() throws JSONException {
		userFiles = new UserFiles(getSessionUserID(), deviceService);
	}

	public void download(String filePath) throws Exception {
		System.out.println("in download");
		File file = new File(filePath);
		InputStream input = new FileInputStream(file);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		setDownloadObject(
				new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	}

	public String delete(String filePath) {
		System.out.println("in delete");
		new File(filePath).delete();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("تم حذف الملف"));
		return "";
	}

	public DefaultStreamedContent getDownloadObject() {
		System.out.println("GET = " + downloadObject.getName());
		return downloadObject;
	}

	public void setDownloadObject(DefaultStreamedContent downloadObject) {
		this.downloadObject = downloadObject;
	}

	public UserFiles getUserFiles() {
		return userFiles;
	}

}
