package modular.api;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ApiModule extends AbstractModule {

    private final ApiEnvironment environment;

    public ApiModule(ApiEnvironment environment)
    {
        this.environment = environment;
    }

    @Override
    protected void configure()
    {
        bind(ApiEnvironment.class).toInstance(environment);
        bind(Api.class).in(Singleton.class);
    }
}
