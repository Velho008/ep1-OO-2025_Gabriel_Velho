package classes;

import java.util.*;
import java.io.*;

public class SIGAA2 
{
    // O QUE FALTA 
    //{
    // criar o aluno especial falta
    //{
    // na hora de lançar notas lançar só presença
    // ter um boletim diferente
    // testar trancar matricula de uma turma e entrar em mais do que 2 dps
    // testar trancar matricula geral e dps entrar em mais do que 2
    // ter um mostrar info proprio que mostra que é especial
    //}
    //}

    // BUGS CONHECIDOS PRA ARRUMAR
    //{ 
    // quando remover turmas do sistema, tirar da disciplina e do professor e da conta de disciplinas atuais do aluno especial
    // quando remover disciplina apagar todas as turmas da disciplina tirar da lista das feitas por aluno
    // quando remover professor, as turmas dele são removidas tmb, desencadeando as coisas de remover turma
    // quando editar a matricula do aluno e trocar a matricula, mudar isso na lista da turma
    // quando remover o aluno do sistema, tirar ele da lista da turma
    //}
    
    //otimização super opcional
    //{
    // poder a qualquer momento voltar pro menu anterior, pra caso tenha digitado e entrado em um menu sem querer
    // melhorar todos os inputs, na hora que digitar errado, não dar erro e fechar o programa, e sim falar qual foi o erro e receber outra entrada
    // deixar os menus mais formatados e bonitos
    // salvar as coisas quando mudar, não só quando criar e fechar o programa
    // facilitar lançar multiplas notas de uma vez
    // talvez mudar a exibição de boletins pra selecionar por semestre
    //}
    
    static ArrayList<Aluno> alunos = new ArrayList<>(); //serve pra manter e criar alunos
    static ArrayList<Disciplina> disciplinas = new ArrayList<>(); //serve pra manter e criar disciplinas
    static ArrayList<Turma> turmas = new ArrayList<>(); //serve para manter e criar turmas
    static ArrayList<Professor> professores = new ArrayList<>(); //serve pra criar e manter professores
    static ArrayList<Boletim> boletins = new ArrayList<>();//server pra criar e manter boletins
    static ArrayList<Relatorio> relatorios = new ArrayList<>(); //serve pra criar e manter relatorios

        public static void main(String[] args)
    {
        //carregar as coisas dos arquivos
        CarregarProfessor();
        CarregarDisciplinas();
        CarregarTurmas();
        CarregarAlunos();
        CarregarBoletins();
        CarregarRelatorios();
        

        Scanner input1 = new Scanner(System.in);//scanner usado no menu inteiro
        int escolha;//variavel de escolha do menu

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
                    ModoNotas(input1);

                    break;
                case 0:
                    SalvarTudo(); //salva tudo antes de fechar
                    input1.close(); //fecha o scanner para impedir perda de memoria
                    System.exit(0);
                    break;
                default:
                    System.out.println("numero invalido, digite novamente");
                    continue;
            }
        } while (escolha !=0); //garante o loop enquanto algo diferente for digitado
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
            System.out.println("digite 4 para editar cadastro de aluno"); 
            System.out.println("digite 5 para trancar a matricula de um aluno"); 
            System.out.println("digite 6 para deletar um aluno do sistema");
            System.out.println("digite 7 para buscar informações sobre um aluno");
            System.out.println("digite 0 voltar ao menu anterior"); 
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
                    MatricularAlunoEmTurma(input);

                    break;
                case 4:
                    EditarAluno(input); 

                    break;
                case 5:
                    TrancarMatricula(input); 

                    break;
                case 6:
                    RemoverAluno(input);

                    break;
                case 7:
                    MostrarInfoAluno(input);
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
        System.out.println("o aluno é calouro? digite 1 para sim");
        int escolha = input.nextInt();
        input.nextLine();
        if (escolha !=1)
        {
            boolean checar = false;
            String disciplinas_cursadas;
            System.out.println("digite os codigos das disciplinas que esse aluno já cursou, separados por espaço");
            do
            {
                disciplinas_cursadas = input.nextLine(); // ver se existem
                for (String disciplina : disciplinas_cursadas.split(" "))
                {
                    if(ChecarCodigoDisciplina(disciplina))
                    {
                        checar = true;
                    }
                    else
                    {
                        System.out.println("algum codigo não existe, digite novamente");
                    }
                }
            }while(!checar);

            System.out.println("digite 1 caso o aluno seja especial (pode cursar apenas 2 disciplinas e não recebe nota, apenas presença)");
            escolha = input.nextInt();
            input.nextLine(); //come o enter
            if (escolha == 1)
            {
                AlunoEspecial aluno = new AlunoEspecial(nome, matricula, curso);
                SalvarAlunoIndividual(aluno);
                alunos.add(aluno);
                return;
            }
            Aluno aluno = new Aluno(nome,matricula,curso,disciplinas_cursadas);//cria de fato o aluno e cadastra no sistema (adiciona na lista)
            alunos.add(aluno);
            SalvarAlunoIndividual(aluno);
        }
        else
        {
        System.out.println("digite 1 caso o aluno seja especial (pode cursar apenas 2 disciplinas e não recebe nota, apenas presença)");
        escolha = input.nextInt();
        input.nextLine(); //come o enter
        if (escolha == 1)
        {
            AlunoEspecial aluno = new AlunoEspecial(nome, matricula, curso);
            SalvarAlunoIndividual(aluno);
            alunos.add(aluno);
            return;
        }
        Aluno aluno = new Aluno(nome,matricula,curso); //calouro
        SalvarAlunoIndividual(aluno);
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
        System.out.println("digite a matricula do aluno que vai ser matriculado");
        int matricula = input.nextInt();
        input.nextLine(); //come o espaço

        if (!ChecarMatricula(matricula))
        {
            System.out.println("matricula não existe no sistema");
            return;
        }
        Aluno aluno = BuscarAluno(matricula);
        System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());

        System.out.println("digite o codigo da disciplina: ");
        String codigoDisciplina = input.nextLine();

        if (!ChecarCodigoDisciplina(codigoDisciplina))
        {
            System.out.println("não existe disciplina com esse codigo");
            return;
        }

        Disciplina disciplina = BuscarDisciplina(codigoDisciplina);
        System.out.println("disciplina selecionada: " + disciplina.getNome()+'/'+disciplina.getCodigo()); 

        //AQUI FALTA OTIMIZAR, TÁ MTO RUIM
        for (String codTurma : disciplina.getTurmasDaDisciplina())
        {
            Turma turma = BuscarTurma(codTurma);
            for (Integer matriculaAluno : turma.getAlunos())
            {
                if (matriculaAluno == matricula)
                {
                    System.out.println("O ALUNO JÁ MATRICULADO NESSA DISCIPLINA");
                    return;
                }
            }
        }

        boolean confirmador = false;
        if (!disciplina.getPreRequisitos().isEmpty() && !aluno.getDisciplinasCursadas().isEmpty()) //checa requisitos
        {
            if (aluno.getDisciplinasCursadas().containsAll(disciplina.getPreRequisitos()))
            {
                confirmador = true;
            }
        }

        if (!(disciplina.getPreRequisitos().isEmpty() || confirmador))
        {
            System.out.println("o aluno não tem algum dos pré-requisitos para cursar a disciplina");
            System.out.println("são eles");
            System.out.println(disciplina.getPreRequisitos()); 
            return;
        }

        System.out.println("digite o numero da turma que o aluno "+aluno.getNome()+" será matriculado:");
        int numTurma = input.nextInt();
        input.nextLine(); //come o enter

        if (!ChecarTurma(numTurma, codigoDisciplina))
        {
            System.out.println("a turma selecionada não existe");
            return;
        }

        Turma turma = BuscarTurma(numTurma, codigoDisciplina);
        System.out.println("turma selecionada: "+turma.getNumero());

        if (turma.getVagasAtuais() <=0)
        {
            System.out.println("não existem mais vagas disponiveis na turma");
            System.out.println("não foi possivel matricular o aluno na turma");
            return;
        }

        if (aluno instanceof AlunoEspecial)
        {
            AlunoEspecial especial = (AlunoEspecial) aluno;
            if (especial.getDisciplinasAtuais() >=2)
            {
                System.out.println("Esse aluno especial já cursa o maximo de disciplinas permitidas");
                return;
            } else
            {
                especial.addDisciplinaAtual();
            }
        }
        turma.addAluno(aluno);
        System.out.println("aluno matriculado com sucesso, vagas atuais da turma: "+turma.getVagasAtuais()+'/'+turma.getVagasTotais());   

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
                Aluno aluno = BuscarAluno(matriculaVelha);
                System.out.println("aluno escolhido: "+aluno.getNome()+" matricula: "+aluno.getMatricula());
                System.out.println("CUIDADO AO EDITAR DADOS DE ALUNOS, ISSO PODE CAUSAR ERROS EM ARQUIVOS!");
                System.out.println("digite 1 para alterar o nome");
                System.out.println("digite 2 para alterar o curso");
                System.out.println("digite 3 para alterar a matricula");
                System.out.println("digite 4 para adicionar disciplinas que o aluno já fez");
                System.out.println("digite 5 para remover disciplinas que o aluno já fez");
                System.out.println("digite 0 para voltar ao menu anterior");

                escolha = input.nextInt();
                String codigo;
                Disciplina disciplina;
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
                        case 3:
                            System.out.println("não recomendado!!! caso o aluno já esteja em uma turma pode causar erro!");
                            System.out.println("digite a nova matricula: ");
                            int matriculaNova = input.nextInt();
                            input.nextLine(); //come o enter
                            Aluno alunoComNovaMatricula = BuscarAluno(matriculaNova);
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
                        case 4: 
                            System.out.println("digite o codigo da disciplina que vai ser adicionada");
                            codigo = input.nextLine();
                            if (!ChecarCodigoDisciplina(codigo))
                            {
                                System.out.println("o codigo digitado não existe");
                                break;
                            }
                            disciplina = BuscarDisciplina(codigo);
                            System.out.println("a disciplina selecionada é: "+disciplina.getNome()+'/'+disciplina.getCodigo());
                            if (!aluno.getDisciplinasCursadas().containsAll(disciplina.getPreRequisitos()))
                            {
                                System.out.println("o aluno não fez os requisitos da materia selecionada");
                                System.out.println("portanto não poderá ter essa materia como já feita");
                                System.out.println("os requisitos da materia são:");
                                for (String requisito : disciplina.getPreRequisitos())
                                {
                                    System.out.println(requisito);
                                }
                                break;
                            }
                            System.out.println("digite novamente a matricula do aluno para confirmar: ");
                            teste = input.nextInt();
                            input.nextLine(); //come o enter
                            if (!(teste == matriculaVelha))
                            {
                                System.out.println("matricula invalida");
                                break;
                            }
                            aluno.addDisciplina(disciplina);
                            System.out.println("disciplina adicionada com sucesso");
                            ;
                            break;
                        case 5: 
                            System.out.println("digite o codigo da disciplina que vai ser removida");
                            codigo = input.nextLine();
                            if (!ChecarCodigoDisciplina(codigo))
                            {
                                System.out.println("o codigo digitado não existe");
                                break;
                            }
                            if (!aluno.fezDisciplina(codigo))
                            {
                                System.out.println("o aluno não fez a disciplina selecionada");
                                break;
                            }
                            disciplina = BuscarDisciplina(codigo);
                            System.out.println("a disciplina selecionada é: "+disciplina.getNome()+'/'+disciplina.getCodigo());
                            System.out.println("digite novamente a matricula do aluno para confirmar: ");
                            teste = input.nextInt();
                            input.nextLine(); //come o enter
                            if (!(teste == matriculaVelha))
                            {
                                System.out.println("matricula invalida");
                                break;
                            }
                            aluno.removerDisciplina(disciplina);
                            System.out.println("disciplina removida com sucesso");
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
    public static void TrancarMatricula(Scanner input)
    {
        System.out.println("LEMBRE-SE...");
        System.out.println("trancar matricula geral = sair de todas as turmas");
        System.out.println("trancar matricula de materia = sair da turma da materia");
        System.out.println("sabendo disso...");
        System.out.println("digite a matricula do aluno: ");

        int matricula = input.nextInt();
        input.nextLine(); //come o enter

        if (!ChecarMatricula(matricula))
        {
            System.out.println("a matricula digitada não existe");
            return;
        }

        Aluno aluno = BuscarAluno(matricula);
        System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());
        System.out.println("digite 1 para realizar o trancamento geral de matricula ou outro numero para trancar apenas uma disciplina");
        int escolha = input.nextInt();
        input.nextLine(); //come o enter

        if (escolha == 1)
        {
            System.out.println("digite novamente a matricula do aluno para confirmar o trancamento geral");
            int teste = input.nextInt();
            input.nextLine(); // come o enter

            if (aluno.getMatricula() != teste)
            {
                System.out.println("matricula errada digitada na confirmação");
                System.out.println("voltando ao menu anterior");
                return;
            }
            List<Turma> turmasParaTrancar = new ArrayList<>();

            for (Turma turma : turmas)
            {
                if (turma.getAlunos().contains(aluno.getMatricula()))
                {
                    turmasParaTrancar.add(turma);
                }
            }
            
            for (Turma turma: turmasParaTrancar)
            {
                turma.removerAluno(aluno);
                Relatorio relatorioTurma = AcessaOuCriaRelatorio('t', turma.getCodigoTurma());
                Relatorio relatorioDisciplina = AcessaOuCriaRelatorio('d', turma.getCodigoDisciplina());
                Relatorio relatorioProfessor = AcessaOuCriaRelatorio('p', String.valueOf(turma.getMatriculaProf()));
                relatorioTurma.addTrancaram();
                relatorioDisciplina.addTrancaram();
                relatorioProfessor.addTrancaram();
                if (aluno instanceof AlunoEspecial)
                {
                    AlunoEspecial especial = (AlunoEspecial) aluno;
                    especial.removerDisciplinaAtual();
                }
            }
            System.out.println("trancamento geral realizado com sucesso");
            return;
        }

        System.out.println("digite o codigo da disciplina que será trancada: ");
        String codigo = input.nextLine();

        if (!ChecarCodigoDisciplina(codigo))
        {
            System.out.println("o codigo digitado não existe");
            return;
        }

        System.out.println("digite o numero da turma: ");
        int num = input.nextInt();
        input.nextLine(); //come o enter 

        if (!ChecarTurma(num, codigo))
            {
                System.out.println("não existe turma com esse numero e codigo");
                return;
            }
        Turma turma = BuscarTurma(num, codigo);

        System.out.println("digite novamente a matricula do aluno para confirmar o trancamento");
        int teste = input.nextInt();
        input.nextLine(); //come o enter

        if (teste == aluno.getMatricula())
        {
            turma.removerAluno(aluno);
            System.out.println("disciplina trancada corretamente");
            Relatorio relatorioTurma = AcessaOuCriaRelatorio('t', turma.getCodigoTurma());
            Relatorio relatorioDisciplina = AcessaOuCriaRelatorio('d', turma.getCodigoDisciplina());
            Relatorio relatorioProfessor = AcessaOuCriaRelatorio('p', String.valueOf(turma.getMatriculaProf()));
            relatorioTurma.addTrancaram();
            relatorioDisciplina.addTrancaram();
            relatorioProfessor.addTrancaram();
            if (aluno instanceof AlunoEspecial)
            {
                AlunoEspecial especial = (AlunoEspecial) aluno;
                especial.removerDisciplinaAtual();
            }
        }
        else
        {
            System.out.println("matricula errada, voltando ao menu anterior");
            return;
        }
    }
    public static void RemoverAluno(Scanner input)
    {
        System.out.println("CUIDADO AO REMOVER ALUNOS DO SISTEMA");
        System.out.println("digite a matricula do aluno que será removido");
        int matricula = input.nextInt();
        input.nextLine();//come o enter
        if(ChecarMatricula(matricula))
        {
            System.out.println("aluno selecionado para remoção:");
            BuscarAluno(matricula).MostrarInfo();
            System.out.println("digite novamente a matricula para confirmar a remoção");
            int teste = input.nextInt();
            input.nextLine();//come o enter
            if (teste == matricula)
            {
                System.out.println("aluno "+BuscarAluno(matricula).getNome()+" removido do sistema");
                alunos.remove(BuscarAluno(matricula)); //tira do sistema
                RemoverAlunoArquivo(matricula); //apaga o arquivo
            }
            else
            {
                System.out.println("matricula errada digitada");
            }
        }
        else
        {
            System.out.println("a matricula não existe");
        }
    }
    public static void MostrarInfoAluno(Scanner input)
    {
        System.out.println("digite a matricula do aluno que sera consultado");
        int matricula = input.nextInt();
        input.nextLine(); //come o espaço
        if (ChecarMatricula(matricula))
        {
            BuscarAluno(matricula).MostrarInfo();
        }
        else
        {
            System.out.println("a matricula digitada não existe no sistema");
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
            System.out.println("digite 3 para listar as disciplinas existentes"); 
            System.out.println("digite 4 para listar as turmas existentes"); 
            System.out.println("digite 5 para remover uma disciplina do sistema"); 
            System.out.println("digite 6 para remover uma turma do sistema");
            System.out.println("digite 7 para ver informações sobre uma disciplina"); 
            System.out.println("digite 8 para ver informações sobre uma turma"); 
            System.out.println("digite 9 para criar um novo professor"); 
            System.out.println("digite 10 para apagar um professor que existe no sistema");
            System.out.println("digite 11 para listar os professores existentes"); 
            System.out.println("digite 12 para ver informações sobre um professor");
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
                case 5:
                    RemoverDisciplina(input);

                    break;
                case 6:
                    RemoverTurma(input);

                    break;
                case 7:
                    MostrarInfoDisciplina(input);

                    break;
                case 8:
                    MostrarInfoTurma(input);

                    break;
                case 9: 
                    CriarProfessor(input);

                    break;
                case 10:
                    RemoverProfessor(input);

                    break;
                case 11:
                    ListarProfessores();

                    break;
                case 12:
                    MostrarInfoProfessor(input);

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
        if (escolha == 1)
        {
            boolean veracidadeRequisitos = true; //checa a veracidade de cada codigo de pre_requisito
            String pre_requisitos;
            do 
            {
                veracidadeRequisitos = true;
                System.out.println("digite o codigo dos pré-requisitos, espaçados por um espaço");
                pre_requisitos = input.nextLine();
                for (String requisito : pre_requisitos.split(" "))
                {
                    if (!ChecarCodigoDisciplina(requisito))
                    {
                        veracidadeRequisitos = false;
                        System.out.println("algum codigo não existe, digite novamente");
                        break;
                    }
                    System.out.println("nome do pré-requisito");
                    System.out.println(BuscarDisciplina(requisito).getNome());
                }
            } while (veracidadeRequisitos == false);
            Disciplina disciplina = new Disciplina(nome, codigo, carga_horaria, pre_requisitos);
            disciplinas.add(disciplina);
            SalvarDisciplina(disciplina);
            
        }
        else
        {
            Disciplina disciplina = new Disciplina(nome,codigo,carga_horaria); 
            disciplinas.add(disciplina); //coloca a disciplina na lista do sistema
            SalvarDisciplina(disciplina);
        }
    }   

    public static void CriarTurma(Scanner input)
    {
        System.out.println("Digite o codigo da disciplina da qual a turma pertence");
        String codigo = input.nextLine();

        if (!ChecarCodigoDisciplina(codigo))
        {
            System.out.println("o codigo digitado não existe");
            System.out.println("voltando ao menu anterior");
            return;
        }

        String nomeDisciplina = BuscarDisciplina(codigo).getNome();
        System.out.println("disciplina selecionada: "+nomeDisciplina+" codigo: "+codigo);
        int numero;
        boolean continuar;

        do
        {
            System.out.println("digite o numero da turma");
            numero = input.nextInt();
            input.nextLine(); //come o enter

            continuar = false;

            for (Turma turma : turmas)
            {
                if (turma != null && turma.getCodigoDisciplina() != null && turma.getNumero() == numero && codigo.equals(turma.getCodigoDisciplina()))
                {
                    System.out.println("o numero digitado já existe em outra turma da mesma disciplina");
                    continuar = true;
                    break;
                }
            }
        } while (continuar);
        boolean online = false;
        System.out.println("digite a quantidade de vagas");
        int vagas = input.nextInt();
        input.nextLine(); //come o enter
        System.out.println("digite o nome da sala onde a aula ocorrerá ou deixe em branco caso seja online: ");
        String sala = input.nextLine();
        if (sala.isEmpty())//online
        {
            online = true;
        }
        System.out.println("digite a matricula do professor que vai ministrar a turma");
        int matriculaProf = input.nextInt();
        input.nextLine(); //come o enter
        if (ChecarMatriculaProf(matriculaProf))
        {
            System.out.println("não existe professor com essa matricula");
            return;
        }
        System.out.println("digite o horario de inicio da aula no formato: 8 (8AM) ou 12(meio dia) NÃO USE APENAS O 0");
        System.out.println("lembre que as aulas duram sempre 2 horas");
        int horario = input.nextInt();
        input.nextLine(); //come o enter
        if (!ChecarHorario(horario) && !ChecarSala(sala) && !online)
        {
            System.out.println("existe outra turma que usa a sala no mesmo horario");
            return;
        }
        if (!ChecarHorario(horario) && !ChecarProfTurma(matriculaProf))
        {
            System.out.println("esse prof já dá aula no mesmo horario");
            return;
        }
        System.out.println("digite o semestre da turma: ");
        int semestre = input.nextInt();
        input.nextLine(); //come o enter
        System.out.println("digite o metodo de avaliação, a ou b");
        char metodoAvaliacao = input.nextLine().charAt(0);
        if (!(metodoAvaliacao == 'b') && !(metodoAvaliacao == 'a'))
        {
            System.out.println("metodo de avaliação selecionado não existe");
            return;
        }
        Turma turmanova = new Turma(matriculaProf, sala, metodoAvaliacao, horario, numero, vagas, semestre, codigo);
        turmas.add(turmanova);
        SalvarTurma(turmanova);
        Professor professor = BuscarProfessor(matriculaProf);
        professor.addTurma(turmanova.getCodigoTurma());
        Disciplina disciplina = BuscarDisciplina(codigo);
        disciplina.addTurma(turmanova.getCodigoTurma());
        System.out.println("turma criada corretamente");
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
        System.out.println("");
        System.out.println("NUMERO //DISCIPLINA //VAGAS ATUAIS //VAGAS TOTAIS");
        for (Turma turma : turmas)
        {
            if (turma == null) continue;
            System.out.print(turma.getNumero());
            System.out.print(turma.getCodigoDisciplina()+"    "+turma.getVagasAtuais()+"/"+turma.getVagasTotais());
            System.out.println("");
        }
        System.out.println("");
    }
    public static void RemoverDisciplina(Scanner input)
    {
        System.out.println("CUIDADO AO REMOVER DISCIPLINAS");
        System.out.println("digite o codigo da disciplina que será removida: ");
        String codigo = input.nextLine();
        if (ChecarCodigoDisciplina(codigo))
        {
            System.out.println("Disciplina a ser apagada: "+BuscarDisciplina(codigo).getNome());
            System.out.println("para confirmar a remoção da disciplina digite novamente o codigo ");
            String teste = input.nextLine();
            if (teste.equals(codigo))
            {
                Disciplina disciplina = BuscarDisciplina(codigo);
                RemoverDisciplinaArquivo(disciplina);
                System.out.println("disciplina removida");
                disciplinas.remove(BuscarDisciplina(codigo));
            }
            else
            {
                System.out.println("algum erro na confirmação do codigo");
            }
        }
        else
        {
            System.out.println("o codigo não existe");
        }
    }
    public static void RemoverTurma(Scanner input)
    {
        System.out.println("CUIDADO AO REMOVER TURMAS");
        System.out.println("digite o codigo da disciplina da turma");
        String codigoDisciplina = input.nextLine();

        System.out.println("digite o numero da turma");
        int numTurma = input.nextInt();
        input.nextLine(); //come o enter

        if (ChecarTurma(numTurma, codigoDisciplina))
        {
            Turma turma = BuscarTurma(numTurma, codigoDisciplina);
            String nomeDisciplina = BuscarDisciplina(codigoDisciplina).getNome();

            System.out.println("turma a ser apagada: "+turma.getNumero()+" de "+nomeDisciplina);
            System.out.println("para confirmar a remoção da turma digite novamente o codigo da disciplina: ");
            String teste = input.nextLine();

            if (teste.equals(codigoDisciplina))
            {
                System.out.println("turma removida");
                RemoverTurmaArquivo(turma);
                turmas.remove(turma);
            }
            else
            {
                System.out.println("algum erro na confirmação");
            }
        }
        else
        {
            System.out.println("algum erro no codigo da disciplina ou no numero da turma");
        }
    }
    public static void MostrarInfoDisciplina(Scanner input)
    {
        System.out.println("digite o codigo da disciplina que sera consultada");
        String codigo = input.nextLine();
        if (!ChecarCodigoDisciplina(codigo))
        {
            System.out.println("o codigo digitado não existe");
            return; //só pra parar mesmo de rodar
        }
        BuscarDisciplina(codigo).MostrarInfo();
    }
    public static void MostrarInfoTurma(Scanner input)
    {
        System.out.println("digite o codigo da disciplina da turma");
        String codigo = input.nextLine();
        if (!ChecarCodigoDisciplina(codigo))
        {
            System.out.println("o codigo digitado não existe");
            return; 
        }
        Disciplina disciplina = BuscarDisciplina(codigo);
        System.out.println("a disciplina selecionada foi: "+disciplina.getNome()+'/'+disciplina.getCodigo());
        System.out.println("digite o numero da turma");
        int numero = input.nextInt();
        input.nextLine(); //come o enter
        if (!ChecarTurma(numero, codigo))
        {
            System.out.println("a turma não existe");
            return;
        }
        Turma turma = BuscarTurma(numero, codigo);
        turma.MostrarInfo();

    }
    public static void CriarProfessor(Scanner input)
    {
        System.out.println("digite o nome do professor: ");
        String nome = input.nextLine();
        System.out.println("digite a matricula do professor: ");
        int matricula = input.nextInt();
        input.nextLine(); //come o enter

        if (!ChecarMatriculaProf(matricula))
        {
            System.out.println("já existe um professor com essa matricula");
            return;
        }

        Professor professor = new Professor(nome, matricula);
        professores.add(professor);
        SalvarProfessor(professor);
    }
    public static void RemoverProfessor(Scanner input)
    {
        System.out.println("CUIDADO AO REMOVER PROFESSORES");
        System.out.println("digite a matricula do professor");
        int matricula = input.nextInt();
        input.nextLine(); //come o enter

        if (ChecarMatriculaProf(matricula))
        {
            System.out.println("não existe nenhum professor com essa matricula");
            return;
        }
        Professor professor = BuscarProfessor(matricula);
        System.out.println("professor a ser apagado: "+professor.getNome()+'/'+professor.getMatricula());
        System.out.println("para confirmar a remoção do professor digite novamente a matricula do professor");
        int teste = input.nextInt();
        input.nextLine(); //come o enter
        if (! (teste == matricula))
        {
            System.out.println("erro na confirmação da matricula");
            return;
        }
        System.out.println("professor removido");
        RemoverProfessorArquivo(professor);
        professores.remove(professor);
    }
    public static void ListarProfessores()
    {
        System.out.println("");
        System.out.println("NOME //MATRICULA");
        for (Professor professor : professores)
        {
            System.out.print(professor.getNome()+'/');
            System.out.print(professor.getMatricula());
            System.out.println("");
        }
        System.out.println("");
    }
    public static void MostrarInfoProfessor(Scanner input)
    {
        System.out.println("digite a matricula do professor");
        int matricula = input.nextInt();
        input.nextLine();//come o enter

        if (ChecarMatriculaProf(matricula))
        {
            System.out.println("a matricula digitada não pertence a nenhum professor");
            return; 
        }
        Professor professor = BuscarProfessor(matricula);
        System.out.println("o professor selecionado foi: "+professor.getNome()+'/'+professor.getMatricula());
        professor.MostrarInfo();

    }

    public static void ModoNotas(Scanner input)
    {
        int escolha;
        do
        {
            System.out.println("MODO NOTAS");
            System.out.println("digite 1 para lança notas/presença e criar boletim");
            System.out.println("digite 2 para calcular media final e presença");
            System.out.println("digite 3 para exibir relatorio de turma");
            System.out.println("digite 4 para exibir relatorio de disciplina");
            System.out.println("digite 5 para exibir relatorio de professor");
            System.out.println("digite 6 para exibir boletim simples de aluno");
            System.out.println("digite 7 para exibir boletim completo de aluno");
            System.out.println("digite 0 para voltar ao menu anterior");
            escolha = input.nextInt();
            input.nextLine(); //come o enter

            switch (escolha)
            {
                case 1:
                    LancarNota(input);
                    break;
                case 2:
                    CalculadoraNota(input);
                    break;
                case 3: 
                    MostrarRelatorioTurma(input);
                    break;
                case 4:
                    MostrarRelatorioDisciplina(input);
                    break;
                case 5:
                    MostrarRelatorioProfessor(input);
                    break;
                case 6: 
                    MostrarBoletimSimples(input);
                    break;
                case 7:
                    MostrarBoletimCompleto(input);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("DIGITO INVALIDO, DIGITE NOVAMENTE");
                    break;
            }
        } while (escolha!=0);

    }
    public static void LancarNota(Scanner input)
    {
        System.out.println("para lançar notas, primeiro identifique a materia da turma");
        System.out.println("digite o codigo da materia da qual a turma faz parte: ");
        String codigoDisciplina = input.nextLine();

        if (!ChecarCodigoDisciplina(codigoDisciplina))
        {
            System.out.println("não existe disciplina com esse codigo");
            return;
        }

        System.out.println("digite o numero da turma");
        int numeroTurma = input.nextInt();
        input.nextLine(); //come o enter

        if (!ChecarTurma(numeroTurma, codigoDisciplina))
        {
            System.out.println("não existe turma dessa disciplina com esse numero");
            return;
        }

        Turma turma = BuscarTurma(numeroTurma, codigoDisciplina);
        turma.ListarAlunos();
        System.out.println("lista dos alunos acima");
        System.out.println("digite a matricula do aluno cujas notas e presença serão lançadas: ");
        int matricula = input.nextInt();
        input.nextLine(); //come o enter

        String codigoTurma = turma.getCodigoTurma();
        if (!ChecarAlunoTurma(matricula, codigoTurma))
        {
            System.out.println("o aluno não está matriculado nessa turma");
            return;
        }

        Aluno aluno = BuscarAluno(matricula);
        System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());
        System.out.println("digite 1 para lançar nota por nota");
        System.out.println("digite 2 para lançar todas as notas de uma vez");
        int num = input.nextInt();
        input.nextLine();

        float p1;
        float p2;
        float p3;
        float lista;
        float seminario;
        int presenca;
        Boletim boletim;
        if (num == 1)
        {
            System.out.println("as notas devem ser escritas com , exemplo: 5,5");
            System.out.println("digite a nota da p1 no formato float: ");
            p1 = input.nextFloat();
            input.nextLine();
            System.out.println("digite a nota da p2 no formato float: ");
            p2 = input.nextFloat();
            input.nextLine();
            System.out.println("digite a nota da p3 no formato float: ");
            p3 = input.nextFloat();
            input.nextLine();
            System.out.println("digite a nota da lista no formato float: ");
            lista = input.nextFloat();
            input.nextLine();
            System.out.println("digite a nota do seminario no formato float: ");
            seminario = input.nextFloat();
            input.nextLine();
            System.out.println("digite o numero da porcentagem de presença do aluno (exemplo: 75 para 75%): ");
            presenca = input.nextInt();
            input.nextLine();
        }
        else
        {
            System.out.println("digite a nota da p1, nota da p2, nota da p3, nota da lista, nota do seminario e a presenca");
            System.out.println("todas as notas devem ser no formato float (exemplo: 5.5 ou 7.3)");
            System.out.println("a presença deve ser escrita sem a porcentagem, exemplo: 75 para 75%");
            System.out.println("todas as entradas devem ser separadas por espaço, exemplo: p1 p2 p3 lista seminario presença");
            String[] entrada = input.nextLine().split(" ");
            p1 = Float.parseFloat(entrada[0]);
            p2 = Float.parseFloat(entrada[1]);
            p3 = Float.parseFloat(entrada[2]);
            lista = Float.parseFloat(entrada[3]);
            seminario = Float.parseFloat(entrada[4]);
            presenca = Integer.parseInt(entrada[5]);
        }

        boletim = new Boletim(matricula, turma, presenca, turma.getMetodoAvaliacao(), p1, p2, p3, lista, seminario);
        boletins.add(boletim);
        SalvarBoletim(boletim);

        float notaMedia = boletim.getMediaFinal();

        Relatorio relatorioTurma = AcessaOuCriaRelatorio('t', codigoTurma);
        Relatorio relatorioProfessor = AcessaOuCriaRelatorio('p', String.valueOf(turma.getMatriculaProf()));
        Relatorio relatorioDisciplina = AcessaOuCriaRelatorio('d', codigoDisciplina);
        if (boletim.getPassou() == 0)
        {
            turma.removerAluno(matricula);
            aluno.addDisciplina(codigoDisciplina); //adiciona a disciplina que o aluno passou
            //adicionar que ele passou no relatorio do prof/turma/disciplina
            System.out.println("o aluno passou");
            relatorioDisciplina.addPassaram();
            relatorioTurma.addPassaram();
            relatorioProfessor.addPassaram();
        }
        else if (boletim.getPassou() == 1)
        {
            turma.removerAluno(aluno);
            System.out.println("reprovado por faltas (frequencia abaixo de 75%)");
            relatorioDisciplina.addReprovaramFalta();
            relatorioTurma.addReprovaramFalta();
            relatorioProfessor.addReprovaramFalta();
        }
        else if (boletim.getPassou() == 2)
        {
            turma.removerAluno(aluno);
            System.out.println("reprovado por nota, media abaixo de 5");
            relatorioDisciplina.addReprovaramNota();
            relatorioTurma.addReprovaramNota();
            relatorioProfessor.addReprovaramNota();
        }
        else 
        {
            turma.removerAluno(aluno);
            System.out.println("reprovado por nota e falta");
            relatorioDisciplina.addReprovaramNotaEFalta();
            relatorioTurma.addReprovaramNotaEFalta();
            relatorioProfessor.addReprovaramNotaEFalta();
        }
        relatorioDisciplina.addNotaMedia(notaMedia);
        relatorioTurma.addNotaMedia(notaMedia);
        relatorioProfessor.addNotaMedia(notaMedia);
        SalvarRelatorio(relatorioDisciplina);
        SalvarRelatorio(relatorioTurma);
        SalvarRelatorio(relatorioProfessor);
        System.out.println("relatorios criados ou atualizados");
        System.out.println("todos os boletins pode ser encontrados na pasta banco_de_dados/boletins, eles estão separados por aluno");
    }
    public static void CalculadoraNota(Scanner input)
    {
        System.out.println("vamos fazer uma simulação de notas e presença");
        System.out.println("digite 1 para lançar nota por nota");
        System.out.println("digite 2 para lançar todas as notas de uma vez");

        int num = input.nextInt();
        input.nextLine();

        float p1;
        float p2;
        float p3;
        float lista;
        float seminario;
        int presenca;

        if (num == 1)
        {
            System.out.println("as notas devem ser escritas com , exemplo: 5,5");
            System.out.println("digite a nota da p1 no formato float: ");
            p1 = input.nextFloat();
            input.nextLine();

            System.out.println("digite a nota da p2 no formato float: ");
            p2 = input.nextFloat();
            input.nextLine();

            System.out.println("digite a nota da p3 no formato float: ");
            p3 = input.nextFloat();
            input.nextLine();

            System.out.println("digite a nota da lista no formato float: ");
            lista = input.nextFloat();
            input.nextLine();

            System.out.println("digite a nota do seminario no formato float: ");
            seminario = input.nextFloat();
            input.nextLine();

            System.out.println("digite o numero da porcentagem de presença do aluno (exemplo: 75 para 75%): ");
            presenca = input.nextInt();
            input.nextLine();
        }
        else
        {
            System.out.println("digite a nota da p1, nota da p2, nota da p3, nota da lista, nota do seminario e a presenca");
            System.out.println("todas as notas devem ser no formato float, exemplo: 5.5 ou 4.8");
            System.out.println("a presença deve ser escrita sem a porcentagem, exemplo: 75 para 75%");
            System.out.println("todas as entradas devem ser separadas por espaço, exemplo: p1 p2 p3 lista seminario presença");
            String[] entrada = input.nextLine().split(" ");
            p1 = Float.parseFloat(entrada[0]);
            p2 = Float.parseFloat(entrada[1]);
            p3 = Float.parseFloat(entrada[2]);
            lista = Float.parseFloat(entrada[3]);
            seminario = Float.parseFloat(entrada[4]);
            presenca = Integer.parseInt(entrada[5]);
        }

        float notaMediaA = (p1+p2+p3+lista+seminario)/5;
        float notaMediaB = (p1+(p2*2)+(p3*3)+lista+seminario)/8;
        System.out.println("a media final do aluno para o metodo a de avaliação seria: "+notaMediaA);
        System.out.println("a media final do aluno para o metodo b de avaliação seria: "+notaMediaB);

        System.out.println("a situação final do aluno seria: ");

        if (presenca < 75)
        {
            System.out.println("reprovado por falta");
        }
        else
        {
            System.out.println("aprovado por presença");
        }
        if (notaMediaA < 5)
        {
            System.out.println("reprovado por nota no metodo a");
        }
        else
        {
            System.out.println("aprovado por nota no metodo a");
        }
        if (notaMediaB < 5)
        {
            System.out.println("reprovado por nota no metodo b");
        }
        else
        {
            System.out.println("aprovado por nota no metodo b");
        }
        
    }
    public static void MostrarRelatorioTurma(Scanner input)
    {
        System.out.println("digite o codigo da disciplina da turma");
        String codigoDisciplina = input.nextLine();

        if (!ChecarCodigoDisciplina(codigoDisciplina))
        {
            System.out.println("não existe disciplina com esse codigo");
            return;
        }

        System.out.println("para exibir o relatorio, digite o numero da turma");
        int numTurma = input.nextInt();
        input.nextLine(); //come o enter

        if (!ChecarTurma(numTurma, codigoDisciplina))
        {
            System.out.println("não existe turma dessa disciplina com esse numero ");
            return;
        }

        Turma turma = BuscarTurma(numTurma, codigoDisciplina);
        Relatorio relatorio = AcessaOuCriaRelatorio('t', turma.getCodigoTurma());

        if (relatorio.getNotaMedia() == 0 &&
            relatorio.getPassaram() == 0 &&
            relatorio.getReprovaram() == 0 &&
            relatorio.getTotalAlunos() == 0 &&
            relatorio.getTrancaram() == 0)
            {
                System.out.println("essa turma ainda não tem relatorio");
                System.out.println("lançar notas ou trancar disciplinas cria relatorios...");
                return;
            }

        relatorio.MostrarRelatorio();
    }
    public static void MostrarRelatorioDisciplina(Scanner input)
    {
        System.out.println("para exibir o relatorio, digite o codigo da disciplina: ");
        String codigoDisciplina = input.nextLine();

        if (!ChecarCodigoDisciplina(codigoDisciplina))
        {
            System.out.println("não existe disciplina com esse codigo");
            return;
        }

        Relatorio relatorio = AcessaOuCriaRelatorio('d', codigoDisciplina);

        if (relatorio.getNotaMedia() == 0 &&
            relatorio.getPassaram() == 0 &&
            relatorio.getReprovaram() == 0 &&
            relatorio.getTotalAlunos() == 0 &&
            relatorio.getTrancaram() == 0)
            {
                System.out.println("essa disciplina ainda não tem relatorio");
                System.out.println("lançar notas ou trancar disciplinas cria relatorios...");
                return;
            }

        relatorio.MostrarRelatorio();
    }
    public static void MostrarRelatorioProfessor(Scanner input)
    {
        System.out.println("para mostrar o relatorio de professor, digite a matricula do profesor: ");
        int matricula = input.nextInt();
        input.nextLine(); //come o enter

        if (!ChecarMatriculaProf(matricula))
        {
            System.out.println("não existe professor com essa matricula");
            return;
        }

        Relatorio relatorio = AcessaOuCriaRelatorio('p', String.valueOf(matricula));

        if (relatorio.getNotaMedia() == 0 &&
            relatorio.getPassaram() == 0 &&
            relatorio.getReprovaram() == 0 &&
            relatorio.getTotalAlunos() == 0 &&
            relatorio.getTrancaram() == 0)
            {
                System.out.println("esse professor ainda não tem relatorio");
                System.out.println("lançar notas ou trancar disciplinas cria relatorios...");
                return;
            }

        relatorio.MostrarRelatorio();
    }
    public static void MostrarBoletimSimples(Scanner input)
    {
        System.out.println("digite a matricula do aluno");
        int matricula = input.nextInt();
        input.nextLine(); //come o enter

        if (!ChecarMatricula(matricula))
        {
            System.out.println("a matricula digitada não existe");
            return;
        }

        Aluno aluno = BuscarAluno(matricula);
        System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());

        System.out.println("Exibindo todos os boletins do aluno...");

        boolean encontrado = false;
        for (Boletim boletim : boletins)
        {
            if (boletim.getMatriculaAluno() == matricula)
            {
                boletim.MostrarInfoSimples();
                encontrado=true;
            }
        }

        if (!encontrado)
        {
            System.out.println("Esse aluno não possui boletins registrados no sistema");
        }
    }
    public static void MostrarBoletimCompleto(Scanner input)
    {
        System.out.println("digite a matricula do aluno");
        int matricula = input.nextInt();
        input.nextLine(); //come o enter

        if (!ChecarMatricula(matricula))
        {
            System.out.println("a matricula digitada não existe");
            return;
        }

        Aluno aluno = BuscarAluno(matricula);
        System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());

        System.out.println("Exibindo todos os boletins do aluno...");

        boolean encontrado = false;
        for (Boletim boletim : boletins)
        {
            if (boletim.getMatriculaAluno() == matricula)
            {
                boletim.MostrarInfoExpandido();
                encontrado=true;
            }
        }

        if (!encontrado)
        {
            System.out.println("Esse aluno não possui boletins registrados no sistema");
        }
    }

    public static Relatorio AcessaOuCriaRelatorio(char tipo, String identificador)
    {
        for (Relatorio relatorio : relatorios)
        {
            if (relatorio.getCharTipo() == tipo && relatorio.getIdentificador().equals(identificador))
            {
                return relatorio; //caso encontre um que já existe
            }
        }
        Relatorio criado = new Relatorio(tipo, identificador); //caso não encontre, cria um
        relatorios.add(criado);
        return criado;
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
    public static boolean ChecarAlunoTurma(int matricula, String codigoTurma)
    {
        if (!ChecarTurma(codigoTurma))
        {
            return false;
        }
        Turma turma = BuscarTurma(codigoTurma);
        for (Integer matriculaAluno : turma.getAlunos())
        {
            if (matriculaAluno == matricula)
            {
                return true;
            }
        }
        return false;
    }
    public static Aluno BuscarAluno(int matricula)
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
    public static boolean ChecarCodigoDisciplina(String codigo)
    {
        for (Disciplina disciplina : disciplinas)
        {
            if (disciplina.getCodigo().equals(codigo))
            {
                return true;
            }
        }
        return false;
    }
    public static Disciplina BuscarDisciplina(String codigo)
    {
        for (Disciplina disciplina : disciplinas)
        {
            if (disciplina.getCodigo().equals(codigo))
            {
                return disciplina;
            }
        }
        return null;
    }    
    public static boolean ChecarTurma(int numero, String codigoDisciplina)
    {
        boolean achou = false;
        if (ChecarCodigoDisciplina(codigoDisciplina))
        {
            for (Turma turma : turmas)
            {
                if (turma.getNumero()==numero && turma.getCodigoDisciplina().equals(codigoDisciplina))
                {
                    achou = true;
                }
            }
        }
        return achou;
    }
    public static boolean ChecarTurma(String codigoTurma)
    {
        boolean achou = false;
        for (Turma turma : turmas)
        {
            if (turma.getCodigoTurma().equals(codigoTurma))
            {
                achou = true;
            }
        }
        return achou;
    }
    public static Turma BuscarTurma(int numero, String codigoDisciplina)
    {
        if (ChecarCodigoDisciplina(codigoDisciplina))
        {
            for (Turma turma : turmas)
            {
                if (turma.getNumero()==numero && turma.getCodigoDisciplina().equals(codigoDisciplina))
                {
                    return turma;
                }
            }
        }
    return null;
    }
    public static Turma BuscarTurma(String codigoTurma)
    {
        if (ChecarTurma(codigoTurma))
        {
            for (Turma turma : turmas)
            {
                if (turma.getCodigoTurma().equals(codigoTurma))
                {
                    return turma;
                }
            }
        }
    return null;
    }
    public static boolean ChecarHorario(int horario)
{
    for (Turma turma : turmas)
    {
        if (turma == null) continue;
        int inicioExistente = turma.getHorario();
        int fimExistente = inicioExistente + 2;

        int inicioNovo = horario;
        int fimNovo = horario + 2;

        if (!(inicioNovo >= fimExistente || fimNovo <= inicioExistente))
        {
            return false; 
        }
    }
    return true; 
}
    public static boolean ChecarSala(String sala)
    {
        for (Turma turma: turmas)
        {
            if (sala.equals(turma.getSala()))
            {
                return false;
            }
        }
        return true;
    }
    public static boolean ChecarProfTurma(int matriculaProf)
    {
        for (Turma turma : turmas)
        {
            if (turma.getMatriculaProf() == matriculaProf)
            {
                return false;
            }
        }
        return true;
    }
    public static boolean ChecarMatriculaProf(int matricula)
    {
        for (Professor professor : professores)
        {
            if (professor.getMatricula() == matricula)
            {
                return false;
            }
        }
        return true;
    }
    public static Professor BuscarProfessor(int matricula)
    {
        if (ChecarMatriculaProf(matricula))
        {
            return null;
        }
        for (Professor professor : professores)
        {
            if (professor.getMatricula() == matricula)
            {
                return professor;
            }
        }
        return null;
    }


    //parte de arquivos
    public static void SalvarAlunoIndividual(Aluno aluno)
    {
        String pasta = "banco_de_dados/alunos"; //caminho do diretorio
        new File(pasta).mkdirs(); //cria a pasta
        String caminhoArquivo = (pasta+'/'+aluno.getMatricula()+"aluno.txt");//caminho completo do arquivo
        try (BufferedWriter salvar = new BufferedWriter(new FileWriter(caminhoArquivo)))
        {
            salvar.write(aluno.toString()); //escreve o conteudo de aluno convertido pra string
        } catch(IOException erro)
        {
            System.out.println("Erro ao salvar aluno "+aluno.getNome()+'/'+aluno.getMatricula()+':'+ erro.getMessage());
        }
    }
    public static void CarregarAlunos()
    {
        File pasta = new File("banco_de_dados/alunos"); //aponta onde vão estar as coisas
        if (pasta.exists() && pasta.isDirectory()) //checa se existe e se é diretorio
        {
            File[] arquivos = pasta.listFiles((dir, nome) -> nome.endsWith("aluno.txt")); //filtrando pra pegar só os com o fim selecionado
            if (arquivos !=null)
            {
                for (File arquivo : arquivos) //para cada arquivo
                {
                    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) //cria o leitor de arquivo
                    {
                        String dados = leitor.readLine(); //TEM QUE SER OBRIGATORIAMENTE UMA LINHA SÓ
                        Aluno aluno;
                        if (dados.startsWith("ESPECIAL;"))
                        {
                            aluno = AlunoEspecial.fromString(dados);
                        }
                        else
                        {
                            aluno = Aluno.fromString(dados);//cria o aluno usando fromstring
                        }
                        if (aluno != null)
                        {
                            alunos.add(aluno); //adiciona no sistema
                        }
                        
                    } catch (IOException | NullPointerException erro)
                    {
                        System.out.println("erro ao carregar arquivos iniciais do sistema: "+arquivo.getName());
                    }
                }
            }
        }
    }
    public static void RemoverAlunoArquivo(int matricula)
    {
        String caminho = "banco_de_dados/alunos/" + matricula + "aluno.txt"; //local do arquivo
        File arquivo = new File(caminho); //encontra o arquivo

        if (arquivo.exists())
        {
            arquivo.delete();
        }
    }
    public static void SalvarTurma(Turma turma)
    {
        if ( //verifica dados invalidos
            turma == null ||
            turma.getMatriculaProf() == 0 ||
            turma.getCodigoDisciplina() == null ||
            (turma.getMetodoAvaliacao() !='a' && turma.getMetodoAvaliacao() !='b') ||
            turma.getHorario() == 0 ||
            turma.getNumero() == 0 ||
            turma.getSemestre() == 0 
        ){
            System.out.println("Erro: salvando turmas com dados invalidos");
            return;
         }

        String pasta = "banco_de_dados/turmas";
        new File(pasta).mkdirs();
        String caminhoArquivo = (pasta+'/'+turma.getNumero()+"DE"+turma.getCodigoDisciplina()+"turma.txt");
        try (BufferedWriter salvar = new BufferedWriter(new FileWriter(caminhoArquivo)))
        {
            salvar.write(turma.toString());
        } catch (IOException erro)
        {
            System.out.println("Erro ao salvar turma "+turma.getNumero()+"DE"+turma.getCodigoDisciplina()+':'+erro.getMessage());
        }
    }
    public static void CarregarTurmas()
    {
        File pasta = new File("banco_de_dados/turmas");
        if (pasta.exists() && pasta.isDirectory())
        {
            File[] arquivos = pasta.listFiles((dir, nome) -> nome.endsWith("turma.txt"));
            if (arquivos !=null)
            {
                for (File arquivo : arquivos)
                {
                    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo)))
                    {   
                        String dados = leitor.readLine();
                        Turma turma = Turma.fromString(dados);
                        turmas.add(turma);
                    } catch (IOException | NullPointerException erro)
                    {
                        System.out.println("erro ao carregar arquivos iniciais do sistema: "+arquivo.getName());
                    }
                }
            }
        }
    }
    public static void RemoverTurmaArquivo(Turma turma)
    {
        int numero = turma.getNumero();
        String codigoDisciplina = turma.getCodigoDisciplina();
        String caminho = "banco_de_dados/turmas/"+numero+"DE"+codigoDisciplina+"turma.txt";
        File arquivo = new File(caminho);

        if (arquivo.exists())
        {
            arquivo.delete();
        }
    }
    public static void SalvarProfessor(Professor professor)
    {
        String pasta = "banco_de_dados/professores";
        new File(pasta).mkdirs();
        String caminhoArquivo = (pasta +'/'+professor.getMatricula()+"professor.txt");
        try (BufferedWriter salvar = new BufferedWriter(new FileWriter(caminhoArquivo)))
        {
            salvar.write(professor.toString());
        } catch (IOException erro)
        {
            System.out.println("Erro ao salvar professor"+professor.getNome()+'/'+professor.getMatricula()+':'+erro.getMessage());
        }
    }
    public static void CarregarProfessor()
    {
        File pasta = new File("banco_de_dados/professores");
        if (pasta.exists() && pasta.isDirectory())
        {
            File[] arquivos = pasta.listFiles((dir,nome) -> nome.endsWith("professor.txt"));
            if (arquivos !=null)
            {
                for (File arquivo : arquivos)
                {
                    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo)))
                    {
                        String dados = leitor.readLine();
                        Professor professor = Professor.fromString(dados);
                        professores.add(professor);
                    } catch (IOException | NullPointerException erro)
                    {
                        System.out.println("Erro ao carregar arquivos iniciais do sistema: "+arquivo.getName());
                    }
                }
            }
        }
    }
    public static void RemoverProfessorArquivo(Professor professor)
    {
        int matricula = professor.getMatricula();
        String caminho = ("banco_de_dados/professores/"+matricula+"professor.txt");
        File arquivo = new File(caminho);

        if (arquivo.exists())
        {
            arquivo.delete();
        }
    }

    public static void SalvarDisciplina(Disciplina disciplina)
    {
        String pasta = "banco_de_dados/disciplinas";
        new File(pasta).mkdirs();
        String caminhoArquivo = (pasta +'/'+disciplina.getCodigo()+"disciplina.txt");
        try (BufferedWriter salvar = new BufferedWriter(new FileWriter(caminhoArquivo)))
        {
            salvar.write(disciplina.toString());
        } catch(IOException erro)
        {
            System.out.println("Erro ao salvar disciplina"+ disciplina.getCodigo()+'/'+disciplina.getNome()+':'+erro.getMessage());
        }
    }

    public static void CarregarDisciplinas()
    {
        File pasta = new File("banco_de_dados/disciplinas");
        if (pasta.exists() && pasta.isDirectory())
        {
            File[] arquivos = pasta.listFiles((dir, nome) -> nome.endsWith("disciplina.txt"));
            if (arquivos !=null)
            {
                for (File arquivo : arquivos)
                {
                    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo)))
                    {
                        String dados = leitor.readLine();
                        Disciplina disciplina = Disciplina.fromString(dados);
                        disciplinas.add(disciplina);
                    } catch (IOException | NullPointerException erro)
                    {
                        System.out.println("erro ao carregar arquivos iniciais do sistema: "+arquivo.getName());
                    }
                }
            }
        }
    }
    public static void RemoverDisciplinaArquivo(Disciplina disciplina)
    {
        String codigo = disciplina.getCodigo();
        String caminho = ("banco_de_dados/disciplinas/"+codigo+"disciplina.txt");
        File arquivo = new File(caminho);

        if (arquivo.exists())
        {
            arquivo.delete();
        }
    }
    public static void SalvarBoletim(Boletim boletim)
    {
        String pasta = "banco_de_dados/boletins/"+boletim.getMatriculaAluno();
        new File(pasta).mkdirs();
        String caminhoArquivo = (pasta+'/'+"semestre"+boletim.getSemestre()+"disciplina"+boletim.getDisciplina()+"boletim.txt");
        try (BufferedWriter salvar = new BufferedWriter(new FileWriter(caminhoArquivo)))
        {
            salvar.write(boletim.toString());
        } catch (IOException erro)
        {
            System.out.println("Erro ao salvar Boletim");
        }
    }
    public static void CarregarBoletins()
    {
        File pasta = new File("banco_de_dados/boletins");
        if (pasta.exists() && pasta.isDirectory())
        {
            CarregarBoletinsRecursivamente(pasta);
        }
    }
    public static void CarregarBoletinsRecursivamente(File pasta)
    {
        File[] arquivos = pasta.listFiles();
        if (arquivos !=null)
        {
            for (File arquivo : arquivos)
            {
                if (arquivo.isDirectory()) //caso seja diretorio
                {
                    CarregarBoletinsRecursivamente(arquivo); //serve pra buscar subpastas
                }
                else if (arquivo.getName().endsWith("boletim.txt")) //caso seja um arquivo de boletim mesmo
                {
                    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo)))
                    {
                        String dados = leitor.readLine();
                        Boletim boletim = Boletim.fromString(dados);
                        if (boletim !=null)
                        {
                            boletins.add(boletim);
                        }
                    } catch (IOException | NullPointerException e)
                    {
                        System.out.println("Erro ao carregar boletim do arquivo: "+arquivo.getName());
                    }
                }
            }
        }
    }
    public static void SalvarRelatorio(Relatorio relatorio)
    {
        String pasta = "banco_de_dados/relatorios";
        new File(pasta).mkdirs();
        String caminhoArquivo = pasta+'/'+relatorio.getCharTipo()+"ID"+relatorio.getIdentificador()+"relatorio.txt";
        try (BufferedWriter salvar = new BufferedWriter(new FileWriter(caminhoArquivo)))
        {
            salvar.write(relatorio.toString());
        }catch (IOException erro)
        {
            System.out.println("Erro ao salvar Relatorio");
        }
    }
    public static void CarregarRelatorios()
    {
        File pasta = new File("banco_de_dados/relatorios");
        if (pasta.exists() && pasta.isDirectory())
        {
            File[] arquivos = pasta.listFiles((dir, nome) -> nome.endsWith("relatorio.txt"));
            if (arquivos !=null)
            {
                for (File arquivo : arquivos)
                {
                    try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo)))
                    {
                        String dados = leitor.readLine();
                        Relatorio relatorio = Relatorio.fromString(dados);
                        relatorios.add(relatorio);
                    } catch (IOException | NullPointerException e)
                    {
                        System.out.println("Erro ao carregar arquivos iniciais do sistema: "+arquivo.getName());
                    }
                }
            }
        }
    }
    
    public static void SalvarTudo()
    {
        for (Aluno aluno : alunos)
        {
            SalvarAlunoIndividual(aluno);
        }
        for (Disciplina disciplina : disciplinas)
        {
            SalvarDisciplina(disciplina);
        }
        for (Turma turma : turmas)
        {
            SalvarTurma(turma);
        }
        for (Professor professor : professores)
        {
            SalvarProfessor(professor);
        }
        for (Boletim boletim : boletins)
        {
            SalvarBoletim(boletim);
        }
        for (Relatorio relatorio : relatorios)
        {
            SalvarRelatorio(relatorio);
        }
    }

}