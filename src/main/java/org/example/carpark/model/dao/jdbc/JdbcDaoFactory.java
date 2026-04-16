package org.example.carpark.model.dao.jdbc;

import org.example.carpark.model.dao.CarDao;
import org.example.carpark.model.dao.CarParkDao;
import org.example.carpark.model.dao.DaoConnection;
import org.example.carpark.model.dao.DaoFactory;
import org.example.carpark.model.dao.exception.DaoException;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import java.sql.*;

public class JdbcDaoFactory extends DaoFactory {
    private final DataSource dataSource;

    public JdbcDaoFactory() {
        try{
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/carpark_db");

        }catch(Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public CarParkDao createCarParkDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCarParkDao(sqlConnection);
    }

    @Override
    public CarDao createCarDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCarDao(sqlConnection);
    }
}
