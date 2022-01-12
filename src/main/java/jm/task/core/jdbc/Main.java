package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Dima","Krasnov", (byte) 38);
        us.saveUser("Vase","Krasnov", (byte) 34);
        us.saveUser("Olga","Krasnova", (byte) 36);
        us.saveUser("Oleg","Krasnov", (byte) 33);
        System.out.println(us.getAllUsers());
        us.cleanUsersTable();
        us.dropUsersTable();

    }
}
