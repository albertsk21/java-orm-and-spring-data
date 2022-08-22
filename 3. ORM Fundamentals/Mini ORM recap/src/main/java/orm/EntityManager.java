package orm;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;
    private final sealed String INSERT_QUERY = "INSERT INTO %s (id, %s) VALUE (%s);"

    public EntityManager(Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {

        Field primaryKey = this.getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object value = primaryKey.get(entity);

        if(value == null || (int)value <= 0){
            return this.doInsert(entity,primaryKey);
        }else{
            return this.doUpdate(entity,primaryKey);
        }

        return false;
    }

    private boolean doUpdate(E entity, Field primaryKey) {

        String tableName = this.getTableName(entity.getClass());

        List<String> setFieldNameAndValues = entity.getClass()
    }

    private boolean doInsert(E entity, Field primaryKey) throws SQLException {
        String tableName = this.getTableName(entity.getClass());
        List<String> fieldNames = this.getFieldNames(entity);
        List<String> fieldValues = this.getFieldValues(entity);

        String insertQuery = String.format(INSERT_QUERY,tableName, String.join(", ",fieldNames),String.join(", ",fieldValues));
        return this.executeQuery(insertQuery);
    }



    private boolean executeQuery(String query) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(query);
        return statement.executeQuery();
    }

    private List<String> getFieldValues(E entity) {
            return  Arrays.stream(entity.getClass().getDeclaredFields())
                    .map(x ->{
                        x.setAccessible(true);
                        try {
                          return x.getType() == String.class || x.getType() == LocalDate.class ? "'" + x.get(entity) + "'" : x.get(entity).toString()
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }).collect(Collectors.toList());
    }

    private List<String> getFieldNames(E entity) {

        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(x-> x.isAnnotationPresent(Column.class))
                .map( x ->{
                    x.setAccessible(true);
                    return x.getAnnotation(Column.class).name();
                }).collect(Collectors.toList());
    }


    private String getTableName(Class<?> entity){
        Entity annotation = entity.getAnnotation(Entity.class);

        if(annotation != null && annotation.name() != null){
            return annotation.name();
        }else{
            return entity.getClass().getSimpleName();
        }
    }

    private Field getId(Class entity){

        return Arrays.stream(entity.getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }
    @Override
    public Iterable<E> find(Class<E> table) {
        return null;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table, String where) {
        return null;
    }
}
