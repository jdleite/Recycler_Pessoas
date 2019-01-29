package meusconvidados.com.br.recyclerpessoas.listener;

import meusconvidados.com.br.recyclerpessoas.entidade.Pessoa;

public interface PessoaListener {
    void  onListClick(int id);

    void onDeleteClick(int id);
}
