package classes;

public class Boletim
{
    //todos esses são FINAL, portanto não tem set
    private int matriculaAluno; // a matricula do aluno pode ser editada
    private final int matriculaProf; // ao apagar professor, o boletim some
    private final String disciplina; // ao apagar disciplina, o boletim some
    private final int semestre;
    private final int turma; // ao apagar turma, o boletim some
    private final int cargaHoraria;
    private final String sala;
    
    private final int presenca;
    private final char metodoAvaliacao;
    private final float p1;
    private final float p2;
    private final float p3;
    private final float lista;
    private final float seminario;
    private int passou; // 0 passa, 1 reprovou por falta, 2 reprovou por nota, 3 reprovou por falta e nota
    private final float mediaFinal;


    //construtores
    public Boletim(int matriculaAluno, Turma turma, int presenca, char metodoAvaliacao, float p1, float p2, float p3, float lista, float seminario)
    {
        this.passou = 0;
        this.matriculaAluno = matriculaAluno;
        this.matriculaProf = turma.getMatriculaProf();
        this.disciplina = turma.getCodigoDisciplina();
        this.semestre = turma.getSemestre();
        this.turma = turma.getNumero();
        this.cargaHoraria = SIGAA2.BuscarDisciplina(turma.getCodigoDisciplina()).getCargaHoraria();
        this.sala = turma.getSala();
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
    public Boletim(int matriculaAluno, int numTurma, int presenca, char metodoAvaliacao, float p1, float p2, float p3, float lista, float seminario, String codDisciplina)
    {
        this.passou = 0;
        this.matriculaAluno = matriculaAluno;
        Turma turma = SIGAA2.BuscarTurma(numTurma, codDisciplina);
        this.cargaHoraria = SIGAA2.BuscarDisciplina(turma.getCodigoDisciplina()).getCargaHoraria();
        this.sala = turma.getSala();
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

    }
    public void MostrarInfoExpandido()
    {
        System.out.println("========================================================");
        Aluno aluno = SIGAA2.BuscarAluno(matriculaAluno);
        Professor professor = SIGAA2.BuscarProfessor(matriculaProf);
        Disciplina disciplina = SIGAA2.BuscarDisciplina(this.disciplina);
        System.out.println("aluno: "+aluno.getNome()+'/'+aluno.getMatricula());
        System.out.println("turma: "+this.turma+" da disciplina: "+disciplina.getNome()+'/'+disciplina.getCodigo()+" ministrada pelo prof: "+professor.getNome()+'/'+professor.getMatricula());
        System.out.println("semestre: "+this.semestre);
        System.out.println("carga horaria da disciplina: "+this.cargaHoraria);
        if (this.sala.isEmpty())
        {
            System.out.println("turma online");
        }
        else
        {
            System.out.println("sala: "+this.sala);
        }
        System.out.println("a presença do aluno foi de: "+this.presenca+'%');

        if (aluno instanceof AlunoEspecial)
        {
            if (this.presenca > 75)
            {
                System.out.println("aluno especial aprovado na disciplina");
            }
            else
            {
                System.out.println("aluno especial reprovado por falta");
            }
            System.out.println("========================================================");
            return;
        }
        System.out.println("NOTAS p1: "+this.p1+" p2: "+this.p2+" p3: "+this.p3+" lista: "+this.lista+" seminario: "+this.seminario);
        System.out.println("media final: "+this.mediaFinal);
        switch (passou)
        {
            case 1:
                System.out.println("aluno reprovou por falta");
                break;
            case 2:
                System.out.println("aluno reprovou por nota");
                break;
            case 3:
                System.out.println("aluno reprovou por falta e por nota");
                break;
            case 0:
                System.out.println("aluno aprovado na disciplina");
                break;
            default:
                System.out.println("algum erro ao saber se passou ou não");
                break;
        }
        System.out.println("========================================================");
    }
    public void MostrarInfoSimples()
    {
        System.out.println("========================================================");
        Aluno aluno = SIGAA2.BuscarAluno(matriculaAluno);
        Disciplina disciplina = SIGAA2.BuscarDisciplina(this.disciplina);
        System.out.println("aluno: "+aluno.getNome()+'/'+aluno.getMatricula());
        System.out.println("turma: "+this.turma+" da disciplina: "+disciplina.getNome()+'/'+disciplina.getCodigo());
        System.out.println("semestre: "+this.semestre);
        System.out.println("a presença do aluno foi de: "+this.presenca+'%');
        if (aluno instanceof AlunoEspecial)
        {
            if (this.presenca > 75)
            {
                System.out.println("aluno especial aprovado na disciplina");
            }
            else
            {
                System.out.println("aluno especial reprovado por falta");
            }
            System.out.println("========================================================");
            return;
        }
        System.out.println("NOTAS p1: "+this.p1+" p2: "+this.p2+" p3: "+this.p3+" lista: "+this.lista+" seminario: "+this.seminario);
        System.out.println("media final: "+this.mediaFinal);
        switch (passou)
        {
            case 1:
                System.out.println("aluno reprovou por falta");
                break;
            case 2:
                System.out.println("aluno reprovou por nota");
                break;
            case 3:
                System.out.println("aluno reprovou por falta e por nota");
                break;
            case 0:
                System.out.println("aluno aprovado na disciplina");
                break;
            default:
                System.out.println("algum erro ao saber se passou ou não");
                break;
        }
        System.out.println("========================================================");
    }
    //construtor de arquivo

    //getters
    public int getMatriculaAluno()
    {
        return this.matriculaAluno;
    }
    public void setMatriculaAluno(int matricula)
    {
        this.matriculaAluno = matricula;
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
    public float getP1()
    {
        return this.p1;
    }
    public float getP2()
    {
        return this.p2;
    }
    public float getP3()
    {
        return this.p3;
    }
    public float getLista()
    {
        return this.lista;
    }
    public float getSeminario()
    {
        return this.seminario;
    }
    public int getPassou()
    {
        return this.passou;
    }
    public float getMediaFinal()
    {
        return this.mediaFinal;
    }


    //parte de arquivos
    @Override
    public String toString()
    {
        return String.valueOf(this.getMatriculaAluno())+';'+
               String.valueOf(this.getTurma())+';'+
               String.valueOf(this.getPresenca())+';'+
               this.getMetodoAvaliacao()+';'+
               String.valueOf(this.getP1())+';'+
               String.valueOf(this.getP2())+';'+
               String.valueOf(this.getP3())+';'+
               String.valueOf(this.getLista())+';'+
               String.valueOf(this.getSeminario())+';'+
               this.getDisciplina();
    }
    public static Boletim fromString(String entrada)
    {
        String[] dados = entrada.split(";");
        int matriculaAluno = Integer.parseInt(dados[0]);
        int numTurma = Integer.parseInt(dados[1]);
        int presenca = Integer.parseInt(dados[2]);
        char metodoAvaliacao = dados[3].charAt(0);
        float p1 = Float.parseFloat(dados[4]);
        float p2 = Float.parseFloat(dados[5]);
        float p3 = Float.parseFloat(dados[6]);
        float lista = Float.parseFloat(dados[7]);
        float seminario = Float.parseFloat(dados[8]);
        try
        {
            return new Boletim(matriculaAluno, numTurma, presenca, metodoAvaliacao, p1, p2, p3, lista, seminario, dados[9]);
        } catch (Exception e)
        {
            System.out.println("erro desconhecido ao carregar boletim do aluno "+matriculaAluno);
            return null;
        }
        
    }

}
