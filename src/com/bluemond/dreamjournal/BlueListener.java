package com.bluemond.dreamjournal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.util.Vector;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_9_R1.EntityHuman;
import net.minecraft.server.v1_9_R1.EnumHand;
import net.minecraft.server.v1_9_R1.EnumMainHand;
import net.minecraft.server.v1_9_R1.Packet;
import net.minecraft.server.v1_9_R1.PacketDataSerializer;
import net.minecraft.server.v1_9_R1.PacketPlayOutCustomPayload;

public class BlueListener implements Listener{

	//old player join
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		//give player book gui for their dream journal
		System.out.println("firing");
		Player player = event.getPlayer();
		loadBookGui(player, createBook(player.getDisplayName()));
		
		player.setAllowFlight(true);
	}
	
	@EventHandler
	public void onchange(PlayerGameModeChangeEvent event){
		event.getPlayer().setAllowFlight(true);
	}
	
	public ItemStack createBook(String name){
		List<String> pages = new ArrayList<String>(Arrays.asList("This is your book bitch\nEnjoy it!","OH! and I hate you."));
		
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		
		BookMeta bm = (BookMeta)item.getItemMeta();
		bm.setTitle("DreamJournal");
		bm.setAuthor(name);
		bm.setPages(pages);
		
		item.setItemMeta(bm);
		return item;
	}
	
	public void loadBookGui(Player p, ItemStack b){
		//switch iteminhand to the book and store old item
		PlayerInventory inv = p.getInventory();
		int held = inv.getHeldItemSlot();
		
		ItemStack old = inv.getItem(held);
		inv.setItem(held, b);
		
		
		System.out.println(p.getInventory().getItem(held).getType().name());
	
		//force client to open

		PacketDataSerializer packetdataserializer = new PacketDataSerializer(Unpooled.buffer());
		packetdataserializer.a(EnumHand.MAIN_HAND);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutCustomPayload("MC|BOpen", packetdataserializer));
		
		
		//return item to hand
		inv.setItem(held, old);
	}
}
	
