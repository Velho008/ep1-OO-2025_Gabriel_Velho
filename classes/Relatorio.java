package classes;

public class Relatorio 
{
    // tirando notaMedia todos os outros são numeros absolutos
    private final char charTipo; //se é de professor, turma ou disciplina (p,t,d) respectivamente
    private String identificador; //prof ->matricula //disciplina ->codigo //turma ->codigo
    private int identificadorP; //para o caso de ser professor, vai ser a matricula dele
    private float notaMedia; //esse é calculado
    private int passaram;
    private int reprovaram;
    private int reprovaramFalta;
    private int reprovaramNota;
    private int reprovaramNotaEFalta;
    private int totalAlunos; //total de alunos que já fizeram parte (até o fim, não conta trancar)
    private int trancaram; //numero total dos alunos que trancaram 

    private final String tipo; // "professor" , "turma" , "disciplina"

    //construtores
    public Relatorio(char charTipo, String identificador)
    {
        //todos iniciam em 0 pois o relatorio tá sendo criado
        this.trancaram = 0;
        this.notaMedia = 0;
        this.passaram = 0;
        this.reprovaram = 0;
        this.reprovaramFalta = 0;
        this.reprovaramNota = 0;
        this.reprovaramNotaEFalta = 0;
        this.totalAlunos = 0;
        this.identificador = identificador;
        this.charTipo = charTipo;
        switch (charTipo)
        {
            case 'p':
                this.identificadorP = Integer.parseInt(identificador); //somente para o caso de ser professor, pois a matricula é int
                this.tipo = "professor";
                break;
            case 't':
                this.tipo = "turma";
                break;
            case 'd':
                this.tipo = "disciplina";
                break;
            default:
                this.tipo = "";
                System.out.println("Erro ao criar relatorio, tipo invalido");
                break;
        }
    }

    //construtor de arquivo
    public Relatorio(char charTipo, String identificador, float notaMedia, int passaram, int reprovaramFalta, int reprovaramNota, int reprovaramNotaEFalta, int trancaram)
    {
        this.identificador = identificador;
        this.charTipo = charTipo;
        switch (charTipo)
        {
            case 'p':
                this.identificadorP = Integer.parseInt(identificador); //somente para o caso de ser professor, pois a matricula é int
                this.tipo = "professor";
                break;
            case 't':
                this.tipo = "turma";
                break;
            case 'd':
                this.tipo = "disciplina";
                break;
            default:
                this.tipo = "";
                System.out.println("Erro ao criar relatorio, tipo invalido");
                break;
        }
        this.notaMedia = notaMedia;
        this.passaram = passaram;
        this.reprovaramFalta = reprovaramFalta;
        this.reprovaramNota = reprovaramNota;
        this.reprovaramNotaEFalta = reprovaramNotaEFalta;
        this.reprovaram = reprovaramFalta + reprovaramNota + reprovaramNotaEFalta;
        this.totalAlunos = passaram + reprovaram;
        this.trancaram = trancaram;
    }

    public void MostrarRelatorio()
    {
        System.out.println("=================================================");
        System.out.println("RELATORIO DE: "+this.tipo); 
        System.out.println("Media final das notas: "+notaMedia);
        System.out.println(trancaram+" alunos já trancaram a materia");
        if (totalAlunos == 0)
        {
            System.out.println("sem alunos no relatorio para mostrar, fechando relatorio...");
            return;
        }
        System.out.println("Aprovados: "+passaram+'('+(Porcentagem(passaram, totalAlunos))+'%'+')');
        System.out.println("Reprovados no total: "+reprovaram+'('+(Porcentagem(reprovaram, totalAlunos))+'%'+')');
        System.out.println("Reprovados somente por falta: "+reprovaramFalta+'('+(Porcentagem(reprovaramFalta, totalAlunos))+'%'+')');
        System.out.println("Reprovados somente por nota: "+reprovaramNota+'('+(Porcentagem(reprovaramNota, totalAlunos))+'%'+')');
        System.out.println("Reprovados por nota e falta: "+reprovaramNotaEFalta+'('+(Porcentagem(reprovaramNotaEFalta, totalAlunos))+'%'+')');
        System.out.println("=================================================");
    }
    
    //get e set
    public int getTrancaram()
    {
        return this.trancaram;
    }
    public void addTrancaram()
    {
        this.trancaram++;
    }
    public void removerTrancaram()
    {
        this.trancaram--;
    }
    public String getIdentificador()
    {
        return this.identificador;
    }
    public int getIdentificadorP()
    {
        return this.identificadorP;
    }
    public char getCharTipo()
    {
        return this.charTipo;
    }
    public String getTipo()
    {
        return this.tipo;
    }
    public float getNotaMedia()
    {
        return this.notaMedia;
    }
    public int getPassaram()
    {
        return this.passaram;
    }
    public int getReprovaram()
    {
        return this.reprovaram;
    }
    public int getReprovaramFalta()
    {
        return this.reprovaramFalta;
    }
    public int getReprovaramNota()
    {
        return this.reprovaramNota;
    }
    public int getReprovaramNotaEFalta()
    {
        return this.reprovaramNotaEFalta;
    }
    public int getTotalAlunos()
    {
        return this.totalAlunos;
    }

    public void addTotalAlunos()
    {
        this.totalAlunos++;
    }
    public void removerTotalAlunos()
    {
        this.totalAlunos--;
    }
    public void addPassaram()
    {
        this.passaram++;
        addTotalAlunos();
    }
    public void removerPassaram()
    {
        this.passaram--;
        removerTotalAlunos();
    }
    public void addReprovaram()
    {
        this.reprovaram++;
    }
    public void removerReprovaram()
    {
        this.reprovaram--;
    }
    public void addReprovaramFalta()
    {
        this.reprovaramFalta++;
        addTotalAlunos();
        addReprovaram();
    }
    public void removerReprovaramFalta()
    {
        this.reprovaramFalta--;
        removerTotalAlunos();
        removerReprovaram();
    }
    public void addReprovaramNota()
    {
        this.reprovaramNota++;
        addTotalAlunos();
        addReprovaram();
    }
    public void removeReprovaramNota()
    {
        this.reprovaramNota--;
        removerTotalAlunos();
        removerReprovaram();
    }
    public void addReprovaramNotaEFalta()
    {
        this.reprovaramNotaEFalta++;
        addTotalAlunos();
        addReprovaram();
    }
    public void removeReprovaramNotaEFalta()
    {
        this.reprovaramNotaEFalta--;
        removerTotalAlunos();
        removerReprovaram();
    }

    public void addNotaMedia(float nota)
    {
        int total = getTotalAlunos();
        if (total <=1)
        {
            this.notaMedia = nota;
        }
        else
        {
            this.notaMedia = ((this.notaMedia * (total - 1))+nota) / total;
        }
    }
    public void setNotaMedia(float nota)
    {
        this.notaMedia = nota;
    }

    public float Porcentagem(float coisa1, float coisa2)
    {
        if (coisa2 == 0) return 0;
        return (coisa1/coisa2)*100;
    }
    public float Porcentagem(int coisa1, int coisa2)
    {
        if (coisa2 == 0) return 0;
        return (coisa1/coisa2)*100;
    }

    //parte de arquivos

    @Override
    public String toString()
    {
        return String.valueOf(this.getCharTipo())+';'+
               this.getIdentificador()+';'+
               this.getNotaMedia()+';'+
               this.getPassaram()+';'+
               this.getReprovaramFalta()+';'+
               this.getReprovaramNota()+';'+
               this.getReprovaramNotaEFalta()+';'+
               this.getTrancaram();
    }

    public static Relatorio fromString(String entrada)
    {
        if (entrada == null || entrada.isEmpty())
        {
            System.out.println("Erro ao carregar relatorios: dados insuficientes");
            return null;
        }

        String[] dados = entrada.split(";");

        if (dados.length < 8)
        {
            System.out.println("Erro ao carregar relatorios: dados insuficientes");
            return null;
        }

        char charTipo = dados[0].charAt(0);
        String identificador = dados[1];
        float notaMedia = Float.parseFloat(dados[2]);
        int passaram = Integer.parseInt(dados[3]);
        int reprovaramFalta = Integer.parseInt(dados[4]);
        int reprovaramNota = Integer.parseInt(dados[5]);
        int reprovaramNotaEFalta = Integer.parseInt(dados[6]);
        int trancaram = Integer.parseInt(dados[7]);

        return new Relatorio(charTipo, identificador, notaMedia, passaram, reprovaramFalta, reprovaramNota, reprovaramNotaEFalta, trancaram);

    }
}
