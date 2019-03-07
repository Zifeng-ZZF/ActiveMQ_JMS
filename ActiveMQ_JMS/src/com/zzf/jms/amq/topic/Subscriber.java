package com.zzf.jms.amq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Subscriber
 * @author FongFong-game
 *
 * NOTE:
 * 1. Subscribers can only consume the messages enqueued after they subscribe the topic.
 * 2. The messages that are already in the queue when new subscribers subscribe cannot be read.
 */
public class Subscriber {

	public Subscriber() {
		// TODO Auto-generated constructor stub
	}
	
	public void subscribe() {
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		
		try {
			connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination myDestination = session.createTopic("myTopic");
			
			connection.start();
			
			MessageConsumer messageConsumer = session.createConsumer(myDestination);
			TextMessage textMessage = (TextMessage) messageConsumer.receive();
			
			System.out.println("Topic message is: " + textMessage.getText());
			
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if(session != null) {
					session.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (JMSException e2) {
				e2.printStackTrace();
			}
		}
	}
}
