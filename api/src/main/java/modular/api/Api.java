package modular.api;

import com.google.inject.Inject;
import modular.core.SomeCoreService;

public class Api {

    @Inject
    private ApiEnvironment environment;

    @Inject
    private SomeCoreService someCoreService;

    public void run()
    {
        System.out.printf("Api.start: on port %d\n", environment.getApiPort());
        System.out.printf("Api.start: is someCoreService initialized: %b\n", someCoreService.isInitialized());
    }
}
