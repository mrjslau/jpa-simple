package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.Formater;
import lt.vu.services.NicknameGen;
import lt.vu.services.NicknameGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateAuthorNickname implements Serializable, GenerateNickname {
    @Inject
    NicknameGen nicknameGenerator;

    @Inject
    Formater nicknameFormater;

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
        return "Suggested nickname: " + nicknameFormater.format(nicknameGenerationTask.get());
    }


}
