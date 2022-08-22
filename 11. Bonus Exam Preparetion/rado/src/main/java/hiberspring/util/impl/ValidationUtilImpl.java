package hiberspring.util.impl;

import hiberspring.util.ValidationUtil;

import java.lang.reflect.Field;

public class ValidationUtilImpl implements ValidationUtil {
    @Override
    public <E> boolean isValid(E entity) throws IllegalAccessException {
        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
          field.setAccessible(true);
          Object value = field.get(entity);
          if(value == null){
              return false;
          }
        }

        return true;
    }
}
