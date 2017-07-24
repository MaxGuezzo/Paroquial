package componente;

import javax.swing.JButton;

public class MeuJButton extends JButton implements MeuComponente {
    private boolean podeHabilitar;
    private String nome;
    
    

    public MeuJButton(boolean podeHabilitar, String nome) {
        this.podeHabilitar = podeHabilitar;
        this.nome = nome;
    
    }

    @Override
    public boolean eObrigatorio() {
        return true;
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public boolean eVazio() {
        return false;
    }

    @Override
    public void limpar() {
        
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(podeHabilitar && status);
    }

    @Override
    public Object getValor() {
        return null;
    }

    @Override
    public void setValor(Object valor) {
        
    }

    @Override
    public String getNome() {
        return nome;
    }
}
