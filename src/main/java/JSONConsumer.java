import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class JSONConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            JSONObject jsonObject = new JSONObject(message);

            System.out.println("Message received: \n");
            System.out.println("from_date: " + jsonObject.get("from_date"));
            System.out.println("to_date: " + jsonObject.get("to_date"));
            System.out.println("email: " + jsonObject.get("email"));
            System.out.println("query: " + jsonObject.get("query"));
        };
        channel.basicConsume("SomeQueue", true, deliverCallback, consumerTag -> {});
    }
}
