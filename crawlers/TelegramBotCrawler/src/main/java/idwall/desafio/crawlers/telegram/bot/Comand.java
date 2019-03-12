package idwall.desafio.crawlers.telegram.bot;

import com.pengrad.telegrambot.model.Update;

public class Comand {

	private long idChat;
	private String comand;
	private boolean hasParameter;
	private String subReddits;
	
	public Comand(Update update) {
		idChat = update.message().chat().id();
		String textMessage = update.message().text();
		
		if (textMessage != null) {
			String comandParts[] = textMessage.split(" ");
			comand = comandParts[0];
			
			if (comandParts.length > 1) {
				hasParameter = true;
				subReddits = comandParts[1];
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
		return comand != null 
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
	
	private boolean thisComandIs(String comand) {
		return this.comand.equals(comand);
	}
}
