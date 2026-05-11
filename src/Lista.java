import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<E> implements Iterable<E> {

    private Celula<E> cabeca;
    private Celula<E> cauda;
    private int tamanho;

    public Lista() {
        Celula<E> sentinela = new Celula<>();
        cabeca = cauda = sentinela;
        tamanho = 0;
    }

    public boolean vazia() {
        return cabeca == cauda;
    }

    public int tamanho() {
        return tamanho;
    }

    public void inserirFinal(E item) {
        Celula<E> nova = new Celula<>(item);
        cauda.setProximo(nova);
        cauda = nova;
        tamanho++;
    }

    public void inserirInicio(E item) {
        Celula<E> nova = new Celula<>(item, cabeca.getProximo());
        if (vazia())
            cauda = nova;
        cabeca.setProximo(nova);
        tamanho++;
    }

    public E removerInicio() {
        if (vazia())
            throw new NoSuchElementException("Lista vazia!");
        Celula<E> primeiro = cabeca.getProximo();
        cabeca.setProximo(primeiro.getProximo());
        if (primeiro == cauda)
            cauda = cabeca;
        primeiro.setProximo(null);
        tamanho--;
        return primeiro.getItem();
    }

    public void imprimir() {
        if (vazia()) {
            System.out.println("A lista está vazia!");
        } else {
            Celula<E> aux = cabeca.getProximo();
            while (aux != null) {
                System.out.println(aux.getItem());
                aux = aux.getProximo();
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Celula<E> atual = cabeca.getProximo();

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                E item = atual.getItem();
                atual = atual.getProximo();
                return item;
            }
        };
    }

    public E buscarPor(Comparator<E> criterioDeBusca, E item) {
        Celula<E> aux = cabeca.getProximo();
        while (aux != null) {
            if (criterioDeBusca.compare(aux.getItem(), item) == 0) {
                return aux.getItem();
            }
            aux = aux.getProximo();
        }
        return null;
    }

    public double somarMultiplicacoes(Function<E, Double> extratorValor, Function<E, Integer> extratorFator) {
        if (vazia())
            throw new IllegalStateException("A lista está vazia!");

        double somatorio = 0;
        Celula<E> aux = cabeca.getProximo();
        while (aux != null) {
            double valor = extratorValor.apply(aux.getItem());
            int fator = extratorFator.apply(aux.getItem());
            somatorio += valor * fator;
            aux = aux.getProximo();
        }
        return somatorio;
    }

    public Lista<E> filtrar(Predicate<E> condicional) {
        if (vazia())
            throw new IllegalStateException("A lista está vazia!");

        Lista<E> novaLista = new Lista<>();
        Celula<E> aux = cabeca.getProximo();
        while (aux != null) {
            if (condicional.test(aux.getItem())) {
                novaLista.inserirFinal(aux.getItem());
            }
            aux = aux.getProximo();
        }
        return novaLista;
    }
}
