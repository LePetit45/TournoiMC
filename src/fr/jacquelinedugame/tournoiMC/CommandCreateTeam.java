package fr.jacquelinedugame.tournoindi2022;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreateTeam implements CommandExecutor {
	private Main main;
	private Player player;
	private ScoreboardTournoi SCTournoi;
	
	public CommandCreateTeam(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		else {
			sender.sendMessage("�6 Seul un joueur peut cr�er une �quipe !");
			return false;
		}
		
		if (SCTournoi == null) {
			SCTournoi = main.getScoreboard();
		}
		
		if (args.length <= 0) { //On v�rifie qu'un nom d'�quipe � �t� donn�e
			player.sendMessage("�6 Veuillez entrer un nom d'�quipe ! ");
			return false;
		}
		
		if (args.length > 1) { //On v�rifie qu'un nom d'�quipe � �t� donn�e
			player.sendMessage("�6 Veuillez entrer un nom d'�quipe sans espaces ! ");
			return false;
		}
		
		if(args[0].length() > 16) {
			player.sendMessage("�6 Le nom de l'�quipe est trop long ! (max 16 caract�res)");
			return false;
		}
		
		if(SCTournoi.isInTeam(player)) { //On v�rifie si le joueur est daj� dans une �quipe
			player.sendMessage("�6 Vous �tes d�j� dans une �quipe ! Faites /leaveteam pour la quitter");
			return false;
		}
		
		try {
			SCTournoi.addTeam(args[0]);
			SCTournoi.addPlayerToTeam(player, args[0]);
			player.sendMessage("�6 Votre �quipe �4 " + args[0] + " �6 � bien �t� cr�e et vous y avez �t� ajout� !");
			return true;
		} catch (Exception e) {
			player.sendMessage("�6 Une erreur s'est produite pendant la cr�ation de l'�quipe. Une �quipe doit d�j� s'appeler par le nom donn�e.");
			e.printStackTrace();
			return false;
		}
		
		
	}

}
