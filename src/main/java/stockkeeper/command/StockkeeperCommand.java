/**
 *
 */
package stockkeeper.command;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import stockkeeper.gui.StockkeeperMenu;
import stockkeeper.service.RenderService;
import stockkeeper.service.RestService;

/**
 * @author Freya
 *
 */
public class StockkeeperCommand extends CommandBase {

	RenderService renderService;
	RestService restService;
	public StockkeeperCommand(RenderService render, RestService rest) {
		renderService = render;
		restService = rest;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#execute(net.minecraft.server.MinecraftServer, net.minecraft.command.ICommandSender, java.lang.String[])
	 */
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if(args.length == 0)
			handleHelp();
		else
		{
		if(args[0].equals("help"))
			handleHelp();
		else if(args[0].equals("invite"))
			handleInvite(args);
		else if(args[0].equals("add"))
			handleAdd(args);
		else if(args[0].equals("register"))
			handleRegister(args);
		else if(args[0].equals("count"))
			handleCount(args);
		else if(args[0].equals("find"))
			handleFind(args);
		else if(args[0].equals("groupinvite") || args[0].equals("gi"))
			handleGroupInvite(args);
		else if(args[0].equals("creategroup") || args[0].equals("cg"))
			handleCreateGroup(args);
		else if (args[0].equals("config"))
			handleConfigMenu();
		else
			commandNotFound();
//		if(args.length == 0)
//			handleHelp();
//		else
//		{
//			String command = args[0];
//			switch(command)
//			{
//			case "help" : handleHelp(); break;
//			case "invite" : handleInvite(args); break;
//			case "add" : handleAdd(args); break;
//			case "register" : handleRegister(args); break;
//			case "count" : handleCount(args); break;
//			case "find" : handleFind(args); break;
//			case "groupinvite" : handleGroupInvite(args); break;
//			case "creategroup" : handleCreateGroup(args); break;
//			case "config" : handleConfigMenu(); break;
//			case "waypoint" : handleWaypoint(args); break;
//			default: commandNotFound(); break;
//			}
		}

	}

	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#getCommandName()
	 */
	@Override
	public String getName() {
		return "stock";
	}
	/* (non-Javadoc)
	 * @see net.minecraft.command.ICommand#getCommandUsage(net.minecraft.command.ICommandSender)
	 */
	@Override
	public String getUsage(ICommandSender sender) {
		String newLine = System.getProperty("line.separator");
		String commandUsage = ""
				+ "Count: counts the amount of the specific item in all chests that the use has access to" + newLine
				+ " Usage: '/stock count [itemName]' Spaces in items are represented with '_', not case senstive" + newLine
				+ "Find: finds the closest chest containing the item"
				+ " Usage: '/stock find [itemName]' Spaces in items are represented with '_', not case senstive "
				+ ""
				+ ""
				+ ""
				+ "";
		return "TODO";
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 0;
	}

	private void commandNotFound() {
		Minecraft.getMinecraft().player.sendChatMessage("�4Invalid command: �f/stock help �6for more information");
		//handleHelp();

	}
	
private void handleAdd(String[] args) {
		

	}


private void handleHelp() {
	String newLine = "\n";//System.getProperty("line.separator");
	String commandUsage = "" +
			TextFormatting.RED + "�4Count: �6counts the amount of the specific item in all chests that the use has access to." + newLine
			+ " �6Usage: �f/stock count [itemName] �6Spaces in items are represented with '_', not case senstive" + newLine
			+ "�4Find: �6finds the closest chest containing the item." + newLine
			+ " �6Usage: �f/stock find [itemName] �6Spaces in items are represented with '_', not case senstive " + newLine
			+ "�4Invite: �6generates an invite code that allows another user to register an account on the server, code is copied to your clipboard" + newLine
			+ " �6Usage: �f/stock invite [level] �6 level 0 = can use the system, 1 = can invite other players 2= can create groups. You can invite one level below your current level." + newLine
			+ "�4Register: �6register an account using an invite code granted by a user" + newLine
			+ " �6Usage: �f/stock register [inviteCode] [password] �6 Password will be used to verify you in the future, make sure to add it to your config!" + newLine
			+ "�4Create Group: �6Creates a group for chests. Only members of this group can get information about chests added to this group" + newLine
			+ " �6Usage: �f/stock cg [groupName] �6Not case sensitive." + newLine
			+ "�4Group Invite: �6 invites another user to a group. You need at least group level 2" + newLine
			+ " �6Usage: �f/stock gi [groupName] [groupLevel [userName] �6Not case sensitive. 0 = Get data from chests 1=Add chests, 2=invite 3=admin" + newLine
			+ "�4Config: �6 Opens the config menu. Can also be opened with hotkey!" + newLine
			+ " �6Usage �f/stock config";
	Minecraft.getMinecraft().player.sendChatMessage(commandUsage);
	

}

private void handleRegister(String[] args) {
	String inviteCode = args[1];
	String password = args[2];
	//client.sendMessage(MessageFactory.createRegistrationMessage(inviteCode, password));

}

private void handleInvite(String[] args) {
	int level = Integer.parseInt(args[1]);
	//client.sendMessage(MessageFactory.createInviteMessage(level));
}

	
	private void handleConfigMenu() {

		Timer timer = new Timer();
		//FMLClientHandler.instance().displayGuiScreen(Minecraft.getMinecraft().thePlayer, new StockkeeperMenu());
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Minecraft.getMinecraft().displayGuiScreen(new StockkeeperMenu());
			}
		}, TimeUnit.SECONDS.toMillis(1));


	}

	private void handleCount(String[] args) {
		if(args.length > 1){}
			//client.sendMessage(MessageFactory.createCountMessage(args[1].replaceAll("_", " ").toLowerCase()));
		else
			Minecraft.getMinecraft().player.sendChatMessage("�4Invalid amount of arguments \n �6Usage: �f/stock count [itemName]");

	}
	private void handleCreateGroup(String[] args) {
		String groupname = args[1];
		//client.sendMessage(MessageFactory.createGroupMessage(groupname));
	}
	
	private void handleWaypoint(String[] args) {
		BlockPos position = Minecraft.getMinecraft().player.getPosition();
		renderService.AddWaypoint(position.getX(), position.getY(), position.getZ(), "test");		
	}

	private void handleFind(String[] args) {
		String itemName = args[1];
		if(itemName != null)
			restService.FindItem(itemName);

	}

	private void handleFindItem(String[] args)
	{
		if(args.length > 1)
		{
			String itemName = args[1];
			//client.sendMessage(MessageFactory.createFindItemMessage(itemName.replaceAll("_", " ").toLowerCase()));
		}
		else
		{
			Minecraft.getMinecraft().player.sendChatMessage("�4Invalid amount of arguments \n �6Usage: �f/stock find [itemName]");
		}
	}
	private void handleGroupInvite(String[] args) {
		String groupname = args[1];
		int grouplevel = Integer.parseInt(args[2]);
		String username = args[3];
		//client.sendMessage(MessageFactory.createGroupInviteMessage(groupname, grouplevel, username));

	}

}
