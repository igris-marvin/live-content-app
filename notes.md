# Web Socket

1. Web Socket is a communication protocol which provides bi-directional communication channel between a server and a client.

2. Once a web socket connection is established between client and server, both can exchange information endlessly until the connection is closed by any of the parties.

3. Web Socket exchnages information at a high frequency and with low latency.
   
# STOMP

1. Web Socket is just a communication protocol, it doesn't know how to send a message only to the users who are subscribed  to a particular topic, or how to send to a particular user. This is where STOMP is required, it achieves these funtionalities

2. STOMP -> Streaming Text Oriented Messaging Protocol

3. STOMP is a simple text-based protocol, It allows STOMP clients to talk with any message broker supporting the protocol.

4. Support by spring by default

# BACK-END

Web Socket
Spring Messaging STOMP protocol

# CLIENT

SockJS 
STOMP