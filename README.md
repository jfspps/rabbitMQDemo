# rabbitMQDemo

Basic demonstration of a Java app publishing and consuming RabbitMQ messages

To install RabbitMQ on Ubuntu, go [here](https://www.vultr.com/docs/install-rabbitmq-server-ubuntu-20-04-lts). Then set
  up a new queue with the defaults applied with the name "SomeQueue". Run this app and observe how one message is published 
to the queue.

## Publisher and Consumer

Run the Publisher class to send messages to the queue. When the queue has several messages in place, run the Consumer class.
Note that the consumer class will not terminate the connection and will continuously listen for messages.