package pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class PedidoCompra {
    private int idpedidocompra;
    private Date datapedido;
    private BigDecimal valor;
    private int idsituacaopedido;
    private int idfornecedor;
    private int idfuncionario;

    public int getIdpedidocompra() {
        return idpedidocompra;
    }

    public void setIdpedidocompra(int idpedidocompra) {
        this.idpedidocompra = idpedidocompra;
    }

    public Date getDatapedido() {
        return datapedido;
    }

    public void setDatapedido(Date datapedido) {
        this.datapedido = datapedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getIdsituacaopedido() {
        return idsituacaopedido;
    }

    public void setIdsituacaopedido(int idsituacaopedido) {
        this.idsituacaopedido = idsituacaopedido;
    }

    public int getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(int idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public int getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    
}
