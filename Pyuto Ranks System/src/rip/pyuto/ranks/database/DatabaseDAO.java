package rip.pyuto.ranks.database;

import java.util.concurrent.CompletableFuture;

import org.bukkit.entity.Player;

import rip.pyuto.ranks.user.RankUser;

public interface DatabaseDAO {

    CompletableFuture<RankUser> getUser(Player player);
    void saveUser(RankUser user);

}