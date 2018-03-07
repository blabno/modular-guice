package modular.core;

import com.google.inject.Inject;

public class SomeCoreService {

    private boolean initialized;

    @Inject
    private CoreEnvironment environment;

    public void init()
    {
        System.out.printf("SomeCoreService.init: port: %d\n", environment.getPort());
        initialized = true;
    }

    public boolean isInitialized()
    {
        return initialized;
    }
}
