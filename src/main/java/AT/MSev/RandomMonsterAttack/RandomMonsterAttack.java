package AT.MSev.RandomMonsterAttack;

import AT.MSev.Mango_Core.Utils.NMSUtils;
import AT.MSev.RandomMonsterAttack.Commands.CommandZoneSafe;
import AT.MSev.RandomMonsterAttack.Monster.MonsterZombie;
import AT.MSev.RandomMonsterAttack.Zones.ZoneSafe;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class RandomMonsterAttack extends JavaPlugin {
    public static NamespacedKey key;

    public static ArrayList<UUID> Safe = new ArrayList<UUID>();

    @Override
    public void onEnable() {
        key = new NamespacedKey(this, this.getDescription().getName());

        saveDefaultConfig();
        this.getCommand("safezone").setExecutor(new CommandZoneSafe());

        NMSUtils.registerEntity("npc_ambusher", NMSUtils.Type.ZOMBIE, MonsterZombie.class, false);

        getServer().getPluginManager().registerEvents(new Handler(this), this);
    }
    @Override
    public void onDisable() {

    }

    static {
        ConfigurationSerialization.registerClass(ZoneSafe.class, "ZoneSafe");
    }
}
