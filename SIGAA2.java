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
        Scanner digitos = new Scanner(System.in);

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
                    System.out.println("MODO ALUNO");

                    System.out.println("digite 1 para cadastrar alunos");
                    System.out.println("digite 2 para listar os alunos cadastrados");
                    System.out.println("digite 3 para matricular um aluno em uma disciplina");
                    System.out.println("digite 4 para editar alunos matriculados ou editar seu cadastro");
                    System.out.println("digite 0 para fechar o programa");
                    System.out.println("digite outro inteiro para retornar ao menu anterior");
                    int escolha2 = input2.nextInt();
                    switch (escolha2) {
                        case 1:
                        System.out.println("MODO CADASTRO DE ALUNOS");
                        System.out.println("digite o nome do aluno: ");
                        String nome = digitos.nextLine();
                        System.out.println("digite a matricula do aluno ou 0 para pular");
                        int matricula = digitos.nextInt();
                        if (matricula == 0)
                        {
                            //fazer o aluno só com nome
                        }
                        else
                        {
                            System.out.println("digite o curso do aluno");
                            {
                                String curso = digitos.nextLine();
                            }
                            System.out.println("digite o codigo das disciplinas já cursadas pelo aluno");
                            List<String> disciplinas_cursadas = digitos.nextLine(); //arrumar isso, a pessoa deve escrever os codigos com algo separado eles e isso deve ser convertido em uma lista com as disciplinas ja cursadas
                        }
                            
                            break;
                        case 2:

                            break;
                        case 3: 

                            break;

                        case 0:
                            System.exit(0);
                    
                        default:
                            break;
                    }


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
}