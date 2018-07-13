package AT.MSev.RandomMonsterAttack.Behaivour;

import AT.MSev.RandomMonsterAttack.Monster.IMonster;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SpawnBehaviourCircle implements ISpawnBehaviour {

    double Radius;
    public SpawnBehaviourCircle(double radius)
    {
        Radius = radius;
    }

    int Amount;
    public void SetAmount(int amount) {
        Amount = amount;
    }

    public int GetAmount() {
        return Amount;
    }

    IMonster Monster;
    public void SetType(IMonster type) {
        Monster = type;
    }

    public IMonster GetType() {
        return Monster;
    }

    public void SpawnAll(Location center) {
        for (int i = 0; i<Amount; i++)
        {
            double mult = ((double)Amount/i);
            double xoffset = Math.sin(Math.toRadians((360/mult))) * Radius;
            double zoffset = Math.cos(Math.toRadians((360/mult))) * Radius;
            Location SL = new Location(center.getWorld(), center.getX() + xoffset, 1, center.getZ() + zoffset);
            SL.setY(center.getWorld().getHighestBlockYAt(SL));
            Monster.SpawnIn(SL);
        }
    }
}
