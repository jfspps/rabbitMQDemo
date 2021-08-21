# rabbitMQDemo

Basic demonstration of a Java app publishing and consuming RabbitMQ messages

To install RabbitMQ on Ubuntu, go [here](https://www.vultr.com/docs/install-rabbitmq-server-ubuntu-20-04-lts). Then set
  up a new queue with the defaults applied with the name "SomeQueue". Run this app and observe how one message is published 
to the queue. RabbitMQ (with management panel enabled) is usually `http://localhost:15672` by default.

## Publisher and Consumer

Run the Publisher class to send messages to the queue. When the queue has several messages in place, run the Consumer class.
Note that the consumer class will not terminate the connection and will continuously listen for messages.

# Exchanges in RabbitMQ

Each exchange type should be demonstrated with different exchanges and set to the give type. The binding can be set up through
the queue itself or via the exchange in the management UI, though there must be bound between the exchange and the
queue(s).

## Direct Exchange

The exchange funnels messages with a _routing key_ to a queue with a matching _binding key_. An exchange can bind to 
multiple queues, each with different binding keys. The message that hits the exchange is then 
sent to the appropriate queue based on the routing key in the message.

## Fanout Exchange

The exchange sends the message to all queues that are bound to the exchange.

## Topic exchange

This is a somewhat less strict version of direct exchange in that the queues'
binding keys take the form of an expression. If the expression allows for or agrees with the routing key of the message 
then the exchange will publish the message to the queue.

For example, queue with the binding key `"*.someText.*"` will accept all messages with a routing key where * is replaced with any 
unbroken sequence of characters e.g. `"abc.someText.xyz12"`. A queue with a binding key `"someMoreText.#"` will accept messages which 
have a routing key where # is replaced by any number of strings. Note here that the period . in the routing and binding keys are 
equivalent to a whitespace in between words. There are other possibilities, including `"#.someOtherText.*.*.someMoreText"` etc.

## Header exchange

For this exchange, there is no routing key involved. Instead, a header is sent with the message, as a key-value pair. The matching `any` 
and `all` are equivalent to `OR` and `AND`.

For example, the message could come with the header

```
header = {
  "keyItem1=mob", "keyValue1=tv"
}
```

The following queues would receive the message:

```
header = {
  "x-match = any",
  "keyItem1=mob", "keyValue2=trees"
}
```

```
header = {
  "x-match = any",
  "keyItem33=bananas", "keyValue1=tv"
}
```

```
header = {
  "x-match = any",
  "keyItem1=mob", "keyValue1=tv"
}
```

```
header = {
  "x-match = all",
  "keyItem1=mob", "keyValue1=tv"
}
```

The following would not receive the message:

```
header = {
  "x-match = all",
  "keyItem33=twigs", "keyValue1=tv"
}
```

```
header = {
  "x-match = all",
  "keyItem1=mob", "keyValue11=branches"
}
```

```
header = {
  "x-match = all",
  "keyItem33=lakes", "keyValue13=rivers"
}
```