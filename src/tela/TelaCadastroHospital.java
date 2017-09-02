package tela;

import componente.MeuCampoData;
import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.DaoCidade;
import dao.DaoHospital;
import pojo.Hospital;

public class TelaCadastroHospital extends TelaCadastro {
    public Hospital hospital = new Hospital();
    public DaoHospital daoHospital = new DaoHospital(hospital);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");         
    public MeuCampoTexto campoEndereco = new MeuCampoTexto(22, 20, true, "Endereco");           
    public MeuCampoTexto campoNumcasa = new MeuCampoTexto(10, 4, true, "Numero");           
    public MeuCampoTexto campoBairro = new MeuCampoTexto(19, 50, true, "Bairro"); 
    public MeuCampoTexto campoTelefone = new MeuCampoTexto(14, 14, true, "Telefone"); 
    public MeuCampoTexto campoComplemento = new MeuCampoTexto(14, 14, true, "Complemento"); 
    public MeuCampoData campoDatacadastro = new MeuCampoData(10,true,false, "Data Cadastro"); 
    public MeuDBComboBox campoCidade = new MeuDBComboBox(this,true,DaoCidade.SQLCOMBOBOX, "Cidade"); 
    
    public TelaCadastroHospital() {
      super("Cadastro de Hospital");
      adicionarComponente(1, 1, 1, 1, campoCodigo, "Campo gerado automaticamente pelo sistema", true);
      adicionarComponente(1, 2, 1, 1, campoDatacadastro , null, false);
      adicionarComponente(3, 1, 1, 2, campoNome , null, false);
      adicionarComponente(3, 3, 1, 2, campoEndereco , null, false);
      adicionarComponente(3, 5, 1, 2, campoNumcasa , null, false);
      adicionarComponente(5, 1, 1, 2, campoBairro , null, false);
      adicionarComponente(5, 3, 1, 1, campoTelefone , null, false);
      adicionarComponente(5, 4, 1, 2, campoComplemento , null, false);
      adicionarComponente(7, 1, 1, 2, campoCidade , null, false);

      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      hospital.setIdhospital((int) campoCodigo.getValor());
      hospital.setNome((String) campoNome.getValor());     
      hospital.setEndereco((String) campoEndereco.getValor());     
      hospital.setNumcasa((String) campoNumcasa.getValor());     
      hospital.setBairro((String) campoBairro.getValor());     
      hospital.setTelefone((String) campoTelefone.getValor());     
      hospital.setComplemento((String) campoComplemento.getValor());     
      hospital.setIdcidade((int) campoCidade.getValor());     
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(hospital.getIdhospital());
      campoNome.setValor(hospital.getNome());  
      campoEndereco.setValor(hospital.getEndereco());  
      campoNumcasa.setValor(hospital.getNumcasa());  
      campoBairro.setValor(hospital.getBairro());  
      campoTelefone.setValor(hospital.getTelefone());  
      campoComplemento.setValor(hospital.getComplemento());  
      campoCidade.setValor(hospital.getIdcidade());  
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoHospital.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoHospital.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoHospital.excluir();       
   }

   @Override
   public void consultar() {
       super.consultar();
       new TelaConsulta(this, 
               "Consulta de Hospital",
               new String[] {"Código", "Nome","Telefone"},
               DaoHospital.SQL_PESQUISAR); 
   }   
   
    @Override
   public void preencherDados(int pk) {
      hospital.setIdhospital(pk);
      daoHospital.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
    @Override
   public String pesquisa(String texto, int valor){
       String sql = daoHospital.pesquisa(texto, valor);
       return(sql);
   }
}