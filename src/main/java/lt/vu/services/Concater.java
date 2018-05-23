package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class Concater implements Formater, Serializable{

    @Override
    public String format(String name) {
        return name.concat("_formated");
    }
}
