package com.zzf.jms.amq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {

	public static void main(String[] args) {
		
		Publisher publisher = new Publisher();
		publisher.publish("Hello World!!");
	}

	public void publish(String msg) {
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		
		try {
			connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination myDestination = session.createTopic("myTopic");
			
			MessageProducer messageProducer = session.createProducer(myDestination);
			
			TextMessage textMessage = session.createTextMessage(msg);
			
			messageProducer.send(textMessage);		
			
		} catch (JMSException e) {
			e.printStackTrace();
		}finally {
			try {
				if(session != null)
					session.close();
				if(connection != null)
					connection.close();
			} catch (JMSException e2) {
				e2.printStackTrace();
			}
		}
	}
}
