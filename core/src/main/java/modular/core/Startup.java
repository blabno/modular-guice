package modular.core;

import com.google.inject.Inject;

public class Startup {

    @Inject
    private SomeCoreService someCoreService;

    public void start()
    {
        System.out.println("Startup in progress...");
        someCoreService.init();
    }
}
