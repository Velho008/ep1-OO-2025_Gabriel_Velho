package classes;

import java.util.ArrayList;

// no set vagas totais, recontar quantas vagas atuais tem
public class Turma 
{
    private int vagasTotais;
    private int vagasAtuais;
    private int numero; // numero da turma mesmo
    private String codigoDisciplina; // codigo de qual disciplina a turma faz parte
    private ArrayList<Integer> alunos; //matricula dos alunos da turma
    private int matriculaProf; //matricula do professor que ministra as aulas
    private String sala; // para ser remota, basta não adicionar sala
    private char metodoAvaliacao; //a ou b, ver github
    private int horario; //toda aula dura 2 horas, exemplo: 8 (começa 8 da manha) e acaba as 10
    private int semestre; //de qual semestre a turma é
    private String codigoDaTurma; //FACILITA BUSCAR A TURMA, é criado de forma automatica

    //construtores
    public Turma(int matriculaProf, String sala, char metodoAvaliacao, int horario,int numero, int vagasTotais, int semestre, String codigoDisciplina)
    {
        this.matriculaProf = matriculaProf;
        this.sala = sala;
        this.metodoAvaliacao = metodoAvaliacao;
        this.horario = horario;
        this.codigoDisciplina = codigoDisciplina;
        this.numero = numero;
        this.vagasAtuais = vagasTotais;
        this.vagasTotais = vagasTotais;
        this.semestre = semestre;
        this.alunos = new ArrayList<>();
        this.codigoDaTurma = codigoDisciplina+numero;
        System.out.println("codigo da turma: "+this.codigoDaTurma);
        System.out.println("nova turma ministrada pelo prof de matricula: "+this.matriculaProf);
        System.out.println("turma num: "+numero+" da disciplina de codigo: "+codigoDisciplina+" do semestre: "+semestre);
        System.out.println("vagas totais: "+vagasTotais);
        System.out.println("metodo "+this.metodoAvaliacao+" de avaliação");
        System.out.println("horario inicial: "+this.horario+':'+"00");
        System.out.println("todas as aulas tem duração de 2 horas");

        if (this.sala.isEmpty())
        {
            System.out.println("turma online");
        }
        else
        {
            System.out.println("ocorre na sala: "+this.sala);
        }
    }
    public Turma(int matriculaProf, char metodoAvaliacao, int horario,int numero, int vagasTotais, int semestre, String codigoDisciplina)
    {
        this.matriculaProf = matriculaProf;
        this.sala = "";
        this.metodoAvaliacao = metodoAvaliacao;
        this.horario = horario;
        this.codigoDisciplina = codigoDisciplina;
        this.numero = numero;
        this.vagasAtuais = vagasTotais;
        this.vagasTotais = vagasTotais;
        this.semestre = semestre;
        this.alunos = new ArrayList<>();
        this.codigoDaTurma = codigoDisciplina+numero;
        System.out.println("codigo da turma: "+this.codigoDaTurma);
        System.out.println("nova turma ministrada pelo prof de matricula: "+this.matriculaProf);
        System.out.println("turma num: "+numero+" da disciplina de codigo: "+codigoDisciplina + " do semestre: "+semestre);
        System.out.println("vagas totais: "+vagasTotais);
        System.out.println("metodo "+this.metodoAvaliacao+" de avaliação");
        System.out.println("horario inicial: "+this.horario+':'+"00");
        System.out.println("todas as aulas tem duração de 2 horas");
        System.out.println("turma online");
    }
    //construtor pra quando puxar de arquivo
    public Turma(int matriculaProf, String sala, char metodoAvaliacao, int horario, int numero, int vagasTotais,int semestre, String codigoDisciplina, String matriculaAlunos)
    {
        this.matriculaProf = matriculaProf;
        this.sala = sala;
        this.horario = horario;
        this.numero = numero;
        this.vagasAtuais = vagasTotais;
        this.vagasTotais = vagasTotais;
        this.codigoDisciplina = codigoDisciplina;
        this.metodoAvaliacao = metodoAvaliacao;
        this.semestre = semestre;
        this.alunos = new ArrayList<>();
        this.codigoDaTurma = codigoDisciplina+numero;
        for (String aluno : matriculaAlunos.split(" "))
        {
            if (!aluno.isEmpty()) //não gera problemas quando não tiver alunos
            {
                int matricula = Integer.parseInt(aluno);
                addAluno(matricula);
            }
            
        }
    }

    public void atualizarCodigoTurma()
    {
        this.codigoDaTurma = codigoDisciplina+numero;
    }
    public String getCodigoTurma()
    {
        this.codigoDaTurma = codigoDisciplina+numero;
        return codigoDaTurma;
    }
    //setters e getters
    public void setSemestre(int semestre)
    {
        this.semestre = semestre;
    }
    public int getSemestre()
    {
        return this.semestre;
    }
    public void setMatriculaProf(int matricula)
    {
        this.matriculaProf = matricula;
    }
    public int getMatriculaProf()
    {
        return this.matriculaProf;
    }
    public void setSala(String sala)
    {
        this.sala = sala;
    }
    public String getSala()
    {
        return this.sala;
    }
    public void setMetodoAvaliacao(char metodo)
    {
        this.metodoAvaliacao = metodo;
    }
    public char getMetodoAvaliacao()
    {
        return this.metodoAvaliacao;
    }
    public void setHorario(int horario)
    {
        this.horario = horario;
    }
    public int getHorario()
    {
        return this.horario;
    }
    public void setVagasTotais(int vagasTotais)
    {
        this.vagasTotais = vagasTotais;
        //talvez recontar quantas vagas tem ao fazer isso, pois pode já ter alunos
    }
    public int getVagasTotais()
    {
        return this.vagasTotais;
    }
    public int getVagasAtuais()
    {
        return this.vagasAtuais;
    }
    public void setNumero(int numero)
    {
        this.numero = numero;
    }
    public int getNumero()
    {
        return this.numero;
    }
    public void setCodigoDisciplina(String codigoDisciplina)
    {
        this.codigoDisciplina = codigoDisciplina;
    }
    public String getCodigoDisciplina()
    {
        return this.codigoDisciplina;
    }
    public ArrayList<Integer> getAlunos()
    {
        return this.alunos;
    }
    public void addAluno(int aluno)
    {
        alunos.add(aluno);
        this.vagasAtuais-=1;
    }
    public void addAluno(Aluno aluno)
    {
        alunos.add(aluno.getMatricula());
        this.vagasAtuais-=1;
    }
    public void removerAluno(int matricula) 
    {
        alunos.remove(Integer.valueOf(matricula));
        this.vagasAtuais+=1;
    }
    public void removerAluno(Aluno alunoEntrada) 
    {   
        alunos.remove(Integer.valueOf(alunoEntrada.getMatricula()));
        this.vagasAtuais+=1;
    }
    

    public void ListarAlunos()
    {
        for (int matricula : alunos)
        {
            System.out.println("matricula: "+matricula);
        }
    }

    public void MostrarInfo()
    {
        System.out.println("codigo da turma: "+this.codigoDaTurma);
        System.out.println("turma ministrada pelo prof de matricula: "+this.matriculaProf);
        System.out.println("turma num: "+numero+" da disciplina de codigo: "+codigoDisciplina+" do semestre: "+semestre);
        System.out.println("vagas: "+this.vagasAtuais+'/'+this.vagasTotais);
        System.out.println("metodo "+this.metodoAvaliacao+" de avaliação");
        System.out.println("horario inicial: "+this.horario+':'+00);
        System.out.println("todas as aulas tem duração de 2 horas");

        if (this.sala == null || this.sala.isEmpty())
        {
            System.out.println("turma online");
        }
        else
        {
            System.out.println("ocorre na sala: "+this.sala);
        }
        System.out.println("vagas atuais: "+vagasAtuais+'/'+vagasTotais);
        System.out.println("alunos matriculados: ");
        ListarAlunos();
    }

    //parte de arquivos
    public String juntarAlunos(ArrayList<Integer> alunos)
    {
        ArrayList<String> str_matriculas = new ArrayList<>();

        for (int matricula : alunos)
        {
            str_matriculas.add(String.valueOf(matricula));
        }
        String res = String.join(" ",str_matriculas);
        return res;
    }

    @Override
    public String toString()
    {
        String salaStr = (this.getSala()== null) ? "" : this.getSala();
        return String.valueOf(this.getMatriculaProf())+';'+ //TRANSFORMO EM STRING PRA EVITAR ERROS
               salaStr+';'+
               this.getMetodoAvaliacao()+';'+
               this.getHorario()+';'+
               this.getNumero()+';'+
               this.getVagasTotais()+';'+
               this.getSemestre()+';'+
               this.getCodigoDisciplina()+';'+
               juntarAlunos(alunos);
    }
    public static Turma fromString(String entrada)
    {
        if (entrada == null || entrada.isEmpty())
        {
            System.out.println("Erro ao criar turma: entrada vazia ou nula na hora de criar a turma");
            return null;
        }
        String[] dados = entrada.split(";");
        if (dados.length < 8)
        {
            System.out.println("Erro ao criar turma: dados insuficientes");
            return null;
        }
        int matriculaProf = Integer.parseInt(dados[0]);
        String sala = dados[1];
        char metodoAvaliacao = dados[2].charAt(0);
        int horario = Integer.parseInt(dados[3]);
        int numero = Integer.parseInt(dados[4]);
        int vagasTotais = Integer.parseInt(dados[5]);
        int semestre = Integer.parseInt(dados[6]);
        String codigoDisciplina = dados[7];
        String alunos = (dados.length > 8) ? dados[8] : "";

        return new Turma(matriculaProf, sala, metodoAvaliacao, horario, numero, vagasTotais, semestre, codigoDisciplina, alunos);
    }
    
}
