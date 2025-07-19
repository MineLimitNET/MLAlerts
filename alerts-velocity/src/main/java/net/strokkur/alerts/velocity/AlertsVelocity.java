/*
 * MLAlerts - A simple velocity alerts plugin for MineLimit.
 * Copyright (C) 2025 Strokkur24
 * Copyright (C) 2025 MineLimit
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.strokkur.alerts.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import net.strokkur.alerts.velocity.commands.CommandAlert;
import net.strokkur.alerts.velocity.commands.CommandAlertReload;
import net.strokkur.alerts.velocity.config.AlertsConfig;
import net.strokkur.alerts.velocity.config.AlertsConfigImpl;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

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

    @Inject
    @DataDirectory
    private Path dataPath;

    @Subscribe
    public void onEnable(ProxyInitializeEvent event) {
        AlertsConfig config = new AlertsConfigImpl();

        try {
            config.reload(dataPath, logger);
        } catch (IOException exception) {
            logger.warn("Failed to initially load the config file '{}'. Please fix all issues and run '/alertreload'.", AlertsConfig.FILE_NAME, exception);
        }

        BrigadierCommand alertCommand = new BrigadierCommand(CommandAlert.create(config, server));
        BrigadierCommand alertReloadCommand = new BrigadierCommand(CommandAlertReload.create(config, dataPath, logger));

        CommandMeta alertMeta = server.getCommandManager().metaBuilder(alertCommand)
            .plugin(this)
            .build();

        CommandMeta alertReloadMeta = server.getCommandManager().metaBuilder(alertReloadCommand)
            .plugin(this)
            .build();

        server.getCommandManager().register(alertMeta, alertCommand);
        server.getCommandManager().register(alertReloadMeta, alertReloadCommand);
    }
}