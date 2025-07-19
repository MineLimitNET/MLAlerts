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
package net.strokkur.alerts.velocity.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import net.strokkur.alerts.velocity.config.AlertsConfig;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

public class CommandAlertReload {
    
    public static LiteralCommandNode<CommandSource> create(AlertsConfig config, Path dataPath, Logger logger) {
        return BrigadierCommand.literalArgumentBuilder("alertreload")
            .requires(source -> source.hasPermission("alerts.command.reload"))
            .executes(ctx -> {
                try {
                    config.reload(dataPath, logger);
                    ctx.getSource().sendRichMessage("<green>Successfully reloaded '" + AlertsConfig.FILE_NAME + "'.");
                } catch (IOException e) {
                    ctx.getSource().sendRichMessage("<red>Failed to reload '" + AlertsConfig.FILE_NAME + "'. See the proxy console for errors.");
                    logger.error("Failed to reload '{}'.", AlertsConfig.FILE_NAME, e);
                }
                
                return 1;
            })
            .build();
    }
}
