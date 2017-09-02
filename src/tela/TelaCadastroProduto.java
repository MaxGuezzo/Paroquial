package tela;

import componente.MeuCampoCpf;
import componente.MeuCampoInteiro;
import componente.MeuCampoTexto;
import componente.MeuDBComboBox;
import dao.DaoProduto;
import pojo.Produto;

public class TelaCadastroProduto extends TelaCadastro {
    public Produto produto = new Produto();
    public DaoProduto daoProduto = new DaoProduto(produto);
    public MeuCampoInteiro campoCodigo = new MeuCampoInteiro(5, true, false, "Código");
    public MeuCampoTexto campoNome = new MeuCampoTexto(20, 50, true, "Nome");
    public MeuCampoInteiro campoValorunitario = new MeuCampoInteiro(5, true, false, "Valor");
    public MeuCampoInteiro campoQuantidade = new MeuCampoInteiro(5, true, false, "Quantidade");
//    public MeuDBComboBox campoCor = new MeuDBComboBox(5, true, false, "Cor");
//    public MeuDBComboBox campoTipoproduto = new MeuDBComboBox(5, true, false, "Tipo Produto");          
    
    public TelaCadastroProduto() {
      super("Cadastro de Produto");
      adicionarComponente(1, 1, 1, 1, campoCodigo, "Campo gerado automaticamente pelo sistema", true);
      adicionarComponente(3, 1, 1, 1, campoNome , null, false);
      habilitarCampos(false);
      pack();
   }
    
   public void setPersistencia() {
      produto.setIdproduto((int) campoCodigo.getValor());
      produto.setNome((String) campoNome.getValor());    
   }
   
   public void getPersistencia() {
      campoCodigo.setValor(produto.getIdproduto());
      campoNome.setValor(produto.getNome()); 
   }
   
   @Override
   public boolean incluirBD() {
      setPersistencia();
      return daoProduto.incluir();
   }
   
   @Override
   public boolean alterarBD() {
      setPersistencia();
      return daoProduto.alterar();       
   }
   
   @Override
   public boolean excluirBD() {
      setPersistencia();
      return daoProduto.excluir();       
   }

//   @Override
//   public void consultar() {
//       super.consultar();
//       new TelaConsulta(this, 
//               "Consulta de Produto",
//               new String[] {"Código", "Nome", "Sigla"},
//               DaoProduto.SQL_PESQUISAR); 
//   }   
   
    @Override
   public void preencherDados(int pk) {
      produto.setIdproduto(pk);
      daoProduto.consultar();
      getPersistencia();
      super.preencherDados(pk);
   }
   
//    @Override
//   public String pesquisa(String texto, int valor){
//       String sql = daoProduto.pesquisa(texto, valor);
//       return(sql);
//   }
}