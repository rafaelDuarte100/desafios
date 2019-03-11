package idwall.desafio.crawlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import idwall.desafio.modelo.ThreadReddit;

public class IdWallCrawler {

	protected static final String URL_BASE = "https://old.reddit.com/r/";
	protected static final String SULFIXO_URL = "/top/?sort=top";
	
	private List<ThreadReddit> threads;
	private int minUpVotes = 5000;
	private Document pagina;
	
	public IdWallCrawler() {
		threads = new ArrayList<>();
	}
	
	public IdWallCrawler(int minUpVotes) {
		threads = new ArrayList<>();
		this.minUpVotes = minUpVotes;
	}
	
	public List<ThreadReddit> getListaConteudo(String subreddits) {
		Arrays.stream(subreddits.split(";"))
			  .forEach(subreddit -> recuperarThreadsPorSubReddit(subreddit));
		return threads;
	}
	
	public void recuperarThreadsPorSubReddit(String subReddit) {
		lerPagina(URL_BASE + subReddit + SULFIXO_URL);
				
		while (subRedditPossuiThreadComMinimoDeUpVotes()) {
			for (Element thread : pagina.getElementById("siteTable").select("[id^=thing_t3]")) {
				if (getUpVoteDaThread(thread) >= minUpVotes) {
					threads.add(converterWebElementParaThreadReddit(thread));
				} else {
					return;
				}
			}
			nextPage();
		}
	}

	private void lerPagina(String url) {
		try {
			pagina = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new RuntimeException("Não foi possível se conectar com o reddit.");
		}
	}
	
	public void nextPage() {
		Element linkNext = pagina.getElementById("siteTable").selectFirst(".next-button > a");
		lerPagina(linkNext.attr("href"));
	}
	
	public boolean subRedditPossuiThreadComMinimoDeUpVotes() {
		Elements threads = pagina.getElementById("siteTable").select("[id^=thing_t3]");
		if (threads.size() > 0) {
			Element thread = threads.first();
			return getUpVoteDaThread(thread) >= minUpVotes;
		}
		return false;
	}
	
	public ThreadReddit converterWebElementParaThreadReddit(Element thread) {
		ThreadReddit threadReddit = new ThreadReddit();
		threadReddit.setSubReddit(getSubRedditDaThread(thread));
		threadReddit.setUpVotes(getUpVoteDaThread(thread));
		threadReddit.setTitulo(getTituloDaThread(thread));
		threadReddit.setLinkComentarios(getLinkComentariosDaThread(thread));
		threadReddit.setLinkThread(getLinkDaThread(thread));
		return threadReddit;
	}
	
	public String getSubRedditDaThread(Element thread) {
		return thread.attr("data-subreddit");
	}
	
	public int getUpVoteDaThread(Element thread) {
		String upVotes = thread.selectFirst(".midcol.unvoted > .score.unvoted").text();
		if (upVotes.replaceAll("[^0-9]","").length() > 0) {
			try {
				return Integer.parseInt(upVotes);
			} catch (Exception e) {
				return (int) (Double.parseDouble(upVotes.substring(0, upVotes.length() - 2)) * 1000);
			}
		}
		return 0;
	}
	
	public String getTituloDaThread(Element thread) {
		return thread.selectFirst(".entry.unvoted > .top-matter > .title > a").text();
	}
	
	public String getLinkComentariosDaThread(Element thread) {
		return thread.selectFirst("div.entry.unvoted > div.top-matter > ul > li.first > a").attr("href");
	}
	
	public String getLinkDaThread(Element thread) {
		return thread.selectFirst(".entry.unvoted > .top-matter > .title > a").attr("href");
	}
}
