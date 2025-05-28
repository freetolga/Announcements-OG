package plugin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.trueog.diamondbankog.PostgreSQL;
import net.trueog.utilitiesog.UtilitiesOG;

public class Listeners implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event) {

		CompletableFuture<PostgreSQL.PlayerBalance> asyncPlayerBalance = TemplateOG.diamondBankAPI().getPlayerBalance(event.getPlayer().getUniqueId(), PostgreSQL.BalanceType.ALL);

		try {

			PostgreSQL.PlayerBalance balanceResult = asyncPlayerBalance.get();

			double totalBalance = balanceResult.getBankBalance() + balanceResult.getEnderChestBalance() + balanceResult.getInventoryBalance();

			// Send a message to the player with their balance.

			UtilitiesOG.trueogMessage(event.getPlayer(), "&BYour balance is: &e" + totalBalance + "&B Diamonds.");

			UtilitiesOG.logToConsole("[Template-OG]", "The player: " + event.getPlayer() + "'s <aqua>balance</aqua> is: " + totalBalance + "&B Diamonds");

		}
		catch (InterruptedException | ExecutionException error) {

			UtilitiesOG.logToConsole("[Template-OG]", "ERROR: The player: " + event.getPlayer() + "'s balance could not be fetched! ERROR: " + error.getMessage());

		}

	}

}