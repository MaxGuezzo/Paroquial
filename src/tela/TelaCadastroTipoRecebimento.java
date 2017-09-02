package tela;

import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import dao.DaoTipoRecebimento;
import pojo.TipoRecebimento;
import pojo.TipoRecebimento;

public class TelaCadastroTipoRecebimento extends TelaCadastro {
    public TipoRecebimento tipoRecebimento = new TipoRecebimento();
    public DaoTipoRecebimento daoTipoRecebimento = new DaoTipoRecebimento(tipoRecebimento);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");           
    
    public TelaCadastroTipoRecebimento() {
      super("Cadastro de Tipo de Recebimento");
      adicionarComponente(1, 1, 1, 1, campoCodigo, "Campo gerado automaticamente pelo sistema", true);
      adicionarComponente(3, 1, 1, 1, campoNome , null, false);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      tipoRecebimento.setIdtiporecebimento((int) campoCodigo.getValor());
      tipoRecebimento.setNome((String) campoNome.getValor());     
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(tipoRecebimento.getIdtiporecebimento());
      campoNome.setValor(tipoRecebimento.getNome());  
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoTipoRecebimento.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoTipoRecebimento.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoTipoRecebimento.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de TipoRecebimento",
               new String[] {"Código", "Nome"},
               DaoTipoRecebimento.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      tipoRecebimento.setIdtiporecebimento(pk);
      daoTipoRecebimento.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
    @Override
   public String pesquisa(String texto, int valor){
       String sql = daoTipoRecebimento.pesquisa(texto, valor);
       return(sql);
   }
}