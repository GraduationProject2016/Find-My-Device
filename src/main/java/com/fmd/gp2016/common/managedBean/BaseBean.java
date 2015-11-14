package com.fmd.gp2016.common.managedBean;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fmd.gp2016.common.entity.User;
import com.fmd.gp2016.common.service.UserService;

/**
 * @author mohamed265 && Ibrahim Ali
 */

@Component("BaseBean")
public class BaseBean {

	private static final Logger logger = LoggerFactory.getLogger(BaseBean.class);

	@PostConstruct
	public void init() {

	}
}
