package componente;

import banco.Conexao;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tela.TelaCadastro;
import tela.TelaCadastroCidade;
import tela.TelaCadastroEstado;
import static tela.TelaPrincipal.jdp;

public class MeuDBComboBox extends JPanel implements MeuComponente, ActionListener {
    private boolean obrigatorio;
    private String sql;
    private String dica;
    private JComboBox jcb = new JComboBox();
    public JButton jbAdicionar = new JButton("...");
    private List<Object[]> dados;
    
    public MeuDBComboBox(boolean obrigatorio, String sql, String dica) {
        this.obrigatorio = obrigatorio;
        this.sql = sql;
        this.dica = dica;
        setLayout(new FlowLayout());
        add(jcb);
        add(jbAdicionar);
        preencher();
        jbAdicionar.addActionListener(this);
    }
    
    public void preencher() {
        jcb.addItem("<Selecione>");
        dados = Conexao.consultarComboBox(sql);
        for (int i = 0; i < dados.size(); i++) {
            jcb.addItem(dados.get(i)[1]);
        }
    }
    
    @Override
    public Object getValor() {
        if (jcb.getSelectedIndex() > 0) {
            return (int) dados.get(jcb.getSelectedIndex()-1)[0];
        } else {
            return -1;
        }
    }
    
    public void setValor(int valor) {
        for (int i = 0; i < dados.size(); i++) {
            if (valor == ((int) dados.get(i)[0])) {
                jcb.setSelectedIndex(i + 1);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, getNome() + " não encontrado(a).");
    }
    
    @Override
    public String getNome() {
        return dica;
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public void habilitar(boolean status) {
        jcb.setEnabled(status);
        jbAdicionar.setEnabled(status);
    }

    @Override
    public boolean eVazio() {
        return (jcb.getSelectedIndex() <= 0);
    }

    @Override
    public boolean eValido() {
        return true;
    }    

    @Override
    public void limpar() {
        jcb.setSelectedIndex(0);
    }

    @Override
    public void setValor(Object valor) {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbAdicionar){
        TelaCadastroCidade.chamarTela();
        }
    }

}

