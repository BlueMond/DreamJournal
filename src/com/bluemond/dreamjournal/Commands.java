package com.bluemond.dreamjournal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		if(sender instanceof Player){
			Player player = (Player)sender;
			Vector pVel = player.getVelocity();
			Vector dir = new Vector(0, 10, 0);
			player.setVelocity(operationVectors(pVel, dir, 2));
		}
		
		return true;
	}

	private Vector operationVectors(Vector vector1, Vector vector2, int oper) {
		double[] vec1 = {vector1.getX(), vector1.getY(), vector1.getZ()};
		double[] vec2 = {vector2.getX(), vector2.getY(), vector2.getZ()};
		double[] newVec = new double[3];
		
		
		for(int x=0; x<vec1.length; x++){
			switch (oper){
				case 0: newVec[x] = vec1[x] * vec2[x];
					break;
				case 1: newVec[x] = vec1[x] / vec2[x];
					break;
				case 2: newVec[x] = vec1[x] + vec2[x];
					break;
				case 3: newVec[x] = vec1[x] - vec2[x];
					break;
			}
		}
		
		return new Vector(newVec[0], newVec[1], newVec[2]);
	}

}
