import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Autenticador autenticador = Autenticador.getInstance();

        // Demonstra que e a mesma instancia (Singleton)
        Autenticador autenticador2 = Autenticador.getInstance();
        System.out.println("Mesma instancia? " + (autenticador == autenticador2));
        System.out.println("[Dica] Credenciais validas: usuario=admin / senha=1234");
        System.out.println();

        int tentativas = 3;

        while (tentativas > 0 && !autenticador.isAutenticado()) {
            boolean sucesso = autenticador.login(scanner);

            if (!sucesso) {
                tentativas--;
                if (tentativas > 0) {
                    System.out.println("Tentativas restantes: " + tentativas);
                    System.out.println();
                }
            }
        }

        if (!autenticador.isAutenticado()) {
            System.out.println("Numero maximo de tentativas atingido. Sistema bloqueado.");
        } else {
            System.out.println("\n--- Area restrita ---");
            System.out.println("Usuario logado: " + autenticador.getNomeUsuario());
            System.out.print("\nDeseja sair? (s/n): ");
            String opcao = scanner.nextLine();

            if (opcao.equalsIgnoreCase("s")) {
                autenticador.logout();
            }
        }

        scanner.close();
    }
}
