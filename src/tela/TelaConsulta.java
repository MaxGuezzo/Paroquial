package tela;

import banco.Conexao;
import componente.MeuCampoTexto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TelaConsulta extends JInternalFrame implements MouseListener, KeyListener {
    private TelaCadastro telaChamadora;
    private JPanel jpPesquisa = new JPanel();
    private JPanel  jptabela = new JPanel();
    private JPanel  jpbotao = new JPanel();
    private JComboBox jcb = new JComboBox();
    private MeuCampoTexto campoPesquisa = new MeuCampoTexto(30, 20, true, "Pesquisar");
    private JLabel descricao = new JLabel("Buscar:");
    private JLabel tipoFiltro = new JLabel("Filtro:");
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable tabela = new JTable(dtm) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }

        @Override
        public Component prepareRenderer(TableCellRenderer renderer,
                int linha, int coluna) {
            Component c = super.prepareRenderer(renderer, linha, coluna);
            if (linha % 2 == 0) {
                c.setBackground(Color.LIGHT_GRAY);
            } else {
                c.setBackground(getBackground());
            }
            if (isCellSelected(linha, coluna)) {
                c.setBackground(Color.YELLOW);
            }
            return c;
        }
    };
    private JScrollPane jsp = new JScrollPane(tabela);

    public TelaConsulta(TelaCadastro telaChamadora, String titulo, String[] colunas, String sql) {
        super(titulo, true, true, true, false);
        this.telaChamadora = telaChamadora;
        tabela.getTableHeader().setReorderingAllowed(false);
        insereColunas(colunas);
        insereDados(sql);
        if (dtm.getRowCount() == 0) { 
            JOptionPane.showMessageDialog
        (null, "Não existem dados cadastrados.");
            telaChamadora.pesquisaSemDados();
            return;
        }
        jpPesquisa.add(descricao);
        jpPesquisa.add(campoPesquisa);
        jpPesquisa.add(tipoFiltro);
        jpPesquisa.add(jcb);
        jptabela.add(jsp);
        getContentPane().add("North", jpPesquisa);
        getContentPane().add("Center", jptabela);
//        jpbotao.add(jsp);
        getContentPane().add(jsp);
        pack();
        setVisible(true);
        TelaPrincipal.jdp.add(this);
        TelaPrincipal.jdp.moveToFront(this);
        centralizaTela();
        tabela.addMouseListener(this);
        campoPesquisa.addKeyListener(this);
        preencher();
    }
    
    private void preencher(){
        jcb.addItem("Nome");
        jcb.addItem("ID");
    }

    public void centralizaTela() {
        Dimension tamanhoTela = getSize();
        Dimension tamanhoJDesktopPane = TelaPrincipal.jdp.getSize();
        int x = (tamanhoJDesktopPane.width - tamanhoTela.width) / 2;
        int y = (tamanhoJDesktopPane.height - tamanhoTela.height) / 2;
        setLocation(x, y);
    }

    public void insereColunas(String[] colunas) {
        for (int i = 0; i < colunas.length; i++) {
            dtm.addColumn(colunas[i]);
        }
    }

    public void insereDados(String sql) {
        esvaziarTabela();
        List<String[]> dados = Conexao.executaQuery(sql);
        for (int i = 0; i < dados.size(); i++) {
            dtm.addRow(dados.get(i));
        }
    }
    
    public void esvaziarTabela(){
        for(int i = dtm.getRowCount(); i!=0 ;i--){
            dtm.removeRow(i-1);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() == 2) {
            telaChamadora.preencherDados(Integer.parseInt((String)dtm.getValueAt(tabela.getSelectedRow(), 0)));
            dispose();
            TelaPrincipal.jdp.remove(this);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String sql = telaChamadora.pesquisa(campoPesquisa.getText(), jcb.getSelectedIndex());
         insereDados(sql);       
     }

}