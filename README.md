# PillarsOfFortune API

![Version](https://img.shields.io/badge/version-2.0.7-blue)
![MC Version](https://img.shields.io/badge/minecraft-1.21.5-green)
![License](https://img.shields.io/badge/license-All%20Rights%20Reserved-red)

The official developer API for the **PillarsOfFortune** Minecraft minigame plugin by Eagle Studios.

---

## Table of Contents
- [Setup](#setup)
- [Getting the API Instance](#getting-the-api-instance)
- [Players](#players)
- [Arenas](#arenas)
- [Events](#events)
- [Enums](#enums)
- [Full Example Plugin](#full-example-plugin)

---

## Setup

### 1. Add the dependency

#### JitPack (Recommended)

**Gradle**
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compileOnly 'com.github.MrKammounYT:POF-API:main-SNAPSHOT'
}
```

**Maven**
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.MrKammounYT</groupId>
    <artifactId>POF-API</artifactId>
    <version>main-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```

---

### 2. Declare the dependency in plugin.yml

```yaml
depend: [PillarsOfFortune]
# or use softdepend if POF is optional:
softdepend: [PillarsOfFortune]
```

---

## Getting the API Instance

Use `POFProvider` to access the API. Always check availability first if using `softdepend`.

```java
import com.eagle.studios.api.POFAPI;
import com.eagle.studios.api.POFProvider;

// Hard depend — API is guaranteed to be loaded
POFAPI api = POFProvider.getAPI();

// Soft depend — check first
if (POFProvider.isAvailable()) {
    POFAPI api = POFProvider.getAPI();
    // use the api
}
```

> `getAPI()` throws `IllegalStateException` if POF is not loaded yet, so always call it inside `onEnable()` or later.

---

## Players

The `POFPlayer` interface gives you access to a player's stats and coin balance.

### Getting a POFPlayer

```java
POFAPI api = POFProvider.getAPI();

// From a Bukkit Player
POFPlayer pofPlayer = api.getPlayer(player);

// From a UUID
POFPlayer pofPlayer = api.getPlayer(uuid);
```

Both return `null` if the player has no data.

### Reading Stats

```java
int kills  = pofPlayer.getKills();
int deaths = pofPlayer.getDeaths();
int wins   = pofPlayer.getWins();
int losses = pofPlayer.getLosses();
double kd  = pofPlayer.getKD();

int gamesPlayed = pofPlayer.getGamesPlayed();
int winStreak   = pofPlayer.getWinStreak();
int maxStreak   = pofPlayer.getMaxWinStreak();
int draws       = pofPlayer.getDraws();

int coins  = pofPlayer.getCoins();
int points = pofPlayer.getPoints();
```

### Modifying Stats

```java
// Coins
pofPlayer.addCoins(100);
pofPlayer.removeCoins(50);
pofPlayer.setCoins(500);
boolean canAfford = pofPlayer.hasEnoughCoins(200);

// Points
pofPlayer.addPoints(10);
pofPlayer.setPoints(100);

// Kills / Deaths / Wins
pofPlayer.addKill();
pofPlayer.addDeath();
pofPlayer.addWin();
pofPlayer.addGamePlayed();
pofPlayer.addDraw();

// Streaks
pofPlayer.setWinStreak(5);
pofPlayer.resetWinStreak();
pofPlayer.setMaxWinStreak(10);
```

### Event Firing Control

By default, stat changes fire `PofPlayerStatsChangeEvent`. You can disable this per-player:

```java
pofPlayer.setFireEvents(false);
pofPlayer.addCoins(100); // no event fired
pofPlayer.setFireEvents(true);
```

---

## Arenas

The `POFArena` interface exposes arena information and state.

### Querying Arenas

```java
POFAPI api = POFProvider.getAPI();

// Get a specific arena by name
POFArena arena = api.getArena("arena1");

// Get all arenas
Collection<? extends POFArena> arenas = api.getArenas();

// Get the next available arena for matchmaking
POFArena next = api.getNextAvailableArena();

// Check total arena count
int total = api.getTotalArenas();
```

### Checking if a Player is in an Arena

```java
if (api.isInArena(player)) {
    POFArena arena = api.getPlayerArena(player);
    // ...
}
```

### Arena Properties

```java
String name        = arena.getArenaName();
String displayName = arena.getDisplayName();
GameState state    = arena.getGameState();

int minPlayers   = arena.getMinPlayers();
int maxPlayers   = arena.getMaxPlayers();
int gameDuration = arena.getGameDuration();
int borderSize   = arena.getBorderSize();
int heightLimit  = arena.getHeightLimit();
int groundLevel  = arena.getGroundLevel();
int voidLevel    = arena.getVoidLevel();
int countdown    = arena.getCountdown();

boolean full     = arena.isFull();
boolean playable = arena.isValidToPlay();
```

### Arena Player Access

```java
Set<UUID> playerUUIDs    = arena.getPlayers();
Set<UUID> spectatorUUIDs = arena.getSpectators();
int activePlayers        = arena.getTotalPlayingPlayers();

// Get online Bukkit Player streams
Stream<Player> players    = arena.getOnlinePlayers();
Stream<Player> spectators = arena.getOnlineSpectators();
Stream<Player> everyone   = arena.getEveryone();

// Check specific players
boolean playing    = arena.isPlayerInArena(player);
boolean spectating = arena.isSpectator(player);

// Get the current winner (if game is finished)
Player winner = arena.getWinner();
```

---

## Events

Listen to POF events like any Bukkit event. All events are fired on the main thread.

### POFArenaJoinEvent

Fired when a player joins an arena.

```java
@EventHandler
public void onArenaJoin(POFArenaJoinEvent event) {
    Player player = event.getPlayer();
    POFArena arena = event.getArena();
    player.sendMessage("Welcome to " + arena.getDisplayName() + "!");
}
```

### POFArenaQuitEvent

Fired when a player leaves an arena.

```java
@EventHandler
public void onArenaQuit(POFArenaQuitEvent event) {
    Player player = event.getPlayer();
    POFArena arena = event.getArena();
}
```

### POFArenaStateChangeEvent

Fired when an arena changes state (e.g. WAITING → STARTING → INPROGRESS).

```java
@EventHandler
public void onStateChange(POFArenaStateChangeEvent event) {
    POFArena arena     = event.getArena();
    GameState oldState = event.getOldState();
    GameState newState = event.getNewState();

    if (newState == GameState.INPROGRESS) {
        // game started
    }
}
```

### POFPlayerElimination

Fired when a player is eliminated during a game.

```java
@EventHandler
public void onElimination(POFPlayerElimination event) {
    Player eliminated = event.getPlayer();
    Player killer     = event.getKiller(); // nullable
    POFArena arena    = event.getArena();
    EntityDamageEvent.DamageCause cause = event.getDeathCause();

    if (killer != null) {
        killer.sendMessage("You eliminated " + eliminated.getName() + "!");
    }
}
```

### POFWinEvent

Fired when a player wins a game.

```java
@EventHandler
public void onWin(POFWinEvent event) {
    Player winner  = event.getPlayer();
    POFArena arena = event.getArena();
    Bukkit.broadcastMessage(winner.getName() + " won in " + arena.getDisplayName() + "!");
}
```

### POFPlayerLobbyJoinEvent

Fired when a player joins the POF lobby.

```java
@EventHandler
public void onLobbyJoin(POFPlayerLobbyJoinEvent event) {
    Player player = event.getPlayer();
}
```

### PofPlayerStatsChangeEvent

Fired when a player's stats are modified (unless `fireEvents` is disabled).

```java
@EventHandler
public void onStatsChange(PofPlayerStatsChangeEvent event) {
    Player player  = event.getPlayer();
    StatTypes type = event.getStatType();

    if (type == StatTypes.COINS_ADD) {
        // player received coins
    }
}
```

---

## Enums

### GameState

Represents the lifecycle of an arena:

| Value | Description |
|---|---|
| `LOBBY` | Players are in the main lobby |
| `INACTIVE` | Arena is disabled |
| `WAITING` | Arena is enabled, waiting for players |
| `STARTING` | Minimum players reached, countdown active |
| `PREGAME` | Short grace period before the game begins |
| `INPROGRESS` | Game is actively running |
| `FINISH` | Game has ended, winner determined |
| `RESETTING` | Arena is resetting to its original state |

### StatTypes

Used in `PofPlayerStatsChangeEvent` to identify which stat changed. Each stat has `_SET`, `_ADD`, and `_REMOVE` variants:

- `COINS_SET`, `COINS_ADD`, `COINS_REMOVE`
- `POINTS_SET`, `POINTS_ADD`, `POINTS_REMOVE`
- `KILLS_SET`, `KILLS_ADD`, `KILLS_REMOVE`
- `DEATHS_SET`, `DEATHS_ADD`, `DEATHS_REMOVE`
- `WINS_SET`, `WINS_ADD`, `WINS_REMOVE`
- `DRAWS_SET`, `DRAWS_ADD`, `DRAWS_REMOVE`
- `GAMES_PLAYED_SET`, `GAMES_PLAYED_ADD`, `GAMES_PLAYED_REMOVE`
- `WIN_STREAK_SET`, `WIN_STREAK_ADD`, `WIN_STREAK_REMOVE`
- `MAX_WIN_STREAK_SET`

---

## Full Example Plugin

```java
import com.eagle.studios.api.POFAPI;
import com.eagle.studios.api.POFProvider;
import com.eagle.studios.api.events.POFWinEvent;
import com.eagle.studios.api.events.POFPlayerElimination;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPOFAddon extends JavaPlugin implements Listener {

    private POFAPI api;

    @Override
    public void onEnable() {
        if (!POFProvider.isAvailable()) {
            getLogger().severe("PillarsOfFortune not found! Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        api = POFProvider.getAPI();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("POF addon enabled! Found " + api.getTotalArenas() + " arenas.");
    }

    @EventHandler
    public void onWin(POFWinEvent event) {
        api.getPlayer(event.getPlayer()).addCoins(50);
        event.getPlayer().sendMessage("You earned 50 bonus coins!");
    }

    @EventHandler
    public void onElimination(POFPlayerElimination event) {
        if (event.getKiller() != null) {
            api.getPlayer(event.getKiller()).addCoins(10);
            event.getKiller().sendMessage("+10 coins for the elimination!");
        }
    }
}
```
