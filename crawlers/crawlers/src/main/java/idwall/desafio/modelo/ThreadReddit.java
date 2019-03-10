package idwall.desafio.modelo;

public class ThreadReddit {

	private int upVotes;
	private String subReddit;
	private String titulo;
	private String linkComentarios;
	private String linkThread;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Número de Upvotes: ").append(getUpVotes()).append("\n");
		sb.append(" Subreddit: ").append(getSubReddit()).append("\n");
		sb.append(" Título: ").append(getTitulo()).append("\n");
		sb.append(" Link para os Comentários: ").append(getLinkComentarios()).append("\n");
		sb.append(" Link da Thread: ").append(getLinkComentarios()).append("\n");
		sb.append("---------------------------------------------------------------------------------").append("\n");
		return sb.toString();
	}

	public int getUpVotes() {
		return upVotes;
	}
	
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}

	public String getSubReddit() {
		return subReddit;
	}

	public void setSubReddit(String subReddit) {
		this.subReddit = subReddit;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getLinkComentarios() {
		return linkComentarios;
	}

	public void setLinkComentarios(String linkComentarios) {
		this.linkComentarios = linkComentarios;
	}

	public String getLinkThread() {
		return linkThread;
	}

	public void setLinkThread(String linkThread) {
		this.linkThread = linkThread;
	}
}
