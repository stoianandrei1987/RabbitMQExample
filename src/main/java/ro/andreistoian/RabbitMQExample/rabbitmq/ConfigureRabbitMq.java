package ro.andreistoian.RabbitMQExample.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.andreistoian.RabbitMQExample.rabbitmq.consumer.RabbitMqReceiveMessageHandler;

@Configuration
public class ConfigureRabbitMq {

    public static final String EXCHANGE_NAME = "springexchange1";
    public static final String QUEUE_NAME = "spgq3";

    @Bean
    MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Queue createQueue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    TopicExchange createExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding createBinding(Queue q, TopicExchange ex) {
        return BindingBuilder.bind(q).to(ex).with(q.getName());
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory factory,
                                             MessageListenerAdapter adapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(adapter);


        return container;

    }

    @Bean
    MessageListenerAdapter createAdapter(RabbitMqReceiveMessageHandler handler) {

        MessageListenerAdapter m = new MessageListenerAdapter(handler, "handleMessage");
        m.setMessageConverter(converter());
        return m;
    }



}
