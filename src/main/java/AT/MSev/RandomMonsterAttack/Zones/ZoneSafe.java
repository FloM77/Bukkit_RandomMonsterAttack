package AT.MSev.RandomMonsterAttack.Zones;

import AT.MSev.Mango_Core.Zones.Interactable.ZoneInteractable;
import AT.MSev.RandomMonsterAttack.RandomMonsterAttack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Map;

public class ZoneSafe extends ZoneInteractable {

    public ZoneSafe(Location bound1, Location bound2) {
        super(bound1, bound2);
    }

    @Override
    @EventHandler
    protected Boolean OnCommandIn(PlayerCommandPreprocessEvent e) {
        if(e.getPlayer().isOp())
        {
            if(e.getMessage().equalsIgnoreCase("/safezoneremove"))
            {
                Remove();
                e.setCancelled(true);
            }
        }
        return true;
    }

    @Override
    @EventHandler
    protected Boolean OnEnter(PlayerMoveEvent e) {
        Boolean res = super.OnEnter(e);
        if(res)
        {
            e.getPlayer().sendMessage("You entered a safe zone");
            RandomMonsterAttack.Safe.add(e.getPlayer().getUniqueId());
        }
        return res;
    }


    @Override
    @EventHandler
    protected Boolean OnLeave(PlayerMoveEvent e) {
        Boolean res = super.OnLeave(e);
        if(res)
        {
            e.getPlayer().sendMessage("You left the safe zone");
            RandomMonsterAttack.Safe.remove(e.getPlayer().getUniqueId());
        }
        return res;
    }

    public static ZoneSafe deserialize(Map<String, Object> args) {
        Location q1 = ((Location)args.get("q1"));
        Location q2 = ((Location)args.get("q2"));
        ZoneSafe ret = new ZoneSafe(q1, q2);
        return ret;
    }
}
