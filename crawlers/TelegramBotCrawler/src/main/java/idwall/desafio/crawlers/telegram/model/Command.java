package idwall.desafio.crawlers.telegram.model;

import com.pengrad.telegrambot.model.Update;

public class Command {

	private long idChat;
	private String command;
	private boolean hasParameter;
	private String subReddits;
	
	public Command(Update update) {
		idChat = update.message().chat().id();
		String textMessage = update.message().text();
		
		if (textMessage != null) {
			String commandParts[] = textMessage.split(" ");
			command = commandParts[0];
			
			if (commandParts.length > 1) {
				hasParameter = true;
				subReddits = commandParts[1];
			}
		}
	}
	
	public long getIdChat() {
		return idChat;
	}
	
	public String getSubReddits() {
		return subReddits;
	}
	
	public boolean isvalid() {
		return command != null 
				&& (isStartComand()
					|| isHelpComand()
					|| (isNadaPraFazerComand() && hasParameter));
	}
	
	public boolean isHelpComand() {
		return thisComandIs("/Help");
	}
	
	public boolean isStartComand() {
		return thisComandIs("/Start");
	}
	
	public boolean isNadaPraFazerComand() {
		return thisComandIs("/NadaPraFazer");
	}
	
	private boolean thisComandIs(String command) {
		return this.command.equals(command);
	}
}
