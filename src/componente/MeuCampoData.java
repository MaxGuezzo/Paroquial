package componente;

import java.util.Date;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class MeuCampoData extends JFormattedTextField implements MeuComponente {
    private boolean obrigatorio;
    private boolean podeHabilitar;
    private String nome;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Date dataAtual = new Date();

    public MeuCampoData(int tamanho, boolean obrigatorio, boolean podeHabilitar,
            String nome) {
        this.obrigatorio = obrigatorio;
        this.podeHabilitar = podeHabilitar;
        this.nome = nome;
        setColumns(tamanho);
        try {
            MaskFormatter mf = new MaskFormatter("####/##/##");
            mf.install(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível"
                    + " iniciar MeuCampoData");
        }
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    public boolean verificaData() {
        String dataCampo = getText();
        String dataDia = sdf.format(dataAtual);
        int data = dataCampo.compareTo(dataDia);
        if (data <= 0) {
            System.out.println("Data validada");
            return true;
        } else {
            
            return false;
        }
    }
    
    
    
    @Override
    public boolean eValido() {         
        try {
                sdf.setLenient(false);
                sdf.parse(getText());
                System.out.println(verificaData());
                if(verificaData()== true){
                    return true;
                }else{
                   return false; 
                }  
        } catch (Exception e) { 
            return false;
        }
        
    }

    @Override
    public boolean eVazio() {
        return getText().trim().equals("");
    }

    @Override
    public void limpar() {
//        if(podeHabilitar == false){
//            String sDataDia = sdf.format(dataAtual);
//            setText(sDataDia);
//        }else{
//            setText("");
//        }
//        
    }

    @Override
    public void habilitar(boolean status ) {
        setEnabled(status && podeHabilitar);
    }

    @Override
    public Object getValor() {
        try {
            java.sql.Date data = new java.sql.Date(sdf.parse(getText()).getTime());
            return data;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel converter o campo data");
        }
        return false;
    }

    @Override
    public void setValor(Object valor) {
        String data = sdf.format(valor);
        setText (data);
    }

    @Override
    public String getNome() {
        return nome;
    }
}