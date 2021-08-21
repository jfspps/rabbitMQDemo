import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class JSON {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("from_date", "01-Mar-2021");
        jsonObject.put("to_date", "15-Mar-2021");
        jsonObject.put("email", "somewhere@something.com");
        jsonObject.put("query", "select * from data");

        channel.basicPublish("", "SomeQueue", null, jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        channel.close();
        connection.close();
    }
}
