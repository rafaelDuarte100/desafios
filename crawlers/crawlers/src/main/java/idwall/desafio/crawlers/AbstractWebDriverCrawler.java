package idwall.desafio.crawlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import idwall.desafio.modelo.ThreadReddit;
import idwall.desafio.util.WebDriverUtil;

/*
 * Criado por Rafael Duarte
 */
public abstract class AbstractWebDriverCrawler {

	private WebDriver driver;
	private long tempoMaximoEspera = 5000;
	
	protected AbstractWebDriverCrawler() throws IOException {
		driver = WebDriverUtil.getInstance().getPhantomJSDriver();
	}
	
	public abstract List<ThreadReddit> getListaConteudo(String subreddits);
	
	protected void get(String url) {
		driver.get(url);
	}
	
	protected void closeDriver() {
		driver.close();
	}
	
	protected void click(String param) throws RuntimeException {
		waitForElementPresent(param);
		WebElement webElement = findByXpath(param);
		webElement.click();
	}
	
	protected String getPageSource() {
		return driver.getPageSource();
	}
	
	protected void waitForElementPresent(String param) throws RuntimeException {
		long tempoDecorrido = 0;
		while(!isElementPresent(param)) {
			
			if (tempoDecorrido > tempoMaximoEspera)
				throw new RuntimeException("Tempo máximo de espera atingido.");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			tempoDecorrido += 100;
		}
	}
	
	protected boolean isElementPresent(String param) {
		return findByXpath(param) != null;
	}
	
	protected WebElement find(String param) {
		try {
			return findById(param);
		} catch (NoSuchElementException e) {
			try {
				return findByXpath(param);
			} catch (NoSuchElementException e2) {
				return null;
			}
		}
	}
	
	protected List<WebElement> findElementsByXpath(String param) {
		try {
			return driver.findElements(By.xpath(param));
		} catch (NoSuchElementException e) {
			return new ArrayList<>();
		}
	}
	
	protected WebElement findById(String id) {
		return driver.findElement(By.id(id));
	}
	
	protected WebElement findByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
}
