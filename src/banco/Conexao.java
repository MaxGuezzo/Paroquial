package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexao {

    private static Connection conexao;

    public static Connection getConexao() {
        if (conexao != null) {
            return conexao;
        } else {
            try {
                Class.forName("org.firebirdsql.jdbc.FBDriver");
                conexao = DriverManager.getConnection("jdbc:firebirdsql:"
                        + "//localhost:3050/"
                        + System.getProperty("user.dir")
                        + "/banco/paroquia.fdb", "SYSDBA", "masterkey");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro no driver.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados.");
            }
            return conexao;
        }
    }
   public static List<String[]> executaQuery(String sql) {
        try {
            List<String[]> dados = new ArrayList();
            Statement st = Conexao.getConexao().createStatement();
            ResultSet rs = st.executeQuery(sql);
            int numeroColunas = rs.getMetaData().getColumnCount();
            while(rs.next()) {
                String[] linha = new String[numeroColunas];
                for(int i = 1; i <= numeroColunas; i++) {
                    linha[i-1] = rs.getString(i);
                }
                dados.add(linha);
            }
            return dados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível consultar o banco de dados");
            return new ArrayList();
        }
    }
    public static List<Object[]> consultarComboBox(String sql) {
        try {
            List<Object[]> retorno = new ArrayList();
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
               Object[] linha = new Object[2];
               linha[0] = rs.getInt(1);
               linha[1] = rs.getString(2);
               retorno.add(linha);
            }
            return retorno;            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível consultar dados para o ComboBox");
            return null;
        }
    }
    
}
