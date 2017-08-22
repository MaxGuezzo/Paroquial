package tela;

import componente.MeuCampoCheckBox;
import componente.MeuCampoCpf;
import componente.MeuCampoData;
import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.DaoCidade;
import dao.DaoPessoa;
import java.util.Date;
import pojo.Pessoa;
import static tela.TelaPrincipal.jdp;

public class TelaCadastroPessoa extends TelaCadastro {
    public Pessoa pessoa = new Pessoa();
    public DaoPessoa daoPessoa = new DaoPessoa(pessoa);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(35, 50, true, "Nome");
    public MeuCampoData campoDatanascimento = new MeuCampoData(10, true, true, "Data de Nasc");
    public MeuDBComboBox campoSexo = new MeuDBComboBox(this, true, "", "Sexo");
    public MeuCampoTexto campoTelefone = new MeuCampoTexto(14, 14, true, "Telefone");           
    public MeuCampoTexto campoCelular = new MeuCampoTexto(14, 14, true, "Celular");           
    public MeuCampoTexto campoEndereco = new MeuCampoTexto(22, 20, true, "Endereco");           
    public MeuCampoTexto campoNumcasa = new MeuCampoTexto(10, 4, true, "Numero");           
    public MeuCampoTexto campoBairro = new MeuCampoTexto(19, 50, true, "Bairro");           
    public MeuCampoTexto campoRg = new MeuCampoTexto(10, 12, true, "RG");           
    public MeuCampoCpf campoCpf = new MeuCampoCpf(10, true, true, "CPF");  
    public MeuCampoCheckBox campoSituacao = new MeuCampoCheckBox(true, true, "Ativo");
    private MeuDBComboBox campoCidade = new MeuDBComboBox(this, true, DaoCidade.SQLCOMBOBOX, "Cidade");
    public MeuCampoData campoDataCadastro = new MeuCampoData(10, false, false, "Data do cadastro");    
    
    
    public TelaCadastroPessoa() {
      super("Cadastro de Pessoa");
      adicionarComponente(1, 1, 1, 1, campoCodigo);
      adicionarComponente(1, 2, 1, 5, campoNome);
      adicionarComponente(1, 6, 1, 1, campoDatanascimento);
      adicionarComponente(3, 1, 1, 3, campoTelefone);
      adicionarComponente(3, 4, 1, 1, campoCelular);
      adicionarComponente(3, 5, 1, 3, campoEndereco);
      adicionarComponente(5, 1, 1, 2, campoNumcasa);
      adicionarComponente(5, 3, 1, 2, campoBairro);
      adicionarComponente(5, 5, 1, 1, campoRg);
      adicionarComponente(5, 6, 1, 1, campoCpf);
      adicionarComponente(7, 1, 1, 4, campoCidade);
      adicionarComponente(7, 5, 1, 1, campoSituacao);
      adicionarComponente(7, 6, 1, 1, campoDataCadastro);
      habilitarCampos(false);
      pack();
   }
    
    
   public void setPersistencia() {
      pessoa.setIdpessoa((int) campoCodigo.getValor());
      pessoa.setNome((String) campoNome.getValor());
      pessoa.setDatanascimento( (Date) campoDatanascimento.getValor());
      pessoa.setTelefone((String) campoTelefone.getValor());
      pessoa.setCelular((String) campoCelular.getValor());
      pessoa.setEndereco((String) campoEndereco.getValor());
      pessoa.setNumcasa((String) campoNumcasa.getValor());
      pessoa.setBairro((String) campoBairro.getValor());
      pessoa.setRg((String) campoRg.getValor());
      pessoa.setCpf((String) campoCpf.getValor());
      pessoa.setSituacao((boolean) campoSituacao.getValor());
      pessoa.setIdcidade((int)campoCidade.getValor());      
      pessoa.setDatacadastro((Date)campoDataCadastro.getValor());      
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(pessoa.getIdpessoa());
      campoNome.setValor(pessoa.getNome());
      campoDatanascimento.setValor( pessoa.getDatanascimento());
      campoTelefone.setValor(pessoa.getTelefone());
      campoCelular.setValor(pessoa.getCelular());
      campoEndereco.setValor(pessoa.getEndereco());
      campoNumcasa.setValor(pessoa.getNumcasa());
      campoBairro.setValor(pessoa.getBairro());
      campoRg.setValor(pessoa.getRg());
      campoCpf.setValor(pessoa.getCpf());
      campoSituacao.setValor(pessoa.getSituacao());
      campoCidade.setValor(pessoa.getIdcidade());
      campoDataCadastro.setValor(pessoa.getDatacadastro());
 
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoPessoa.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoPessoa.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoPessoa.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de Pessoa",
               new String[] {"Código", "Nome", "CPF"},
               DaoPessoa.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      pessoa.setIdpessoa(pk);
      daoPessoa.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
//   public String pesquisa(String texto){
//       String sql = daoPessoa.pesquisa(texto);
//       return(sql);
//   }
   
    @Override
      public void chamarTela(){
       TelaCadastroCidade telaCadastroCidade = new TelaCadastroCidade();
       jdp.add(telaCadastroCidade);
       telaCadastroCidade.setLocation(50, 50);
       jdp.moveToFront(telaCadastroCidade);
   }

}