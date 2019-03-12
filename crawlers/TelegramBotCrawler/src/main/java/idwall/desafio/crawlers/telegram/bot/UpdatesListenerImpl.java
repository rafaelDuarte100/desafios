package idwall.desafio.crawlers.telegram.bot;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;

import idwall.desafio.crawlers.IdWallCrawler;
import idwall.desafio.modelo.ThreadReddit;

public class UpdatesListenerImpl implements UpdatesListener {

	private static final String DEFAUT_MESSAGE_START = "Este robô gera e imprimi uma lista contendo número de upvotes, subreddit, "
													 + "\ntítulo da thread, link para os comentários da thread, link da thread, "
													 + "\npara as threads do site https://old.reddit.com/ que possuam mais 5k upvotes.";
	
	private static final String DEFAUT_MESSAGE_HELP = "Comandos existentes:"
													+ "\n/Start -> Descreve a função deste bot."
													+ "\n/Help -> Descreve os comandos existentes."
													+ "\n/NadaPraFazer [+ Lista de subrredits] -> Retorna lista de conteudos, "
													+ "\npor exemplo /NadaPraFazer programming;dogs;brazil";
	private TelegramBot bot;
	private IdWallCrawler crawler;
	
	public UpdatesListenerImpl(TelegramBot bot) {
		this.bot = bot;
		crawler = new IdWallCrawler();
	}
	
	@Override
	public int process(List<Update> updates) {
		updates.stream()
			   .map(update -> new Comand(update))
			   .forEach(update -> response(update));
		return UpdatesListener.CONFIRMED_UPDATES_ALL;
	}
	
	public void response(Comand comand) {
		
		if (comand.isvalid()) {
			if (comand.isStartComand())
				sendMessage(comand, DEFAUT_MESSAGE_START);
			
			if (comand.isHelpComand())
				sendMessage(comand, DEFAUT_MESSAGE_HELP);
			
			if (comand.isNadaPraFazerComand())
				sendResultMessageOfCrawler(comand);
		}
		else {
			sendMessage(comand, "Não Entendi...");
		}
	}

	private void sendResultMessageOfCrawler(Comand comand) {
		List<ThreadReddit> threads = crawler.getListaConteudo(comand.getSubReddits());
		if (threads.isEmpty()) {
			sendMessage(comand, "Nenhum conteudo encontrado...");
		}
		else {
			threads.stream().forEach(thread -> sendMessage(comand, thread.toString()));
		}
	}
	
	public void sendMessage(Comand comand, String message) {
		bot.execute(new SendChatAction(comand.getIdChat(), ChatAction.typing.name()));
		bot.execute(new SendMessage(comand.getIdChat(), message));
	}
}
