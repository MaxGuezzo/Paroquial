package pojo;

import java.math.BigDecimal;

public class ItensPedido {
    private int iditenspedido;
    private int quantidade;
    private BigDecimal valorunitario;
    private int idproduto;

    public int getIditenspedido() {
        return iditenspedido;
    }

    public void setIditenspedido(int iditenspedido) {
        this.iditenspedido = iditenspedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(BigDecimal valorunitario) {
        this.valorunitario = valorunitario;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    
}
