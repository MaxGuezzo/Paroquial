package componente;

import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class MeuCampoCpf extends JFormattedTextField implements MeuComponente {

    private boolean obrigatorio;
    private boolean podeHabilitar;
    private String nome;

    public MeuCampoCpf(int tamanho, boolean obrigatorio, boolean podeHabilitar,
            String nome) {
        this.obrigatorio = obrigatorio;
        this.podeHabilitar = podeHabilitar;
        this.nome = nome;
        setColumns(tamanho);
        try {
            MaskFormatter mf = new MaskFormatter("###.###.###-##");
            mf.install(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível"
                    + " iniciar MeuCampoCpf");
        }
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean eValido() {

        String texto = getText();
        String CPF = (texto.replace(".", "").replace("-", ""));

        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {

                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    @Override
    public boolean eVazio() {
        return false;
    }

    @Override
    public void limpar() {
        setText("");
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(podeHabilitar && status);
    }

    @Override
    public Object getValor() {
        return getText();
    }

    @Override
    public void setValor(Object valor) {
        setText("" + valor);
    }

    @Override
    public String getNome() {
        return nome;
    }
}
