package classes;

import java.util.ArrayList;
import java.util.List;
public class Turma 
{
    private int vagasTotais;
    private int vagasAtuais; //deve ser calculado automaticamente
    private int numero; // numero da turma mesmo
    private String codigoDisciplina; // codigo de qual disciplina a turma faz parte
    private ArrayList<Integer> alunos; //matricula dos alunos da turma
    // colocar horario, duas turmas de uma mesma sala não podem coexistir no mesmo horario
    // adicionar algo para mostrar se é remota ou não
    // adicionar String sala onde a turma tem as aulas
    // adicionar forma a ou b de avaliação (ver github para mais info)

    //construtores
    public Turma(int numero, int vagasTotais, String codigoDisciplina)
    {
        this.codigoDisciplina = codigoDisciplina;
        this.numero = numero;
        this.vagasAtuais = vagasTotais;
        this.vagasTotais = vagasTotais;
        this.alunos = new ArrayList<>();

        System.out.println("nova turma");
        System.out.println("turma num: "+numero+" da disciplina de codigo: "+codigoDisciplina);
        System.out.println("vagas totais: "+vagasTotais);
    }
    //construtor pra quando puxar de arquivo
    public Turma(int numero, int vagasTotais, String codigoDisciplina, String matriculaAlunos)
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
        return this.getNumero()+";"+this.getVagasTotais()+";"+this.getcodigoDisciplina()+";"+juntarAlunos(alunos);
    }
    public static Turma fromString(String entrada)
    {
        String[] dados = entrada.split(";");
        if (dados.length < 3)
        {
            return null;
        }
        else if (dados.length == 3)
        {
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
