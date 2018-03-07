package modular.api;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import modular.spi.LoadableExtension;

public class ApiExtension implements LoadableExtension {

    @Override
    public void decorateOptionParser(OptionParser parser)
    {
        System.out.printf("ApiExtension.decorateOptionParser\n");
        parser.accepts("apiPort", "REST API port").withOptionalArg().ofType(int.class).defaultsTo(8080);
        parser.accepts("api", "Enable REST API").withOptionalArg().ofType(boolean.class).defaultsTo(true);
    }

    @Override
    public AbstractModule configure(OptionSet options)
    {
        System.out.printf("ApiExtension.configure: %s\n", options.asMap());
        final ApiEnvironment environment = new ApiEnvironment(options);
        return new ApiModule(environment);
    }

    @Override
    public void start(Injector injector)
    {
        final ApiEnvironment environment = injector.getInstance(ApiEnvironment.class);
        if (!environment.isEnabled()) {
            System.out.printf("ApiExtension will not be started\n");
            return;
        }
        System.out.printf("ApiExtension.start\n");
        final Api api = injector.getInstance(Api.class);
        api.run();
    }
}
