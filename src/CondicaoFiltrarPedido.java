import java.util.function.Predicate;

public class CondicaoFiltrarPedido implements Predicate<Pedido> {

    private String descricaoProduto;

    public CondicaoFiltrarPedido(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    @Override
    public boolean test(Pedido pedido) {
        ItemDePedido itemProcurado = new ItemDePedido(
            new ProdutoNaoPerecivel(descricaoProduto, 1.0, 0.1), 0, 0.1
        );
        CriterioDeBuscaPorDescricao criterio = new CriterioDeBuscaPorDescricao();
        return pedido.getItensDoPedido().buscarPor(criterio, itemProcurado) != null;
    }
}
