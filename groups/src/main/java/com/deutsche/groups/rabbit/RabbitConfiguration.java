package com.deutsche.groups.rabbit;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);

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

    //объявляем очередь с именем VkTasksQueue
    @Bean
    public Queue myVkTasksQueue() {
        return new Queue("myVkTasksQueue");
    }

    @Bean
    public Queue VkTasksResponseQueue() {
        return new Queue("VkTasksResponseQueue");
    }

    //объявляем контейнер, который будет содержать листенер для сообщений
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer1() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("myVkTasksQueue");
        container.setMessageListener(new MessageListener() {
            //тут ловим сообщения из myVkTasksQueue
            public void onMessage(Message message) {
                System.out.println("received from myVkTasksQueue : " + message.getBody().toString());
                //vkDataService.addPosts(groupId, amountPosts);
            }
        });
        return container;
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
                logger.info("received from VkTasksResponseQueue : " + new String(message.getBody()));

            }
        });
        return container;
    }
}
