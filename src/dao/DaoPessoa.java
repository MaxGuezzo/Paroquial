package dao;

import banco.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Funcionario;
import pojo.Pessoa;

public class DaoPessoa {
    private Pessoa pessoa;
    private final String SQL_INCLUIR =
            "INSERT INTO PESSOA VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String SQL_ALTERAR =
            "UPDATE FUNCIONARIO SET NOME = ?, SEXO = ?, RG = ?, CPF = ?, DATANASCIMENTO = ?, TELEFONE = ?,"
            + "CELULAR = ?, ENDERECO = ?, NUMCASA = ?, BAIRRO = ?, DATACADASTRO = ?,"
            + "SITUACAO = ?, IDCIDADE = ?, DATACADASTRO = ? WHERE IDPESSOA = ?";
    
    private final String SQL_EXCLUIR =
            "DELETE FROM PESSOA WHERE IDPESSOA = ?";
    
    private final String SQL_CONSULTAR =
            "SELECT * FROM PESSOA WHERE IDPESSOA= ?";
    
    public static final String SQL_PESQUISAR =
            "SELECT CEB.IDCEB, CEB.NOME, COLABORADOR.NOME "
            + "FROM CEB, COLABORADOR "
            + "WHERE COLABORADOR.IDCOLABORADOR = CEB.IDCOLABORADORORDER BY CEB.NOME";
                
    
    public DaoPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public boolean incluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
           ps.setString(1, pessoa.getNome());
           ps.setString(2, pessoa.getSexo());
           ps.setString(3, pessoa.getRg());
           ps.setString(4, pessoa.getCpf());
           ps.setDate(5, (Date) pessoa.getDatanascimento());
           ps.setString(6, pessoa.getTelefone());
           ps.setString(7, pessoa.getCelular());
           ps.setString(8, pessoa.getEndereco());
           ps.setString(9, pessoa.getNumcasa());
           ps.setString(10, pessoa.getBairro());
           ps.setDate(11, (Date) pessoa.getDatacadastro());
           ps.setString(12, pessoa.getSituacao() ? "A" : "I");
           ps.setInt(13, pessoa.getIdcidade());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar incluir a pessoa.");
            return false;
        }
    }
    
    public boolean alterar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
           ps.setString(1, pessoa.getNome());
           ps.setString(2, pessoa.getSexo());
           ps.setString(3, pessoa.getRg());
           ps.setString(4, pessoa.getCpf());
           ps.setDate(5, (Date) pessoa.getDatanascimento());
           ps.setString(6, pessoa.getTelefone());
           ps.setString(7, pessoa.getCelular());
           ps.setString(8, pessoa.getEndereco());
           ps.setString(9, pessoa.getNumcasa());
           ps.setString(10, pessoa.getBairro());
           ps.setDate(11, (Date) pessoa.getDatacadastro());
           ps.setString(12, pessoa.getSituacao() ? "A" : "I");
           ps.setInt(13, pessoa.getIdcidade());
           ps.setInt(15, pessoa.getIdpessoa());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar alterar a pessoa.");
            return false;
        }
    }

    public boolean excluir() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
           ps.setInt(1, pessoa.getIdpessoa());
           ps.executeUpdate();
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve um problema ao tentar excluir a pessoa.");
            return false;
        }
    }       
    
    public boolean consultar() {
        try {
           PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
           ps.setInt(1, pessoa.getIdpessoa());
           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               pessoa.setNome(rs.getString(2));
               pessoa.setSexo(rs.getString(3));
               pessoa.setRg(rs.getString(4));
               pessoa.setCpf(rs.getString(5));
               pessoa.setDatanascimento(rs.getDate(6));
               pessoa.setTelefone(rs.getString(7));
               pessoa.setCelular(rs.getString(8));
               pessoa.setEndereco(rs.getString(9));
               pessoa.setNumcasa(rs.getString(10));
               pessoa.setBairro(rs.getString(11));
               pessoa.setDatacadastro(rs.getDate(12));
               pessoa.setSituacao(rs.getString(14).equals("A"));
               pessoa.setIdcidade(rs.getInt(15));
           }
           return true;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Não foi possível consultar a pessoa.");
           e.printStackTrace();
           return false;
        }
    }
}