import java.util.HashSet;
import java.util.Set;

public class Tarefa1 {
    public static void main(String[] args) {
        Pilha<Integer> pilha = new Pilha<>();

        // Matrícula informada: 8, 8, 5, 5, 0, 9
        int[] matriculaInput = {8, 8, 5, 5, 0, 9};
        
        System.out.println("Processando matrícula: 8, 8, 5, 5, 0, 9");
        System.out.println("Dígitos sem repetição (na ordem de aparecimento):");
        
        // Usando um Set para controlar duplicatas mas mantendo a ordem manual
        Set<Integer> vistos = new HashSet<>();
        
        for (int digito : matriculaInput) {
            if (!vistos.contains(digito)) {
                System.out.print(digito + " ");
                pilha.empilhar(digito);
                vistos.add(digito);
            }
        }
        System.out.println();

        System.out.println("\nTestando subPilha(2):");
        try {
            Pilha<Integer> sub = pilha.subPilha(2);
            System.out.println("Topo da subPilha (deve ser o último inserido, no caso 9): " + sub.consultarTopo());
        } catch (Exception e) {
            System.out.println("Erro no subPilha: " + e.getMessage());
        }

        System.out.println("\nDesempilhando toda a pilha original (Ordem LIFO):");
        while (!pilha.vazia()) {
            System.out.println("Desempilhando: " + pilha.desempilhar());
        }

        // Teste de pilha vazia
        try {
            pilha.consultarTopo();
        } catch (Exception e) {
            System.out.println("\nTeste de pilha vazia (consultarTopo): " + e.getMessage());
        }
    }
}
