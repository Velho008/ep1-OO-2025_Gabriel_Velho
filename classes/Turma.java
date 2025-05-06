package classes;

import java.util.ArrayList;

public class Turma //PEDIR AJUDA PRA RESOLVER ESSE PROBLEMA DE TURMA
{
    private int vagasTotais;
    private int vagasAtuais; //deve ser calculado automaticamente
    private int numero; // numero da turma mesmo
    private String codigoDisciplina; // codigo de qual disciplina a turma faz parte
    private String nomeDisciplina; //nome da disciplina da qual a turma faz parte 
    private ArrayList<String> alunos; //matricula dos alunos matriculados
    // colocar horario, duas turmas de uma mesma disciplina não podem coexistir no mesmo horario
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
        //this.nomeDisciplina = BuscarDisciplina(codigoDisciplina); //arrumar isso passando SIGAA2 pra dentro de classes
        //puxar o nome da disciplina a partir do codigo

        System.out.println("nova turma");
        System.out.println("turma num: "+numero+" de "+nomeDisciplina+" codigo: "+codigoDisciplina);
        System.out.println("vagas totais: "+vagasTotais);
    }

    //setters e getters
    public void setVagasTotais(int vagasTotais)
    {
        this.vagasTotais = vagasTotais;
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
        //this.nomeDisciplina = BuscarDisciplina(this.codigoDisciplina);
        //trocar o nome de acordo com a disciplina
    }
    public String getcodigoDisciplina()
    {
        return this.codigoDisciplina;
    }
    public String getnomeDisciplina() //não tem set nome da disciplina pq é feito automatico com o codigo
    {
        return this.nomeDisciplina;
    }
}
