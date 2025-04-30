import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

import classes.*;

public class SIGAA2
{
        public static void main(String[] args)
    {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        boolean continuar = true;
        while(continuar)
        {
            
            System.out.println("MENU");
            System.out.println("digite 1 para o modo aluno");
            System.out.println("digite 2 para o modo turma");
            System.out.println("digite 3 para o modo notas");
            System.out.println("digite 0 para sair");
            int escolha = input1.nextInt();

            switch (escolha)
            {
                case 1:

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
            input2.close();
        }
    }
    public static void ModoAluno(Scanner input)
    {
        int escolha;

        do //menuzinho o modo aluno
        {
        System.out.println("MODO ALUNO");
        System.out.println("digite 1 para cadastrar alunos");
        System.out.println("digite 2 para listar os alunos cadastrados");
        System.out.println("digite 3 para matricular um aluno em uma disciplina");
        System.out.println("digite 4 para editar alunos matriculados ou editar seu cadastro");
        System.out.println("digite 0 para fechar o programa");
        escolha = input.nextInt();

        switch (escolha)
        {
            case 1:
                // cadastro

                break;
            case 2:
                //listar alunos

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

    public static void ModoTurma(Scanner input)
    {

    }
    public static void ModoNotas(Scanner input)
    {

    }
}