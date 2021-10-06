package com.crasoftinc.jmatrailers.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Value("${jma.queues.trailers-queue}")
  private String trailersQueueName;
  @Value("${jma.queues.notifications-queue}")
  private String notificationsQueueName;

  @Value("${jma.main-topic-exchange}")
  private String mainTopicExchange;

  @Value("${jma.queues.trailers}")
  private String trailersBindingKey;
  @Value("${jma.queues.notifications}")
  private String notificationsBindingKey;

  @Bean
  public Queue usersQueue(){
    return new Queue(trailersQueueName, false);
  }

  @Bean
  public Queue notificationsQueue(){
    return new Queue(notificationsQueueName, false);
  }

  @Bean
  public TopicExchange topicExchange(){
    return new TopicExchange(mainTopicExchange);
  }

  @Bean
  public Binding usersBinding(TopicExchange topicExchange, Queue usersQueue){
    return BindingBuilder.bind(usersQueue).to(topicExchange).with(trailersBindingKey);
  }

  @Bean
  public Binding notificationsBinding(TopicExchange topicExchange, Queue notificationsQueue){
    return BindingBuilder.bind(notificationsQueue).to(topicExchange).with(notificationsBindingKey);
  }

}
