package componente;

import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class MeuCampoCnpj extends JFormattedTextField implements MeuComponente {

    private boolean obrigatorio;
    private boolean podeHabilitar;
    private String nome;
    private SimpleDateFormat sdf = new SimpleDateFormat("##.###.###/####-##");

    public MeuCampoCnpj(int tamanho, boolean obrigatorio, boolean podeHabilitar,
            String nome) {
        this.obrigatorio = obrigatorio;
        this.podeHabilitar = podeHabilitar;
        this.nome = nome;
        setColumns(tamanho);
        try {
            MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
            mf.install(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível"
                    + " iniciar MeuCampoCnpj");
        }
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean eValido() {
        String texto = getText();
        String CNPJ = (texto.replace(".", "").replace("-", "").replace("/", ""));
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {

            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {

                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
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
        return Integer.parseInt(getText());
    }

    @Override
    public void setValor(Object valor) {
        setText("" + (int) valor);
    }

    @Override
    public String getNome() {
        return nome;
    }
}
