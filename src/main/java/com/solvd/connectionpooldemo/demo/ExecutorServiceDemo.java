package com.solvd.connectionpooldemo.demo;

import com.solvd.connectionpooldemo.AccountDao;
import com.solvd.connectionpooldemo.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

    private static final Logger log = LogManager.getLogger(ExecutorServiceDemo.class);

    public static void main(String[] args) throws InterruptedException {

        int poolSize = 5;
        ConnectionPool pool = ConnectionPool.getInstance(poolSize);

        ExecutorService executor = Executors.newFixedThreadPool(7);

        Runnable task = () -> {
            try {
                AccountDao conn = pool.getConnection();
                conn.update();
                Thread.sleep(1500);
                pool.releaseConnection(conn);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 1; i < 8; i++)
            executor.submit(task);

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        log.info("All tasks completed.");
    }
}
