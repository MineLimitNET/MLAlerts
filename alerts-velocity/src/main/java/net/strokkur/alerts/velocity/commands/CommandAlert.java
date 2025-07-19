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

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.title.Title;
import net.strokkur.alerts.velocity.config.AlertsConfig;

public class CommandAlert {

    public static LiteralCommandNode<CommandSource> create(AlertsConfig config, ProxyServer proxy) {
        return BrigadierCommand.literalArgumentBuilder("alert")
            .requires(source -> source.hasPermission("alerts.command.use"))
            .then(BrigadierCommand.requiredArgumentBuilder("message", StringArgumentType.greedyString())
                .executes(ctx -> {
                    String sourceName = ctx.getSource() instanceof Player player ? player.getUsername() : "console";

                    TagResolver resolver = TagResolver.resolver(
                        Placeholder.unparsed("sender", sourceName),
                        Placeholder.parsed("message", StringArgumentType.getString(ctx, "message"))
                    );

                    for (RegisteredServer server : proxy.getAllServers()) {
                        server.sendMessage(config.getMessageFormat(resolver));
                        server.showTitle(Title.title(
                            config.getTitleFormat(resolver),
                            config.getSubTitleFormat(resolver),
                            Title.DEFAULT_TIMES
                        ));
                    }
                    
                    proxy.getConsoleCommandSource().sendMessage(config.getMessageFormat(resolver));
                    return 1;
                })
            )
            .build();
    }
}
