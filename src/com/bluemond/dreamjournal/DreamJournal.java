package com.bluemond.dreamjournal;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;


public class DreamJournal extends JavaPlugin{
	BlueListener listener = new BlueListener();
	Logger log = this.getLogger();
	
	//start plugin
	public void onEnable(){
		getServer().getPluginManager().registerEvents(listener, this);
		log.info("DreamJournal v" + getDescription().getVersion() + " is ENABLED!");
		
		getCommand("jump").setExecutor(new Commands());
	}
	
	//stop plugin
	public void onDisable(){
		
	}
}
