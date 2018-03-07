package modular.gui;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import modular.spi.LoadableExtension;

public class GuiExtension implements LoadableExtension {

    @Override
    public void decorateOptionParser(OptionParser parser)
    {
        System.out.printf("GuiExtension.decorateOptionParser\n");
        parser.accepts("guiTheme", "Color theme").withOptionalArg().ofType(String.class).defaultsTo("light");
        parser.accepts("gui", "Enable GUI").withOptionalArg().ofType(boolean.class).defaultsTo(true);
    }

    @Override
    public AbstractModule configure(OptionSet options)
    {
        System.out.printf("GuiExtension.configure: %s\n", options.asMap());
        final GuiEnvironment environment = new GuiEnvironment(options);
        return new GuiModule(environment);
    }

    @Override
    public void start(Injector injector)
    {
        final GuiEnvironment environment = injector.getInstance(GuiEnvironment.class);
        if (!environment.isEnabled()) {
            System.out.printf("GuiExtension will not be started\n");
            return;
        }
        System.out.printf("GuiExtension.start\n");
        final Gui gui = injector.getInstance(Gui.class);
        gui.run();
    }
}
