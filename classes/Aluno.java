package classes;
import java.util.List;

public class Aluno
{
    private String curso;
    private String nome;
    private int matricula;
    private List<String> disciplinas_cursadas; //serve para checar pré-requisitos, deve ser o codigo da disciplina


    //construtores
    public Aluno(String nome, int matricula, String curso, List<String> disciplinas_cursadas)
    {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinas_cursadas = disciplinas_cursadas;

        System.out.println("um novo aluno foi criado");
        System.out.println("nome: "+nome);
        System.out.println("matricula: "+matricula);
        System.out.println("curso: "+curso);
        if (this.disciplinas_cursadas.isEmpty())
        {
            System.out.println("não cursou nenhuma disciplina");
        }
        else
        {
            System.out.println("atualmente cursou: ");
            for (String discuplina_cursada : this.disciplinas_cursadas)
            {
                System.out.println(discuplina_cursada);
            }
        }
    }
    public Aluno(String nome, int matricula, String curso)
    {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;

        System.out.println("um novo aluno foi criado");
        System.out.println("nome: "+nome);
        System.out.println("matricula: "+matricula);
        System.out.println("curso: "+curso);
        System.out.println("não cursou nenhuma disciplina");
    }
    public Aluno(String nome) //construtor sem infos
    {
        System.out.println("um novo aluno "+nome+" sem info foi criado");
        this.nome = nome;
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
