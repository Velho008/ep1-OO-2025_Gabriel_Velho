package classes;

import java.util.*;
import java.io.*;

public class SIGAA2 //SALVAR TUDO DNV QUANDO FOR FECHAR O PROGRAMA, POIS AS COISAS MUDAM
{
    static ArrayList<Aluno> alunos = new ArrayList<>(); //serve pra manter e criar alunos
    static ArrayList<Disciplina> disciplinas = new ArrayList<>(); //serve pra manter e criar disciplinas
    static ArrayList<Turma> turmas = new ArrayList<>(); //serve para manter e criar turmas

        public static void main(String[] args)
    {
        //carregar as coisas dos arquivos
        CarregarAlunos();
        CarregarTurmas();
        CarregarDisciplinas();

        //scanner usado no menu inteiro
        Scanner input1 = new Scanner(System.in);
        //variavel de escolha do menu
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
            System.out.println("digite 3 para matricular um aluno em uma turma"); //falta esse //falta mudar os arquivos quando muda aqui
            System.out.println("digite 4 para editar cadastro de aluno"); //falta mudar os arquivos quando muda aqui
            System.out.println("digite 5 para trancar a matricula de um aluno"); //falta esse //falta mudar os arquivos quando muda aqui
            System.out.println("digite 6 para deletar um aluno do sistema");
            System.out.println("digite 7 para buscar informações sobre um aluno");
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
                    // colocar aluno em turma

                    break;
                case 4:
                    EditarAluno(input); //editar tambem o arquivo do aluno

                    break;
                case 5:
                    // trancar essencialmente remove a turma das coisas que o aluno tá fazendo

                    break;
                case 6:
                    RemoverAluno(input); //remover o arquivo do sistema

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
        // ainda preciso checar se é especial ou não
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
            //escolher a disciplina e dps a turma, ai colocar o aluno na lista de ambas
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
                alunos.remove(BuscarAluno(matricula)); //tira d sistema
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
            System.out.println("digite 1 para criar uma nova disciplina"); //falta salvar arquivos
            System.out.println("digite 2 para criar uma nova turma pertencente a uma disciplina cadastrada");//falta deixar 100%
            System.out.println("digite 3 para listar as disciplinas existentes"); //talvez escolher para listar as turmas de cada disciplina
            System.out.println("digite 4 para listar as turmas existentes"); //mostrar quantas vagas tem e pode escolher uma turma para listar os alunos dela
            System.out.println("digite 5 para remover uma disciplina do sistema"); //falta tirar arquivo
            System.out.println("digite 6 para remover uma turma do sistema"); //FALTA FAZER //falta tirar arquivo
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
            boolean veracidadeRequisitos = true;//checa a veracidade de cada codigo de pre_requisito
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

    public static void CriarTurma(Scanner input) //ALGUM BUG NA HORA DE CHECAR SE JÁ TEM OUTRA TURMA NA MESMA DISCIPLINA E NUM
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
                if (turma.getNumero() == numero && turma.getcodigoDisciplina().equals(codigo))
                {
                    System.out.println("o numero digitado já existe em outra turma da mesma disciplina");
                    continuar = true;
                    break;
                }
            }
        } while (continuar);

        System.out.println("digite a quantidade de vagas");
        int vagas = input.nextInt();
        input.nextLine(); //come o enter

        Turma turmanova = new Turma(numero, vagas, codigo);
        turmas.add(turmanova);
        SalvarTurma(turmanova);
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
        //caso tenha 0 vagas, marcar a turma com um X no começo, para mostrar que acabaram as vagas
        System.out.println("");
        System.out.println("NUMERO //DISCIPLINA //VAGAS ATUAIS //VAGAS TOTAIS");
        for (Turma turma : turmas)
        {
            System.out.print(turma.getNumero());
            System.out.print(turma.getcodigoDisciplina()+"    "+turma.getVagasAtuais()+"/"+turma.getVagasTotais());
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
                if (turma.getNumero()==numero && turma.getcodigoDisciplina().equals(codigoDisciplina))
                {
                    achou = true;
                }
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
                if (turma.getNumero()==numero && turma.getcodigoDisciplina().equals(codigoDisciplina))
                {
                    return turma;
                }
            }
        }
    return null;
    }

    //parte de arquivos
    // PARA CADA UM FAZER UM QUE SALVA TUDO
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
    //criar um pra cada uma das outras coisas, talvez usar herança
    //talvez criar um que salva todos os alunos de uma vez
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
        String caminhoArquivo = (pasta+'/'+turma.getNumero()+"DE"+turma.getcodigoDisciplina()+"turma.txt");
        try (BufferedWriter salvar = new BufferedWriter(new FileWriter(caminhoArquivo)))
        {
            salvar.write(turma.toString());
        } catch (IOException erro)
        {
            System.out.println("Erro ao salvar turma "+turma.getNumero()+"DE"+turma.getcodigoDisciplina()+':'+erro.getMessage());
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
        String codigoDisciplina = turma.getcodigoDisciplina();
        String caminho = "banco_de_dados/turmas/"+numero+"DE"+codigoDisciplina+"turma.txt";
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

}