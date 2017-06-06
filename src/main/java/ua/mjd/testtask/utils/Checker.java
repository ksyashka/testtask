package ua.mjd.testtask.utils;


import org.springframework.stereotype.Component;
import ua.mjd.testtask.exceptions.UnexpectedNullException;

@Component
public class Checker {
    public void checkNull(Object o, String s) throws UnexpectedNullException {
        if(o == null) throw new UnexpectedNullException("Result of action - null pointer!\t" + s);
    }
}
