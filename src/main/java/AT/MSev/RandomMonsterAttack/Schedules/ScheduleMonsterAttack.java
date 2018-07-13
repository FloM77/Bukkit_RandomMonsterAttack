package AT.MSev.RandomMonsterAttack.Schedules;

import AT.MSev.RandomMonsterAttack.Behaivour.ISpawnBehaviour;
import AT.MSev.RandomMonsterAttack.RandomMonsterAttack;
import org.bukkit.entity.Player;

import java.util.Random;

public class ScheduleMonsterAttack implements Runnable {

    ISpawnBehaviour Behaviour;
    Player Target;
    double Chance;

    public ScheduleMonsterAttack(double chance, ISpawnBehaviour behaviour, Player target)
    {
        Chance = chance;
        Behaviour = behaviour;
        Target = target;
    }

    Random random = new Random();
    public void run() {
        if(!RandomMonsterAttack.Safe.contains(Target.getUniqueId())) {
            if (random.nextDouble() < Chance) {
                Target.sendMessage("You are being ambushed!");
                Behaviour.SpawnAll(Target.getLocation());
            }
        }
    }
}
