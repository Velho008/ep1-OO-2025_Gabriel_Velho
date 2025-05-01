package classes;
import java.util.List;
import java.util.ArrayList;

public class Disciplina {

    private String nome;
    private String codigo;
    private int carga_horaria;
    private ArrayList<String> pre_requisitos;
    private int vagasTotais; // fazer setter e getter // talvez passar isso pra turma
    private int vagasAtuais; //alterar isso quando um aluno for cadastrado //fazer setter e getter
    // criar uma arraylist com os alunos fazendo a disciplina // talvez passar isso pra turma

    //construtores
    public Disciplina(String nome, String codigo, int carga_horaria, ArrayList<String> pre_requisitos, int vagasTotais)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.carga_horaria = carga_horaria;
        this.pre_requisitos = pre_requisitos;
        this.vagasTotais = vagasTotais;
        this.vagasAtuais = vagasTotais;


        System.out.println("uma nova disciplina foi criada: "+this.nome);
        System.out.println("codigo: "+this.codigo);
        System.out.println("carga horaria: "+this.carga_horaria+" Horas");
        System.out.println("vagas : "+this.vagasAtuais+"/"+this.vagasTotais);
        if (pre_requisitos.isEmpty())
        {
            System.out.println("não tem pré-requisitos");
        }
        else
        {
            System.out.println("seus pré-requisitos são: ");
            for (String pre_requisito : pre_requisitos)
            {
                System.out.println(pre_requisito);
            }
        }
    }
    public Disciplina(String nome, String codigo, int carga_horaria)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.carga_horaria = carga_horaria;

        System.out.println("uma nova disciplina foi criada: "+this.nome);
        System.out.println("codigo: "+this.codigo);
        System.out.println("carga horaria: "+this.carga_horaria+" Horas");
        System.out.println("não tem pré-requisitos");
    }
    public Disciplina()
    {
        System.out.println("uma nova disciplina foi criada, sem info");
    }

    // fazer o metodo que realiza o cadastro do aluno na disciplina
    // metodo que mostra a lista dos alunos da disciplina
    // metodo pro aluno trancar a disciplina

    //setters e getters
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public String getNome()
    {
        return this.nome;
    }
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }
    public String getCodigo()
    {
        return this.codigo;
    }
    public void setCargaHoraria(int carga_horaria)
    {
        this.carga_horaria = carga_horaria;
    }
    public int getCargaHoraria()
    {
        return this.carga_horaria;
    }
    public void setPreRequisitos(ArrayList<String> pre_requisitos)
    {
        this.pre_requisitos = pre_requisitos;
    }
    public List<String> getPreRequisitos()
    {
        return this.pre_requisitos;
    }
}
