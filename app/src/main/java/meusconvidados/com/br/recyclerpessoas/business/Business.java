package meusconvidados.com.br.recyclerpessoas.business;

import java.util.List;

import meusconvidados.com.br.recyclerpessoas.entidade.Pessoa;
import meusconvidados.com.br.recyclerpessoas.repositorio.Repositorio;

public class Business {
    Repositorio repositorio = new Repositorio();

    public Boolean inserirPessoa(Pessoa pessoa){
        return repositorio.inserirPessoa(pessoa);
    }

    public Boolean alterar(Pessoa pessoa){
        return repositorio.alterar(pessoa);
    }


    public List<Pessoa> listaPessoa(){
        return  repositorio.listaPessoa();
    }

    public Boolean removerPessoa(int id){
        return repositorio.removerPessoa(id);
    }
}


