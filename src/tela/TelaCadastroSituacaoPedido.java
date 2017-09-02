package tela;

import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import dao.DaoSituacaoPedido;
import pojo.SituacaoPedido;

public class TelaCadastroSituacaoPedido extends TelaCadastro {
    public SituacaoPedido situacaopedido = new SituacaoPedido();
    public DaoSituacaoPedido daoSituacaoPedido = new DaoSituacaoPedido(situacaopedido);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");           
    
    public TelaCadastroSituacaoPedido() {
      super("Cadastro de Situacao Pedido");
      adicionarComponente(1, 1, 1, 1, campoCodigo, "Campo gerado automaticamente pelo sistema", true);
      adicionarComponente(3, 1, 1, 1, campoNome , null, false);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      situacaopedido.setIdsituacaopedido((int) campoCodigo.getValor());
      situacaopedido.setNome((String) campoNome.getValor());     
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(situacaopedido.getIdsituacaopedido());
      campoNome.setValor(situacaopedido.getNome());  
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoSituacaoPedido.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoSituacaoPedido.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoSituacaoPedido.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de SituacaoPedido",
               new String[] {"Código", "Nome"},
               DaoSituacaoPedido.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      situacaopedido.setIdsituacaopedido(pk);
      daoSituacaoPedido.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
    @Override
   public String pesquisa(String texto, int valor){
       String sql = daoSituacaoPedido.pesquisa(texto, valor);
       return(sql);
   }
}