package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class TelaPrincipal extends JFrame implements ActionListener {
    public static JDesktopPane jdp = new JDesktopPane();
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmCadastro = new JMenu("Cadastro");
    private JMenu jmMovimento = new JMenu("Movimento");
    private JMenu jmRelatorio = new JMenu("Relatorio");
    private JMenu jmUteis = new JMenu("Uteis");
    private JMenu jmProduto = new JMenu("Produto");
    private JMenu jmVisita = new JMenu("Visita");
    private JMenuItem jmiPessoa = new JMenuItem("Pessoa");
    private JMenuItem jmiColaborador = new JMenuItem("Colaborador");
    private JMenuItem jmiFornecedor = new JMenuItem("Fornecedor");
    private JMenuItem jmiProduto = new JMenuItem("Produto");
    private JMenuItem jmiPadre = new JMenuItem("Padre");
    private JMenuItem jmiCeb = new JMenuItem("CEB");
    private JMenuItem jmiComunidade = new JMenuItem("Comunidade");
    private JMenuItem jmiHospital = new JMenuItem("Hospital");
    private JMenuItem jmiEnfermo = new JMenuItem("Enfermo");
    private JMenuItem jmiTipoAgendamento = new JMenuItem("Tipo Agendamento");
    private JMenuItem jmiTipoRecebimento = new JMenuItem("Tipo de Recebimento");
    private JMenuItem jmiTipoProduto = new JMenuItem("Tipo Produto");
    private JMenuItem jmiMissa = new JMenuItem("Missa");
    private JMenuItem jmiCor = new JMenuItem("Cor");
    private JMenuItem jmiSituacaoPedido = new JMenuItem("Situação Pedido");
    private JMenuItem jmiCidade = new JMenuItem("Cidade");
    private JMenuItem jmiEstado = new JMenuItem("Estado");
    
    
    public TelaPrincipal(){
        setTitle("Sistema Paroquial");
        getContentPane().add(jdp);
        setJMenuBar(jmb);
        jmb.add(jmCadastro);
        jmb.add(jmMovimento);
        jmb.add(jmRelatorio);
        adicionaItensMenu();
        jmCadastro.add(jmProduto);
        jmCadastro.add(jmVisita);
        jmCadastro.add(jmUteis);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void adicionaItensMenu() {
        adicionaItemNoMenu(jmCadastro, jmiPessoa);
        adicionaItemNoMenu(jmCadastro, jmiColaborador);
        adicionaItemNoMenu(jmCadastro, jmiFornecedor);
        adicionaItemNoMenu(jmProduto, jmiProduto);
        adicionaItemNoMenu(jmProduto, jmiTipoProduto);
        adicionaItemNoMenu(jmProduto, jmiCor);
        adicionaItemNoMenu(jmVisita, jmiPadre);
        adicionaItemNoMenu(jmVisita, jmiHospital);
        adicionaItemNoMenu(jmVisita, jmiEnfermo);
        adicionaItemNoMenu(jmVisita, jmiTipoAgendamento);
        adicionaItemNoMenu(jmUteis, jmiMissa);
        adicionaItemNoMenu(jmUteis, jmiSituacaoPedido);
        adicionaItemNoMenu(jmUteis, jmiCidade);
        adicionaItemNoMenu(jmUteis, jmiEstado);
        adicionaItemNoMenu(jmUteis, jmiTipoRecebimento);
        adicionaItemNoMenu(jmUteis, jmiCeb);
        adicionaItemNoMenu(jmUteis, jmiComunidade);
    }
    
    public void adicionaItemNoMenu (JMenu menu, JMenuItem menuItem){
        menu.add(menuItem);
        menuItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jmiEstado){
            TelaCadastroEstado telaCadastroEstado = new TelaCadastroEstado();
            jdp.add(telaCadastroEstado);
        }else if(e.getSource() == jmiCidade){
            TelaCadastroCidade telaCadastroCidade = new TelaCadastroCidade();
            jdp.add(telaCadastroCidade);
        }else if(e.getSource() == jmiColaborador){
            TelaCadastroColaborador telaCadastroColaborador = new TelaCadastroColaborador();
            jdp.add(telaCadastroColaborador);
        }else if(e.getSource() == jmiPessoa){
            TelaCadastroPessoa telaCadastroPessoa = new TelaCadastroPessoa();
            jdp.add(telaCadastroPessoa);
        }else if(e.getSource() == jmiCor){
            TelaCadastroCor telaCadastroCor = new TelaCadastroCor();
            jdp.add(telaCadastroCor);
        }else if(e.getSource() == jmiProduto){
            TelaCadastroProduto telaCadastroProduto = new TelaCadastroProduto();
            jdp.add(telaCadastroProduto);
        }else if(e.getSource() == jmiTipoProduto){
            TelaCadastroTipoProduto telaCadastroTipoProduto = new TelaCadastroTipoProduto();
            jdp.add(telaCadastroTipoProduto);
        }else if(e.getSource() == jmiHospital){
            TelaCadastroHospital telaCadastroHospital = new TelaCadastroHospital();
            jdp.add(telaCadastroHospital);
        }else if(e.getSource() == jmiTipoAgendamento){
            TelaCadastroTipoAgendamento telaCadastroTipoAgendamento = new TelaCadastroTipoAgendamento();
            jdp.add(telaCadastroTipoAgendamento);
        }else if(e.getSource() == jmiMissa){
            TelaCadastroMissa telaCadastroMissa = new TelaCadastroMissa();
            jdp.add(telaCadastroMissa);
        }else if(e.getSource() == jmiSituacaoPedido){
            TelaCadastroSituacaoPedido telaCadastroSituacaoPedido = new TelaCadastroSituacaoPedido();
            jdp.add(telaCadastroSituacaoPedido);
        }else if(e.getSource() == jmiTipoRecebimento){
            TelaCadastroTipoRecebimento telaCadastroTipoRecebimento = new TelaCadastroTipoRecebimento();
            jdp.add(telaCadastroTipoRecebimento);
        }else if(e.getSource() == jmiCeb){
            TelaCadastroCeb telaCadastroCeb = new TelaCadastroCeb();
            jdp.add(telaCadastroCeb);
        }else if(e.getSource() == jmiComunidade){
            TelaCadastroComunidade telaCadastroComunidade = new TelaCadastroComunidade();
            jdp.add(telaCadastroComunidade);
        }    
    }
}

    
