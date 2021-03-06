package AT.MSev.RandomMonsterAttack;

import AT.MSev.RandomMonsterAttack.Behaivour.ISpawnBehaviour;
import AT.MSev.RandomMonsterAttack.Behaivour.SpawnBehaviourCircle;
import AT.MSev.RandomMonsterAttack.Monster.IMonster;
import AT.MSev.RandomMonsterAttack.Monster.MonsterZombie;
import AT.MSev.RandomMonsterAttack.Schedules.ScheduleMonsterAttack;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Handler implements Listener {

    Plugin plugin;
    public Handler(Plugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    void OnJoin(PlayerJoinEvent e)
    {
        IMonster monster = new MonsterZombie(((CraftWorld)e.getPlayer().getWorld()).getHandle());

        ISpawnBehaviour behaviour = new SpawnBehaviourCircle(
                plugin.getConfig().getDouble("attackDistance")
        );
        behaviour.SetAmount(
                plugin.getConfig().getInt("attackAmount")
        );
        behaviour.SetType(monster);

        int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new ScheduleMonsterAttack(
            plugin.getConfig().getDouble("attackChance"),
            behaviour,
            e.getPlayer()
        ), 0,
            plugin.getConfig().getInt("attackReapeatTicks")
        );

        AmbushSchedules.put(e.getPlayer(), taskID);
    }

    HashMap<Player, Integer> AmbushSchedules = new HashMap<Player, Integer>();

    @EventHandler
    void OnLeave(PlayerQuitEvent e)
    {
        Bukkit.getScheduler().cancelTask(AmbushSchedules.get(e.getPlayer()));
    }
}
