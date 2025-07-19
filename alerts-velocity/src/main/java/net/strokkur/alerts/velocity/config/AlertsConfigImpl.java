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
package net.strokkur.alerts.velocity.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.slf4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class AlertsConfigImpl implements AlertsConfig {

    @MonotonicNonNull
    private AlertsConfigModel model = null;

    @Override
    public void reload(Path path, Logger logger) throws IOException {
        Path filePath = path.resolve(AlertsConfig.FILE_NAME);

        if (!Files.exists(filePath)) {
            saveFile(path, logger);
        }

        YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
            .path(filePath)
            .indent(2)
            .nodeStyle(NodeStyle.BLOCK)
            .build();

        CommentedConfigurationNode node = loader.load(ConfigurationOptions.defaults());
        model = node.get(AlertsConfigModel.class);
    }

    private void saveFile(Path path, Logger logger) throws IOException {
        Path filePath = path.resolve(AlertsConfig.FILE_NAME);
        if (Files.exists(filePath)) {
            return;
        }

        Files.createDirectories(path);
        Files.createFile(filePath);

        OutputStream outputStream = Files.newOutputStream(filePath);
        InputStream inputStream = getClass().getResourceAsStream("/" + AlertsConfig.FILE_NAME);

        if (inputStream == null) {
            logger.warn("Failed to find resource '/{}", AlertsConfig.FILE_NAME);
            return;
        }

        inputStream.transferTo(outputStream);
    }

    @Override
    public Component getMessageFormat(TagResolver... resolvers) {
        return MiniMessage.miniMessage().deserialize(model.format, resolvers);
    }
}
