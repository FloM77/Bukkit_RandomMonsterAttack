package AT.MSev.RandomMonsterAttack.Commands;

import AT.MSev.RandomMonsterAttack.Zones.ZoneSafe;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandZoneSafe implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try
        {
            Player p = (Player)commandSender;
            double[] pars = new double[6];
            for(int i=0;i<pars.length;i++)
            {
                pars[i] = Double.parseDouble(strings[i]);
            }
            new ZoneSafe(new Location(p.getWorld(), pars[0], pars[1], pars[2]),
                    new Location(p.getWorld(), pars[3], pars[4], pars[5]));
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
