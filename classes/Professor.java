package classes;
import java.util.ArrayList;

//fazer outro construtor que puxa de arquivo
//tostring e fromstring
public class Professor {
    private String nome;
    private int matricula;
    private ArrayList<String> turmas_ministradas; //codigo das turmas que o prof ministra

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
            System.out.println("turmas ministradas: ");
            for (String codTurma : turmas_ministradas)
            {
                Turma turma = SIGAA2.BuscarTurma(codTurma);
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
    public ArrayList<String> getTurmas()
    {
        return this.turmas_ministradas;
    } 
    public void addTurma(String codTurma)
    {
        this.turmas_ministradas.add(codTurma);
    }
    public void removerTurma(String codTurma)
    {
        this.turmas_ministradas.remove(codTurma);
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
        System.out.println("turmas ministradas: ");
        for (String codTurma : turmas_ministradas)
        {
            Turma turma = SIGAA2.BuscarTurma(codTurma);
            System.out.println("turma de: "+turma.getcodigoDisciplina()+"numero: "+turma.getNumero());

        }
    }


    //arquivos
    public String juntarTurmas()
    {
        String fim = String.join(" ",this.turmas_ministradas);
        return fim;
    }
}
