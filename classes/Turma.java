package classes;

import java.util.ArrayList;
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
    private char[] horario; //toda aula dura 2 horas, hora final exemplo: 0800 (começa 8 da manha)
    // duas turmas de uma mesma sala não podem coexistir no mesmo horario

    //construtores //fazer um sem sala, pra online
    public Turma(int matriculaProf, String sala, char metodoAvaliacao, char[] horario,int numero, int vagasTotais, String codigoDisciplina)
    {
        this.matriculaProf = matriculaProf;
        this.sala = sala;
        this.metodoAvaliacao = metodoAvaliacao;
        this.horario = horario;
        this.codigoDisciplina = codigoDisciplina;
        this.numero = numero;
        this.vagasAtuais = vagasTotais;
        this.vagasTotais = vagasTotais;
        this.alunos = new ArrayList<>();

        System.out.println("nova turma ministrada pelo prof de matricula: "+this.matriculaProf);
        System.out.println("turma num: "+numero+" da disciplina de codigo: "+codigoDisciplina);
        System.out.println("vagas totais: "+vagasTotais);
        System.out.println("metodo "+this.metodoAvaliacao+"de avaliação");
        System.out.println("horario inicial: "+this.horario[0]+this.horario[1]+':'+this.horario[2]+this.horario[3]);
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
    public Turma(int matriculaProf, char metodoAvaliacao, char[] horario,int numero, int vagasTotais, String codigoDisciplina)
    {
        this.matriculaProf = matriculaProf;
        this.sala = "";
        this.metodoAvaliacao = metodoAvaliacao;
        this.horario = horario;
        this.codigoDisciplina = codigoDisciplina;
        this.numero = numero;
        this.vagasAtuais = vagasTotais;
        this.vagasTotais = vagasTotais;
        this.alunos = new ArrayList<>();

        System.out.println("nova turma ministrada pelo prof de matricula: "+this.matriculaProf);
        System.out.println("turma num: "+numero+" da disciplina de codigo: "+codigoDisciplina);
        System.out.println("vagas totais: "+vagasTotais);
        System.out.println("metodo "+this.metodoAvaliacao+"de avaliação");
        System.out.println("horario inicial: "+this.horario[0]+this.horario[1]+':'+this.horario[2]+this.horario[3]);
        System.out.println("todas as aulas tem duração de 2 horas");
        System.out.println("turma online");
    }
    //construtor pra quando puxar de arquivo
    public Turma(int matriculaProf, String sala, char metodoAvaliacao, char[] horario, int numero, int vagasTotais, String codigoDisciplina, String matriculaAlunos)
    {
        this.numero = numero;
        this.vagasAtuais = vagasTotais;
        this.vagasTotais = vagasTotais;
        this.codigoDisciplina = codigoDisciplina;
        this.alunos = new ArrayList<>();
        for (String aluno : matriculaAlunos.split(" "))
        {
            int matricula = Integer.parseInt(aluno);
            addAluno(matricula);
        }
    }

    //setters e getters
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
    public void setHorario(char[] horario)
    {
        this.horario = horario;
    }
    public char[] getHorario()
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
    public void setcodigoDisciplina(String codigoDisciplina)
    {
        this.codigoDisciplina = codigoDisciplina;
    }
    public String getcodigoDisciplina()
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
        alunos.remove(matricula);
        this.vagasAtuais+=1;
    }
    public void removerAluno(Aluno aluno)
    {
        alunos.remove(aluno.getMatricula());
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
        System.out.println("turma num: "+numero+" da disciplina de codigo: "+codigoDisciplina);
        System.out.println("vagas atuais: "+vagasAtuais+'/'+vagasTotais);
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
        String hora = getHorario().toString();
        return this.getMatriculaProf()+';'+this.getSala()+';'+this.getMetodoAvaliacao()+';'+hora+';'+this.getNumero()+';'+this.getVagasTotais()+';'+this.getcodigoDisciplina()+';'+juntarAlunos(alunos);
    }
    public static Turma fromString(String entrada)
    {
        String[] dados = entrada.split(";");
        if (dados.length < 8)
        {
            return null;
        }
        else if (dados.length == 8)
        {
            int matriculaProf = Integer.parseInt(dados[0]);
            String sala = dados[1];
            char metodoAvaliacao = dados[2];
            int numero = Integer.parseInt(dados[0]);
            int vagasTotais = Integer.parseInt(dados[1]);
            return new Turma(numero, vagasTotais, dados[2]);
        }
        else
        {
            int numero = Integer.parseInt(dados[0]);
            int vagasTotais = Integer.parseInt(dados[1]);
            return new Turma(numero, vagasTotais, dados[2], dados[3]);
        }
    }
    
}
