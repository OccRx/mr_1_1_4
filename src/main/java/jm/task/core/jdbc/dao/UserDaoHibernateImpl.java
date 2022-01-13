package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user" +
                " (id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(25), lastName VARCHAR(25), age TINYINT)";
        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE IF EXISTS user";
        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.createSQLQuery(sqlDrop).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
            System.out.printf("User с именем – %s добавлен в базу данных \n", name);
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = Util.getSessionFactory().openSession()){
            return session.createQuery("from User").list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
