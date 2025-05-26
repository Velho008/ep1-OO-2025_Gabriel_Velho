package classes;
import java.util.List;
import java.util.ArrayList;

public class Aluno
{
    
    private String curso;
    private String nome;
    private int matricula;
    private List<String> disciplinas_cursadas; //serve para checar pré-requisitos, deve ser o codigo da disciplina

    //construtores
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
    
    //construtor de arquivo
    public Aluno(String nome, int matricula, String curso, String disciplinas_cursadas,char arq)
    {
        // o char só serve pra puxar esse no lugar do outro
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinas_cursadas = new ArrayList<>();
        for (String disciplina : disciplinas_cursadas.split(" "))
        {
            this.disciplinas_cursadas.add(disciplina.trim());
        }
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
    public void addDisciplina(String codigo)
    {
        this.disciplinas_cursadas.add(codigo);
    }
    public void addDisciplina(Disciplina disciplina)
    {
        this.disciplinas_cursadas.add(disciplina.getCodigo());
    }
    public void removerDisciplina(String codigo)
    {
        this.disciplinas_cursadas.remove(codigo);
    }
    public void removerDisciplina(Disciplina disciplina)
    {
        this.disciplinas_cursadas.remove(disciplina.getCodigo());
    }

    public boolean fezDisciplina(String codigo)
    {
        boolean res = false;
        for (String disciplina : disciplinas_cursadas)
        {
            if (codigo.equals(disciplina))
            {
                res = true;
                break;
            }
        }
        return res;
    }

    public void MostrarInfo()
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
            for (String disciplina_cursada : this.disciplinas_cursadas)
            {
                if (!(disciplina_cursada.isEmpty() || disciplina_cursada == null))
                {
                System.out.println(disciplina_cursada);
                }
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
    public static Aluno fromString(String entrada)
    {
        String[] infos = entrada.split(";");
        if (infos.length < 3)
        {
            return null;
        }

        String nome = infos[0];
        int matricula = Integer.parseInt(infos[1]);
        String disciplinasCursadas = infos[2];
        String turmasAtuais = infos.length > 3 ? infos[3] : "";

        return new Aluno(nome, matricula, disciplinasCursadas, turmasAtuais, 'a'); //sempre puxa o correto
    }
}
