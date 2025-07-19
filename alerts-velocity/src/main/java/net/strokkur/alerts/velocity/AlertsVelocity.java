package net.strokkur.alerts.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.strokkur.alerts.shared.AlertsShared;
import org.slf4j.Logger;

@Plugin(
    id = "mlalerts",
    name = "MLAlerts",
    version = Versions.PLUGIN_VERSION,
    description = "Alert plugin for MineLimit",
    url = "https://github.com/MineLimitNET/MlAlerts",
    authors = "Strokkur24"
)
public class AlertsVelocity {

    @Inject
    private Logger logger;

    @Inject
    private ProxyServer server;

    @Subscribe
    public void onEnable(ProxyInitializeEvent event) {
        AlertsShared.printInit(logger);
    }
}