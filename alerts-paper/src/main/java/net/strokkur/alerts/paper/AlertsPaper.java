package net.strokkur.alerts.paper;

import net.strokkur.alerts.shared.AlertsShared;
import org.bukkit.plugin.java.JavaPlugin;

public class AlertsPaper extends JavaPlugin {

    @Override
    public void onLoad() {
        AlertsShared.printInit(getSLF4JLogger());
    }
}
