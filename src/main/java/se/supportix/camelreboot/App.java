package se.supportix.camelreboot;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args) throws Exception {
        
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    	
    	CamelContext camelContext = new SpringCamelContext(context);
        
        
        ProducerTemplate template = camelContext.createProducerTemplate();
        
        RouteBuilder route1 = new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				//BeanRef används i en route som processor.
				from("direct:javadsl1").filter( simple("${body} == 'Hej-1'") ).beanRef("fixerBean").to("log:se.supportix?level=INFO&showAll=true");
				
				//BeanRef används i en route som endpoint!.
				from("direct:javadsl2").filter( simple("${body} == 'Hej-2'") ).beanRef("fixerBean");
				
			}
		};
        
		camelContext.addRoutes(route1);
		
		camelContext.start();
		
		template.sendBody("direct:javadsl1", "Hej-1");
		template.sendBody("direct:javadsl1", "Hej-2");
		template.sendBody("direct:javadsl2", "Hej-1");
		template.sendBody("direct:javadsl2", "Hej-2");
		
		
		camelContext.stop();
    }
    
    
}
