package classes;
import java.util.List;
import java.util.ArrayList;

public class Aluno
{
    //talvez adicionar semestre e turmas que faz parte
    private String curso;
    private String nome;
    private int matricula;
    private List<String> disciplinas_cursadas; //serve para checar pré-requisitos, deve ser o codigo da disciplina
    //lembrar de quando for colocar o aluno na turma, se ele fez os requisitos

    //construtores
    //depois criar um construtor pra quando carregar um aluno ele n aparecer que foi feito
    public Aluno(String nome, int matricula, String curso, String disciplinas_cursadas)
    {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinas_cursadas = new ArrayList<>();
        for (String disciplina : disciplinas_cursadas.split(" "))
        {
            this.disciplinas_cursadas.add(disciplina);
        }

        System.out.println("um novo aluno foi criado");
        System.out.println("nome: "+nome);
        System.out.println("matricula: "+matricula);
        System.out.println("curso: "+curso);
        if (this.disciplinas_cursadas.isEmpty())
        {
            System.out.println("ainda não cursou nenhuma disciplina(calouro)");
        }
        else
        {
            System.out.println("atualmente cursou: ");
            for (String disciplina : this.disciplinas_cursadas)
            {
                System.out.println(disciplina);
            }
        }
    }
    public Aluno(String nome, int matricula, String curso)
    {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinas_cursadas = new ArrayList<>();

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
    public List<String> getDisciplinasCursadas()
    {
        return this.disciplinas_cursadas;
    }

    public void MostrarInfo() //adicionar as disciplinas cursadas
    {
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

    //parte de arquivos
    public String juntarDisciplinas(List<String> disciplinas_cursadas)
    {
        String res = String.join(" ",disciplinas_cursadas);
        return res;
    }
    @Override
    public String toString()
    {
        return this.nome +';'+ this.matricula +';'+ this.curso +';'+juntarDisciplinas(this.disciplinas_cursadas);
    }
    public static Aluno fromString(String entrada) //CRIAR UM PRAS COISAS QUE O ALUNO JA FEZ
    {
        String[] infos = entrada.split(";");
        if (infos.length == 3)
        {
            int matricula = Integer.parseInt(infos[1]); //torna a matricula de volta em int
            return new Aluno(infos[0],matricula,infos[2]);
        }
        else if (infos.length <3)
        {
            return null;
        }
        else
        {
            int matricula = Integer.parseInt(infos[1]); //torna a matricula de volta em int
            return new Aluno(infos[0],matricula,infos[2],infos[3]);
        }
    }
}
