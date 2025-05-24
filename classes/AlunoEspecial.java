package classes;

public class AlunoEspecial extends Aluno
{
    private final int maxDisciplinas = 2;
    private int disciplinasAtuais;  
    private  boolean especial;  

    //construtores
    public AlunoEspecial(String nome, int matricula, String curso, String disciplinas_cursadas)
    {
        super(nome, matricula, curso, disciplinas_cursadas);
        this.especial = true;
        this.disciplinasAtuais = 0;
        System.out.println("esse aluno é especial, pode cursar no maximo "+maxDisciplinas+" disciplinas");
    }
    public AlunoEspecial(String nome, int matricula, String curso)
    {
        super(nome, matricula, curso);
        this.especial = true;
        this.disciplinasAtuais = 0;
        System.out.println("esse aluno é especial, pode cursar no maximo "+maxDisciplinas+" disciplinas");
    }

    //construtor de arquivo
    public AlunoEspecial(String nome, int matricula, String curso, String disciplinas_cursadas, char arq, int disciplinasAtuais) 
    {
        super(nome, matricula, curso, disciplinas_cursadas, arq);
        this.especial = true;
        this.disciplinasAtuais = disciplinasAtuais;
    }

    //partes especificas do aluno especial
    @Override
    public void addDisciplina(String codigo) {
        if (this.disciplinasAtuais >= maxDisciplinas) {
            System.out.println("Aluno especial só pode cursar até " + maxDisciplinas + " disciplinas.");
            System.out.println("como atualmente ele cursa o maximo de disciplinas, não será possivel realizar a matricula ");
        } else 
        {
            super.addDisciplina(codigo);
        }
    }

    @Override
    public void addDisciplina(Disciplina disciplina) 
    {
        if (super.getDisciplinasCursadas().size() >= maxDisciplinas) 
        {
            System.out.println("Aluno especial só pode cursar até " + maxDisciplinas + " disciplinas.");
            System.out.println("como atualmente ele cursa o maximo de disciplinas, não será possivel realizar a matricula ");
        } else 
        {
            super.addDisciplina(disciplina);
        }
    }

    @Override
    public void MostrarInfo() 
    {
        super.MostrarInfo();
        System.out.println("Tipo: ALUNO ESPECIAL");
    }

    public boolean isEspecial() 
    {
        return especial;
    }

    //parte de arquivos
     @Override
    public String toString() {
        return "ESPECIAL;" + this.disciplinasAtuais+';'+super.toString();
    }

    public static AlunoEspecial fromString(String entrada) {
        String[] infos = entrada.split(";");
        if (!infos[0].equals("ESPECIAL")) {
            return null;
        }
        int disciplinasAtuais = Integer.parseInt(infos[1]);
        String nome = infos[2];
        int matricula = Integer.parseInt(infos[3]);
        String curso = infos[4];
        String disciplinasCursadas = infos.length > 5 ? infos[5] : "";
        

        return new AlunoEspecial(nome, matricula, curso, disciplinasCursadas, 'a', disciplinasAtuais);
    }

}
