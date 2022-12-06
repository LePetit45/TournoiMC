package fr.jacquelinedugame.tournoimc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLeaveTeam implements CommandExecutor {
	private Main main;
	private Player player;
	private ScoreboardTournoi SCTournoi;
	
	public CommandLeaveTeam(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) { //On v�rifie que c'est un player qui execute la commande
			player = (Player) sender;
		}
		else {
			sender.sendMessage("�6 Seul un joueur peut rejoindre une �quipe !");
			return false;
		}
		
		if (SCTournoi == null) {
			SCTournoi = main.getScoreboard();
		}
		
		if(!(main.getState().equals(State.WAITING))) { //On v�rifie si la partie � d�j� commenc�
			player.sendMessage("�6 La partie � d�j� commenc� ! Vous ne pouvez plus quitter votre �quipe !");
			return false;
		}
		
		
		if (SCTournoi.leavePlayerTeam(player)) { //On retire le joueur de l'�quipe si il est dans celle-ci
			player.sendMessage("�6 Vous avez �t� retir� de votre �quipe !");
			return true;
		}
		else {
			player.sendMessage("�6 Vous n'�tes dans aucune �quipe !");
			return false;
		}
	}

}
