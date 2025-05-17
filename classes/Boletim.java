package classes;
import java.util.List;
import java.util.ArrayList;

public class Boletim
{
    // talvez mudar pro boletim ter todas as turmas de um mesmo semestre que o aluno pegar, e só falar se e como ele reprovou em cada uma, com qual prof pegou...
    // qual disciplina era e não salvar p1,p2,p3,lista,seminario,presenca,metodoAvaliacao
    //vai ter boletins, dentro vai ter uma pasta pra cada semestre, dentro vai ter uma pasta pra cada disciplina, dentro vai ter um arquivo pra cada aluno
    //todos esses são FINAL, portanto não tem set
    private int matriculaAluno;
    private int matriculaProf;
    private String disciplina;
    private int semestre;
    private String turma;
    
    private int presenca;
    private char metodoAvaliacao;
    private int p1;
    private int p2;
    private int p3;
    private int lista;
    private int seminario;
    private boolean passou;

    //construtores

    //getters
    public int getMatriculaAluno()
    {
        return this.matriculaAluno;
    }
    public int getMatriculaProf()
    {
        return this.matriculaProf;
    }
    public String getDisciplina()
    {
        return this.disciplina;
    }
    public int getSemestre()
    {
        return this.semestre;
    }
    public String getTurma()
    {
        return this.turma;
    }
    public int getPresenca()
    {
        return this.presenca;
    }
    public char getMetodoAvaliacao()
    {
        return this.metodoAvaliacao;
    }
    public int getP1()
    {
        return this.p1;
    }
    public int getP2()
    {
        return this.p2;
    }
    public int getP3()
    {
        return this.p3;
    }
    public int getLista()
    {
        return this.lista;
    }
    public int getSeminario()
    {
        return this.seminario;
    }
    public boolean passou()
    {
        return this.passou;
    }


    //parte de arquivos


}
