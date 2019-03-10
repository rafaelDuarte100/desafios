package idwall.desafio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import idwall.desafio.string.IdwallFormatter;
import idwall.desafio.string.StringFormatter;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class Main {

    private static final String DEFAULT_INPUT_TEXT = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" +
            "\n" +
            "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
    
    private static final Integer DEFAULT_LIMIT = 40;
    private static final Boolean DEFAULT_JUSTIFY = true;
    
    public static void main(String[] args) {
        String text = DEFAULT_INPUT_TEXT;
        Integer limit = DEFAULT_LIMIT;
        Boolean justify = DEFAULT_JUSTIFY;
        
        int opcao = -1;
        
        imprimeMenu();    	
    	Scanner scan = new Scanner(System.in);
    	Scanner scanText = new Scanner(System.in);
    	
    	try {
    		try {
    			opcao = scan.nextInt();
			} catch (Exception e) {
				System.out.println("OPÇÃO ÍNVÁLIDA!");
			}
        	
    		if (opcao == 1 || opcao == 2) {
    			if (opcao == 2) {
            		String caminhoArquivo = lerCaminhoArquivo(scanText);
            		text = lerArquivo(text, caminhoArquivo);
            	}
    			limit = lerTamanhoLimite(scan);
	    		justify = lerSeTextoDeveSerJustificado(scan);
	    		formata(text, limit, justify);
    		}
        	
		} finally {
			scan.close();
			scanText.close();
		}    
    }

	private static void imprimeMenu() {
		System.out.println("+---------------------------------------------------+");
    	System.out.println("|                    STRING FORMATTER               |");
    	System.out.println("| DIGITE:                                           |");
    	System.out.println("| 1 - USAR O TEXO PADRÃO.                           |");
    	System.out.println("| 2 - IMPORTAR UM ARQUIVO TXT.                      |");
    	System.out.println("|                                                   |");
    	System.out.println("+---------------------------------------------------+");
	}
    
    public static void formata(String text, int limit, boolean justify) {
    	// Print input data
        System.out.println("Inputs: ");
        System.out.println("=========================");
        System.out.println("Text: " + text);
        System.out.println("=========================");
        System.out.println("Limit: " + limit);
        System.out.println("Should justify: " + justify);
        System.out.println("=========================");

        // Run IdwallFormatter
        final StringFormatter sf = new IdwallFormatter(limit, justify);
        String outputText = sf.format(text);

        // Print output text
        System.out.println("Output: ");
        System.out.println(outputText);
        System.out.println("=========================");
    }

	private static String lerArquivo(String text, String caminhoArquivo) {
		Path caminho = Paths.get(caminhoArquivo);
		Stream<String> linhas = null;
		try {
			linhas = Files.lines(caminho);
			text = linhas.collect(Collectors.joining("\n"));
		} catch (IOException e) {
			System.out.println("ARQUIVO NÃO ENCONTRADO!");
		} finally {
			if (linhas != null) linhas.close();
		}
		return text;
	}

	private static String lerCaminhoArquivo(Scanner scanText) {
		System.out.println("QUAL O CAMINHO COMPLETO DO ARQUIVO?");
		String caminhoArquivo = scanText.nextLine();
		return caminhoArquivo;
	}

	private static Boolean lerSeTextoDeveSerJustificado(Scanner scan) {
		Boolean justify;
		System.out.println("O TEXTO DEVE SER JUSTIFICADO? (true, false)");
		justify = scan.nextBoolean();
		return justify;
	}

	private static Integer lerTamanhoLimite(Scanner scan) {
		Integer limit;
		System.out.println("QUAL O TAMANHO LIMITE?");
		limit = scan.nextInt();
		return limit;
	}
}