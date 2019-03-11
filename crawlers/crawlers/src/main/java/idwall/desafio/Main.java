package idwall.desafio;

import java.util.List;
import java.util.Scanner;

import idwall.desafio.crawlers.IdWallCrawler;
import idwall.desafio.modelo.ThreadReddit;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite a lista com nomes de sibreddits separados por (';'). Ex: \"askreddit;worldnews;cats\"");
		String subReddits = scan.nextLine();
		System.out.println("Buscando...");
		scan.close();
		
		IdWallCrawler webCrawler = new IdWallCrawler();
		List<ThreadReddit> conteudos = webCrawler.getListaConteudo(subReddits);
		imprimirResultado(conteudos);
		
	}

	private static void imprimirResultado(List<ThreadReddit> conteudos) {
		if (conteudos.isEmpty()) {
			System.out.println("Nenhum conteúdo Encontrado.");
		} else {
			System.out.println("\n\n Resultado da Busca:");
			System.out.println("------------------------------------------------------------");
			conteudos.stream().forEach(thread -> System.out.println(thread));
			System.out.println("Quantidade de conteudos encontrados: " + conteudos.size());
		}
	}
}
