package TopicExchange;

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

        // binding keys available for AC-q, Mobile-q and TV-q: #.ac, *.mobile.* and *.tv.*
        String message = "Mobile and AC binding established";
        channel.basicPublish("topic-exchange", "abc.mobile.ac", null, message.getBytes(StandardCharsets.UTF_8));

        String message2 = "TV binding established";
        channel.basicPublish("topic-exchange", "abc.tv.booth", null, message2.getBytes(StandardCharsets.UTF_8));

        String message3 = "AC binding established";
        channel.basicPublish("topic-exchange", "zyt.abc.ac", null, message3.getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
