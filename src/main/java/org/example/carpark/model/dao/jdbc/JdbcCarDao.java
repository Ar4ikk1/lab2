package org.example.carpark.model.dao.jdbc;

import org.example.carpark.model.dao.CarDao;
import org.example.carpark.model.dao.exception.DaoException;
import org.example.carpark.model.entity.Car;
import org.example.carpark.model.entity.CarPark;
import org.example.carpark.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCarDao extends AbstractJdbcDao<Car> implements CarDao{
    private static final String SELECT_FROM_CAR = "SELECT c.*, cp.name AS car_park_name " + "FROM cars c JOIN car_parks cp ON c.car_park_id = cp.id ";
    private static final String INSERT_INTO_CAR = "INSERT INTO cars " + "(brand, model, color, license_plate, year, car_park_id)" + " VALUES ( ?, ?, ?, ?, ?, ?) ";
    private static final String DELETE_CAR_BY_ID = "DELETE FROM cars WHERE id = ? ";
    private static final String WHERE_ID = "WHERE id = ? ";
    private static final String UPDATE_CAR = "UPDATE cars " + "SET brand = ?, model = ?, color = ?, license_plate = ?, year = ?, car_park_id = ? " ;
    public JdbcCarDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_CAR;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_CAR;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_CAR + WHERE_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_CAR_BY_ID;
    }

    @Override
    protected Car getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return getCarFromResultSet(resultSet);
    }
    static Car getCarFromResultSet(ResultSet resultSet) throws SQLException {
        CarPark carPark = new CarPark();
        carPark.setId(resultSet.getInt("car_park_id"));
        carPark.setName(resultSet.getString("car_park_name"));
        Car car = new Car(resultSet.getString("brand"), resultSet.getString("model"), resultSet.getString("color"), resultSet.getString("license_plate"), resultSet.getInt("year"), carPark);
        car.setId(resultSet.getInt("id"));
        return car;
    }
    @Override
    protected void setIdForEntity(Car entity, int id) {
        entity.setId(id);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Car entity) throws SQLException {
        query.setString(1 , entity.getBrand());
        query.setString(2 , entity.getModel());
        query.setString(3, entity.getColor());
        query.setString(4, entity.getLicensePlate());
        query.setInt(5, entity.getYear());
        query.setInt(6, entity.getCarPark().getId());
    }
    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Car entity) throws SQLException {
        query.setString(1 , entity.getBrand());
        query.setString(2 , entity.getModel());
        query.setString(3, entity.getColor());
        query.setString(4, entity.getLicensePlate());
        query.setInt(5, entity.getYear());
        query.setInt(6, entity.getCarPark().getId());
        query.setInt(7, entity.getId());
    }
    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_CAR + "WHERE c.id = ? ";
    }

    @Override
    public List<Car>  findByBrand(String brand) {
        List<Car> cars = new ArrayList<>();
        try(PreparedStatement query = connection.prepareStatement(SELECT_FROM_CAR + " WHERE c.brand ILIKE ?")) {
            query.setString(1, "%" + brand + "%");
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                cars.add(getCarFromResultSet(resultSet));
            }
            return cars;
        }
        catch(SQLException e){
            throw new DaoException(e);
        }
    }
    public Optional<Car> findByLicensePlate(String licensePlate) {
        Optional<Car> result = Optional.empty();
        try(PreparedStatement query = connection.prepareStatement(SELECT_FROM_CAR + " WHERE c.license_plate = ?")) {
            query.setString(1, licensePlate);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Car car = getCarFromResultSet(resultSet);
                result = Optional.of(car);
            }
        }
        catch(SQLException e){
            throw new DaoException(e);
        }
        return result;
    }
}