package org.example.carpark.model.dao.jdbc;

import org.example.carpark.model.dao.CarParkDao;
import org.example.carpark.model.entity.CarPark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcCarParkDao extends AbstractJdbcDao<CarPark> implements CarParkDao {
    private static final String DELETE_CARPARK_BY_ID = "DELETE FROM car_parks WHERE id = ? ";
    private static final String INSERT_INTO_CARPARK = "INSERT INTO car_parks (name) VALUES ( ? ) ";
    private static final String SELECT_FROM_CARPARK = "SELECT * FROM car_parks ";
    private static final String WHERE_ID = "WHERE id = ? ";

    public JdbcCarParkDao(Connection connection) {
        super(connection);
    }
    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_CARPARK;
    }
    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_CARPARK;
    }
    @Override
    protected String getUpdateQuery() {
        throw new UnsupportedOperationException();
    }
    @Override
    protected String getDeleteQuery() {
        return DELETE_CARPARK_BY_ID;
    }
    @Override
    protected CarPark getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return getCarParkFromResultSet(resultSet);
    }

    static CarPark getCarParkFromResultSet(ResultSet resultSet) throws SQLException {
        CarPark carPark = new  CarPark();
        carPark.setId(resultSet.getInt("id"));
        carPark.setName(resultSet.getString("name"));
        return carPark;
    }

    @Override
    protected void setIdForEntity(CarPark entity, int id) {
        entity.setId(id);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement query, CarPark entity) throws SQLException {
        query.setString(1 , entity.getName());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, CarPark entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_CARPARK + WHERE_ID;
    }

    @Override
    public List<CarPark> findByName(String name) {
        throw new UnsupportedOperationException();
    }
}
