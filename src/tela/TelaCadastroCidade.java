package tela;

import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.DaoCidade;
import dao.DaoEstado;
import pojo.Cidade;
import static tela.TelaPrincipal.jdp;

public class TelaCadastroCidade extends TelaCadastro {
    public Cidade cidade = new Cidade();
    public DaoCidade daoCidade = new DaoCidade(cidade);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");
    private MeuDBComboBox campoEstado = new MeuDBComboBox(this, true, DaoEstado.SQLCOMBOBOX, "Estado"); 

    
    public TelaCadastroCidade() {
      super("Cadastro de Cidade");
      adicionarComponente(1, 1, 1, 3, campoCodigo,null, false);
      adicionarComponente(3, 1, 1, 1, campoNome,null, false);
      adicionarComponente(3, 3, 1, 1, campoEstado,null, false);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      cidade.setIdcidade((int) campoCodigo.getValor());
      cidade.setNome((String) campoNome.getValor());
      cidade.setIdestado((int) campoEstado.getValor());      
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(cidade.getIdcidade());
      campoNome.setValor(cidade.getNome());
      campoEstado.setValor(cidade.getIdestado());   
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoCidade.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoCidade.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoCidade.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de Cidade",
               new String[] {"Código", "Nome", "Estado"},
               DaoCidade.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      cidade.setIdcidade(pk);
      daoCidade.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
   @Override
   public void chamarTela(){
       TelaCadastroEstado telaCadastroEstado = new TelaCadastroEstado();
       jdp.add(telaCadastroEstado);
       telaCadastroEstado.setLocation(50, 50);
       jdp.moveToFront(telaCadastroEstado);
   }
}