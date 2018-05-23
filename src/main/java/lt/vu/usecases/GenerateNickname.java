package lt.vu.usecases;

import java.util.concurrent.ExecutionException;

public interface GenerateNickname {

    String getNicknameGenerationStatus() throws ExecutionException, InterruptedException;

}
