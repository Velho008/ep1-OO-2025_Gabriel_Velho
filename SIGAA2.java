import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import classes.*;

public class SIGAA2
{
    static ArrayList<Aluno> alunos = new ArrayList<>(); //serve pra manter e criar alunos
        public static void main(String[] args)
    {
        Scanner input1 = new Scanner(System.in);

        boolean continuar = true;
        while(continuar)
        {
            
            System.out.println("SIGAA2.0");
            System.out.println("digite 1 para o modo aluno");
            System.out.println("digite 2 para o modo turma");
            System.out.println("digite 3 para o modo notas");
            System.out.println("digite 0 para sair");
            int escolha = input1.nextInt();

            switch (escolha)
            {
                case 1:
                    ModoAluno(input1);

                    continuar = false;
                    break;
                case 2:
                    System.out.println("MODO TURMA");


                    continuar = false;
                    break;
                case 3:
                    System.out.println("MODO NOTAS");


                    continuar = false;
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("numero invalido, digite novamente");
                    continue;
            }
            input1.close();
        }
    }
    public static void ModoAluno(Scanner input)
    {
        int escolha;

        do //menuzinho do modo aluno
        {
        System.out.println("MODO ALUNO");
        System.out.println("digite 1 para cadastrar alunos");
        System.out.println("digite 2 para listar os alunos cadastrados");
        System.out.println("digite 3 para matricular um aluno em uma disciplina");
        System.out.println("digite 4 para editar alunos matriculados ou editar seu cadastro");
        System.out.println("digite 0 para fechar o programa");
        escolha = input.nextInt();
        input.nextLine(); //come o enter

        switch (escolha)
        {
            case 1:
                Cadastro(input);

                break;
            case 2:
                ListarAlunos(input);

                break;
            case 3:
                //colocar aluno em disciplina

                break;
            case 4:
                //editar aluno

                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("DIGITO INVALIDO, DIGITE NOVAMENTE");
                break;
        }

        } while (escolha !=0);

    }
    public static void Cadastro(Scanner input)
    {
        System.out.println("digite o nome: ");
        String nome = input.nextLine();
        System.out.println("digite a matricula: ");
        int matricula = input.nextInt();
        input.nextLine(); // come o enter

        for (Aluno pessoa : alunos)
        {
            if ( pessoa.getMatricula() == matricula) //checar se a matricula já pertence a algum dos alunos da lista
            {
                System.out.println("essa matricula já existe");
                return;
            }
        }
        System.out.println("digite o curso: ");
        String curso = input.nextLine();
        // ainda preciso checar se é especial ou não
        // ainda preciso adicionar os codigos das materias ja feitas pelo aluno

        //cria de fato o aluno e cadastra no sistema (adiciona na lista)
        Aluno aluno = new Aluno(nome,matricula,curso);
        alunos.add(aluno); //coloca o aluno na lista do sistema
    }
    public static void ListarAlunos(Scanner input)
    {
        int contador = 0;
        System.out.println("");
        System.out.println("n   nome        matricula");
        for (Aluno aluno : alunos)
        {
            contador ++;

            System.out.print(contador+": ");
            System.out.print(aluno.getNome()+"    "+aluno.getMatricula());
            System.out.println("");

        } 
        System.out.println("");
    }

    public static void ModoTurma(Scanner input)
    {

    }
    public static void ModoNotas(Scanner input)
    {

    }
}