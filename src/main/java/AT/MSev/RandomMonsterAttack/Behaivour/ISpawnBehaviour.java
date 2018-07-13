package AT.MSev.RandomMonsterAttack.Behaivour;


import AT.MSev.RandomMonsterAttack.Monster.IMonster;
import org.bukkit.Location;

public interface ISpawnBehaviour {
    void SetAmount(int amount);
    int GetAmount();

    void SetType(IMonster type);
    IMonster GetType();

    void SpawnAll(Location center);
}
