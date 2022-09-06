package softuni.workshop.util;

public interface IValidator {
    <O>  boolean isValid(O object) throws IllegalAccessException, InstantiationException;
}
