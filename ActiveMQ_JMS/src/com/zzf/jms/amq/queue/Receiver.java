package com.zzf.jms.amq.queue;

import java.util.ArrayList;
import java.util.Arrays;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		receiver.receive();
	}
	
	public void receive() {
		ActiveMQConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		
		try {
			connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			//Trust package if using objectMessage
			connectionFactory.setTrustAllPackages(true);
//			connectionFactory.setTrustedPackages(new ArrayList<>(Arrays.asList("com.zzf.jms.amq.queue.Student")));
			
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination myDestination = session.createQueue("myQueue");
			
			//Create a consumer for certain queue
			MessageConsumer messageConsumer = session.createConsumer(myDestination);
			
			//A receiver has to start the connection
			connection.start();
			
			//Text message
			/*
			TextMessage textMessage = (TextMessage)messageConsumer.receive();
			System.out.println("Text: " + textMessage.getText());
			*/
			
			//Object message
			/*
			ObjectMessage objectMessage = (ObjectMessage)messageConsumer.receive();
			Student student = (Student)objectMessage.getObject();
			System.out.println(student.getId() + " " + student.getName() + " " + student.getDepartment());
			*/
			
			//Map message
			/*
			MapMessage mapMessage = (MapMessage)messageConsumer.receive();
			System.out.println("Received object: \n" + "Name: " + mapMessage.getString("Name") + "\n"
					+ "Niubi: " + mapMessage.getBoolean("niubi"));
			*/
			
			//Byte message
			/*
			BytesMessage bytesMessage = (BytesMessage)messageConsumer.receive();
			boolean boo = bytesMessage.readBoolean();
			long lon = bytesMessage.readLong();
			String string = bytesMessage.readUTF();
			System.out.println(boo + " " + lon + " " + string);
			*/
			
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
