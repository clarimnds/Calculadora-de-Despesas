import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class CalculadoraDespesas {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Calculadora de Despesas Proporcionais ---");

        List<MembroFamilia> membros = new ArrayList<>();
        System.out.print("Quantas pessoas irão dividir as despesas? ");
        int numeroDeMembros = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numeroDeMembros; i++) {
            System.out.print("Digite o nome da pessoa " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            System.out.print("Digite o salário de " + nome + ": ");
            double salario = scanner.nextDouble();
            scanner.nextLine(); 
            membros.add(new MembroFamilia(nome, salario));
        }

        List<Despesa> despesas = new ArrayList<>();
        while (true) {
            System.out.print("\nDigite o nome da despesa (ou 'fim' para calcular): ");
            String nomeDespesa = scanner.nextLine();
            if (nomeDespesa.equalsIgnoreCase("fim")) {
                break;
            }
            System.out.print("Digite o valor de " + nomeDespesa + ": ");
            double valorDespesa = scanner.nextDouble();
            scanner.nextLine();
            despesas.add(new Despesa(nomeDespesa, valorDespesa));
        }


        double rendaTotal = 0.0;
        for (MembroFamilia membro : membros) {
            rendaTotal += membro.getSalario();
        }


        if (rendaTotal == 0) {
            System.out.println("\nA renda total é zero. Não é possível dividir as despesas.");
            scanner.close();
            return;
        }

        System.out.println("\n--- RESULTADO DA DIVISÃO ---");
        System.out.printf("Renda total da família: R$ %.2f\n\n", rendaTotal);
        
        double[] totalAPagarPorMembro = new double[membros.size()];

        for (Despesa despesa : despesas) {
            System.out.println("----- Despesa: " + despesa.getNome() + " (Total: R$ " + String.format("%.2f", despesa.getValor()) + ") -----");
            for (int i = 0; i < membros.size(); i++) {
                MembroFamilia membro = membros.get(i);
                double proporcao = membro.getSalario() / rendaTotal;
                double valorAPagar = despesa.getValor() * proporcao;
                totalAPagarPorMembro[i] += valorAPagar;
                
                System.out.printf("%s (salário R$ %.2f) paga: R$ %.2f\n", membro.getNome(), membro.getSalario(), valorAPagar);
            }
            System.out.println(); 
        }
        
        System.out.println("--- TOTAL A PAGAR POR PESSOA ---");
        for (int i = 0; i < membros.size(); i++) {
            MembroFamilia membro = membros.get(i);
            System.out.printf("%s deve pagar no total: R$ %.2f\n", membro.getNome(), totalAPagarPorMembro[i]);
        }


        scanner.close();
    }
}
