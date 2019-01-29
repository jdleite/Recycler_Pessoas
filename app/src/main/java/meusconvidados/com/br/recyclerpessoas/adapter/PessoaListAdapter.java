package meusconvidados.com.br.recyclerpessoas.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import meusconvidados.com.br.recyclerpessoas.banco.MyApp;
import meusconvidados.com.br.recyclerpessoas.R;
import meusconvidados.com.br.recyclerpessoas.entidade.Pessoa;
import meusconvidados.com.br.recyclerpessoas.listener.PessoaListener;
import meusconvidados.com.br.recyclerpessoas.viewHolder.PessoaViewHolder;

public class PessoaListAdapter extends RecyclerView.Adapter<PessoaViewHolder> {
    private List<Pessoa> listaPessoa;
    private PessoaListener listListener;

    public PessoaListAdapter(List<Pessoa> pessoas, PessoaListener listener) {
        listaPessoa = pessoas;
        listListener = listener;
    }

    @NonNull
    @Override
    public PessoaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View pessoaView = inflater.inflate(R.layout.pessoa_row, viewGroup, false);
        return new PessoaViewHolder(pessoaView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaViewHolder pessoaViewHolder, int i) {

        Pessoa pessoa = listaPessoa.get(i);

        pessoaViewHolder.bindData(pessoa, listListener);

    }

    @Override
    public int getItemCount() {
        return listaPessoa.size();
    }
}
