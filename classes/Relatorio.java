package classes;

public class Relatorio 
{
    //falta a identificação, codigo da turma ou disciplina, ou matricula do prof
    //falta a parte de arquivos 
    // tirando notaMedia todos os outros são numeros absolutos
    private final Object tipo; //se é de professor, turma ou disciplina
    private float notaMedia; //esse é calculado
    private float passaram; 
    private float reprovaram;
    private float reprovaramFalta;
    private float reprovaramNota;
    private float reprovaramNotaEFalta;

    //construtores
    public Relatorio(Object tipo)
    {
        this.tipo = tipo;
    }
    
    //get e set
    public Object getTipo()
    {
        return this.tipo;
    }
    public float getNotaMedia()
    {
        return this.notaMedia;
    }
    public float getPassaram()
    {
        return this.passaram;
    }
    public float getReprovaram()
    {
        return this.reprovaram;
    }
    public float getReprovaramFalta()
    {
        return this.reprovaramFalta;
    }
    public float getReprovaramNota()
    {
        return this.reprovaramNota;
    }
    public float getReprovaramNotaEFalta()
    {
        return this.reprovaramNotaEFalta;
    }
    public void addPassaram()
    {
        this.passaram++;
    }
    public void removePassaram()
    {
        this.passaram--;
    }
    public void addReprovaram()
    {
        this.reprovaram++;
    }
    public void removeReprovarm()
    {
        this.reprovaram--;
    }
    public void addReprovaramFalta()
    {
        this.reprovaramFalta++;
    }
    public void removeReprovaramFalta()
    {
        this.reprovaramFalta--;
    }
    public void addReprovaramNota()
    {
        this.reprovaramNota++;
    }
    public void removeReprovaramNota()
    {
        this.reprovaramNota--;
    }
    public void addReprovaramNotaEFalta()
    {
        this.reprovaramNotaEFalta++;
    }
    public void removeReprovaramNotaEFalta()
    {
        this.reprovaramNotaEFalta--;
    }
    public void addNotaMedia(float nota)
    {
        this.notaMedia+=nota;
        this.notaMedia/=2;
    }
    public void setNotaMedia(float nota)
    {
        this.notaMedia = nota;
    }
    public float Porcentagem(float coisa1, float coisa2)
    {
        return (coisa1/coisa2)*100;
    }
    public float Porcentagem(int coisa1, int coisa2)
    {
        return (coisa1/coisa2)*100;
    }
    //parte de arquivos
}
