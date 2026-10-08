# Pillars of Fortune API

![Version](https://img.shields.io/badge/version-3.0.0-blue)
![MC Version](https://img.shields.io/badge/minecraft-1.21.11-green)
![Java](https://img.shields.io/badge/java-25-orange)

The public developer API for **Pillars of Fortune 3**.

📖 **Documentation:** [POF Wiki](https://github.com/MrKammounYT/POF/tree/V3/wiki), and for developers, the [API Overview](https://github.com/MrKammounYT/POF/blob/V3/wiki/developers/api-overview.md)

## What you can do

* **Addons:** add your own Item Modes, Map Modes, celebration effects and achievements with a jar in `plugins/POF/addons/`. See [Writing an Addon](https://github.com/MrKammounYT/POF/blob/V3/wiki/developers/writing-an-addon.md).
* **Bukkit plugins:** read POF's registries, check and grant achievements, and listen to POF's events.

POF's own built-in modes and celebration effects use this same API, so anything they do, your addon can do too.

## Quick start

Add the API jar as a `compileOnly` dependency. Don't shade it, because POF provides it at runtime.

```kotlin
dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly(files("libs/POF-API-3.0.0.jar"))
}
```

Add `softdepend: [POF]` to your `plugin.yml`, then:

```java
if (POFProvider.isAvailable()) {
    POFAPI pof = POFProvider.get();
}
```

Inside an addon, use `context.getApi()` instead.

## Looking for v2?

The v2 API lives on the [`v2`](https://github.com/MrKammounYT/POF-API/tree/v2) branch and the `v2.0.7` tag.
