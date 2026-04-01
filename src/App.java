import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SistemaLogin sistema = SistemaLogin.getInstancia();

        // Demonstra que e a mesma instancia (Singleton)
        SistemaLogin sistema2 = SistemaLogin.getInstancia();
        System.out.println("Mesma instancia? " + (sistema == sistema2));
        System.out.println();

        int tentativas = 3;

        while (tentativas > 0 && !sistema.isAutenticado()) {
            boolean sucesso = sistema.login(scanner);

            if (!sucesso) {
                tentativas--;
                if (tentativas > 0) {
                    System.out.println("Tentativas restantes: " + tentativas);
                    System.out.println();
                }
            }
        }

        if (!sistema.isAutenticado()) {
            System.out.println("Numero maximo de tentativas atingido. Sistema bloqueado.");
        } else {
            System.out.println("\n--- Area restrita ---");
            System.out.println("Usuario logado: " + sistema.getNomeUsuario());
            System.out.print("\nDeseja sair? (s/n): ");
            String opcao = scanner.nextLine();

            if (opcao.equalsIgnoreCase("s")) {
                sistema.logout();
            }
        }

        scanner.close();
    }
}
