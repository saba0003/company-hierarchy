package com.solvd.connectionpooldemo.demo;

import com.solvd.connectionpooldemo.AccountDao;
import com.solvd.connectionpooldemo.ConnectionPool;

public class ConnectionPoolDemo {

    public static void main(String[] args) {

        int poolSize = 5;
        ConnectionPool pool = ConnectionPool.getInstance(poolSize);

        Runnable task = () -> {
            try {
                AccountDao conn = pool.getConnection();
                conn.read();
                Thread.sleep(1500); // simulate work
                pool.releaseConnection(conn);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Create 7 threads -> 2 will have to wait
        for (int i = 1; i < 8; i++) {
            Thread thread = new Thread(task, "Worker-" + i);
            thread.start();
        }
    }
}
