package classes;

public class Turma extends Disciplina
{
    private int vagasTotais;
    private int vagasAtuais; //deve ser calculado automaticamente
    private int numero; // numero da turma mesmo
    private String disciplina; // de qual disciplina a turma faz parte
    //talvez fazer uma lista de alunos fazendo a turma
    // colocar horario, duas turmas de uma mesma disciplina não podem coexistir no mesmo horario
    // adicionar algo para mostrar se é remota ou não
    // adicionar String sala onde a turma tem as aulas
    // adicionar forma a ou b de avaliação (ver github para mais info)

    //construtores
    public Turma(int numero, int vagasTotais, String disciplina)
    {
        this.disciplina = disciplina;
        this.numero = numero;
        this.vagasAtuais = vagasTotais;
        this.vagasTotais = vagasTotais;

        System.out.println("nova turma");
        System.out.println("turma num: "+numero+" de "+disciplina);
        System.out.println("vagas totais: "+vagasTotais);
    }

    //setters e getters
    public void setVagasTotais(int vagasTotais)
    {
        this.vagasTotais = vagasTotais;
    }
    public int getVagasTotais()
    {
        return this.vagasTotais;
    }
    public int getVagasAtuais()
    {
        return this.vagasAtuais;
    }
    public void setNumero(int numero)
    {
        this.numero = numero;
    }
    public int getNumero()
    {
        return this.numero;
    }
    public void setDisciplina(String disciplina)
    {
        this.disciplina = disciplina;
    }
    public String getDisciplina()
    {
        return this.disciplina;
    }
}
