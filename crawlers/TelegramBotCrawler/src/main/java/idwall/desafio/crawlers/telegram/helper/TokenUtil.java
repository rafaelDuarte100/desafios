package idwall.desafio.crawlers.telegram.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TokenUtil {
	
	private static TokenUtil token;
	
	private TokenUtil() {}

	public static TokenUtil getInstance() {
		if (token == null)
			token = new TokenUtil();
		
		return token;
	}
	
	public String token() throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("token.properties");
		
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
