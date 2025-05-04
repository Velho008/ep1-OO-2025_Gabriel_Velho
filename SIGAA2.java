import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import classes.*;

public class SIGAA2
{
    static ArrayList<Aluno> alunos = new ArrayList<>(); //serve pra manter e criar alunos
    static ArrayList<Disciplina> disciplinas = new ArrayList<>(); //serve pra manter e criar disciplinas
    static ArrayList<Turma> turmas = new ArrayList<>(); //serve para manter e criar turmas

        public static void main(String[] args)
    {
        Scanner input1 = new Scanner(System.in);
        int escolha;

        do
        {
            
            System.out.println("SIGAA2.0");
            System.out.println("digite 1 para o modo aluno");
            System.out.println("digite 2 para o modo turma");
            System.out.println("digite 3 para o modo notas");
            System.out.println("digite 0 para sair");
            escolha = input1.nextInt();

            switch (escolha)
            {
                case 1:
                    ModoAluno(input1);

                    break;
                case 2:
                    ModoTurma(input1);


                    break;
                case 3:
                    System.out.println("MODO NOTAS");


                    break;
                case 0:
                    input1.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("numero invalido, digite novamente");
                    continue;
            }
            
        } while (escolha !=0);
        
    }
    public static void ModoAluno(Scanner input)
    {
        int escolha;

        do //menuzinho do modo aluno
        {
            System.out.println("MODO ALUNO");
            System.out.println("digite 1 para cadastrar alunos");
            System.out.println("digite 2 para listar os alunos cadastrados");
            System.out.println("digite 3 para matricular um aluno em uma turma");
            System.out.println("digite 4 para editar cadastro de aluno"); //para fazer qualquer uma dessas alterações o operador deve digitar a matricula do aluno
            System.out.println("digite 5 para trancar a matricula de um aluno");//para fazer qualquer uma dessas alterações o operador deve digitar a matricula do aluno
            System.out.println("digite 0 voltar ao menu anterior"); //separar o trancamento e a edição do cadastro
            escolha = input.nextInt();
            input.nextLine(); //come o enter

            switch (escolha)
            {
                case 1:
                    Cadastro(input);

                    break;
                case 2:
                    ListarAlunos();

                    break;
                case 3:
                    //colocar aluno em turma

                    break;
                case 4:
                    EditarAluno(input);

                    break;
                case 5:
                    // trancar 

                    break;
                case 0:
                    break;
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

        for (Aluno aluno : alunos) //checa matricula dupla
        {
            if ( aluno.getMatricula() == matricula) 
            {
                System.out.println("essa matricula já existe");
                return;
            }
        }
        System.out.println("digite o curso: ");
        String curso = input.nextLine();
        // ainda preciso checar se é especial ou não
        System.out.println("o aluno é calouro? digite 1 para sim");
        int escolha = input.nextInt();
        input.nextLine();
        if (escolha !=1)
        {
            System.out.println("digite os codigos das disciplinas que esse aluno já cursou, separados por espaço");
            String disciplinas_cursadas = input.nextLine();
            Aluno aluno = new Aluno(nome,matricula,curso,disciplinas_cursadas);//cria de fato o aluno e cadastra no sistema (adiciona na lista)
            alunos.add(aluno);
        }
        else
        {
        Aluno aluno = new Aluno(nome,matricula,curso); //calouro
        alunos.add(aluno); //coloca o aluno na lista do sistema
        }
        
    }
    public static void ListarAlunos()
    {
        int contador = 0;
        System.out.println("");
        System.out.println("N //NOME //MATRICULA //CURSO");
        for (Aluno aluno : alunos)
        {
            contador ++;

            System.out.print(contador+": ");
            System.out.print(aluno.getNome()+"    "+aluno.getMatricula()+"    "+aluno.getCurso());
            System.out.println("");

        } 
        System.out.println("");
    }

    public static void MatricularAlunoEmTurma(Scanner input)
    {

    }
    public static void EditarAluno(Scanner input)
    {
        int escolha;
        int matriculaVelha; //SERVE PRA ESCOLHER O ALUNO
        int teste; //serve para checar se a matricula digitada é a correta

        System.out.println("digite a matricula do aluno cujo cadastro vai ser editado");
        matriculaVelha = input.nextInt();
        input.nextLine();
        if (ChecarMatricula(matriculaVelha))
        {
            do
            {
                Aluno aluno = Buscaraluno(matriculaVelha);
                System.out.println("aluno escolhido: "+aluno.getNome()+" matricula: "+aluno.getMatricula());
                System.out.println("CUIDADO AO EDITAR DADOS DE ALUNOS");
                System.out.println("digite 1 para alterar o nome");
                System.out.println("digite 2 para alterar o curso");
                System.out.println("digite 3 para alterar a matricula");
                System.out.println("digite 0 para voltar ao menu anterior"); //adicionar um que apaga o aluno do sistema

                escolha = input.nextInt();
                input.nextLine(); //come o enter
                    switch (escolha)
                    {
                        case 1:
                            System.out.println("digite o novo nome: ");
                            String nome = input.nextLine();
                            System.out.println("o nome antigo era: "+aluno.getNome()+" o novo nome será: "+nome);
                            System.out.println("digite a matricula do aluno para confirmar a mudança");
                            teste = input.nextInt();
                            input.nextLine(); //come o enter
                            if (teste == matriculaVelha)
                            {
                                aluno.setNome(nome);
                                System.out.println("nome alterado com sucesso");
                            }
                            else
                            {
                                System.out.println("MATRICULA ERRADA");
                            }
                            break;
                        case 2:
                            System.out.println("digite o novo curso: ");
                            String curso = input.nextLine();
                            System.out.println("o curso antigo era: "+aluno.getCurso()+" o novo curso será: "+curso);
                            System.out.println("digite a matricula do aluno para confirmar a mudança");
                            teste = input.nextInt();
                            input.nextLine(); //come o enter
                            if (teste == matriculaVelha)
                            {
                                aluno.setCurso(curso);
                                System.out.println("curso alterado com sucesso");
                            }
                            else
                            {
                                System.out.println("MATRICULA ERRADA");
                            }
                            break;
                        case 3: //verificar duplicidade de matricula ao digitar a nova, lembre que a matricula já vai existir, por ser a de um aluno existente, mas ão pode colidir com outros
                            System.out.println("digite a nova matricula: ");
                            int matriculaNova = input.nextInt();
                            input.nextLine(); //come o enter
                            Aluno alunoComNovaMatricula = Buscaraluno(matriculaNova);
                            if (alunoComNovaMatricula != null && alunoComNovaMatricula != aluno)
                            {
                                System.out.println("essa matricula já existe");
                            }
                            else
                            {
                                System.out.println("a matricula antiga era: "+aluno.getMatricula()+" a nova matricula será: "+matriculaNova);
                                System.out.println("digite a matricula ANTIGA do aluno para confirmar a mudança");
                                teste = input.nextInt();
                                input.nextLine(); //come o enter
                                if (teste == matriculaVelha)
                                {
                                    aluno.setMatricula(matriculaNova);
                                    System.out.println("matricula alterada com sucesso");
                                    matriculaVelha = matriculaNova;
                                    break;
                                }
                                else
                                {
                                    System.out.println("MATRICULA ERRADA");
                                }
                            }

                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("digite novamente");
                                
                    }
            } while (escolha !=0);
        }  
        else
        {
            System.out.println("matricula não existe");
        }

    }
        

    public static void ModoTurma(Scanner input)
    {
        int escolha;

        do //menuzinho do modo escolha
        {
            System.out.println("MODO TURMA");
            System.out.println("digite 1 para criar uma nova disciplina");
            System.out.println("digite 2 para criar uma nova turma pertencente a uma disciplina cadastrada");
            System.out.println("digite 3 para listar as disciplinas existentes"); //talvez escolher para listar as turmas de cada disciplina
            System.out.println("digite 4 para listar as turmas existentes"); //mostrar quantas vagas tem e pode escolher uma turma para listar os alunos dela
            System.out.println("digite 0 para voltar ao menu anterior");
            escolha = input.nextInt();
            input.nextLine(); //come o enter

            switch (escolha)
            {
                case 1:
                    CriarDisciplina(input);

                    break;
                case 2:
                    CriarTurma(input);

                    break;
                case 3:
                    ListarDisciplinas();

                    break;
                case 4:
                    ListarTurmas();

                    break;
                case 0 :
                    break;
                default:
                    System.out.println("DIGITO INVALIDO, DIGITE NOVAMENTE");
                    break;
            }
        } while (escolha !=0);

    }
    public static void CriarDisciplina(Scanner input)
    {
        System.out.println("digite o nome da disciplina: ");
        String nome = input.nextLine();
        System.out.println("digite o codigo da disciplina: ");
        String codigo = input.nextLine();

        for (Disciplina disciplina: disciplinas)//checa codigo duplo
        {
            if (disciplina.getCodigo().equals(codigo))
            {
                System.out.println("esse codigo já existe");
                return;
            }
        }
        System.out.println("digite a carga horaria: ");
        int carga_horaria = input.nextInt();
        input.nextLine(); //come o enter
        System.out.println("digite 1 caso essa materia tenha pré-requisitos");
        int escolha = input.nextInt();
        input.nextLine();
        switch (escolha)
        {
            case 1:
                //fazer a coisa dos pré_requisitos
                break;
            default:

                break;
        }
        Disciplina disciplina = new Disciplina(nome,codigo,carga_horaria); 
        disciplinas.add(disciplina); //coloca a disciplina na lista do sistema
    }   

    public static void CriarTurma(Scanner input)
    {
        System.out.println("Digite o codigo da disciplina da qual a turma pertence");
        String codigo = input.nextLine();
        String nomeDisciplina = "";

        for (Disciplina disciplina: disciplinas) //define a disciplina a qual a turma pertence
        {
            if (disciplina.getCodigo().equals(codigo))
            {
                nomeDisciplina = disciplina.getNome();
            }
        }
        if (nomeDisciplina.isEmpty()) //checa se o codigo existe, só roda o resto se existir
        {
            System.out.println("o codigo digitado não existe");
            return;
        }
        System.out.println("disciplina selecionada: "+nomeDisciplina);
        System.out.println("digite o numero da turma: ");
        int numero = input.nextInt();
        input.nextLine(); //come o espaço
        System.out.println("digite a quantidade de vagas: ");
        int vagasTotais = input.nextInt();
        input.nextLine();
        Turma turma = new Turma(numero,vagasTotais,nomeDisciplina);
        turmas.add(turma); //coloca a turma na lista do sistema

    }

    public static void ListarDisciplinas()
    {
        int contador = 0;
        System.out.println("");
        System.out.println("N //NOME //CODIGO //CARGA HORARIA");
        for (Disciplina disciplina : disciplinas)
        {
            contador++;
            System.out.print(contador+": ");
            System.out.print(disciplina.getNome()+"    "+disciplina.getCodigo()+"    "+disciplina.getCargaHoraria()+"H");
            System.out.println("");
        }
        System.out.println("");
    }

    public static void ListarTurmas()
    {
        //caso tenha 0 vagas, marcar a turma com um X no começo, para mostrar que acabaram as vagas
        System.out.println("");
        System.out.println("NUMERO //DISCIPLINA //VAGAS ATUAIS //VAGAS TOTAIS");
        for (Turma turma : turmas)
        {
            System.out.print(turma.getNumero());
            System.out.print(turma.getDisciplina()+"    "+turma.getVagasAtuais()+"/"+turma.getVagasTotais());
            System.out.println("");
        }
        System.out.println("");
    }

    public static void ModoNotas(Scanner input)
    {

    }
    public static boolean ChecarMatricula(int matricula)
    {
        for (Aluno aluno: alunos)
        {
            if (aluno.getMatricula()==matricula)
            {
                return true;
            }
        }
        return false;
    }
    public static Aluno Buscaraluno(int matricula)
    {
        for (Aluno aluno : alunos)
        {
            if (aluno.getMatricula()==matricula)
            {
                return aluno;
            }
        }
        return null;
    }
    
}