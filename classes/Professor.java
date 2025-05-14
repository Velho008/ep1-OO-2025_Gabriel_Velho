package classes;
import java.util.ArrayList;

public class Professor {
    private String nome;
    private int matricula;
    private ArrayList<Turma> turmas_ministradas; //turmas que o professor ministra

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
    public ArrayList<Turma> getTurmas()
    {
        return this.turmas_ministradas;
    } 
    public void addTurma(Turma turma)
    {
        this.turmas_ministradas.add(turma);
    }
    public void removerTurma(Turma turma)
    {
        this.turmas_ministradas.remove(turma);
    }
    public void MostrarInfo()
    {
        System.out.println("nome: "+this.nome);
        System.out.println("matricula: "+this.matricula);
        if (this.turmas_ministradas.isEmpty())
        {
            System.out.println("ainda não ministra nenhuma turma");
            return;
        }
        for (Turma turma : turmas_ministradas)
        {
            System.out.println("turmas ministradas: ");
            System.out.println("turma de: "+turma.getcodigoDisciplina()+"numero: "+turma.getNumero());
        }
    }

    //arquivos
    public String juntarTurmas()
    {
        ArrayList<String> res = new ArrayList<>();
        for (Turma turma : this.turmas_ministradas)
        {
            res.add(String.valueOf(turma.getNumero()));
            res.add(" ");
            res.add(turma.getcodigoDisciplina());
        }
        String fim = String.join(" ",res);
        return fim;
    }
    //falta o tostring e o fromstring
}
