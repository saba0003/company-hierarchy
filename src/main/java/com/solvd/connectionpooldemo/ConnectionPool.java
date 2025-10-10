package com.solvd.connectionpooldemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final Logger log = LogManager.getLogger(ConnectionPool.class);

    private static volatile ConnectionPool instance; // needed for double-checked locking
    private final BlockingQueue<AccountDao> pool;    // no volatile needed!

    private ConnectionPool(int size) {
        pool = new ArrayBlockingQueue<>(size);
        for (int i = 1; i <= size; i++)
            pool.add(new AccountDao(i));
    }

    public static ConnectionPool getInstance(int size) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(size);
                }
            }
        }
        return instance;
    }

    public AccountDao getConnection() throws InterruptedException {
        AccountDao connection = pool.take(); // waits if no connection
        log.info("{} acquired {}", Thread.currentThread().getName(), connection);
        return connection;
    }

    public void releaseConnection(AccountDao connection) throws InterruptedException {
        pool.put(connection); // puts it back, possibly unblocking waiting threads
        log.info("{} released {}", Thread.currentThread().getName(), connection);
    }

    public int availableConnections() {
        return pool.size();
    }
}
