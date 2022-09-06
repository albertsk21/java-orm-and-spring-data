package softuni.workshop.util;

import java.lang.reflect.Field;

public class ValidatorImpl implements IValidator {

    @Override
    public <O> boolean isValid(O object) throws IllegalAccessException, InstantiationException {

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);
            Object value = field.get(object);

             if(value == null){
                 return false;
             }
        }

        return true;
    }
}
