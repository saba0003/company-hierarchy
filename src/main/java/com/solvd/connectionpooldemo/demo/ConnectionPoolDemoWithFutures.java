package com.solvd.connectionpooldemo.demo;

import com.solvd.connectionpooldemo.AccountDao;
import com.solvd.connectionpooldemo.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;

public class ConnectionPoolDemoWithFutures {

    private static final Logger log = LogManager.getLogger(ConnectionPoolDemoWithFutures.class);

    public static void main(String[] args) {

        int poolSize = 5;
        ConnectionPool pool = ConnectionPool.getInstance(poolSize);

        Runnable task = () -> {
            try {
                AccountDao conn = pool.getConnection();

                CompletableFuture<Void> readFuture = CompletableFuture.runAsync(() -> {
                    conn.read();
                    log.info("{} completed async read task", Thread.currentThread().getName());
                });

                CompletableFuture<Void> computeFuture = CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(500);
                        log.info("{} completed async compute task", Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

                CompletableFuture.allOf(readFuture, computeFuture).join();

                Thread.sleep(1000);
                pool.releaseConnection(conn);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 1; i < 8; i++) {
            Thread thread = new Thread(task, "Worker-" + i);
            thread.start();
        }
    }
}
