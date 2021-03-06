package com.deutsche.operator.rabbit;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RabbitConfiguration {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    @Autowired
    private RestTemplate restTemplate;
    @Value("${bot.url}")
    private String botUrl;
//    @Autowired
//    VkDataService vkDataService;

    //настраиваем соединение с RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue VkTasksResponseQueue() {
        return new Queue("VkTasksResponseQueue");
    }

    //объявляем контейнер, который будет содержать листенер для сообщений
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer2() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("VkTasksResponseQueue");
        container.setMessageListener(new MessageListener() {
            //тут ловим сообщения из VkTasksResponseQueue
            public void onMessage(Message message) {
                RestTemplate restTemplate2 = new RestTemplate();
                String query = botUrl + "queue";
                String answerFromQueue =  new String(message.getBody());
                System.out.println("received from VkTasksResponseQueue : " + answerFromQueue);
                System.out.println(query);
                String answer = restTemplate2.postForObject(query, answerFromQueue, String.class);
                System.out.println("received from TelegramBot : " + answer);
            }
        });
        return container;
    }
}
