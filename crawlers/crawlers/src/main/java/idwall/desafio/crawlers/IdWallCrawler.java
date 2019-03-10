package idwall.desafio.crawlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import idwall.desafio.modelo.ThreadReddit;

public class IdWallCrawler extends AbstractWebDriverCrawler {
	
	protected static final String URL_BASE = "https://old.reddit.com/r/";
	protected static final String SULFIXO_URL = "/top/?sort=top";
	protected static final String XPATH_TABELA_THREADS = "//div[@id='siteTable']";
	protected static final String XPATH_THREADS = XPATH_TABELA_THREADS + "//div[contains(@id, 'thing_t3')]";
	
	private List<ThreadReddit> threads;
	private int minUpVotes = 5000;
	
	public IdWallCrawler() throws IOException {
		super();
		threads = new ArrayList<>();
	}

	@Override
	public List<ThreadReddit> getListaConteudo(String subreddits) {
		Arrays.stream(subreddits.split(";"))
			  .forEach(subreddit -> recuperarThreadsPorSubReddit(subreddit));
		closeDriver();
		return threads;
	}
	
	public void recuperarThreadsPorSubReddit(String subReddit) throws RuntimeException {
		get(URL_BASE + subReddit + SULFIXO_URL);
		
		while (subRedditPossuiThreadComMinimoDeUpVotes()) {
			for (WebElement thread : findElementsByXpath(XPATH_THREADS)) {
				if (getUpVotesDaThread(thread) >= minUpVotes) {
					threads.add(converterWebElementParaThreadReddit(thread));
				}
				else {
					return;
				}
			}
			nextPage();
		}
	}
	
	public void nextPage() throws RuntimeException {
		click(XPATH_TABELA_THREADS + "//a[text()='next ›']");
	}
	
	public boolean subRedditPossuiThreadComMinimoDeUpVotes() {
		try {
			WebElement primeiraThread = findByXpath(XPATH_THREADS);
			return getUpVotesDaThread(primeiraThread) >= minUpVotes;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public ThreadReddit converterWebElementParaThreadReddit(WebElement thread) {
		ThreadReddit threadReddit = new ThreadReddit();
		threadReddit.setSubReddit(getSubRedditDaThread(thread));
		threadReddit.setUpVotes(getUpVotesDaThread(thread));
		threadReddit.setTitulo(getTituloDaThread(thread));
		threadReddit.setLinkComentarios(getLinkComentariosDaThread(thread));
		threadReddit.setLinkThread(getLinkDaThread(thread));
		return threadReddit;
	}
	
	private String getSubRedditDaThread(WebElement thread) {
		return thread.getAttribute("data-subreddit");
	}
	
	private int getUpVotesDaThread(WebElement thread) {
		String upVotes = findByXpath(getSeletorUpVote(thread)).getText();
		
		if (upVotes.replaceAll("[^0-9]","").length() > 0) {
			try {
				return Integer.parseInt(upVotes);
			} catch (Exception e) {
				return (int) (Double.parseDouble(upVotes.substring(0, upVotes.length() - 2)) * 1000);
			}
		}
		return 0;		
	}
	
	private String getSeletorUpVote(WebElement thread) {
		return getSeletorThread(thread) + "//*[@class='score unvoted']";
	}
	
	private String getLinkComentariosDaThread(WebElement thread) {
		return findByXpath(getSeletorLinkComentarios(thread)).getAttribute("href");
	}
	
	private String getSeletorLinkComentarios(WebElement thread) {
		return getSeletorThread(thread) + "//a[@data-event-action='comments']";
	}
	
	private String getLinkDaThread(WebElement thread) {
		return findByXpath(getSeletorTitulo(thread)).getAttribute("href");
	}
	
	private String getTituloDaThread(WebElement thread) {
		return findByXpath(getSeletorTitulo(thread)).getText();
	}
	
	private String getSeletorTitulo(WebElement thread) {
		return getSeletorThread(thread) + "//div[@class='top-matter']/p[@class='title']/a";
	}
	
	private String getSeletorThread(WebElement thread) {
		return "//div[@id='" + thread.getAttribute("id") + "']";
	}
}
