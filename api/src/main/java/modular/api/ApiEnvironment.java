package modular.api;

import joptsimple.OptionSet;

public class ApiEnvironment {

    private final int apiPort;

    private boolean enabled;

    public ApiEnvironment(OptionSet options)
    {
        apiPort = options.has("apiPort") ? (int) options.valueOf("apiPort") : 8080;
        enabled = !options.has("api") || (boolean) options.valueOf("api");
    }

    public int getApiPort()
    {
        return apiPort;
    }

    public boolean isEnabled()
    {
        return enabled;
    }
}
