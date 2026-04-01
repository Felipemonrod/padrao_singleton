import java.util.Scanner;

public class SistemaLogin {

    private static SistemaLogin instancia;

    private String nomeUsuario;
    private String senha;
    private boolean autenticado;

    private SistemaLogin() {
        this.autenticado = false;
    }

    public static SistemaLogin getInstancia() {
        if (instancia == null) {
            instancia = new SistemaLogin();
        }
        return instancia;
    }

    public boolean login(Scanner scanner) {
        System.out.println("========== SISTEMA DE LOGIN ==========");

        System.out.print("Usuario: ");
        this.nomeUsuario = scanner.nextLine();

        System.out.print("Senha: ");
        this.senha = scanner.nextLine();

        if (nomeUsuario.isEmpty() || senha.isEmpty()) {
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
            this.autenticado = false;
            return false;
        }

        this.autenticado = true;
        System.out.println("\nLogin realizado com sucesso! Bem-vindo, " + nomeUsuario + "!");
        return true;
    }

    public void logout() {
        if (autenticado) {
            System.out.println("Usuario " + nomeUsuario + " desconectado.");
            this.autenticado = false;
            this.nomeUsuario = null;
            this.senha = null;
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
