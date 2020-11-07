package ro.andreistoian.RabbitMQExample.rabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.andreistoian.RabbitMQExample.rabbitmq.ConfigureRabbitMq;

@RestController
@Slf4j
public class RabbitMqController {

    @Autowired
    RabbitMessagingTemplate template;

    @PostMapping(value = "/sendrmsg")
    public String sendMessage(@RequestParam String message) {
        template.convertAndSend(ConfigureRabbitMq.EXCHANGE_NAME, ConfigureRabbitMq.QUEUE_NAME, message);
        log.info("Message sent via post request : " + message);
        return "Message sent!";
    }

}
