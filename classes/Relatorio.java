package classes;

public class Relatorio 
{
    //fazer o que calcula porcentagem nos "set"
    private final Object tipo;
    private float notaMedia;
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
    //parte de arquivos
}
