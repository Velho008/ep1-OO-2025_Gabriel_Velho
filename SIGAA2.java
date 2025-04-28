import java.util.Scanner;

public class SIGAA2
{
        public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        boolean continuar = true;
        while(continuar)
        {
            
            System.out.println("seja bem vindo ao menu");
            System.out.println("digite 1 para o modo aluno");
            System.out.println("digite 2 para o modo turma");
            System.out.println("digite 3 para o modo notas");
            System.out.println("digite 0 para sair");
            int escolha = input.nextInt();

            switch (escolha)
            {
                case 1:
                    System.out.println("escolheu 1");
                    continuar = false;
                    break;
                case 2:
                    System.out.println("escolheu 2");
                    continuar = false;
                    break;
                case 3:
                    System.out.println("escolheu 3");
                    continuar = false;
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("numero invalido, digite novamente");
                    continue;
            }
            input.close();
        }
    }
}