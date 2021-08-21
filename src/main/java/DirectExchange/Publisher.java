package DirectExchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Publisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        // set up a queue, SomeQueue
        Channel channel = connection.createChannel();
        String message = "Message for queue, Mobile-Q";

        // first param is the name of the exchange, the second is the routing key of the message
        channel.basicPublish("direct-exchange", "mobile", null, message.getBytes(StandardCharsets.UTF_8));

        String message2 = "Message for queue, AC-Q";
        channel.basicPublish("direct-exchange", "ac", null, message2.getBytes(StandardCharsets.UTF_8));

        String message3 = "Message for queue, TV-Q";
        channel.basicPublish("direct-exchange", "tv", null, message3.getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
