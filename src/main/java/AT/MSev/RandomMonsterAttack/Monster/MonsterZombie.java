package AT.MSev.RandomMonsterAttack.Monster;

import AT.MSev.Mango_Core.Entity.EntityNPC.DefaultNPC;
import AT.MSev.Mango_Core.Utils.MangoUtils;
import com.earth2me.essentials.Essentials;
import net.ess3.api.Economy;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.math.BigDecimal;

public class MonsterZombie extends DefaultNPC implements IMonster {
    double moneyDrop;
    public MonsterZombie(World world) {
        super(world);
        setHealth(1);
        moneyDrop = 100;
    }

    @Override
    @EventHandler
    protected Boolean OnKilled(EntityDeathEvent e) {
        Boolean res = super.OnKilled(e);
        if(res){
            if(e.getEntity().getKiller() instanceof Player)
                try {
                    Economy.setMoney(e.getEntity().getKiller().getName(), Economy.getMoneyExact(e.getEntity().getKiller().getName()).add(new BigDecimal(moneyDrop)));
                    e.getEntity().getKiller().sendMessage("You looted $" + moneyDrop);
                } catch (Exception ex) {
                    Bukkit.getLogger().info(ex.getMessage());
                }
        }
        return res;
    }

    public void SpawnIn(Location location) {
        new MonsterZombie(((CraftWorld)location.getWorld()).getHandle()).Spawn(location);
    }
}
