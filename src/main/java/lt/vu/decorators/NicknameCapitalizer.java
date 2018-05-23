package lt.vu.decorators;

import lt.vu.services.Concater;
import lt.vu.services.Formater;
import lt.vu.usecases.GenerateNickname;
import lt.vu.usecases.GenerateAuthorNickname;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.concurrent.ExecutionException;

@Decorator
public class NicknameCapitalizer implements Formater {
    @Inject @Delegate private Concater nicknameConcater;

    @Override
    public String format(String name){
        String formatted_nick = nicknameConcater.format(name);
        return formatted_nick.toUpperCase();
    }
}
