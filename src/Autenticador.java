import java.util.Scanner;

public class Autenticador {

    private static Autenticador instancia;

    private static final String USUARIO_VALIDO = "admin";
    private static final String SENHA_VALIDA = "1234";

    private String nomeUsuario;
    private boolean autenticado;

    private Autenticador() {
        this.autenticado = false;
    }

    public static Autenticador getInstance() {
        if (instancia == null) {
            instancia = new Autenticador();
        }
        return instancia;
    }

    public boolean login(Scanner scanner) {
        System.out.println("========== SISTEMA DE LOGIN ==========");

        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (usuario.isEmpty() || senha.isEmpty()) {
            System.out.println("Usuario e senha nao podem ser vazios!");
            return false;
        }

        String captcha = GeradorCaptcha.gerar();
        System.out.println("\n[Verificacao anti-bot]");
        System.out.println("Digite o codigo: " + captcha);
        System.out.print("Codigo: ");
        String resposta = scanner.nextLine();

        if (!resposta.equals(captcha)) {
            System.out.println("Codigo incorreto! Acesso negado.");
            return false;
        }

        if (!usuario.equals(USUARIO_VALIDO) || !senha.equals(SENHA_VALIDA)) {
            System.out.println("Usuario ou senha incorretos!");
            return false;
        }

        this.nomeUsuario = usuario;
        this.autenticado = true;
        System.out.println("\nLogin realizado com sucesso! Bem-vindo, " + nomeUsuario + "!");
        return true;
    }

    public void logout() {
        if (autenticado) {
            System.out.println("Usuario " + nomeUsuario + " desconectado.");
            this.autenticado = false;
            this.nomeUsuario = null;
        } else {
            System.out.println("Nenhum usuario conectado.");
        }
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
