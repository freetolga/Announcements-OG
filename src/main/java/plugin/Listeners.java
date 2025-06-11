package plugin;

import net.trueog.diamondbankog.DiamondBankException;
import net.trueog.utilitiesog.UtilitiesOG;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static net.trueog.diamondbankog.PostgreSQL.PlayerShards;
import static net.trueog.diamondbankog.PostgreSQL.ShardType;

public class Listeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        // Make sure to never run any other Bukkit functions in runTaskAsynchronously() (for example accessing players' inventories)
        // runTaskAsynchronously() is needed in this case since getPlayerShards().get() calls a database which can be slow to run on the main thread
        Bukkit.getScheduler().runTaskAsynchronously(TemplateOG.getPlugin(), () -> {
            CompletableFuture<PlayerShards> completablePlayerShards;
            try {
                completablePlayerShards = TemplateOG.diamondBankAPI().getPlayerShards(event.getPlayer().getUniqueId(), ShardType.ALL);
            } catch (DiamondBankException.EconomyDisabledException e) {
                UtilitiesOG.trueogMessage(event.getPlayer(), "<red>The economy is disabled.");
                return;
            } catch (DiamondBankException.TransactionsLockedException e) {
                UtilitiesOG.trueogMessage(event.getPlayer(), "<red>Your transactions are locked.");
                return;
            } catch (DiamondBankException.OtherException e) {
                UtilitiesOG.trueogMessage(event.getPlayer(), "<red>Something went wrong.");
                return;
            }

            try {
                PlayerShards playerShardsResult = completablePlayerShards.get();
                if (playerShardsResult.getShardsInBank() == null || playerShardsResult.getShardsInInventory() == null || playerShardsResult.getShardsInEnderChest() == null) {
                    UtilitiesOG.trueogMessage(event.getPlayer(), "<red>An error has occurred.");
                    return;
                }
                double totalBalance = playerShardsResult.getShardsInBank() + playerShardsResult.getShardsInInventory() + playerShardsResult.getShardsInEnderChest();

                // Send a message to the player with their balance.
                UtilitiesOG.trueogMessage(event.getPlayer(), "&BYour balance is: &e" + totalBalance + "&B Diamonds.");
                UtilitiesOG.logToConsole("[Template-OG]", "The player: " + event.getPlayer() + "'s <aqua>balance</aqua> is: " + totalBalance + "&B Diamonds");

            } catch (InterruptedException | ExecutionException error) {
                UtilitiesOG.logToConsole("[Template-OG]", "ERROR: The player: " + event.getPlayer() + "'s balance could not be fetched! ERROR: " + error.getMessage());
            }
        });
    }

}