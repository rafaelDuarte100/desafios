package idwall.desafio.fonteTexto;

import java.io.IOException;

public class FonteTextoEstatico implements FonteTexto {

	@Override
	public String getTexto() throws IOException {
		return "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" +
	            "\n" +
	            "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
	}

}
