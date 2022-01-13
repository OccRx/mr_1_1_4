package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/db_user";
    private static final String UNAME = "root";
    private static final String PAS = "123456789";
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, UNAME);
                properties.put(Environment.PASS, PAS);
                //properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                //properties.put(Environment.SHOW_SQL, "true");
                //properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //properties.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection =  DriverManager.getConnection(URL,UNAME,PAS);
        } catch (SQLException e) {
            System.out.println("Conn FAIL" + e);
        }
        return connection;
    }
}
