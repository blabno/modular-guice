package modular.core;

import joptsimple.OptionSet;

public class CoreEnvironment {

    private final int port;

    public CoreEnvironment(OptionSet options)
    {
        port = options.has("port") ? (Integer) options.valueOf("port") : 8383;
    }

    public int getPort()
    {
        return port;
    }
}
