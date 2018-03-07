package modular.core;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import modular.spi.LoadableExtension;

public class CoreExtension implements LoadableExtension {

    @Override
    public void decorateOptionParser(OptionParser parser)
    {
        System.out.printf("CoreExtension.decorateOptionParser\n");
        parser.accepts("port", "P2P port").withOptionalArg().ofType(int.class).defaultsTo(8383);
    }

    @Override
    public AbstractModule configure(OptionSet options)
    {
        System.out.printf("CoreExtension.configure: %s\n", options.asMap());
        final CoreEnvironment environment = new CoreEnvironment(options);
        return new CoreModule(environment);
    }

    @Override
    public void start(Injector injector)
    {
        System.out.printf("CoreExtension.start\n");
        final Startup startup = injector.getInstance(Startup.class);
        startup.start();
    }
}
