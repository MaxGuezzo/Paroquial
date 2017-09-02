package tela;

import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import dao.DaoMissa;
import pojo.Missa;

public class TelaCadastroMissa extends TelaCadastro {
    public Missa missa = new Missa();
    public DaoMissa daoMissa = new DaoMissa(missa);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");          
    
    public TelaCadastroMissa() {
      super("Cadastro de Missa");
      adicionarComponente(1, 1, 1, 1, campoCodigo, "Campo gerado automaticamente pelo sistema", true);
      adicionarComponente(3, 1, 1, 1, campoNome , null, false);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      missa.setIdmissa((int) campoCodigo.getValor());
      missa.setNome((String) campoNome.getValor());     
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(missa.getIdmissa());
      campoNome.setValor(missa.getNome());  
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoMissa.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoMissa.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoMissa.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de Missa",
               new String[] {"Código", "Nome"},
               DaoMissa.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      missa.setIdmissa(pk);
      daoMissa.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
    @Override
   public String pesquisa(String texto, int valor){
       String sql = daoMissa.pesquisa(texto, valor);
       return(sql);
   }
}