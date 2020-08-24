package andreiwasfound.nightvisioncommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    private List<Player> NightVisionPlayers = new ArrayList<Player>();

    @Override
    public void onEnable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (label.equalsIgnoreCase("nv") || label.equalsIgnoreCase("nightvision")) {
            if (player.hasPermission("nightvisioncommand.use")) {
                if (getNightVisionPlayers().contains(player)) {
                    removeNightVisionPlayer(player);
                    player.sendMessage(ChatColor.RED + "You are no longer night visioned");
                    return true;
                }
                if (!getNightVisionPlayers().contains(player)) {
                    addNightVisionPlayer(player);
                    player.sendMessage(ChatColor.GREEN + "You are now night visioned");
                    return true;
                }
            }
        }
        return false;
    }

    public void addNightVisionPlayer(Player player) {
        PotionEffect nightvisionpotion = new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 100);
        player.addPotionEffect(nightvisionpotion);
        NightVisionPlayers.add(player);
    }

    public void removeNightVisionPlayer(Player player) {
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        NightVisionPlayers.remove(player);
    }

    public List<Player> getNightVisionPlayers() {
        return NightVisionPlayers;
    }

    public boolean hasNightVisionPlayers() {
        if (NightVisionPlayers.isEmpty())
            return false;
        return true;
    }
}
