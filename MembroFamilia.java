public class MembroFamilia {
    private String nome;
    private double salario;

    
    public MembroFamilia(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }
}