package idall.desafio.crawlers.telegram;

import java.io.IOException;

import idall.desafio.crawlers.telegram.bot.BotTelegramSubReddits;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		BotTelegramSubReddits botTelegramSubReddits = new BotTelegramSubReddits();
		botTelegramSubReddits.start();
	}
}
