package tela;

import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import dao.DaoCor;
import pojo.Cor;

public class TelaCadastroCor extends TelaCadastro {
    public Cor cor = new Cor();
    public DaoCor daoCor = new DaoCor(cor);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");           
    
    public TelaCadastroCor() {
      super("Cadastro de Cor");
      adicionarComponente(1, 1, 1, 1, campoCodigo, "Campo gerado automaticamente pelo sistema", true);
      adicionarComponente(3, 1, 1, 1, campoNome , null, false);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      cor.setIdcor((int) campoCodigo.getValor());
      cor.setNome((String) campoNome.getValor());     
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(cor.getIdcor());
      campoNome.setValor(cor.getNome());  
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoCor.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoCor.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoCor.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de Cor",
               new String[] {"Código", "Nome"},
               DaoCor.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      cor.setIdcor(pk);
      daoCor.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
    @Override
   public String pesquisa(String texto, int valor){
       String sql = daoCor.pesquisa(texto, valor);
       return(sql);
   }
}