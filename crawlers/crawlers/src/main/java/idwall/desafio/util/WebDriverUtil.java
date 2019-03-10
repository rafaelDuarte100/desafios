package idwall.desafio.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverUtil {

	private static WebDriverUtil driver;
	
	private WebDriverUtil() {}
	
	public static WebDriverUtil getInstance() {
		if (driver == null)
			driver = new WebDriverUtil();
		
		return driver;
	}
	
	public WebDriver getPhantomJSDriver() throws IOException {
		System.setProperty("phantomjs.binary.path", getCaminhoDriver() + "phantomjs.exe");
		DesiredCapabilities dcap = new DesiredCapabilities();
		dcap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new  String[] {"--webdriver-loglevel=NONE"});
		Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);
		return new PhantomJSDriver(dcap);
	}
	
	public WebDriver getCrhomeDriver() throws IOException {
		System.setProperty("webdriver.chrome.driver", getCaminhoDriver() + "chromedriver.exe");
		return new ChromeDriver();
	}
	
	private static String getCaminhoDriver() throws IOException {
		String caminhoProjeto = getCaminhoProjeto();
		caminhoProjeto += "\\src\\main\\resources\\driver\\";
		return caminhoProjeto;
	}
	
	private static String getCaminhoProjeto() throws IOException {
		return new File(".").getCanonicalPath();
	}	
}
