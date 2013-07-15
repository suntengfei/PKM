package cn.edu.cust.util.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class Init implements ServletContextListener {
	private static Logger log = Logger.getLogger(Init.class);

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.setProperty("java.security.auth.login.config", "c:\\jaas.config");
	}
}
