package classes;
import java.util.ArrayList;
import java.util.List;

public class Disciplina { //ainda falta muito

    private String nome;
    private String codigo;
    private int carga_horaria;
    private List<String> pre_requisitos; // os pré-requisitos devem referenciar a outras disciplinas existentes, pra quando for mostrar listar codigo, nome e carga horaria dos pré
    // criar uma arrylist com as turmas da disciplina

    //construtores
    public Disciplina(String nome, String codigo, int carga_horaria, String pre_requisitos)
    {
        this.nome = nome;
        this.codigo = codigo;
        this.carga_horaria = carga_horaria;
        this.pre_requisitos = new ArrayList<>();
        for (String requisito : pre_requisitos.split(" "))
        {
            this.pre_requisitos.add(requisito);
        }


        System.out.println("uma nova disciplina foi criada: "+this.nome);
        System.out.println("codigo: "+this.codigo);
        System.out.println("carga horaria: "+this.carga_horaria+" Horas");
        if (pre_requisitos.isEmpty())
        {
            System.out.println("não tem pré-requisitos");
        }
        else
        {
            System.out.println("seus pré-requisitos são: ");
            for (String requisito : this.pre_requisitos)
            {
                System.out.println(requisito);
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
