package com.solvd.connectionpooldemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountDao {

    private static final Logger log = LogManager.getLogger(AccountDao.class);

    private final int id;

    public AccountDao(int id) {
        this.id = id;
    }

    public void create() {
        log.info("{} - Creating account (Conn {})", Thread.currentThread().getName(), id);
    }

    public void read() {
        log.info("{} - Reading account (Conn {})", Thread.currentThread().getName(), id);
    }

    public void update() {
        log.info("{} - Updating account (Conn {})", Thread.currentThread().getName(), id);
    }

    public void delete() {
        log.info("{} - Deleting account (Conn {})", Thread.currentThread().getName(), id);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof AccountDao that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "AccountDao{id=" + id + "}";
    }
}
