package tela;

import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import dao.DaoTipoAgendamento;
import pojo.TipoAgendamento;

public class TelaCadastroTipoAgendamento extends TelaCadastro {
    public TipoAgendamento tipoagendamento = new TipoAgendamento();
    public DaoTipoAgendamento daoTipoAgendamento = new DaoTipoAgendamento(tipoagendamento);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");           
    
    public TelaCadastroTipoAgendamento() {
      super("Cadastro de TipoAgendamento");
      adicionarComponente(1, 1, 1, 1, campoCodigo, "Campo gerado automaticamente pelo sistema", true);
      adicionarComponente(3, 1, 1, 1, campoNome , null, false);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      tipoagendamento.setIdtipoagendamento((int) campoCodigo.getValor());
      tipoagendamento.setNome((String) campoNome.getValor());     
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(tipoagendamento.getIdtipoagendamento());
      campoNome.setValor(tipoagendamento.getNome());  
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoTipoAgendamento.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoTipoAgendamento.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoTipoAgendamento.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de TipoAgendamento",
               new String[] {"Código", "Nome"},
               DaoTipoAgendamento.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      tipoagendamento.setIdtipoagendamento(pk);
      daoTipoAgendamento.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
    @Override
   public String pesquisa(String texto, int valor){
       String sql = daoTipoAgendamento.pesquisa(texto, valor);
       return(sql);
   }
}