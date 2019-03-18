# ActiveMQ_JMS
Thesis topic relevant

Measurement and optimization of Java message against Apache ActiveMQ.

Active MQ features:
  1. Asynchronous
  2. Language-independent: client applications can be written in different languages supported by ActiveMQ
  3. High throughput
  
Active MQ acknowledgement mechanism:

Active MQ Safety mechanism:
 1. Security of Web Portal: jetty.xml, jetty-realm.properpties(change account/password)
 2. Security of broker: activemq.xml
    -- Simple authenticication plugin: username password group
    -- Changes in code: ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("usename", "password", "tcp://...");

Active MQ Message:
  1. Header
    1.1 Message Expiration
    1.2 Message Persistence
  2. Properties
  3. Body

Active MQ Storage & Memory:


Two ways of delivering: 
1. Queue: push and pull mode for point to point messaging.
2. Topic: uses Publisher and Subscriber mode for one to many messaging.

queue address: localhost:61616
web portal: localhost:8161

Performance elements:
  1. Throughput
  2. QoS
  3. Network topolpgy
  4. Transport protocols
  5. Hardware, JVM and operating system
  6. Distribution of messages
  7. Message size
  8. Number of producers & consumers
  
Peformace Test: 
  1. JMeter performance test.
  2. Load testing with Camel.
