package FanoutExchange;

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
        String message = "Fanout exchange message";

        // second param, routing key cannot be null
        channel.basicPublish("fanout-exchange", "", null, message.getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
