package idall.desafio.crawlers.telegram.bot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import idwall.desafio.crawlers.IdWallCrawler;
import idwall.desafio.modelo.ThreadReddit;

public class BotTelegramSubReddits extends Thread {

	protected TelegramBot bot;
	protected boolean stop;
	protected int indiceMensagem;
	private IdWallCrawler idWallCrawler;
	
	public BotTelegramSubReddits() throws IOException {
		bot = new TelegramBot(getToken());
		idWallCrawler = new IdWallCrawler();
	}
	
	@Override
	public void run() {
		iniciar();		
	}
	
	public void iniciar() {
		System.out.println("Iniciando bot...");
		indiceMensagem = 0;
		
		while(!stop) {
			List<Update> updates = lerMensagens();
			updates.stream()
				   .forEach(update -> { indiceMensagem = update.updateId() + 1;
				   						tratarMensagem(update);});
		}
	}

	private List<Update> lerMensagens() {
		GetUpdatesResponse getUpdatesResponse;
		getUpdatesResponse =  bot.execute(new GetUpdates().limit(100).offset(indiceMensagem));
		List<Update> updates = getUpdatesResponse.updates();
		return updates;
	}
	
	private void tratarMensagem(Update update) {
		System.out.println("Mensagem recebida: " + update.message().text());
		String resposta = "Não Entendi...";
		String mensagem = update.message().text();
		
		if (mensagem.startsWith("/Start")) {
			resposta = getMensagemStart();
		}
		if (mensagem.startsWith("/Help")) {
			resposta = getMensagemAjuda();
		}
		if (mensagem.startsWith("/NadaPraFazer") && mensagem.split(" ").length > 1) {
			List<ThreadReddit> threads = buscarConteudos(mensagem);			
			enviarRespostaComOsConteudosEncontrados(update, threads);
		} else {
			enviarResposta(update, resposta);
		}
		
	}

	private void enviarRespostaComOsConteudosEncontrados(Update update, List<ThreadReddit> threads) {
		if (threads.isEmpty()) {
			enviarResposta(update, "Nenhum conteudo encontrado.");
		} else {
			threads.stream().forEach(thread -> enviarResposta(update, thread.toString()));
		}
	}

	private List<ThreadReddit> buscarConteudos(String mensagem) {
		String subReddits = mensagem.split(" ")[1].trim();
		
		List<ThreadReddit> threads = idWallCrawler.getListaConteudo(subReddits);
		return threads;
	}
	
	private String getMensagemAjuda() {
		return "Comando existente: /NadaPraFazer [+ Lista de subrredits] (ex.: /NadaPraFazer programming;dogs;brazil)";
	}
	
	private String getMensagemStart() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Este robô gera e imprimi uma lista contendo número de upvotes, subreddit,");
		sb.append(" título da thread, link para os comentários da thread, link da thread, ");
		sb.append(" para as threads do site https://old.reddit.com/ ");
		return sb.toString();
	}
	
	private void enviarResposta(Update update, String resposta) {
		if (!stop) {
			bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
			bot.execute(new SendMessage(update.message().chat().id(), resposta));
			System.out.println("Resposta enviada.");
		}
	}
	
	public void parar() {
		stop = true;
	}
	
	private String getToken() throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("token.properties");
		
		try {
			if (inputStream != null) {
				properties.load(inputStream);
				return properties.getProperty("token");
			} else {
				throw new FileNotFoundException("Arquivo \"token.properties\" não foi encontrado.");
			}
		} finally {
			inputStream.close();
		}
	}
}
