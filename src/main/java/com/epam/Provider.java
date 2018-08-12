package com.epam;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Provider implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(Provider.class);

  private BlockingQueue<String> blockingQueue;

  public Provider( BlockingQueue<String> blockingQueue ) {
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    int i = 0;
    while (true) {
      final String message = "message" + i;
      addElementWithAdd(message);
      try {
        Thread.sleep(1000);
      } catch ( InterruptedException e) {
        logger.info("can not sleep");
      }
      i++;
    }
  }

  private void addElementWithPut(final String message) {
    try {
      blockingQueue.put(message);
      logger.info("message was successfully added ({})", message);
    } catch (InterruptedException e) {
      logger.info("message was not added ({})", message);
    }
  }

  private void addElementWithOffer(final String message) {
      if( blockingQueue.offer(message) ) {
      logger.info("message was successfully added ({})", message);
    } else{
      logger.info("probably the queue is full");
    }
  }

  private void addElementWithAdd(final String message) {
    try {
      blockingQueue.add(message);
      logger.info("message was successfully added ({})", message);
    } catch ( IllegalStateException e) {
      logger.info("probably the queue is full, actually: {}", e.getMessage());
    }
  }
}
