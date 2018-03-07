package modular.core;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class CoreModule extends AbstractModule {

    private final CoreEnvironment environment;

    public CoreModule(CoreEnvironment environment)
    {
        this.environment = environment;
    }

    @Override
    protected void configure()
    {
        bind(CoreEnvironment.class).toInstance(environment);
        bind(SomeCoreService.class).in(Singleton.class);
        bind(Startup.class).in(Singleton.class);
    }
}
