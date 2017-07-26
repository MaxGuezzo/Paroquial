package tela;

import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import dao.DaoColaborador;
import dao.DaoEstado;
import pojo.Colaborador;

public class TelaCadastroColaborador extends TelaCadastro {
    public Colaborador colaborador = new Colaborador();
    public DaoColaborador daoColaborador = new DaoColaborador(colaborador);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(25, 50, true, "Nome");
    public MeuCampoTexto campoDatanascimento = new MeuCampoTexto(10, 10, true, "Data de Nasc");           
    public MeuCampoTexto campoTelefone = new MeuCampoTexto(14, 10, true, "Telefone");           
    public MeuCampoTexto campoCelular = new MeuCampoTexto(14, 10, true, "Celular");           
    public MeuCampoTexto campoEndereco = new MeuCampoTexto(20, 10, true, "Endereco");           
    public MeuCampoTexto campoNumcasa = new MeuCampoTexto(10, 10, true, "Numero");           
    public MeuCampoTexto campoBairro = new MeuCampoTexto(10, 10, true, "Bairro");           
    public MeuCampoTexto campoRg = new MeuCampoTexto(10, 10, true, "RG");           
    public MeuCampoTexto campoCpf = new MeuCampoTexto(10, 10, true, "CPF");           
    public MeuCampoTexto campoSituacao = new MeuCampoTexto(10, 10, true, "Situação");           
    
    public TelaCadastroColaborador() {
      super("Cadastro de Colaborador");
      adicionarComponente(1, 1, 1, 1, campoCodigo);
      adicionarComponente(1, 2, 1, 4, campoNome);
      adicionarComponente(1, 6, 1, 1, campoDatanascimento);
      adicionarComponente(3, 1, 1, 2, campoTelefone);
      adicionarComponente(3, 3, 1, 2, campoCelular);
      adicionarComponente(3, 6, 1, 1, campoEndereco);
//      adicionarComponente(7, 3, 1, 1, campoNumcasa);
//      adicionarComponente(9, 1, 1, 1, campoBairro);
//      adicionarComponente(11, 1, 1, 1, campoRg);
//      adicionarComponente(11, 3, 1, 1, campoCpf);
//      adicionarComponente(13, 1, 1, 1, campoSituacao);
      habilitarCampos(false);
      pack();
   }
    
//   public void setPersistencia() {
//      estado.setIdestado((int) campoCodigo.getValor());
//      estado.setNome((String) campoNome.getValor());
//      estado.setSigla((String) campoSigla.getValor());      
//   }
//   
//   public void getPersistencia() {
//      campoCodigo.setValor(estado.getIdestado());
//      campoNome.setValor(estado.getNome());
//      campoSigla.setValor(estado.getSigla());   
//   }
//   
//   @Override
//   public boolean incluirBD() {
//      setPersistencia();
//      return daoEstado.incluir();
//   }
//   
//   @Override
//   public boolean alterarBD() {
//      setPersistencia();
//      return daoEstado.alterar();       
//   }
//   
//   @Override
//   public boolean excluirBD() {
//      setPersistencia();
//      return daoEstado.excluir();       
//   }
//
//   @Override
//   public void consultar() {
//       super.consultar();
//       new TelaConsulta(this, 
//               "Consulta de Estado",
//               new String[] {"Código", "Nome", "Sigla"},
//               DaoEstado.SQL_PESQUISAR); 
//   }   
//   
//    @Override
//   public void preencherDados(int pk) {
//      estado.setIdestado(pk);
//      daoEstado.consultar();
//      getPersistencia();
//      super.preencherDados(pk);
//   }
}