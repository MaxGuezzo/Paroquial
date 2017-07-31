package tela;

import com.sun.glass.ui.Cursor;
import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.DaoCidade;
import dao.DaoEstado;
import pojo.Estado;
import static tela.TelaPrincipal.jdp;

public class TelaCadastroCidade extends TelaCadastro {
    public Estado estado = new Estado();
    public DaoEstado daoEstado = new DaoEstado(estado);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");
    private MeuDBComboBox campoEstado = new MeuDBComboBox(true, DaoEstado.SQLCOMBOBOX, "Estado"); 

    
    public TelaCadastroCidade() {
      super("Cadastro de Cidade");
      adicionarComponente(1, 1, 1, 3, campoCodigo);
      adicionarComponente(3, 1, 1, 1, campoNome);
      adicionarComponente(3, 3, 1, 1, campoEstado);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      estado.setIdestado((int) campoCodigo.getValor());
      estado.setNome((String) campoNome.getValor());
      estado.setSigla((String) campoEstado.getValor());      
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(estado.getIdestado());
      campoNome.setValor(estado.getNome());
      campoEstado.setValor(estado.getSigla());   
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
               "Consulta de Cidade",
               new String[] {"Código", "Nome", "Estado"},
               DaoCidade.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      estado.setIdestado(pk);
      daoEstado.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
   public static void chamarTela(){
       TelaCadastroEstado telaCadastroEstado = new TelaCadastroEstado();
       jdp.add(telaCadastroEstado);
       telaCadastroEstado.setLocation(50, 50);
       jdp.moveToFront(telaCadastroEstado);
   }
}