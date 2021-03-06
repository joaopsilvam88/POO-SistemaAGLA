package object;

import java.util.*;

public class Professor {

    private int id;
    String matricula, lattes;
    DadosPessoais dadosPessoais;
    List<Disciplina> disciplinas;

    public Professor() {
        this("", "", new DadosPessoais(), new ArrayList<Disciplina>());
    }

    public Professor(String matricula, String lattes, DadosPessoais dadosPessoais, List<Disciplina> disciplinas) {
        this.matricula = matricula;
        this.lattes = lattes;
        this.dadosPessoais = dadosPessoais;
        this.disciplinas = disciplinas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getLattes() {
        return lattes;
    }

    public void setLattes(String lattes) {
        this.lattes = lattes;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id = " + this.id + ", Matricula = " + this.matricula + ", Lattes = " + this.lattes
                + ", DadosPessoais = " + this.dadosPessoais;
    }
}
