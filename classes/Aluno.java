package classes;
import java.util.List;

public class Aluno
{
    private String curso;
    private String nome;
    private int matricula;
    private List<String> disciplinas_cursadas; //serve para checar pré-requisitos

    public Aluno(String nome, int matricula, String curso) //construtor
    {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;

        System.out.println("um novo aluno foi criado");
        System.out.println("nome: "+nome);
        System.out.println("matricula: "+matricula);
        System.out.println("curso: "+curso);
    }
    public Aluno() //construtor sem infos
    {
        System.out.println("um novo aluno sem info foi criado");
    }

    //setters e getters
    public String getCurso()
    {
        return this.curso;
    }
    public void setCurso(String curso)
    {
        this.curso = curso;
    }

    public String getNome()
    {
        return this.nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public int getMatricula()
    {
        return this.matricula;
    }
    public void setMatricula(int matricula)
    {
        this.matricula = matricula;
    }
}
