package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.NicknameGenerator;

import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//@Specializes
public class SpecGenerateAuthorNickname extends GenerateAuthorNickname{
    @Inject
    NicknameGenerator nicknameGenerator;

    private Future<String> nicknameGenerationTask = null;

    @LoggedInvocation
    public String generateNewNickname() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        nicknameGenerationTask = nicknameGenerator.generateNickname();
        return  "/author.xhtml?faces-redirect=true&authorId=" + requestParameters.get("authorId");
    }

    @LoggedInvocation
    public boolean isNicknameGenerationRunning() {
        return nicknameGenerationTask != null && !nicknameGenerationTask.isDone();
    }

    @LoggedInvocation
    public String getNicknameGenerationStatus() throws ExecutionException, InterruptedException {
        if (nicknameGenerationTask == null) {
            return null;
        } else if (isNicknameGenerationRunning()) {
            return "Nickname generation in progress";
        }
        return "Suggested specialized nickname: " + nicknameGenerationTask.get();
    }
}
