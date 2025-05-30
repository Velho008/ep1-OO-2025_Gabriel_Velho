package classes;

import java.util.*;
import java.io.*;

public class SIGAA2 
{
    
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
        CarregarAlunos();
        CarregarTurmas();
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

            escolha = LerInt(input1, "");

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

            escolha = LerInt(input, "");

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
        System.out.println("digite o nome: (ou 0 para voltar)");
        String nome = input.nextLine();
        if (nome.equals("0"))
        {
            return;
        }

        int matricula = LerInt(input, "digite a matricula: (ou 0 para voltar)");
        if (matricula == 0)
        {
            return;
        }

        for (Aluno aluno : alunos) //checa matricula dupla
        {
            if (aluno.getMatricula() == matricula) 
            {
                System.out.println("essa matricula já existe");
                System.out.println("ela pertence ao aluno de nome: "+aluno.getNome());
                return;
            }
        }

        System.out.println("digite o curso: (ou 0 para voltar)");
        String curso = input.nextLine();
        if (curso.equals("0"))
        {
            return;
        }

        int escolha = LerInt(input, "o aluno é calouro? digite 1 para sim ou outro num para não");
        if (escolha !=1)
        {
            boolean checar = false;
            String disciplinas_cursadas;
            System.out.println("digite os codigos das disciplinas que esse aluno já cursou, separados por espaço (ou 0 para voltar)");
            do
            {
                disciplinas_cursadas = input.nextLine(); // ver se existem
                if (disciplinas_cursadas.equals("0")) return;
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

            escolha = LerInt(input, "digite 1 caso o aluno seja especial ou outro numero para normal (pode cursar apenas 2 disciplinas e não recebe nota, apenas presença)");
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
        escolha = LerInt(input, "digite 1 caso o aluno seja especial (pode cursar apenas 2 disciplinas e não recebe nota, apenas presença)");
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
        int matricula = LerInt(input, "digite a matricula do aluno que vai ser matriculado (ou 0 para voltar)");
        if (matricula == 0)
        {
            return;
        }

        if (!ChecarMatricula(matricula))
        {
            System.out.println("matricula não existe no sistema");
            return;
        }
        Aluno aluno = BuscarAluno(matricula);
        ListarDisciplinas();
        System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());

        System.out.println("digite o codigo da disciplina: (ou 0 para voltar)");
        String codigoDisciplina = input.nextLine();
        if (codigoDisciplina.equals("0"))
        {
            return;
        }

        if (!ChecarCodigoDisciplina(codigoDisciplina))
        {
            System.out.println("não existe disciplina com esse codigo");
            return;
        }

        Disciplina disciplina = BuscarDisciplina(codigoDisciplina);
        System.out.println("disciplina selecionada: " + disciplina.getNome()+'/'+disciplina.getCodigo()); 

        for (String codTurma : disciplina.getTurmasDaDisciplina())
        {
            Turma turma = BuscarTurma(codTurma);
            for (Integer matriculaAluno : turma.getAlunos())
            {
                if (matriculaAluno == matricula)
                {
                    System.out.println("ALUNO JÁ MATRICULADO NESSA DISCIPLINA");
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

        ListarTurmas(); //lista todas as turmas que existem, não só as da disciplina
        int numTurma = LerInt(input, "digite o numero da turma que o aluno "+aluno.getNome()+" será matriculado: (ou 0 para voltar)");
        if (numTurma == 0)
        {
            return;
        }

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
            } 
            else
            {
                especial.addDisciplinaAtual();
            }
        }
        turma.addAluno(aluno);
        SalvarTudo();
        System.out.println("aluno matriculado com sucesso, vagas atuais da turma: "+turma.getVagasAtuais()+'/'+turma.getVagasTotais());   
    }
    
    public static void EditarAluno(Scanner input)
    {
        int escolha;
        int matriculaVelha; //SERVE PRA ESCOLHER O ALUNO
        int teste; //serve para checar se a matricula digitada é a correta

        ListarAlunos();

        matriculaVelha = LerInt(input, "digite a matricula do aluno cujo cadastro vai ser editado (ou 0 para voltar)");
        if (matriculaVelha == 0) return;

        if (!ChecarMatricula(matriculaVelha))
        {
            System.out.println("a matricula digitada não existe...");
            System.out.println("voltando ao menu anterior");
            return;
        }

        do
        {
            Aluno aluno = BuscarAluno(matriculaVelha);
            System.out.println("aluno escolhido: "+aluno.getNome()+" matricula: "+aluno.getMatricula());

            System.out.println("digite 1 para alterar o nome");
            System.out.println("digite 2 para alterar o curso");
            System.out.println("digite 3 para alterar a matricula");
            System.out.println("digite 4 para adicionar disciplinas que o aluno já fez");
            System.out.println("digite 5 para remover disciplinas que o aluno já fez");
            System.out.println("digite 0 para voltar ao menu anterior");

            escolha = LerInt(input, "");

            String codigo;
            Disciplina disciplina;
            switch (escolha)
            {
                case 1:
                    System.out.println("digite o novo nome: (ou 0 para voltar)");
                    String nome = input.nextLine();
                    if (nome.equals("0"))
                    {
                        return;
                    }

                    System.out.println("o nome antigo era: "+aluno.getNome()+" o novo nome será: "+nome);

                    teste = LerInt(input, "digite a matricula do aluno para confirmar a mudança");

                    if (!(teste == matriculaVelha))
                    {
                        System.out.println("matricula errada digitada...");
                        System.out.println("voltando ao menu anterior");
                        return;
                    }

                    aluno.setNome(nome);
                    System.out.println("nome alterado com sucesso");
                    
                    SalvarTudo();
                    break;
                case 2:
                    System.out.println("digite o novo curso: (ou 0 para voltar)");
                    String curso = input.nextLine();
                    if (curso.equals("0"))
                    {
                        return;
                    }

                    System.out.println("o curso antigo era: "+aluno.getCurso()+" o novo curso será: "+curso);

                    teste = LerInt(input, "digite a matricula do aluno para confirmar a mudança");

                    if (!(teste == matriculaVelha))
                    {
                        System.out.println("matricula errada digitada...");
                        System.out.println("voltando ao menu anterior");
                        return;
                    }
                    aluno.setCurso(curso);
                    System.out.println("curso alterado com sucesso");
                    
                    SalvarTudo();
                    break;
                case 3:

                    int matriculaNova = LerInt(input, "digite a nova matricula: (ou 0 para voltar)");
                    if (matriculaNova == 0)
                    {
                        return;
                    }

                    Aluno alunoComNovaMatricula = BuscarAluno(matriculaNova);

                    if (alunoComNovaMatricula != null && alunoComNovaMatricula != aluno)
                    {
                        System.out.println("essa matricula já existe, pertence ao aluno de nome: "+alunoComNovaMatricula.getNome());
                        System.out.println("voltando ao menu anterior");
                        return;
                    }

                    System.out.println("a matricula antiga era: "+aluno.getMatricula()+" a nova matricula será: "+matriculaNova);

                    teste = LerInt(input, "digite a matricula ANTIGA do aluno para confirmar a mudança");

                    if (!(teste == matriculaVelha))
                    {
                        System.out.println("matricula errada digitada...");
                        System.out.println("voltando ao menu anterior");
                        return;
                    }
                    RemoverAlunoArquivo(matriculaVelha); //apaga o arquivo antigo
                    aluno.setMatricula(matriculaNova);

                    ArrayList<Turma> turmasPraTirarOAluno = new ArrayList<>();

                    for (Turma turma : turmas) //tira o aluno da lista das turmas
                    {
                        for (int alunosDaTurma : turma.getAlunos())
                        {
                            if (alunosDaTurma == matriculaVelha)
                            {
                                turmasPraTirarOAluno.add(turma);
                            }
                        }
                    }

                    for (Turma turma : turmasPraTirarOAluno) //tira da turma a antiga e coloca a nova
                    {
                        turma.removerAluno(matriculaVelha);
                        turma.addAluno(aluno.getMatricula());
                        SalvarTurma(turma);
                    }

                    ArrayList<Boletim> boletinsParaApagar = new ArrayList<>();
                    for (Boletim boletim : boletins)
                    {
                        if (boletim != null && boletim.getMatriculaAluno() == matriculaVelha)
                        {
                            boletinsParaApagar.add(boletim);
                            RemoverBoletimArquivo(boletim); //apaga os arquivos antigos
                            boletim.setMatriculaAluno(matriculaNova); //troca a matricula nos boletins
                            SalvarBoletim(boletim); //cria um novo com a matricula trocada
                        }
                    }
                    for (Boletim boletim : boletinsParaApagar)
                    {
                        boletins.remove(boletim); //apaga os boletins antigos da lista do sistema
                    }

                    System.out.println("matricula alterada com sucesso");
                    matriculaVelha = matriculaNova; // na hora de voltar pra parte de editar, tem que voltar com a nova matricula selecionada
                    
                    SalvarTudo();
                    break;
                case 4: 
                    aluno.MostrarInfo();

                    System.out.println("digite o codigo da disciplina que vai ser adicionada (ou 0 para voltar)");
                    codigo = input.nextLine();
                    if (codigo.equals("0"))
                    {
                        return;
                    }

                    if (!ChecarCodigoDisciplina(codigo))
                    {
                        System.out.println("o codigo digitado não existe");
                        System.out.println("voltando ao menu anterior");
                        return;
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

                    for (String disciplinaJaFeita : aluno.getDisciplinasCursadas()) //checa se o aluno já fez a disciplina
                    {
                        if (disciplinaJaFeita.equals(codigo))
                        {
                            System.out.println("o aluno já tem essa disciplina como feita...");
                            break;
                        }
                    }

                    teste = LerInt(input, "digite novamente a matricula do aluno para confirmar: ");

                    if (!(teste == matriculaVelha))
                    {
                        System.out.println("matricula invalida");
                        break;
                    }

                    aluno.addDisciplina(disciplina); 
                    System.out.println("disciplina adicionada com sucesso");

                    // não cria boletim nem adiciona aos relatorios pq isso já tem que ser feito pela outra faculdade
                    // de onde veio a conclusão da disciplina pelo aluno
                    // sem falar que ao lançar nota tudo isso já é feito automaticamente
                    SalvarTudo();
                    break;
                case 5: 
                    aluno.MostrarInfo();

                    System.out.println("digite o codigo da disciplina que vai ser removida (ou 0 para voltar)");
                    codigo = input.nextLine();
                    if (codigo.equals("0")) return;

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
                    teste = LerInt(input, "digite novamente a matricula do aluno para confirmar: ");

                    if (!(teste == matriculaVelha))
                    {
                        System.out.println("matricula invalida");
                        break;
                    }

                    aluno.removerDisciplina(disciplina);
                    System.out.println("disciplina removida com sucesso");


                    // relatorios/boletins não removidos, pois o aluno continua tendo feito a disciplina
                    SalvarTudo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("digite novamente");
                }
        } while (escolha !=0);

    }
    public static void TrancarMatricula(Scanner input)
    {
        ListarAlunos();
        System.out.println("LEMBRE-SE...");
        System.out.println("trancar matricula geral = sair de todas as turmas");
        System.out.println("trancar matricula de materia = sair da turma da materia");
        System.out.println("sabendo disso...");

        int matricula = LerInt(input, "digite a matricula do aluno: (ou 0 para voltar)");

        if (matricula == 0) return;

        if (!ChecarMatricula(matricula))
        {
            System.out.println("a matricula digitada não existe");
            return;
        }

        Aluno aluno = BuscarAluno(matricula);
        System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());
        int escolha = LerInt(input, "digite 1 para realizar o trancamento geral de matricula ou outro numero para trancar apenas uma disciplina (ou 0 para voltar)");
        if (escolha == 0) return;

        if (escolha == 1)
        {
            int teste = LerInt(input, "digite novamente a matricula do aluno para confirmar o trancamento geral");

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
            SalvarTudo();
            return;
        }

        System.out.println("digite o codigo da disciplina que será trancada: (ou 0 para voltar)");
        String codigo = input.nextLine();
        if (codigo.equals("0")) return;

        if (!ChecarCodigoDisciplina(codigo))
        {
            System.out.println("o codigo digitado não existe");
            return;
        }

        int num = LerInt(input, "digite o numero da turma: ");

        if (!ChecarTurma(num, codigo))
            {
                System.out.println("não existe turma com esse numero e codigo");
                return;
            }
        Turma turma = BuscarTurma(num, codigo);

        int teste = LerInt(input, "digite novamente a matricula do aluno para confirmar o trancamento");

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
        SalvarTudo();
    }
    public static void RemoverAluno(Scanner input)
    {
        ListarAlunos();
        System.out.println("CUIDADO AO REMOVER ALUNOS DO SISTEMA, seus boletins são perdidos!");
        int matricula = LerInt(input, "digite a matricula do aluno que será removido (ou 0 para voltar)");
        if (matricula == 0) return;

        if(!ChecarMatricula(matricula))
        {
            System.out.println("a matricula digitada não existe");
            return;
        }

        System.out.println("aluno selecionado para remoção:");
        BuscarAluno(matricula).MostrarInfo();

        int teste = LerInt(input, "digite novamente a matricula para confirmar a remoção");

        if (!(teste == matricula))
        {
            System.out.println("matricula errada digitada...");
            System.out.println("voltando ao menu anterior");
            return;
        }
        System.out.println("aluno "+BuscarAluno(matricula).getNome()+" removido do sistema");

        ArrayList<Turma> turmasPraTirarOAluno = new ArrayList<>();
        for (Turma turma : turmas) //tira o aluno da lista das turmas
        {
            for (int aluno : turma.getAlunos())
            {
                if (aluno == matricula)
                {
                    turmasPraTirarOAluno.add(turma);
                }
            }
        }

        for (Turma turma : turmasPraTirarOAluno) //tira da turma
        {
            turma.removerAluno(matricula);
        }

        ArrayList<Boletim> boletinsParaApagar = new ArrayList<>();
        for (Boletim boletim : boletins)
        {
            if (boletim.getMatriculaAluno() == matricula)
            {
                boletinsParaApagar.add(boletim);
                RemoverBoletimArquivo(boletim);
            }
        }
        if (boletinsParaApagar != null && !boletinsParaApagar.isEmpty())
        {
            for (Boletim boletim : boletinsParaApagar)
            {
                boletins.remove(boletim);
            }
        }

        alunos.remove(BuscarAluno(matricula)); //tira do sistema
        RemoverAlunoArquivo(matricula); //apaga o arquivo
        
        SalvarTudo();
    }
    public static void MostrarInfoAluno(Scanner input)
    {
        int matricula = LerInt(input, "digite a matricula do aluno que sera consultado (ou 0 para voltar)");
        if (matricula == 0) return;

        if (!ChecarMatricula(matricula))
        {
            System.out.println("a matricula digitada não existe");
            return;
        }
        BuscarAluno(matricula).MostrarInfo();
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
            escolha = LerInt(input, "");

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
        System.out.println("digite o nome da disciplina: (ou 0 para voltar)");
        String nome = input.nextLine();
        if (nome.equals("0")) return;

        System.out.println("digite o codigo da disciplina: (ou 0 para voltar)");
        String codigo = input.nextLine();
        if (codigo.equals("0")) return;

        for (Disciplina disciplina: disciplinas) //checa codigo duplo
        {
            if (disciplina.getCodigo().equals(codigo))
            {
                System.out.println("esse codigo já existe, pertence a disciplina de nome: "+disciplina.getNome());
                System.out.println("voltando ao menu anterior");
                return;
            }
        }

        int carga_horaria = LerInt(input, "digite a carga horaria: (ou 0 para voltar)");
        if (carga_horaria == 0) return;

        int escolha = LerInt(input, "digite 1 caso essa materia tenha pré-requisitos ou outro numero caso não tenha (ou 0 para voltar)");
        if (escolha == 0) return;

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
            SalvarTudo();
            return;
            
        }
        
        Disciplina disciplina = new Disciplina(nome, codigo, carga_horaria); 
        disciplinas.add(disciplina); //coloca a disciplina na lista do sistema 
        SalvarDisciplina(disciplina); 
        SalvarTudo();
    }   

    public static void CriarTurma(Scanner input)
    {
        System.out.println("Digite o codigo da disciplina da qual a turma pertence (ou 0 para voltar)");
        String codigo = input.nextLine();
        if (codigo.equals("0")) return;

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
            numero = LerInt(input, "digite o numero da turma (ou 0 para voltar)");
            if (numero == 0) return;

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

        int vagas = LerInt(input, "digite a quantidade de vagas (ou 0 para voltar)");
        if (vagas == 0) return;

        System.out.println("digite o nome da sala onde a aula ocorrerá ou deixe em branco caso seja online: (ou 0 para voltar)");
        String sala = input.nextLine();
        if (sala.equals("0")) return;

        if (sala.isEmpty())//online
        {
            online = true;
        }

        int matriculaProf = LerInt(input, "digite a matricula do professor que vai ministrar a turma (ou 0 para voltar)");
        if (matriculaProf == 0) return;

        if (ChecarMatriculaProf(matriculaProf))
        {
            System.out.println("não existe professor com essa matricula");
            return;
        }

        System.out.println("lembre que as aulas duram sempre 2 horas");
        int horario = LerInt(input, "digite o horario de inicio da aula no formato: 8 (8AM) ou 12(meio dia) NÃO USE APENAS O 0");

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

        int semestre = LerInt(input, "digite o semestre da turma: ");

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
        SalvarTudo();
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
        ListarDisciplinas();
        System.out.println("CUIDADO AO REMOVER DISCIPLINAS, pois seus relatorios serão apagados");
        System.out.println("alem disso, todos os boletins de alunos que dependem da disciplina serão apagados");
        System.out.println("alem disso todas as turmas da disciplina serão apagadas, desencadeando uma serie de remoções");
        System.out.println("digite o codigo da disciplina que será removida: (ou 0 para voltar)");
        String codigo = input.nextLine();
        if (codigo.equals("0")) return;

        if (!ChecarCodigoDisciplina(codigo))
        {
            System.out.println("o codigo digitado não existe...");
            System.out.println("voltando ao menu anterior");
            return;
        }

        System.out.println("Disciplina a ser apagada: "+BuscarDisciplina(codigo).getNome());
        System.out.println("para confirmar a remoção da disciplina digite novamente o codigo (ou 0 para voltar)");
        String teste = input.nextLine();
        if (teste.equals("0")) return;

        if (!teste.equals(codigo))
        {
            System.out.println("erro na confirmação do codigo...");
            System.out.println("voltando ao menu anterior");
            return;
        }

        // tudo que desencadeia ao retirar disciplina
        Disciplina disciplina = BuscarDisciplina(codigo);

        DesencadeamentoRemoverDisciplina(disciplina);
    }
    public static void RemoverTurma(Scanner input)
    {
        ListarTurmas();
        System.out.println("CUIDADO AO REMOVER TURMAS, pois seus relatorios são apagados");
        System.out.println("alem disso, todos os boletins de alunos que dependem da turma serão apagados");
        System.out.println("digite o codigo da disciplina da turma (ou 0 para voltar)");
        String codigoDisciplina = input.nextLine();
        if (codigoDisciplina.equals("0")) return;

        int numTurma = LerInt(input, "digite o numero da turma (ou 0 para voltar)");
        if (numTurma == 0) return;

        if (!ChecarTurma(numTurma, codigoDisciplina))
        {
            System.out.println("não existe tuma com esse numero e esse codigo de disciplina...");
            System.out.println("voltando ao menu anterior");
            return;
        }

        Turma turma = BuscarTurma(numTurma, codigoDisciplina);
        String nomeDisciplina = BuscarDisciplina(codigoDisciplina).getNome();

        System.out.println("turma a ser apagada: "+turma.getNumero()+" de "+nomeDisciplina);
        System.out.println("para confirmar a remoção da turma digite novamente o codigo da disciplina: (ou 0 para voltar)");
        String teste = input.nextLine();
        if (teste.equals("0")) return;

        if (!teste.equals(codigoDisciplina))
        {
            System.out.println("erro na confirmação do codigo da disciplina da turma...");
            System.out.println("voltando ao menu anterio");
            return;
        }

        //COISAS QUE DESENCADEIAM COM A REMOÇAO DE TURMA
        DesencadeamentoRemoverTurma(turma);
    }
    public static void MostrarInfoDisciplina(Scanner input)
    {
        System.out.println("digite o codigo da disciplina que sera consultada (ou 0 para voltar)");
        String codigo = input.nextLine();
        if (codigo.equals("0")) return;

        if (!ChecarCodigoDisciplina(codigo))
        {
            System.out.println("o codigo digitado não existe");
            return; 
        }

        BuscarDisciplina(codigo).MostrarInfo();
    }
    public static void MostrarInfoTurma(Scanner input)
    {
        System.out.println("digite o codigo da disciplina da turma (ou 0 para voltar)");
        String codigo = input.nextLine();
        if (codigo.equals("0")) return;

        if (!ChecarCodigoDisciplina(codigo))
        {
            System.out.println("o codigo digitado não existe");
            return; 
        }

        Disciplina disciplina = BuscarDisciplina(codigo);
        System.out.println("a disciplina selecionada foi: "+disciplina.getNome()+'/'+disciplina.getCodigo());

        int numero = LerInt(input, "digite o numero da turma");

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
        System.out.println("digite o nome do professor: (ou 0 para voltar)");
        String nome = input.nextLine();
        if (nome.equals("0")) return;

        int matricula = LerInt(input, "digite a matricula do professor: (ou 0 para voltar)");
        if (matricula == 0) return;

        if (!ChecarMatriculaProf(matricula))
        {
            System.out.println("já existe um professor com essa matricula, seu nome é: "+BuscarProfessor(matricula).getNome());
            return;
        }

        Professor professor = new Professor(nome, matricula);
        professores.add(professor);
        SalvarProfessor(professor);

        SalvarTudo();
    }
    public static void RemoverProfessor(Scanner input)
    {
        ListarProfessores();
        System.out.println("CUIDADO AO REMOVER PROFESSORES, seus relatorios serão perdidos");
        System.out.println("suas turmas serão apagadas e os boletins de alunos que dependem do professor tambem serão");
        int matricula = LerInt(input, "digite a matricula do professor (ou 0 para voltar)");
        if (matricula == 0) return;

        if (ChecarMatriculaProf(matricula))
        {
            System.out.println("não existe nenhum professor com essa matricula");
            return;
        }

        Professor professor = BuscarProfessor(matricula);
        System.out.println("professor a ser apagado: "+professor.getNome()+'/'+professor.getMatricula());

        int teste = LerInt(input, "para confirmar a remoção do professor digite novamente a matricula do professor (ou 0 para voltar)");
        if (teste == 0) return;

        if (! (teste == matricula))
        {
            System.out.println("erro na confirmação da matricula");
            return;
        }

        ArrayList<Turma> turmasParaApagar = new ArrayList<>();
        for (String codTurma : professor.getTurmas())
        {
            if (codTurma != null)
            {
                Turma turma = BuscarTurma(codTurma);
                turmasParaApagar.add(turma);
            }
        }
        for (Turma turma : turmasParaApagar)
        {
            DesencadeamentoRemoverTurma(turma);//coisas que desencadeiam quando as turmas do professor deixam de existir
        }

        Relatorio relatorio = AcessaOuCriaRelatorio('p', String.valueOf(professor.getMatricula()));
        relatorios.remove(relatorio); // tira o relatorio da lista do sistema
        RemoverRelatorioArquivo(relatorio); //apaga o arquivo de relatorio

        System.out.println("professor removido");
        RemoverProfessorArquivo(professor); //tira o arquivo
        professores.remove(professor); // tira da lista do sistema

        SalvarTudo();
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
        int matricula = LerInt(input, "digite a matricula do professor (ou 0 para voltar)");
        if (matricula == 0) return;

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
            escolha = LerInt(input, "");

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
        ListarDisciplinas();
        System.out.println("para lançar notas, primeiro identifique a materia da turma");
        System.out.println("digite o codigo da materia da qual a turma faz parte: (ou 0 para voltar)");
        String codigoDisciplina = input.nextLine();
        if (codigoDisciplina.equals("0")) return;

        if (!ChecarCodigoDisciplina(codigoDisciplina))
        {
            System.out.println("não existe disciplina com esse codigo");
            return;
        }

        int numeroTurma = LerInt(input, "digite o numero da turma (ou 0 para voltar)");
        if (numeroTurma == 0) return;

        if (!ChecarTurma(numeroTurma, codigoDisciplina))
        {
            System.out.println("não existe turma dessa disciplina com esse numero");
            return;
        }

        Turma turma = BuscarTurma(numeroTurma, codigoDisciplina);
        turma.ListarAlunos();
        System.out.println("lista dos alunos da turma acima");

        int matricula = LerInt(input, "digite a matricula do aluno cujas notas e presença serão lançadas: (ou 0 para voltar)");
        if (matricula == 0) return;

        String codigoTurma = turma.getCodigoTurma();
        if (!ChecarAlunoTurma(matricula, codigoTurma))
        {
            System.out.println("o aluno não está matriculado nessa turma");
            return;
        }

        Aluno aluno = BuscarAluno(matricula);
        int presenca;
        Boletim boletim;

        Relatorio relatorioTurma = AcessaOuCriaRelatorio('t', codigoTurma);
        Relatorio relatorioProfessor = AcessaOuCriaRelatorio('p', String.valueOf(turma.getMatriculaProf()));
        Relatorio relatorioDisciplina = AcessaOuCriaRelatorio('d', codigoDisciplina);

        System.out.println("aluno selecionado: "+aluno.getNome()+'/'+aluno.getMatricula());
        if (aluno instanceof AlunoEspecial)
        {
            System.out.println("o aluno selecionado é do tipo especial");
            System.out.println("alunos especiais recebem apenas presença");

            presenca = LerInt(input, "digite o numero da porcentagem de presença do aluno (exemplo: 75 para 75%): ");

            if (presenca < 75)
            {
                turma.removerAluno(aluno);
                System.out.println("aluno reprovado por presença");
                relatorioDisciplina.addReprovaramFalta();
                relatorioTurma.addReprovaramFalta();
                relatorioProfessor.addReprovaramFalta();
            }
            else
            {
                System.out.println("aluno aprovado");
                turma.removerAluno(matricula);
                aluno.addDisciplina(codigoDisciplina);
                relatorioDisciplina.addPassaram();
                relatorioTurma.addPassaram();
                relatorioProfessor.addPassaram();
            }

            boletim = new Boletim(matricula, turma, presenca, turma.getMetodoAvaliacao(), 10, 10, 10, 10, 10);
            boletins.add(boletim);
            SalvarBoletim(boletim);
            SalvarRelatorio(relatorioDisciplina);
            SalvarRelatorio(relatorioTurma);
            SalvarRelatorio(relatorioProfessor);
            System.out.println("relatorios criados ou atualizados");
            System.out.println("todos os boletins pode ser encontrados na pasta banco_de_dados/boletins, eles estão separados por aluno");

            SalvarTudo();
            return;
        }
        System.out.println("digite 1 para lançar nota por nota");
        System.out.println("digite 2 para lançar todas as notas de uma vez");
        System.out.println("ou digite 0 para voltar");
        int num = LerInt(input, "");
        if (num == 0) return;

        float p1;
        float p2;
        float p3;
        float lista;
        float seminario;
        
        if (num == 1)
        {
            System.out.println("as notas devem ser escritas com , exemplo: 5,5");
            p1 = LerFloat(input, "digite a nota da p1 no formato float: ");

            p2 = LerFloat(input, "digite a nota da p2 no formato float: ");

            p3 = LerFloat(input, "digite a nota da p3 no formato float: ");

            lista = LerFloat(input, "digite a nota da lista no formato float: ");

            seminario = LerFloat(input, "digite a nota do seminario no formato float: ");

            presenca = LerInt(input, "digite o numero da porcentagem de presença do aluno (exemplo: 75 para 75%): ");
        }
        else
        {
            while (true)
            {
                try
                {
                    System.out.println("digite a nota da p1, p2, p3, lista, seminario e a presenca");
                    System.out.println("todas as notas devem ser no formato float com ponto, exemplo: 5.5 ou 4.8");
                    System.out.println("a presença deve ser escrita sem a porcentagem, exemplo: 75 para 75%");
                    System.out.println("todas as entradas devem ser separadas por espaço, exemplo: p1 p2 p3 lista seminario presença");

                    String[] entrada = input.nextLine().split(" ");

                    if (entrada.length !=6)
                    {
                        System.out.println("quantidade de valores incorreta, digite novamente os 6 valores");
                        continue;
                    }
                    p1 = Float.parseFloat(entrada[0]);
                    p2 = Float.parseFloat(entrada[1]);
                    p3 = Float.parseFloat(entrada[2]);
                    lista = Float.parseFloat(entrada[3]);
                    seminario = Float.parseFloat(entrada[4]);
                    presenca = Integer.parseInt(entrada[5]);

                    break; // se tudo der certo sai do loop while true
                } catch (NumberFormatException e )
                {
                    System.out.println("Entrada invalida, lembre de usar ponto para decimais e espaço entre as notas");
                }
            }
        }

        boletim = new Boletim(matricula, turma, presenca, turma.getMetodoAvaliacao(), p1, p2, p3, lista, seminario);
        boletins.add(boletim);
        SalvarBoletim(boletim);

        float notaMedia = boletim.getMediaFinal();

        if (boletim.getPassou() == 0)
        {
            turma.removerAluno(matricula);
            aluno.addDisciplina(codigoDisciplina); //adiciona a disciplina que o aluno passou
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

        SalvarTudo();
    }
    public static void CalculadoraNota(Scanner input)
    {
        System.out.println("vamos fazer uma simulação de notas e presença");
        System.out.println("digite 1 para lançar nota por nota");
        System.out.println("digite 2 para lançar todas as notas de uma vez");
        System.out.println("ou digite 0 para voltar");

        int num = LerInt(input, "");
        if (num == 0) return;

        float p1;
        float p2;
        float p3;
        float lista;
        float seminario;
        int presenca;

        if (num == 1)
        {
            System.out.println("as notas devem ser escritas com , exemplo: 5,5");
            p1 = LerFloat(input, "digite a nota da p1 no formato float: ");

            p2 = LerFloat(input, "digite a nota da p2 no formato float: ");

            p3 = LerFloat(input, "digite a nota da p3 no formato float: ");

            lista = LerFloat(input, "digite a nota da lista no formato float: ");

            seminario = LerFloat(input, "digite a nota do seminario no formato float: ");

            presenca = LerInt(input, "digite o numero da porcentagem de presença do aluno (exemplo: 75 para 75%): ");
        }
        else
        {
            while (true)
            {
                try
                {
                    System.out.println("digite a nota da p1, p2, p3, lista, seminario e a presenca");
                    System.out.println("todas as notas devem ser no formato float com ponto, exemplo: 5.5 ou 4.8");
                    System.out.println("a presença deve ser escrita sem a porcentagem, exemplo: 75 para 75%");
                    System.out.println("todas as entradas devem ser separadas por espaço, exemplo: p1 p2 p3 lista seminario presença");

                    String[] entrada = input.nextLine().split(" ");

                    if (entrada.length !=6)
                    {
                        System.out.println("quantidade de valores incorreta, digite novamente os 6 valores");
                        continue;
                    }
                    p1 = Float.parseFloat(entrada[0]);
                    p2 = Float.parseFloat(entrada[1]);
                    p3 = Float.parseFloat(entrada[2]);
                    lista = Float.parseFloat(entrada[3]);
                    seminario = Float.parseFloat(entrada[4]);
                    presenca = Integer.parseInt(entrada[5]);

                    break; // se tudo der certo sai do loop while true
                } catch (NumberFormatException e )
                {
                    System.out.println("Entrada invalida, lembre de usar ponto para decimais e espaço entre as notas");
                }
            }
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
        ListarDisciplinas();
        System.out.println("digite o codigo da disciplina da turma (ou 0 para voltar)");
        String codigoDisciplina = input.nextLine();
        if (codigoDisciplina.equals("0")) return;

        if (!ChecarCodigoDisciplina(codigoDisciplina))
        {
            System.out.println("não existe disciplina com esse codigo");
            return;
        }

        int numTurma = LerInt(input, "para exibir o relatorio, digite o numero da turma (ou 0 para voltar)");
        if (numTurma == 0) return;

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
        ListarDisciplinas();
        System.out.println("para exibir o relatorio, digite o codigo da disciplina: (ou 0 para voltar)");
        String codigoDisciplina = input.nextLine();
        if (codigoDisciplina.equals("0")) return;

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
        ListarProfessores();
        int matricula = LerInt(input, "para mostrar o relatorio de professor, digite a matricula do profesor: (ou 0 para voltar)");
        if (matricula == 0) return;

        if (ChecarMatriculaProf(matricula))
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
        ListarAlunos();
        int matricula = LerInt(input, "digite a matricula do aluno (ou 0 para voltar)");
        if (matricula == 0) return;

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
        ListarAlunos();
        int matricula = LerInt(input, "digite a matricula do aluno (ou 0 para voltar)");
        if (matricula == 0) return;

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
    public static void DesencadeamentoRemoverTurma(Turma turma)
    {
        Disciplina disciplinaComTurma = BuscarDisciplina(turma.getCodigoDisciplina());
        
        if (disciplinaComTurma !=null)
        {
            System.out.println("a turma n"+turma.getNumero()+" fazia parte da disciplina: "+disciplinaComTurma.getNome()+'/'+disciplinaComTurma.getCodigo());
            System.out.println("agora a turma não consta mais na lista de turmas da disciplina");
            disciplinaComTurma.removerTurma(turma);
        }

        Professor professorComTurma = BuscarProfessor(turma.getMatriculaProf());

        if (professorComTurma !=null)
        {
            System.out.println("a turma era ministrada pelo prof: "+professorComTurma.getNome()+'/'+professorComTurma.getMatricula());
            System.out.println("agora a turma não consta mais na lista de turmas do professor");
            professorComTurma.removerTurma(turma.getCodigoTurma());
        }

        Relatorio relatorioTurma = AcessaOuCriaRelatorio('t', turma.getCodigoTurma());
        relatorios.remove(relatorioTurma);//tira o relatorio da turma do sistema
        RemoverRelatorioArquivo(relatorioTurma); //apaga o arquivo

        for (Aluno aluno : alunos) 
        {
            if (aluno instanceof AlunoEspecial)
            {
                if (turma.getAlunos().contains(aluno.getMatricula()))
                {
                    ((AlunoEspecial)aluno).removerDisciplinaAtual(); //arruma o calculo de disciplinas atuais de aluno especial
                }
            }
        }

        // apaga os boletins de alunos da turma
        ArrayList<Boletim> boletinsParaApagar = new ArrayList<>();
        for (Boletim boletim : boletins)
        {
            if (boletim.getTurma() == (turma.getNumero()) && boletim.getDisciplina().equals(turma.getCodigoDisciplina()))
            {
                boletinsParaApagar.add(boletim); //só faz isso se tiver o mesmo codigo e o mesmo num de turma
            }
        }
        for (Boletim boletim : boletinsParaApagar)
        {
            if (boletim !=null)
            {
                boletins.remove(boletim); //tira os boletins da lista do sistema
                RemoverBoletimArquivo(boletim); // tira o arquivo do boletim
            }
        }

        System.out.println("turma removida");
        RemoverTurmaArquivo(turma); // apaga o arquivo
        turmas.remove(turma); // tira da lista do sistema 
        

        SalvarTudo();
    }
    public static void DesencadeamentoRemoverDisciplina(Disciplina disciplina)
    {
        // tudo que desencadeia ao retirar disciplina

        // apaga as disciplinas que tem essa como pré-requisito e desencadeia tudo de novo pra cada uma delas
        ArrayList<Disciplina> disciplinasParaApagar = new ArrayList<>();
        for (Disciplina disciplinaDaLista : disciplinas)
        {
            if ((!disciplinaDaLista.getPreRequisitos().isEmpty()) && disciplinaDaLista.getPreRequisitos().contains(disciplina.getCodigo()))
            {
                System.out.println("a disciplina: "+disciplinaDaLista.getNome()+'/'+disciplinaDaLista.getCodigo()+" tem essa disciplina como pré-requisito");
                System.out.println("ao apagar essa disciplina, todas as que tem ela como requisito serão apagadas");
                disciplinasParaApagar.add(disciplinaDaLista);
            }
        }
        for (Disciplina disciplinaParaRemover : disciplinasParaApagar)
        {
            DesencadeamentoRemoverDisciplina(disciplinaParaRemover);
        }

        // apaga os boletins de alunos dessa materia
        ArrayList<Boletim> boletinsParaApagar = new ArrayList<>();
        for (Boletim boletim : boletins)
        {
            if (boletim.getDisciplina().equals(disciplina.getCodigo()))
            {
                boletinsParaApagar.add(boletim);
            }
        }
        for (Boletim boletim : boletinsParaApagar)
        {
            boletins.remove(boletim); //tira os boletins da lista do sistema
            RemoverBoletimArquivo(boletim); // tira o arquivo do boletim
        }

        // apaga o relatorio da disciplina
        Relatorio relatorio = AcessaOuCriaRelatorio('d', disciplina.getCodigo());
        relatorios.remove(relatorio); //tira o relatorio da lista do sistema
        RemoverRelatorioArquivo(relatorio); //apaga o arquivo do relatorio

        //tira a disciplina da lista de já feitas por todos os alunos que tem ela como feita
        ArrayList<Aluno> alunosComDisciplina = new ArrayList<>();
        for (Aluno aluno : alunos)
        {
            if (!aluno.getDisciplinasCursadas().isEmpty())
            {
                for (String codDisciplina : aluno.getDisciplinasCursadas())
                {
                    if (disciplina.getCodigo().equals(codDisciplina))
                    {
                        alunosComDisciplina.add(aluno);
                    }
                }
            }
        }
        for (Aluno aluno : alunosComDisciplina)
        {
            aluno.removerDisciplina(disciplina);
        }

        // apaga as turmas da disciplina
        ArrayList<Turma> turmasDaDisciplina = new ArrayList<>();
        for (Turma turma : turmas)
        {
            if (turma.getCodigoDisciplina().equals(disciplina.getCodigo()))
            {
                turmasDaDisciplina.add(turma);
            }
        }
        for (Turma turma : turmasDaDisciplina)
        {
            DesencadeamentoRemoverTurma(turma);
        }

        //parte normal da remoção
        RemoverDisciplinaArquivo(disciplina); //remove o arquivo
        disciplinas.remove(disciplina); //tira da lista de disciplinas do sistema
        System.out.println("disciplina removida");
        
        SalvarTudo();
    }

    // metodos que mostram/acessam/buscam
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

    // parte pra checagem do menu
    public static int LerInt(Scanner input, String mensagem) // garante que ao digitar um não inteiro, o programa não explode
    {
        int valor;
        while (true)
        {
            try 
            {
                System.out.println(mensagem);
                valor = input.nextInt();
                input.nextLine(); //come o enter
                break;
            } catch (InputMismatchException e )
            {
                System.out.println("Entrada invalida, deve ser um numero inteiro (3 5 8 2 9)");
                input.nextLine(); //come o enter
            }
        }
        return valor;
    }
    public static float LerFloat(Scanner input, String mensagem)
    {
        float valor;
        while (true)
        {
            try
            {
                System.out.println(mensagem);
                valor = input.nextFloat();
                input.nextLine(); // come o enter
                break;
            } catch (InputMismatchException e )
            {
                System.out.println("Entrada invalida, deve ser um numero no formato float (5,6 77,8)");
                input.nextLine(); // come o enter
            }
        }
        return valor;
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
    public static void RemoverBoletimArquivo(Boletim boletim)
    {
        String pasta = "banco_de_dados/boletins/"+boletim.getMatriculaAluno();
        String caminhoEspecifico = pasta+'/'+"semestre"+boletim.getSemestre()+"disciplina"+boletim.getDisciplina()+"boletim.txt";
        File arquivoBoletim = new File(caminhoEspecifico);

        if (!arquivoBoletim.exists())
        {
            System.out.println("arquivo de boletim não encontrado: "+caminhoEspecifico);
            return;
        }

        boolean deletado = arquivoBoletim.delete();
        if (!deletado)
        {
            System.out.println("não foi possivel remover o boletim");
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
    public static void RemoverRelatorioArquivo(Relatorio relatorio)
    {
        String caminho = ("banco_de_dados/relatorios/"+relatorio.getCharTipo()+"ID"+relatorio.getIdentificador()+"relatorio.txt");
        File arquivo = new File(caminho);

        if (arquivo.exists())
        {
            arquivo.delete();
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