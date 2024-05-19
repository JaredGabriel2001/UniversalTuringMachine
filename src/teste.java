import java.util.*;

public class teste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leitura da quantidade de estados
        int quant = scanner.nextInt();
        scanner.nextLine();

        // Leitura do alfabeto Σ
        String sigmaLine = scanner.nextLine();
        String[] sigma = sigmaLine.split(" "); //separando por espaços utilizando split

        // Leitura do alfabeto Γ
        String gammaLine = scanner.nextLine();
        String[] gamma = gammaLine.split(" ");
        gamma = concatenateGammaAndSigma(gamma, sigma);

        // Leitura do estado inicial
        int inicial = scanner.nextInt();
        scanner.nextLine();

        // Leitura dos estados finais
        String finalStatesLine = scanner.nextLine();
        String[] finalStates = finalStatesLine.split(" ");

        // Leitura do número de transições
        int trans = scanner.nextInt();
        scanner.nextLine();

        // Leitura das transições
        List<String> transitions = new ArrayList<>();
        for (int i = 0; i < trans; i++) {
            String transition = scanner.nextLine();
            transitions.add(transition);
        }

        // Impressão da representação R⟨M⟩
        universalTuring(quant, sigma, gamma, inicial, finalStates, transitions);
        scanner.close();
    }
    public static String[] concatenateGammaAndSigma(String[] gamma, String[] sigma) {
        String[] combined = new String[gamma.length + sigma.length];
        System.arraycopy(gamma, 0, combined, 0, gamma.length);
        System.arraycopy(sigma, 0, combined, gamma.length, sigma.length);
        return combined;
    }

    public static void universalTuring(int Q, String[] sigma, String[] gamma, int S, String[] finalStates, List<String> transitions) {
        StringBuilder fullRepresentation = new StringBuilder();

        // Imprimir os estados em formato unário
        for (int i = 1; i <= Q; i++) {
            String unary = getUnary(i);
            System.out.println(unary);
            fullRepresentation.append(unary).append(" ");
        }

        // Imprimir os símbolos de sigma e gamma concatenados em formato unário
        for (int i = 0; i < gamma.length; i++) {
            String unary = getUnary(i + 1);
            System.out.println(unary);
            fullRepresentation.append(unary).append(" ");
        }

        // Imprimir as direções em formato unário
        System.out.println("1"); // Direita
        fullRepresentation.append("1").append(" ");
        System.out.println("11"); // Esquerda
        fullRepresentation.append("11").append(" ");

        // Imprimir as transições em formato unário, cada uma em uma linha
        for (String transition : transitions) {
            String[] parts = transition.split(" ");
            String EA = getUnary(Integer.parseInt(parts[0]));
            String SL = getUnary(findIndex(gamma, parts[1]) + 1);
            String PE = getUnary(Integer.parseInt(parts[2]));
            String SE = getUnary(findIndex(gamma, parts[3]) + 1);
            String Dir = parts[4].equals("D") ? "1" : "11";

            String transitionUnary = String.format("%s0%s0%s0%s0%s", EA, SL, PE, SE, Dir);
            System.out.println(transitionUnary);
            fullRepresentation.append(transitionUnary).append("00");
        }

        // Remover o último " 00 " desnecessário
        if (transitions.size() > 0) {
            fullRepresentation.setLength(fullRepresentation.length() - 4);
        }

        // Imprimir a representação completa numa única linha
        System.out.println();
        System.out.println(fullRepresentation.toString());
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