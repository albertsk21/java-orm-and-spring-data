package orm;

import entities.User;
import orm.anntotations.Column;
import orm.anntotations.Entity;
import orm.anntotations.Id;

import javax.swing.plaf.nimbus.State;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E>{


        private final static String INSERT_QUERY = "INSERT INTO %s (id, %s) VALUE (%s);";
        private final static String UPDATE_QUERY = "UPDATE %s SET %s WHERE %s";
        private final static String SELECT_STAR_FROM = "SELECT * FROM ";
        private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException{

        Field primaryKey = this.getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object value = primaryKey.get(entity);


        if(value == null || (int) value <= 0){
            return  this.doInsert(entity,primaryKey);
        }
        return this.doUpdate(entity,primaryKey);

    }



    private Field getId(Class entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(x-> x.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(()-> new UnsupportedOperationException("Entity does not have a primary key"));
    }


    private void fillEntity(Class < E > table, ResultSet resultSet, E entity)
            throws SQLException, IllegalAccessException {

        Field[] declaredFields = table.getDeclaredFields();
        for (Field field: declaredFields) {
            field.setAccessible(true);
            fillField(field, entity, resultSet,
                    field.isAnnotationPresent(Id.class)
                            ? "id" : field.getAnnotation(Column.class).name());
        }
    }

    private void fillField(Field field, E entity, ResultSet resultSet, String name)
            throws SQLException, IllegalAccessException {

        field.setAccessible(true);
        switch (name) {
            case "id" : field.set(entity, resultSet.getInt("id")); break;
            case "username" : field.set(entity, resultSet.getString("username")); break;
            case "password" : field.set(entity, resultSet.getString("password")); break;
            case "age" : field.set(entity, resultSet.getInt("age")); break;
            case "registration_date" : field.set(entity,LocalDate.parse( resultSet
                    .getString("registration_date"))); break;
        }
    }
    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<E> foundUsers = new ArrayList<>();
        Statement statement = connection.createStatement();
        String query = SELECT_STAR_FROM + getTableName(table);

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            E entity = table.getConstructor().newInstance();
            this.fillEntity(table,resultSet,entity);
            foundUsers.add(entity);
        }
        return foundUsers;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<E> foundUsers = new ArrayList<>();
        Statement statement = connection.createStatement();
        String query = SELECT_STAR_FROM + getTableName(table) + (where.equals("") ? "" :" " + where + ";");

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            E entity = table.getConstructor().newInstance();
            this.fillEntity(table,resultSet,entity);
            foundUsers.add(entity);
        }
        return foundUsers;
    }

    @Override
    public E findFirst(Class<E> table) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Statement statement = connection.createStatement();
        String query = SELECT_STAR_FROM + getTableName(table) + " LIMIT 1;";
       ResultSet resultSet =  statement.executeQuery(query);

       E entity = table.getConstructor().newInstance();
        resultSet.next();
        this.fillEntity(table,resultSet,entity);
        return entity;
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        Statement statement = connection.createStatement();
        String query = SELECT_STAR_FROM + getTableName(table) + (where.equals("") ? "" :" " + where + " ")  +"LIMIT 1;";
        ResultSet resultSet =  statement.executeQuery(query);

        E entity = table.getConstructor().newInstance();
        resultSet.next();
        this.fillEntity(table,resultSet,entity);
        return entity;
    }


    private String getTableName (Class<?> entity){
        Entity annotation = entity.getAnnotation(Entity.class);


        if(annotation != null && annotation.name() != null){
            return  annotation.name();
        }else{
            return  entity.getClass().getSimpleName();
        }



    }

    private  boolean doInsert(E entity, Field primaryKey) throws SQLException {


        String tableName = this.getTableName(entity.getClass());

        List<String> fieldNames = this.getFieldNames(entity);
        List<String> fieldValues = this.getFieldValues(entity);



        String insertQuery = String.format(INSERT_QUERY,tableName,String.join(", ",fieldNames),String.join(", ",fieldValues));


        return this.executeQuery(insertQuery);
    }

    private boolean executeQuery(String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.execute();
    }
    private boolean doUpdate(E entity, Field primaryKey) throws IllegalAccessException, SQLException {

        String tableName = this.getTableName(entity.getClass());

        List<String> setFieldNameAndValues = Arrays.stream(entity.getClass()
                .getDeclaredFields())
                .map(getFieldNameAndValue(entity))
                .collect(Collectors.toList());

        String updateQuery  = String.format(UPDATE_QUERY,tableName,String.join(", ",setFieldNameAndValues)," id = " + primaryKey.get(entity));


        return this.executeQuery(updateQuery);
    }


    private Function<Field,String> getFieldNameAndValue (E entity){

        return x -> {
            x.setAccessible(true);
            try {
                return String.format(" %s = %s",x.isAnnotationPresent(Id.class) ? "id" : x.getAnnotation(Column.class).name(), x.getType() == String.class || x.getType() == LocalDate.class ?"'" + x.get(entity) + "'" : x.get(entity).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return "";
        };



    }


    private List<String> getFieldValues(E entity) {


        return Arrays.stream(entity.getClass().getDeclaredFields())
         .map( x->{
            x.setAccessible(true);

            try{
                return x.getType() == String.class || x.getType() == LocalDate.class
                        ? "'" + x.get(entity) + "'"
                        : x.get(entity).toString();


            }catch (IllegalAccessException e){
                e.printStackTrace();
            }

            return "";
            }).collect(Collectors.toList());
    }
    private List<String> getFieldNames(E entity) {


        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(x-> x.isAnnotationPresent(Column.class))
                .map(x ->{
                    x.setAccessible(true);
                    return x.getAnnotation(Column.class).name();
                }).collect(Collectors.toList());



    }
}

