package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Fornecedor;

public class DaoFornecedor {
    private Fornecedor fornecedor;
    private final String SQL_INCLUIR =
            "INSERT INTO FORNECEDOR VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE FORNECEDOR SET NOME = ?, RAZAOSOCIAL = ?, CNPJ = ?, TELEFONE = ?,"
            + "CELULAR = ?, ENDERECO = ?, NUMCASA = ?, BAIRRO = ?, COMPLEMENTO = ?,"
            + "DATACADASTRO = ?, SITUACAO = ?, IDCIDADE = ? WHERE IDFORNECEDOR = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM FORNECEDOR WHERE IDFORNECEDOR = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM FORNECEDOR WHERE IDFORNECEDOR = ?";
    
//    public static final String SQL_PESQUISAR =
//            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
//            + "FROM CEB, COLABORADOR "
//            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setInt(1, fornecedor.getIdfornecedor());
           ps.setString(2, fornecedor.getNome());
           ps.setString(3, fornecedor.getRazaosocial());
           ps.setString(4, fornecedor.getCnpj());
           ps.setString(5, fornecedor.getTelefone());
           ps.setString(6, fornecedor.getCelular());
           ps.setString(7, fornecedor.getEndereco());
           ps.setString(8, fornecedor.getNumcasa());
           ps.setString(9, fornecedor.getBairro());
           ps.setString(10, fornecedor.getComplemento());
           ps.setDate(11, fornecedor.getDatacadastro());
           ps.setString(12, fornecedor.getSituacao());
           ps.setInt(13, fornecedor.getIdcidade());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir o fornecedor.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, fornecedor.getNome());
           ps.setString(2, fornecedor.getRazaosocial());
           ps.setString(3, fornecedor.getCnpj());
           ps.setString(4, fornecedor.getTelefone());
           ps.setString(5, fornecedor.getCelular());
           ps.setString(6, fornecedor.getEndereco());
           ps.setString(7, fornecedor.getNumcasa());
           ps.setString(8, fornecedor.getBairro());
           ps.setString(9, fornecedor.getComplemento());
           ps.setDate(10, fornecedor.getDatacadastro());
           ps.setString(11, fornecedor.getSituacao());
           ps.setInt(12, fornecedor.getIdcidade());
           ps.setInt(13, fornecedor.getIdfornecedor());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar o fornecedor.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, fornecedor.getIdfornecedor());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir o fornecedor.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, fornecedor.getIdfornecedor());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               fornecedor.setNome(rs.getString(2));
               fornecedor.setRazaosocial(rs.getString(3));
               fornecedor.setCnpj(rs.getString(4));
               fornecedor.setTelefone(rs.getString(5));
               fornecedor.setCelular(rs.getString(6));
               fornecedor.setEndereco(rs.getString(7));
               fornecedor.setNumcasa(rs.getString(8));
               fornecedor.setBairro(rs.getString(9));
               fornecedor.setComplemento(rs.getString(10));
               fornecedor.setDatacadastro(rs.getDate(11));
               fornecedor.setSituacao(rs.getString(12));
               fornecedor.setIdcidade(rs.getInt(13));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar o fornecedor.");
           e.printStackTrace();
           return false;
        }
    }
}