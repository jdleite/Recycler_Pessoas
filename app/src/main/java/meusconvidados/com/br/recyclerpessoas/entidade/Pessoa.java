package meusconvidados.com.br.recyclerpessoas.entidade;

public class Pessoa {
    private int id;
    private String nome;
    private String sexo;
    private String dt_nasc;

    public Pessoa(){

    }

    public Pessoa(int id, String nome, String sexo, String dt_nasc) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dt_nasc = dt_nasc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(String dt_nasc) {
        this.dt_nasc = dt_nasc;
    }
}
