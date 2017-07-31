package tela;

import componente.MeuCampoCheckBox;
import componente.MeuCampoCpf;
import componente.MeuCampoData;
import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.DaoCidade;
import dao.DaoColaborador;
import java.sql.Date;
import pojo.Colaborador;

public class TelaCadastroColaborador extends TelaCadastro {
    public Colaborador colaborador = new Colaborador();
    public DaoColaborador daoColaborador = new DaoColaborador(colaborador);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(35, 50, true, "Nome");
//    public MeuCampoData campoDatanascimento = new MeuCampoData(10, true, true, "Data de Nasc");           
    public MeuCampoTexto campoTelefone = new MeuCampoTexto(14, 14, true, "Telefone");           
    public MeuCampoTexto campoCelular = new MeuCampoTexto(14, 14, true, "Celular");           
    public MeuCampoTexto campoEndereco = new MeuCampoTexto(22, 20, true, "Endereco");           
    public MeuCampoTexto campoNumcasa = new MeuCampoTexto(10, 4, true, "Numero");           
    public MeuCampoTexto campoBairro = new MeuCampoTexto(19, 50, true, "Bairro");           
    public MeuCampoTexto campoRg = new MeuCampoTexto(10, 12, true, "RG");           
    public MeuCampoCpf campoCpf = new MeuCampoCpf(10, true, true, "CPF");           
    public MeuCampoCheckBox campoSituacao = new MeuCampoCheckBox(true, true, "Ativo");
    private MeuDBComboBox campoCidade = new MeuDBComboBox(true, DaoCidade.SQLCOMBOBOX, "Cidade"); 
    
    
    public TelaCadastroColaborador() {
      super("Cadastro de Colaborador");
      adicionarComponente(1, 1, 1, 1, campoCodigo);
      adicionarComponente(1, 2, 1, 5, campoNome);
//      adicionarComponente(1, 6, 1, 1, campoDatanascimento);
      adicionarComponente(3, 1, 1, 3, campoTelefone);
      adicionarComponente(3, 4, 1, 1, campoCelular);
      adicionarComponente(3, 5, 1, 3, campoEndereco);
      adicionarComponente(5, 1, 1, 2, campoNumcasa);
      adicionarComponente(5, 3, 1, 2, campoBairro);
      adicionarComponente(5, 5, 1, 1, campoRg);
      adicionarComponente(5, 6, 1, 1, campoCpf);
      adicionarComponente(7, 1, 1, 4, campoCidade);
      adicionarComponente(7, 5, 1, 1, campoSituacao);
      habilitarCampos(false);
      pack();
   }
    
    
   public void setPersistencia() {
      colaborador.setIdcolaborador((int) campoCodigo.getValor());
      colaborador.setNome((String) campoNome.getValor());
//      colaborador.setDatanascimento((Date) campoDatanascimento.getValor());
      colaborador.setTelefone((String) campoTelefone.getValor());
      colaborador.setCelular((String) campoCelular.getValor());
      colaborador.setEndereco((String) campoEndereco.getValor());
      colaborador.setNumcasa((String) campoNumcasa.getValor());
      colaborador.setBairro((String) campoBairro.getValor());
      colaborador.setRg((String) campoRg.getValor());
      colaborador.setCpf((String) campoCpf.getValor());
//      colaborador.setSituacao((String) campoSituacao.getValor());
      colaborador.setIdcidade((int)campoCidade.getValor());      
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(colaborador.getIdcolaborador());
      campoNome.setValor(colaborador.getNome());
//      campoDatanascimento.setValor(colaborador.getDatanascimento());
      campoTelefone.setValor(colaborador.getTelefone());
      campoCelular.setValor(colaborador.getCelular());
      campoEndereco.setValor(colaborador.getEndereco());
      campoNumcasa.setValor(colaborador.getNumcasa());
      campoBairro.setValor(colaborador.getBairro());
      campoRg.setValor(colaborador.getRg());
      campoCpf.setValor(colaborador.getCpf());
      campoSituacao.setValor(colaborador.getSituacao());
      campoCidade.setValor(colaborador.getIdcidade());
 
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoColaborador.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoColaborador.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoColaborador.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de Colaborador",
               new String[] {"Código", "Nome", "CPF"},
               DaoColaborador.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      colaborador.setIdcolaborador(pk);
      daoColaborador.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   public String pesquisa(String texto){
       String sql = daoColaborador.pesquisa(texto);
       return(sql);
   }

}