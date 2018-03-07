package modular.gui;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class GuiModule extends AbstractModule {

    private final GuiEnvironment environment;

    public GuiModule(GuiEnvironment environment)
    {
        this.environment = environment;
    }

    @Override
    protected void configure()
    {
        bind(GuiEnvironment.class).toInstance(environment);
        bind(Gui.class).in(Singleton.class);
    }
}
