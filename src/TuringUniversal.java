import java.util.*;


public class TuringUniversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leitura da quantidade de estados
        System.out.println("Digite a quantidade de estados da MT: ");
        int Q = scanner.nextInt();
        scanner.nextLine();
        if (Q<0 || Q > 20){
            System.out.println("Quantidade de estados incorreta");
            System.exit(1);
        }
        // Leitura do alfabeto Σ
        System.out.println("Informe o alfabeto Σ: ");
        String sigmaLine = scanner.nextLine();
        String[] sigma = sigmaLine.split(" "); //separando por espaços utilizando split

        // Leitura do alfabeto Γ
        System.out.println("Informe o alfabeto Γ: ");
        String gammaLine = scanner.nextLine();
        String[] gamma = gammaLine.split(" ");

        // Leitura do estado inicial
        System.out.println("Informe o estado inicial: ");
        int S = scanner.nextInt();
        scanner.nextLine();

        // Leitura dos estados finais
        System.out.println("Digite o(s) estados finais: ");
        String finalStatesLine = scanner.nextLine();
        String[] finalStates = finalStatesLine.split(" ");

        // Leitura do número de transições
        System.out.println("Digite a quantidade de transições ");
        int D = scanner.nextInt();
        scanner.nextLine();

        // Leitura das transições
        System.out.println("Informe as transições ");
        List<String> transitions = new ArrayList<>();
        for (int i = 0; i < D; i++) {
            String transition = scanner.nextLine();
            transitions.add(transition);
        }

        // Impressão da representação R⟨M⟩
        UniversalTuring(Q, sigma, gamma, S, finalStates, transitions);

        scanner.close();
    }

    public static void UniversalTuring(int Q, String[] sigma, String[] gamma, int S, String[] finalStates, List<String> transitions) {

        System.out.println("Estados: ");
        // Imprimir os estados em formato unário
        for (int i = 1; i <= Q; i++) {
            System.out.println(getUnary(i));
        }

        System.out.println("Alfabeto Σ: ");
        // Imprimir os símbolos de Σ em formato unário
        for (int i = 0; i < sigma.length; i++) {
            System.out.println(getUnary(i + 1));
        }

        System.out.println("Alfabeto Γ: ");
        // Imprimir os símbolos de Γ em formato unário
        for (int i = 0; i < gamma.length; i++) {
            System.out.println(getUnary(i + 1));
        }

        System.out.println("Direita e esquerda: ");
        // Imprimir as direções em formato unário
        System.out.println("1"); // Direita
        System.out.println("11"); // Esquerda

        System.out.println("Transições: ");
        // Imprimir as transições em formato unário, cada uma em uma linha
        for (String transition : transitions) {
            String[] parts = transition.split(" ");
            String EA = getUnary(Integer.parseInt(parts[0]));
            String SL = parts[1].equals("$") ? "1" : getUnary(findIndex(sigma, parts[1]) + 1);
            String PE = getUnary(Integer.parseInt(parts[2]));
            String SE = parts[3].equals("$") ? "1" : getUnary(findIndex(gamma, parts[3]) + 1);
            String Dir = parts[4].equals("D") ? "1" : "11";

            System.out.printf("%s 0 %s 0 %s 0 %s 0 %s%n", EA, SL, PE, SE, Dir);
        }

        System.out.println("Estados Finais: ");
        // Imprimir os estados finais em formato unário, separados por 0 se mais de um
        for (int i = 0; i < finalStates.length; i++) {
            System.out.print(getUnary(Integer.parseInt(finalStates[i])));
            if (i < finalStates.length - 1) {
                System.out.print("0");
            }
        }
        System.out.println();
    }

    public static String getUnary(int number) {
        StringBuilder unary = new StringBuilder();
        for (int i = 0; i < number; i++) {
            unary.append("1");
        }
        return unary.toString();
    }

    public static int findIndex(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1; // Not found
    }
}
