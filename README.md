# CaptureX-Desktop

A desktop/CLI hybrid project built with Java 17 and Kotlin that includes:
- A Discord bot interface (Javacord) launched via `BotUi`.
- Swing-based desktop UI components (`MainFrame`, `TyperXView`, etc.).
- Utility modules and console helpers.

This repository uses Gradle with the Shadow plugin to produce a single runnable JAR.


## Prerequisites
- Java Development Kit (JDK) 17
  - The project configures the Java 17 toolchain automatically, but having JDK 17 installed is recommended.
- Git (to clone the repository)
- Internet access for Gradle to download dependencies on first build

Note: The Gradle Wrapper (`./gradlew` / `gradlew.bat`) is included, so you don’t need a system-wide Gradle installation.


## Configure the Discord Bot Token (important)
This project embeds the Discord bot token at build time via a generated `BuildConfig` class. You must provide a token before you build; changing the token requires rebuilding.

Two options:

1) Using a properties file (recommended for local builds)
- Create a file named `capture.prop` in the project root (same folder as `build.gradle`).
- Add the following line:
  
  ```
  discord_bot_id=YOUR_DISCORD_BOT_TOKEN_HERE
  ```

2) Using an environment variable
- Set an environment variable named `DISCORD_KEY` to your bot token before running the build.
  - macOS/Linux (bash/zsh): `export DISCORD_KEY=YOUR_DISCORD_BOT_TOKEN_HERE`
  - Windows (PowerShell): `$env:DISCORD_KEY = "YOUR_DISCORD_BOT_TOKEN_HERE"`

Build-time behavior:
- If `capture.prop` exists, its `discord_bot_id` is used.
- Else, if `DISCORD_KEY` is set, that is used.
- Else, an empty key is embedded and the bot will not authenticate.


## Build
From the project root:

- macOS/Linux:
  ```bash
  ./gradlew clean shadowJar
  ```
- Windows (PowerShell or CMD):
  ```bat
  gradlew.bat clean shadowJar
  ```

Artifacts:
- Runnable fat JAR: `build/libs/capturex.jar`

Notes:
- The `shadowJar` task produces the all-in-one JAR without a classifier or version, per the Gradle config.
- The main class is `BotUi` (set in both the application plugin and JAR manifest).


## Run
After building, run the fat JAR:

```bash
java -jar build/libs/capturex.jar
```

Alternatively, you can run directly via Gradle (useful during development):

- macOS/Linux:
  ```bash
  ./gradlew run
  ```
- Windows:
  ```bat
  gradlew.bat run
  ```

Remember: The Discord token is embedded at build time. If you change your token in `capture.prop` or via `DISCORD_KEY`, you must rebuild for the new token to take effect.


## Project Structure (overview)
```
CaptureX-Desktop/
├─ build.gradle                 # Gradle build configuration (Java 17 toolchain, Kotlin, Shadow JAR, token embed)
├─ gradle.properties            # Gradle settings (if any)
├─ settings.gradle              # Gradle settings (root project name, etc.)
├─ capture.prop                 # (optional) Local token config: discord_bot_id=...
├─ gradlew, gradlew.bat         # Gradle wrapper scripts
├─ gradle/wrapper/              # Gradle wrapper JAR and properties
├─ manifest.mf                  # (legacy/aux) manifest file (not required for Shadow JAR)
├─ nbproject/                   # NetBeans project files (optional/IDE)
└─ src/
   └─ main/
      ├─ java/
      │  ├─ MainFrame.java      # Swing UI main frame
      │  ├─ TyperXView.java     # Swing component
      │  ├─ TyperXWorker.java   # Background worker
      │  └─ console/            # Console utilities (completion, input processing, demo)
      ├─ kotlin/
      │  ├─ BotUi.kt            # Application entry point (main class)
      │  ├─ DiscordApp.kt       # Discord/Javacord integration
      │  ├─ ImageUtils.kt       # Image utilities
      │  └─ XBoardApp.kt        # Additional app module
      └─ resources/
         └─ META-INF/native-image/  # GraalVM/native-image configs (JNI, reflection, resources, etc.)
```

Generated sources at build time:
- `build/generated/sources/buildConfig/.../BuildConfig.java` contains:
  - `DISCORD_KEY` (token)
  - `GITHUB_LINK`
  - build timestamps


## Dependencies
- Kotlin: 2.0.21 (JVM target 17)
- Javacord: 3.3.2
- Gradle Shadow Plugin: 8.1.1
- Java toolchain: 17


## Troubleshooting
- Bot not logging in
  - Ensure the token is valid and was provided before building.
  - If you changed the token, rebuild the project.
- `Unsupported class file version` or JVM version issues
  - Ensure you’re using Java 17 to run the JAR.
- Build fails on first run due to network
  - Retry once your network is stable so Gradle can download dependencies.


## Notes
- For Windows users, substitute `gradlew.bat` for `./gradlew` in all commands.
- Ensure your system’s Java points to a JDK 17 for running the app.
