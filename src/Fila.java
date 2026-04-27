import java.util.NoSuchElementException;

public class Fila<E> {

	private Celula<E> frente;
	private Celula<E> tras;

	public Fila() {
		Celula<E> sentinela = new Celula<E>();
		frente = sentinela;
		tras = sentinela;
	}

	public boolean vazia() {
		return frente == tras;
	}

	public void enfileirar(E item) {
		tras.setProximo(new Celula<E>(item));
		tras = tras.getProximo();
	}

	public E desenfileirar() {
		if (vazia()) {
			throw new NoSuchElementException("Fila vazia!");
		}
		frente = frente.getProximo();
		return frente.getItem();
	}

	public E consultarPrimeiro() {
		if (vazia()) {
			throw new NoSuchElementException("Fila vazia!");
		}
		return frente.getProximo().getItem();
	}
}
