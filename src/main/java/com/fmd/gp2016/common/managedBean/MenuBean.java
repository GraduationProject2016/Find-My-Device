/**
 * @author Ahmed
 * Created On : Feb 13, 2016 1:26:50 PM
 */
package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;

@Named("menuBean")
@SpringViewScoped
public class MenuBean extends BaseBean {

	@PostConstruct
	public void init() {
	}

	public void logOut() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		redirect("index.xhtml");
	}

}
