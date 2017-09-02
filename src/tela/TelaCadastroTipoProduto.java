package tela;

import componente.MeuCampoCpf;
import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import dao.DaoTipoProduto;
import pojo.TipoProduto;

public class TelaCadastroTipoProduto extends TelaCadastro {
    public TipoProduto tipoproduto = new TipoProduto();
    public DaoTipoProduto daoTipoProduto = new DaoTipoProduto(tipoproduto);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");

    public TelaCadastroTipoProduto() {
      super("Cadastro de TipoProduto");
      adicionarComponente(1, 1, 1, 1, campoCodigo, "Campo gerado automaticamente pelo sistema", true);
      adicionarComponente(3, 1, 1, 1, campoNome , null, false);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      tipoproduto.setIdtipoproduto((int) campoCodigo.getValor());
      tipoproduto.setNome((String) campoNome.getValor());      
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(tipoproduto.getIdtipoproduto());
      campoNome.setValor(tipoproduto.getNome());   
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoTipoProduto.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoTipoProduto.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoTipoProduto.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de TipoProduto",
               new String[] {"Código", "Nome"},
               DaoTipoProduto.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      tipoproduto.setIdtipoproduto(pk);
      daoTipoProduto.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
    @Override
   public String pesquisa(String texto, int valor){
       String sql = daoTipoProduto.pesquisa(texto, valor);
       return(sql);
   }
}