package pojo;

import java.math.BigDecimal;

public class Produto {
    private int idproduto;
    private String nome;
    private BigDecimal valorunitario;
    private int quantidade;
    private int idcor;
    private int idtipoproduto;

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(BigDecimal valorunitario) {
        this.valorunitario = valorunitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdcor() {
        return idcor;
    }

    public void setIdcor(int idcor) {
        this.idcor = idcor;
    }

    public int getIdtipoproduto() {
        return idtipoproduto;
    }

    public void setIdtipoproduto(int idtipoproduto) {
        this.idtipoproduto = idtipoproduto;
    }
}
