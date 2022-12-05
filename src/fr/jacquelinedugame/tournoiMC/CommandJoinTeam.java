package fr.jacquelinedugame.tournoindi2022;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandJoinTeam implements CommandExecutor {
	private Main main;
	private Player player;
	private ScoreboardTournoi SCTournoi;
	
	public CommandJoinTeam(Main main) {
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
		
		if(SCTournoi.isInTeam(player)) {
			player.sendMessage("�6 Vous �tes d�j� dans une �quipe ! Faites /leaveteam pour la quitter");
			return false;
		}
		
		if (args.length <= 0) { //On v�rifie qu'un nom d'�quipe � �t� donn�e
			player.sendMessage("�6 Veuillez entrer un nom d'�quipe ! ");
			return false;
		}
		
		if (SCTournoi.getTeam(args[0]) == null) { //On v�rifie que la team existe
			player.sendMessage("�6 L'�quipe �4 " + args[0] + " �6 n'existe pas ! Utilisez /createteam <nom de team> pour la cr�er");
			return false;
		}
		
		if (SCTournoi.addPlayerToTeam(player, args[0])){ //On ajoute le joueur � la team et on l'informe si il est ajout� ou non
			player.sendMessage("�6 Vous avez bien �t� ajout� � l'�quipe �4 " + args[0]);
			return true;
		}
		else {
			player.sendMessage("�6 Vous �tes d�j� dans une �quipe ou le nombre maximum de membres dans l'�quipe �4 "+args[0]+" �6 � d�j� �t� atteint !");
			return false;
		}
		
	}

}
