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
    private String nome;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date dataDoDia = new Date();

    public MeuCampoData(int tamanho, boolean obrigatorio, boolean podeHabilitar,
            String nome) {
        this.obrigatorio = obrigatorio;
        this.nome = nome;
        setColumns(tamanho);
        try {
            MaskFormatter mf = new MaskFormatter("##/##/####");
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

    @Override
    public boolean eValido() {  
        try {
            sdf.setLenient(false);
            sdf.parse(getText());
            String sHoraDia = sdf.format(dataDoDia);
            String dataCampo = getText();
            int comp = dataCampo.compareTo(sHoraDia);
            System.out.println(comp);
            if(comp <= 0) {
                System.out.println("Data do campo menor que a hora do sistema");
                return true; 
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A data:"+getText()+" esta maio que a data atual.");
            return false;
        }
        return false;
    }

    @Override
    public boolean eVazio() {
        return getText().trim().equals("");
    }

    @Override
    public void limpar() {
        String sHoraDia = sdf.format(dataDoDia);
        setText(sHoraDia);
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(status);
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