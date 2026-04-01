import java.util.Random;

public class GeradorCaptcha {

    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
    private static final int TAMANHO = 6;

    public static String gerar() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < TAMANHO; i++) {
            int indice = random.nextInt(CARACTERES.length());
            captcha.append(CARACTERES.charAt(indice));
        }

        return captcha.toString();
    }
}
