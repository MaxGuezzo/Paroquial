package tela;

import banco.Conexao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TelaConsulta extends JInternalFrame implements MouseListener, KeyListener {
    private TelaCadastro telaChamadora;
    private JLabel dica = new JLabel("Pesquisar");
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
            JOptionPane.showMessageDialog(null,
                    "Não existem dados cadastrados.");
            telaChamadora.pesquisaSemDados();
            return;
        }
        getContentPane().add(jsp);
        pack();
        setVisible(true);
        TelaPrincipal.jdp.add(this);
        TelaPrincipal.jdp.moveToFront(this);
        centralizaTela();
        tabela.addMouseListener(this);
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
        List<String[]> dados = Conexao.executaQuery(sql);
        for (int i = 0; i < dados.size(); i++) {
            dtm.addRow(dados.get(i));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}