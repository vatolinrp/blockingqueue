package com.epam;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable {

  private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

  private BlockingQueue<String> blockingQueue;
  private String name;

  public Consumer( String name, BlockingQueue<String> blockingQueue) {
    this.blockingQueue = blockingQueue;
    this.name = name;
  }

  @Override
  public void run() {
    while (true) {
      logger.info( "consumer: {} is trying to get a message...", name);
      runByBlockingAndSleeping();
    }
  }

  private void runByBlocking() {
    try {
      final String message = blockingQueue.take();
      logger.info( "message got successfully: {} by consumer: {}", message, name);
    } catch (InterruptedException e) {
      logger.info("exception on get took place");
    }
  }

  private void runByBlockingAndSleeping() {
    try {
      final String message = blockingQueue.take();
      logger.info( "message got successfully: {} by consumer: {}", message, name);
    } catch (InterruptedException e) {
      logger.info("exception on get took place");
    }
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      logger.info("exception on get took place");
    }
  }

  private void runWithoutBlocking() {
    final String message = blockingQueue.poll();
    logger.info( "message got: {} by consumer: {}", message, name);
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      logger.info("exception on get took place");
    }
  }

  private void runWithoutBlockingWithExceptionOnGetFail() {
    try {
      final String message = blockingQueue.remove();
      logger.info( "message got: {} by consumer: {}", message, name);
    } catch (final NoSuchElementException e) {
      logger.info("NoSuchElementException on get took place");
    }
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      logger.info("exception on get took place");
    }
  }
}
