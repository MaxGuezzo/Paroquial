package componente;

import java.util.Date;
import java.text.SimpleDateFormat;
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
        
        String data = getText();
        
        try {
            sdf.setLenient(false);
            sdf.parse(getText());
            String sHoraDia = sdf.format(dataDoDia);
            String dataCampo = getText();
            int comp = sHoraDia.compareTo(dataCampo);
            System.out.println(comp);
            if(comp < 0) {
                
                System.out.println("sHoraDia menor que DataCampo");
            }
  
            return true;  
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
        setText("");
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(status);
    }

    @Override
    public Object getValor() {
        return getText();
    }

    @Override
    public void setValor(Object valor) {
        setText((String) valor);
    }

    @Override
    public String getNome() {
        return nome;
    }
}