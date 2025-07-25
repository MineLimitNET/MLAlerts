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
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

public interface AlertsConfig {

    String FILE_NAME = "config.yml";

    void reload(Path dataPath, Logger logger) throws IOException;

    Component getMessageFormat(TagResolver... resolvers);

    Component getTitleFormat(TagResolver... resolvers);

    Component getSubTitleFormat(TagResolver... resolvers);
}