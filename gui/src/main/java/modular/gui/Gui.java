package modular.gui;

import com.google.inject.Inject;
import modular.core.SomeCoreService;

public class Gui {

    @Inject
    private GuiEnvironment environment;

    @Inject
    private SomeCoreService someCoreService;

    public void run()
    {
        System.out.printf("Gui.start: theme %s\n", environment.getTheme());
        System.out.printf("Gui.start: is someCoreService initialized: %b\n", someCoreService.isInitialized());
    }
}
