package com.crasoftinc.jmatrailers.messages.recievers;

import com.crasoftinc.jmatrailers.messages.models.TrailerDeactivated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TrailerMessageReciever {
  @RabbitListener(queues = "${jma.queues.trailers-queue}")
  public void receivedMessage(TrailerDeactivated trailerDeactivated) {
    log.info("Received Message From RabbitMQ: " + trailerDeactivated);
//    TODO: do something (maybe change database state, refresh cache, service call)
  }
}
