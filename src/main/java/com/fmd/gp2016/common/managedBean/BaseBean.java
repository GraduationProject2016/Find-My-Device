package com.fmd.gp2016.common.managedBean;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fmd.gp2016.common.util.jsf.annotation.SpringViewScoped;
import com.fmd.gp2016.common.util.language.Language;

/**
 * @author mohamed265 && Ibrahim Ali
 */

@SpringViewScoped
public class BaseBean {

	private static final Logger logger = LoggerFactory.getLogger(BaseBean.class);

	public Language getLanguage() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (Language) context.getExternalContext().getSessionMap().get("language");
	}
}
