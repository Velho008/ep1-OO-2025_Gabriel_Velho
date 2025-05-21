package classes;

import java.util.*;
import java.io.*;

public class SIGAA2 
{

    //talvez não salva boletins, só relatorio, talvez fazer um txt formatado pro boletim e o usuario faz o que quiser com isso
    //nesse txt do boletim, adicionar novas turmas que o aluno fizer

    //criar o aluno especial
    //TERMINAR O BOLETIM //quando lançar as notas, passar o aluno que passar na disciplina e colocar na lista de materias feitas pelo aluno
    // quando lançar nota, adicionar ao relatorio da materia se ele passou ou reprovou, como reprovou, com que nota passou
    // quando for lançar nota e presença, pode lançar só de um aluno ou da turma toda
    // quando for lançar a nota de cada aluno, pode digitar tudo espaçado ou um de cada vez
    //falta o modo notas inteiro
    //{ 
    //criar boletim de aluno por semestre para poder lançar a nota
    //lançar notas e presenças (caso o aluno não passe, falar pq, caso passe, adicionar a materia nas que ele já fez)
    //calcular media final e presença e salvar no boletim do semestre
    //relatorio de turma {quantos alunos passaram, quantas aulas tiveram, nota media de cada prova}
    //relatorio de disciplina {quantas turmas tem, quantos alunos passaram, nota media de cada prova}
    //relatorio de professor {quantos alunos tem (soma das turmas que ministra) quantos passaram, media de cada prova}
    // exibir boletim do aluno por semestre com ou sem dados da turma
    //}
    
    static ArrayList<Aluno> alunos = new ArrayList<>(); //serve pra manter e criar alunos
    static ArrayList<Disciplina> disciplinas = new ArrayList<>(); //serve pra manter e criar disciplinas
    static ArrayList<Turma> turmas = new ArrayList<>(); //serve para manter e criar turmas
    static ArrayList<Professor> professores = new ArrayList<>(); //serve pra criar e manter professores

        public static void main(String[] args)
    {
        //carregar as coisas dos arquivos
        CarregarProfessor();
        CarregarDisciplinas();
        CarregarTurmas();
        CarregarAlunos();
        

        Scanner input1 = new Scanner(System.in);//scanner usado no menu inteiro
        int escolha;//variavel de escolha do menu

        do
        {
            
            System.out.println("SIGAA2.0");
            System.out.println("digite 1 para o modo aluno");
            System.out.println("digite 2 para o modo turma");
            System.out.println("digite 3 para o modo notas");
            System.out.println("digite 0 para sair"); //falta salvar tudo quando for fechar
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
                    SalvarTudo(); //salva tudo antes de fechar
                    input1.close();
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
            Aluno aluno = new Aluno(nome,matricula,curso,disciplinas_cursadas);//cria de fato o aluno e cadastra no sistema (adiciona na lista)
            alunos.add(aluno);
            SalvarAlunoIndividual(aluno);
        }
        else
        {
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
        if (ChecarMatricula(matricula))
        {
            Aluno aluno = BuscarAluno(matricula);
            System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());
            System.out.println("digite o codigo da disciplina: ");
            String codigo = input.nextLine();
            if (ChecarCodigoDisciplina(codigo))
            {
                Disciplina disciplina = BuscarDisciplina(codigo);
                System.out.println("disciplina selecionada: " + disciplina.getNome()+'/'+disciplina.getCodigo()); 

                //AQUI
                for (String codTurma : disciplina.getTurmasDaDisciplina())
                {
                    Turma turma = BuscarTurma(codTurma);
                    for (Integer matriculaAluno : turma.getAlunos())
                    {
                        if (matriculaAluno == matricula)
                        {
                            System.out.println("O ALUNO JÁ FAZ ESSA DISCIPLINA");
                            return;
                        }
                    }
                }

                boolean confirmador = false;
                if (!disciplina.getPreRequisitos().isEmpty() && !aluno.getDisciplinasCursadas().isEmpty())
                {
                    if (aluno.getDisciplinasCursadas().containsAll(disciplina.getPreRequisitos()))
                    {
                        confirmador = true;
                    }
                }
                if (disciplina.getPreRequisitos().isEmpty() || confirmador)
                {
                    System.out.println("digite o numero da turma que o aluno "+aluno.getNome()+" será matriculado:");
                    int num = input.nextInt();
                    input.nextLine(); //come o enter
                    if (ChecarTurma(num, codigo))
                    {
                        Turma turma = BuscarTurma(num, codigo);
                        System.out.println("turma selecionada: "+turma.getNumero());
                        turma.addAluno(aluno);
                        System.out.println("aluno matriculado com sucesso, vagas atuais da turma: "+turma.getVagasAtuais()+'/'+turma.getVagasTotais());
                    }
                    else
                    {
                        System.out.println("a turma selecionada não existe");
                    }
                    
                }
                else
                {
                    System.out.println("o aluno não tem algum dos pré-requisitos para cursar a disciplina");
                    System.out.println("são eles");
                    System.out.println(disciplina.getPreRequisitos());
                }
            }
            else
            {
                System.out.println("codigo da disciplina inexistente");
            }
        }
        else
        {
            System.out.println("matricula não existe");
        }
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
                System.out.println("CUIDADO AO EDITAR DADOS DE ALUNOS");
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
        System.out.println("trancar matricula geral = sair de todas as turmas");
        System.out.println("trancar matricula de materia = sair da turma da materia");
        System.out.println("digite a matricula do aluno: ");
        int matricula = input.nextInt();
        input.nextLine(); //come o enter
        if (ChecarMatricula(matricula))
        {
            Aluno aluno = BuscarAluno(matricula);
            System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());
            System.out.println("digite 1 para realizar o trancamento geral de matricula");
            int escolha = input.nextInt();
            input.nextLine(); //come o enter
            if (escolha == 1)
            {
                for (Turma turma : turmas)
                {
                    for (int alunoDaTurma : turma.getAlunos())
                    {
                        if (alunoDaTurma == aluno.getMatricula())
                        {
                            turma.removerAluno(aluno);
                        }
                    }
                }
            }
            else
            {
                System.out.println("digite o codigo da disciplina que será trancada: ");
                String codigo = input.nextLine();
                if (ChecarCodigoDisciplina(codigo))
                {
                    System.out.println("digite o numero da turma: ");
                    int num = input.nextInt();
                    input.nextLine(); //come o enter 

                    if (ChecarTurma(num, codigo))
                    {
                        Turma turma = BuscarTurma(num, codigo);
                        System.out.println("digite novamente a matricula do aluno para confirmar o trancamento");
                        int teste = input.nextInt();
                        input.nextLine(); //come o enter
                        if (teste == aluno.getMatricula())
                        {
                            turma.removerAluno(aluno);
                        }
                        else
                        {
                            System.out.println("matricula errada, voltando ao menu anterior");
                        }
                    }
                    else
                    {
                        System.out.println("turma selecionada não existe");
                    }
                }
                else
                {
                    System.out.println("o codigo da disciplina não existe");
                }
            }
        }
        else
        {
            System.out.println("o aluno selecionado não existe");
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
        System.out.println("digite o horario de inicio da aula no formato: 8 (8AM) ou 12(meio dia)");
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
            System.out.println("digite 1 para lança notas/presença");
            System.out.println("digite 2 para calcular media final e presença"); 
            System.out.println("digite 3 para exibir relatorio de turma");
            System.out.println("digite 4 para exibir relatorio de disciplina");
            System.out.println("digite 5 para exibir relatorio de professor");
            System.out.println("digite 6 para exibir boletim de aluno");
            escolha = input.nextInt();
            input.nextLine(); //come o enter

            switch (escolha)
            {
                case 1:
                    LancarNota(input); //falta fazer ainda
                    break;
                case 2:

                    break;
                case 3: 

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6: 

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
        System.out.println("escolha um aluno da turma: ");
        turma.ListarAlunos();
        System.out.println("digite a matricula do aluno cujas notas e presença serão lançadas");
        int matricula = input.nextInt();
        input.nextLine(); //come o enter
        String codigoTurma = turma.getCodigoTurma();
        if (!ChecarAlunoTurma(matricula, codigoTurma))
        {
            System.out.println("o aluno não está matriculado nessa turma");
            return;
        }
        Aluno aluno = BuscarAluno(matricula);


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
                        Aluno aluno = Aluno.fromString(dados); //cria o aluno usando fromstring
                        alunos.add(aluno); //adiciona no sistema
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
    public static void SalvarBoletim
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
    }

}