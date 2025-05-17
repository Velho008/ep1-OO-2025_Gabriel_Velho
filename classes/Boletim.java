package classes;
import java.util.List;
import java.util.ArrayList;

public class Boletim
{
    //fazer o que mostra info do boletim, separado entre com ou sem info da turma
    //
    //boletim vai ser salvo em um banco_de_dados/boletins/"matricula aluno"/semestre +'num semestre'
    //todos esses são FINAL, portanto não tem set
    private final int matriculaAluno;
    private final int matriculaProf;
    private final String disciplina;
    private final int semestre;
    private final int turma;
    
    private final int presenca;
    private final char metodoAvaliacao;
    private final int p1;
    private final int p2;
    private final int p3;
    private final int lista;
    private final int seminario;
    private int passou; // 0 passa, 1 reprovou por falta, 2 reprovou por nota, 3 reprovou por falta e nota
    private final int mediaFinal;


    //construtores
    public Boletim(int matriculaAluno, Turma turma, int presenca, char metodoAvaliacao, int p1, int p2, int p3, int lista, int seminario)
    {
        this.passou = 0;
        this.matriculaAluno = matriculaAluno;
        this.matriculaProf = turma.getMatriculaProf();
        this.disciplina = turma.getCodigoDisciplina();
        this.semestre = turma.getSemestre();
        this.turma = turma.getNumero();
        this.presenca = presenca;
        this.metodoAvaliacao = turma.getMetodoAvaliacao();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.lista = lista;
        this.seminario = seminario;

        switch (metodoAvaliacao)
        {
            case 'a':
                mediaFinal = (p1+p2+p3+lista+seminario)/5;
                break;
            case 'b':
                mediaFinal = (p1+(p2*2)+(p3*3)+lista+seminario)/8;
                break;
            default:
                mediaFinal = 0;
                System.out.println("erro no metodo de avaliação da turma");
                break;
        }
        if ((mediaFinal<5))
        {
            passou +=2;
        }
        if (presenca<75)
        {
            passou++;
        }

        System.out.println("novo boletim do aluno de matricula: "+this.matriculaAluno+ " criado");

    }
    
    //construtor de arquivo

    //getters
    public int getMatriculaAluno()
    {
        return this.matriculaAluno;
    }
    public int getMatriculaProf()
    {
        return this.matriculaProf;
    }
    public String getDisciplina()
    {
        return this.disciplina;
    }
    public int getSemestre()
    {
        return this.semestre;
    }
    public int getTurma()
    {
        return this.turma;
    }
    public int getPresenca()
    {
        return this.presenca;
    }
    public char getMetodoAvaliacao()
    {
        return this.metodoAvaliacao;
    }
    public int getP1()
    {
        return this.p1;
    }
    public int getP2()
    {
        return this.p2;
    }
    public int getP3()
    {
        return this.p3;
    }
    public int getLista()
    {
        return this.lista;
    }
    public int getSeminario()
    {
        return this.seminario;
    }
    public int passou()
    {
        return this.passou;
    }


    //parte de arquivos


}
