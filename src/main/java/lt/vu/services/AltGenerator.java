package lt.vu.services;

import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.concurrent.Future;

@ApplicationScoped
@Alternative
public class AltGenerator implements Serializable, NicknameGen {
    private static int count = 0;

    @Futureable
    public Future<String> generateNickname() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        final String generatedNickname = "nicknameALTERNATIVES" + count;
        count++;
        return new AsyncResult<>(generatedNickname);
    }
}