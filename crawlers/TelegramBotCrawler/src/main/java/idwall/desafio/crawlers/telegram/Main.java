package idwall.desafio.crawlers.telegram;

import java.io.IOException;

import com.pengrad.telegrambot.TelegramBot;

import idwall.desafio.crawlers.telegram.listener.UpdatesListenerImpl;
import idwall.desafio.crawlers.telegram.util.TokenUtil;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Iniciando o bot...");
		TelegramBot bot = new TelegramBot(TokenUtil.getInstance().token());
		bot.setUpdatesListener(new UpdatesListenerImpl(bot));
		System.out.println("Bot iniciado...");
	}
}
