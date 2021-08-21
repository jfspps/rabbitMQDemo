package HeadersExchange;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Publisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();

        // set up a queue, SomeQueue
        Channel channel = connection.createChannel();
        String message = "Header exchange message";

        Map<String, Object> mobileTVMap = new HashMap<>();
        mobileTVMap.put("item1", "mobile");
        mobileTVMap.put("item2", "tv");

        BasicProperties basicPropertiesMobTV = new BasicProperties();
        basicPropertiesMobTV = basicPropertiesMobTV.builder().headers(mobileTVMap).build();

        // send to mobile and TV
        channel.basicPublish("headers-exchange", "", basicPropertiesMobTV, message.getBytes(StandardCharsets.UTF_8));

        Map<String, Object> tvMap = new HashMap<>();
        tvMap.put("item1", "television");
        tvMap.put("item2", "trousers");

        BasicProperties basicPropertiesTV = new BasicProperties();
        basicPropertiesTV = basicPropertiesTV.builder().headers(tvMap).build();

        // send to mobile and TV
        channel.basicPublish("headers-exchange", "", basicPropertiesTV, message.getBytes(StandardCharsets.UTF_8));

        Map<String, Object> acMap = new HashMap<>();
        acMap.put("item1", "mobile");
        acMap.put("item2", "ac");

        BasicProperties basicPropertiesAC = new BasicProperties();
        basicPropertiesAC = basicPropertiesAC.builder().headers(acMap).build();

        // send to AC only
        channel.basicPublish("headers-exchange", "", basicPropertiesAC, message.getBytes(StandardCharsets.UTF_8));

        // mobile: 2, TV: 2 and AC :1

        channel.close();
        connection.close();
    }
}
