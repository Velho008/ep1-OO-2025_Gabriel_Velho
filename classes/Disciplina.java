package classes;
import java.util.List;

public class Disciplina {

    private String nome;
    private String codigo;
    private int carga_horaria;
    private List<String> pre_requisitos;

    //construtores
    public Disciplina(String nome, String codigo, int carga_horaria, List<String> pre_requisitos)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.carga_horaria = carga_horaria;
        this.pre_requisitos = pre_requisitos;

        System.out.println("uma nova disciplina foi criada: "+nome);
        System.out.println("codigo: "+codigo);
        System.out.println("carga horaria: "+carga_horaria+"Horas");
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

    public Disciplina()
    {
        System.out.println("uma nova disciplina foi criada, sem info");
    }

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
    public void setPreRequisitos(List<String> pre_requisitos)
    {
        this.pre_requisitos = pre_requisitos;
    }
    public List<String> getPreRequisitos()
    {
        return this.pre_requisitos;
    }
}
