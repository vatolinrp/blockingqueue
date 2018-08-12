package com.epam;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

	public static void main(String[] args) {
		final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
		final Provider provider = new Provider(blockingQueue);
		final Consumer consumer = new Consumer("consumer", blockingQueue);
		final Consumer otherConsumer = new Consumer("otherConsumer", blockingQueue);

		Thread providerThread = new Thread(provider);
		Thread consumerThread = new Thread(consumer);
		Thread otherConsumerThread = new Thread(otherConsumer);

		providerThread.start();
		consumerThread.start();
		otherConsumerThread.start();

	}

}
