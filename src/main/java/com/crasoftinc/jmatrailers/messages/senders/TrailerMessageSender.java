package com.crasoftinc.jmatrailers.messages.senders;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TrailerMessageSender {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Qualifier("notificationsQueue")
  @Autowired
  private Queue queue;

  private void send(String message) {
    rabbitTemplate.convertAndSend(this.queue.getName(), message);
  }
}
