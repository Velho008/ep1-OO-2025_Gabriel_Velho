package classes;
import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String nome;
    private int matricula;
    private List<Turma> turmas_ministradas; //turmas que o professor ministra

    //construtores
    //criar outro construtor que coloca as turmas corretamente
    public Professor(String nome, int matricula) 
    {
        this.nome = nome;
        this.matricula = matricula;
        this.turmas_ministradas = new ArrayList<>();
        
        System.out.println("um novo professor foi criado");
        System.out.println("nome: "+this.nome);
        System.out.println("matricula: "+this.matricula);
        if (this.turmas_ministradas.isEmpty())
        {
            System.out.println("ainda não ministra nenhuma turma");
        }
        else
        {
            for (Turma turma : turmas_ministradas)
            {
                System.out.println("turmas ministradas: ");
                System.out.println("turma de: "+turma.getcodigoDisciplina()+"numero: "+turma.getNumero());

            }
        }
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
