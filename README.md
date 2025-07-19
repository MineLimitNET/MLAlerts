[![MLAlerts: Build](https://github.com/MineLimitNET/MLAlerts/actions/workflows/build.yml/badge.svg)](https://github.com/MineLimitNET/MLAlerts/actions/workflows/build.yml)

# MLAlerts

A very simple velocity plugin for a `/alert` command.

## Installation

Simply drop the jar onto the `/plugins` directory of your Velocity server.
The `-unshaded` jar should **not** be used.

## Commands

| Command Name       | Description                                           |
|--------------------|-------------------------------------------------------|
| `/alert <message>` | Send an alert to all servers in this velocity network |
| `/alertreload`     | Reload the plugin's config file                       |

## Permissions

| Permission Node         | Description                  |
|-------------------------|------------------------------|
| `alerts.command.use`    | Use the /alert command       | 
| `alerts.command.reload` | Use the /alertreload command |

## Config

The config is located under `/plugins/mlalerts/config.yml` and has the following content:

```yml
format: |-
  <white>
  <b><dark_red>ALERT</b> <dark_gray>(<gray><sender></gray>)</dark_gray> <message>
  </white>
  ```

The default one declares a 3-line message to be sent. You can use the full MiniMessage specifications,
viewable here: https://docs.advntr.dev/minimessage/format.html.

You can use the following placeholders:

- `<message>` - The message of the alert.
- `<sender>` - The sender of the alert.