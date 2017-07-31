package tela;

import componente.MeuCampoCpf;
import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import dao.DaoEstado;
import pojo.Estado;

public class TelaCadastroEstado extends TelaCadastro {
    public Estado estado = new Estado();
    public DaoEstado daoEstado = new DaoEstado(estado);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");
    public MeuCampoTexto campoSigla = new MeuCampoTexto(2, 2, true, "Sigla");           
    
    public TelaCadastroEstado() {
      super("Cadastro de Estado");
      adicionarComponente(1, 1, 1, 1, campoCodigo);
      adicionarComponente(3, 1, 1, 1, campoNome);
      adicionarComponente(3, 3, 1, 1, campoSigla);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      estado.setIdestado((int) campoCodigo.getValor());
      estado.setNome((String) campoNome.getValor());
      estado.setSigla((String) campoSigla.getValor());      
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(estado.getIdestado());
      campoNome.setValor(estado.getNome());
      campoSigla.setValor(estado.getSigla());   
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoEstado.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoEstado.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoEstado.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de Estado",
               new String[] {"Código", "Nome", "Sigla"},
               DaoEstado.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      estado.setIdestado(pk);
      daoEstado.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
    @Override
   public String pesquisa(String texto){
       String sql = daoEstado.pesquisa(texto);
       return(sql);
   }
}