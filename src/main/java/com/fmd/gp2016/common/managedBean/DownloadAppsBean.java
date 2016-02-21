/**
 * @author mohamed265
 * Created On : Feb 15, 2016 12:26:19 AM
 */
package com.fmd.gp2016.common.managedBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;

import com.fmd.gp2016.common.util.Constants;
import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;

/**
 * @author mohamed265
 *
 */
@Named("downloadAppsBean")
@SpringViewScoped
public class DownloadAppsBean extends BaseBean {

	private DefaultStreamedContent downloadObject;

	public DownloadAppsBean() {
	}

	public void download(String filePath) throws Exception {
		System.out.println("in download");
		File file = new File(filePath);
		InputStream input = new FileInputStream(file);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		setDownloadObject(
				new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	}

	public DefaultStreamedContent getDownloadObject() {
		System.out.println("GET = " + downloadObject.getName());
		return downloadObject;
	}

	public void setDownloadObject(DefaultStreamedContent downloadObject) {
		this.downloadObject = downloadObject;
	}

	public void downloadDesktopApp() throws Exception {
		download(Constants.CLIENT_DESKTOP);
	}

	public void downloadAndroidApp() throws Exception {
		download(Constants.CLIENT_ANDROID);
	}
}
