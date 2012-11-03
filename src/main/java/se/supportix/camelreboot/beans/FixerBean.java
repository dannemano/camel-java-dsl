package se.supportix.camelreboot.beans;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FixerBean {

	private static final Logger logger = LoggerFactory.getLogger(FixerBean.class);
	
	public void setCorrectValues(Object inmessage,Exchange exchange) {
		logger.info("{} I did nothing here, but autowiring in Spring, connection to databases and more is possible.", inmessage);
		
	}
	
}
