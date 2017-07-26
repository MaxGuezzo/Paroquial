package tela;

import componente.MeuComponente;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaCadastro extends JInternalFrame implements ActionListener {
    private final int PADRAO = 0;
    private final int INCLUINDO = 1;
    private final int ALTERANDO = 2;
    private final int EXCLUINDO = 3;
    private final int CONSULTANDO = 4;
    private int estadoTela = PADRAO;
    private boolean temDadosNaTela = false;
    private JPanel jpComponentes = new JPanel();
    private JPanel jpBotoes = new JPanel();
    private JButton jbIncluir = new JButton("Incluir");
    private JButton jbAlterar = new JButton("Alterar");
    private JButton jbExcluir = new JButton("Excluir");
    private JButton jbConsultar = new JButton("Consultar");
    private JButton jbConfirmar = new JButton("Confirmar");
    private JButton jbCancelar = new JButton("Cancelar");
    private List<MeuComponente> campos = new ArrayList();

    public TelaCadastro(String titulo) {
        super(titulo, false, true, false, true);
        getContentPane().add("Center", jpComponentes);
        getContentPane().add("South", jpBotoes);
        jpComponentes.setLayout(new GridBagLayout());
        jpBotoes.setLayout(new GridLayout());
        adicionarBotao(jbIncluir);
        adicionarBotao(jbAlterar);
        adicionarBotao(jbExcluir);
        adicionarBotao(jbConsultar);
        adicionarBotao(jbConfirmar);
        adicionarBotao(jbCancelar);
        pack();
        setVisible(true);
        habilitarBotoes();
    }

    public void adicionarComponente(int linha, int coluna,int linhas,int colunas,
            MeuComponente componente) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 1, 0, 1);
        gbc.gridy = linha;
        gbc.gridx = coluna;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        String nome = "<html><body>" + componente.getNome();
        if (componente.eObrigatorio()) {
            nome = nome + "<font color=red>*</font>";
        }
        nome = nome + ": </body></html>";
        JLabel label = new JLabel(nome);
        jpComponentes.add(label, gbc);
        gbc.gridy++;
        gbc.gridheight = linhas;
        gbc.gridwidth = colunas;
        gbc.anchor = GridBagConstraints.WEST;
        jpComponentes.add((JComponent) componente, gbc);
        campos.add(componente);
    }

    private void adicionarBotao(JButton botao) {
        jpBotoes.add(botao);
        botao.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jbIncluir) {
            incluir();
        } else if (ae.getSource() == jbAlterar) {
            alterar();
        } else if (ae.getSource() == jbExcluir) {
            excluir();
        } else if (ae.getSource() == jbConsultar) {
            consultar();
        } else if (ae.getSource() == jbConfirmar) {
            confirmar();
        } else if (ae.getSource() == jbCancelar) {
            cancelar();
        }
    }

    private void habilitarBotoes() {
        jbIncluir.setEnabled(estadoTela == PADRAO);
        jbAlterar.setEnabled(estadoTela == PADRAO && temDadosNaTela);
        jbExcluir.setEnabled(estadoTela == PADRAO && temDadosNaTela);
        jbConsultar.setEnabled(estadoTela == PADRAO);
        jbConfirmar.setEnabled(estadoTela != PADRAO && estadoTela != CONSULTANDO);
        jbCancelar.setEnabled(estadoTela != PADRAO && estadoTela != CONSULTANDO);
    }

    private void incluir() {
        estadoTela = INCLUINDO;
        limparCampos();
        habilitarCampos(true);
        habilitarBotoes();
    }

    private void alterar() {
        estadoTela = ALTERANDO;
        habilitarCampos(true);        
        habilitarBotoes();
    }

    private void excluir() {
        estadoTela = EXCLUINDO;
        habilitarBotoes();
    }

    public void consultar() {
        estadoTela = CONSULTANDO;
        habilitarBotoes();
    }

    private void confirmar() {
        if (estadoTela == INCLUINDO) {
            if (!verificarCampos() || !incluirBD()) {
                return;
            }
        }
        if (estadoTela == ALTERANDO) {
            if (!verificarCampos() || !alterarBD()) {
                return;
            }
        }
        if (estadoTela == EXCLUINDO) {
            if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (!excluirBD()) {
                    return;
                }
                limparCampos();
                temDadosNaTela = false;
            } else {
                return;
            }
        }         
        estadoTela = PADRAO;
        habilitarCampos(false);
        habilitarBotoes();
    }

    private void cancelar() {
        estadoTela = PADRAO;
        habilitarCampos(false);
        habilitarBotoes();
    }

    public void habilitarCampos(boolean status) {
        for (int i = 0; i < campos.size(); i++) {
            campos.get(i).habilitar(status);
        }
    }

    public void limparCampos() {
        for (int i = 0; i < campos.size(); i++) {
            campos.get(i).limpar();
        }
    }
    
    private boolean verificarCampos() {
        String obrigatoriosNaoPreenchidos = "";
        for (int i = 0; i < campos.size(); i++) {
            if (campos.get(i).eObrigatorio() && campos.get(i).eVazio()) {
                obrigatoriosNaoPreenchidos = obrigatoriosNaoPreenchidos + campos.get(i).getNome() + "\n";                
            }
        }
        if (!obrigatoriosNaoPreenchidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                    "Os campos abaixo são obrigatórios e não foram informados:\n\n" +
                            obrigatoriosNaoPreenchidos);
        }
        
        String invalidos = "";
        for (int i = 0; i < campos.size(); i++) {
            if (!campos.get(i).eValido()) {
                invalidos = invalidos + campos.get(i).getNome() + "\n";                
            }
        }
        if (!invalidos.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                    "Os campos abaixo são estão válidos:\n\n" +
                            invalidos);
        }

//        if (obrigatoriosNaoPreenchidos.isEmpty() && invalidos.isEmpty()) {
//            return true;
//        } else {
//            return false;
//        }
//  A linha abaixo significa a mesma coisa que este bloco comentado.        
        return (obrigatoriosNaoPreenchidos.isEmpty() && invalidos.isEmpty());
    }
    
    public boolean incluirBD() {
        //A ser implementado nas subclasses.
        return false;
    }
    
    public boolean alterarBD() {
        //A ser implementado nas subclasses.
        return false;
    }    
    
    public boolean excluirBD() {
        //A ser implementado nas subclasses.
        return false;
    }    

    public void pesquisaSemDados() {
        estadoTela = PADRAO;
        habilitarBotoes();
    }
    
    public void preencherDados(int pk) {
        estadoTela = PADRAO;
        temDadosNaTela = true;
        habilitarBotoes();
    }
}