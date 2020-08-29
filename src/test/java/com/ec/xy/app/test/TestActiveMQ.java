package com.ec.xy.app.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Producer;
import org.junit.Test;

import com.xiuye.sharp.X;
import com.xiuye.util.cls.XType;

public class TestActiveMQ {

	@SuppressWarnings("static-access")
//	@Test
	public void test1() throws JMSException {
		ConnectionFactory cf = 
				XType.newInstance(ActiveMQConnectionFactory::new,
						ActiveMQConnection.DEFAULT_USER,
						ActiveMQConnection.DEFAULT_PASSWORD,
						ActiveMQConnection.DEFAULT_BROKER_URL
						);
		
		Connection conn = cf.createConnection();
		conn.start();
		
		
		//true : open transaction
		Session s = conn.createSession(true, Session.SESSION_TRANSACTED/* AUTO_ACKNOWLEDGE */);
		Queue q =s.createQueue("demoqueue");
		
		X.taskS(()->{
			try {
				MessageConsumer csr = s.createConsumer(q);
				TextMessage msg = XType.cast(csr.receive());
				msg.acknowledge();
				s.commit();//消费一个数据，队列中数据消失
				X.lnS("receive data:",msg.getText());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		})
		.task(()->{
			MessageProducer pdr;
			
			try {
				pdr = s.createProducer(q);
				pdr.send(s.createTextMessage("Hello World"));
				s.commit();
				X.lnS("send data");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		
		for(;;) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}
