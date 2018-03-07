package modular.main;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import modular.spi.LoadableExtension;

import java.io.IOException;
import java.util.ServiceLoader;

public class Main {

    final ServiceLoader<LoadableExtension> loader;

    public static void main(String[] args) throws Exception
    {
        new Main().run(args);
    }

    public Main()
    {
        loader = ServiceLoader.load(LoadableExtension.class);
    }

    private void run(String[] args) throws IOException
    {
        final OptionSet optionSet = parseOptions(args);
        final AbstractModule aggregatedModule = constructAggregatedModule(optionSet);
        final Injector injector = Guice.createInjector(aggregatedModule);
        start(injector);
    }

    private OptionSet parseOptions(String[] args) throws IOException
    {
        final OptionParser parser = decorateOptionParser();
        OptionSet optionSet;
        try {
            optionSet = parser.parse(args);
            return optionSet;
        } catch (OptionException e) {
            System.err.println(e.getMessage());
            parser.printHelpOn(System.err);
            System.exit(1);
            return null;
        }
    }

    private OptionParser decorateOptionParser() throws IOException
    {
        final OptionParser parser = new OptionParser();
        loader.forEach(extension -> extension.decorateOptionParser(parser));
        return parser;
    }

    private AbstractModule constructAggregatedModule(final OptionSet optionSet)
    {
        return new AbstractModule() {
            @Override
            protected void configure()
            {
                loader.forEach(extension -> install(extension.configure(optionSet)));
            }
        };
    }

    private void start(Injector injector)
    {
        loader.forEach(extension -> extension.start(injector));
    }
}
