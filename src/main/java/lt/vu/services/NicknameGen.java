package lt.vu.services;

import org.apache.deltaspike.core.api.future.Futureable;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Futureable
public interface NicknameGen {
    Future<String> generateNickname();
}
