package componente;

import java.util.List;
import javax.swing.JComboBox;

public class MeuCampoComboBox extends JComboBox implements MeuComponente {
   private boolean obrigatorio;
   private String nome;
   private String[] dados;
   
   public MeuCampoComboBox(boolean obrigatorio, String[] dados, String nome) {
       this.obrigatorio = obrigatorio;
       this.dados = dados;
       this.nome = nome;
       preencher();
   }
   
   public void preencher() {
       removeAllItems();
       addItem("<Selecione>");
       for (int i = 0; i < dados.length; i++) {
           addItem(dados[i]);
       }
   }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean eValido() {
        
        if(getSelectedIndex()>0) return true;
            
        
        return false;
    }
    @Override
    public boolean eVazio() {
        return getSelectedIndex() < 0;
    }

    @Override
    public void limpar() {
        setSelectedIndex(0);
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(status);
    }

    @Override
    public Object getValor() {
        return dados[getSelectedIndex()];
    }

    @Override
    public void setValor(Object valor) {
        for (int i = 0; i < dados.length; i++) {
            if (dados[i].equals((String) valor)) {
                setSelectedIndex(i);
                return;
            }
        }
        setSelectedIndex(-1);
    }

    @Override
    public String getNome() {
        return nome;
    }
}