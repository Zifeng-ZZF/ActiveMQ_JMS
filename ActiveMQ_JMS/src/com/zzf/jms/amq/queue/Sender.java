/**
 *  1. Open cmd under apache-activemq/bin/
 *  2. run/stop activemq via command "./activemq [start/stop/restart]"
 */
package com.zzf.jms.amq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author ZZF
 * Sender class during point to point communication via ActiveMQ
 */
public class Sender {
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {

		//Run the sender
		Sender sender = new Sender();
		sender.sendMsg("Hello world!");

	}

	public void sendMsg (String msg) {
		
		//Declare necessary connection parts
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		
		try {
			//1. Build a new ActiveMQ connection factory
			connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			
			//2. Set up a connection via this factory
			connection = connectionFactory.createConnection();
			
			/* 3. Create a session through this connection
			 * parameter: boolean transacted, int acknowledgeMode
			 */
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//4. Create a queue in the broker
			Destination myDestination = session.createQueue("myQueue");
			
			//5. Name a producer which produce message in the queue
			MessageProducer messageProducer = session.createProducer(myDestination);
			
			//6. Create the message to be sent with content in it
			
//			TextMessage textMessage = session.createTextMessage(msg);
			
//			Student student = new Student(001, "ZZF", "Sino German");
//			ObjectMessage objectMessage = session.createObjectMessage(student);
			
			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setBoolean("niubi", true);
			mapMessage.setString("Name", "ZZF");
			
			//7. Use the producer to send the message to the queue
			messageProducer.send(mapMessage);
			
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			
			//Close unnecessary connection
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
