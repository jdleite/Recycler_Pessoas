package meusconvidados.com.br.recyclerpessoas.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import meusconvidados.com.br.recyclerpessoas.R;
import meusconvidados.com.br.recyclerpessoas.adapter.PessoaListAdapter;
import meusconvidados.com.br.recyclerpessoas.business.Business;
import meusconvidados.com.br.recyclerpessoas.constantes.Constantes;
import meusconvidados.com.br.recyclerpessoas.entidade.Pessoa;
import meusconvidados.com.br.recyclerpessoas.listener.PessoaListener;


public class ListaPessoaActivity extends AppCompatActivity {

    private ViewHolder viewHolder = new ViewHolder();
    private PessoaListener pessoaListener;

    Business business = new Business();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa);





        pessoaListener = new PessoaListener() {
            @Override
            public void onListClick(int id) {
                Bundle bundle = new Bundle();

                bundle.putInt(Constantes.PessoaConstantes.PESSOA_ID, id);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                intent.putExtras(bundle);

                startActivity(intent);


            }

            @Override
            public void onDeleteClick(int id) {
                business.removerPessoa(id);
                onResume();
            }
        };


        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.addAll(business.listaPessoa());

        viewHolder.recyclerPessoa = findViewById(R.id.recycler_pessoa);

        PessoaListAdapter adapter = new PessoaListAdapter(pessoas, pessoaListener);
        viewHolder.recyclerPessoa.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        viewHolder.recyclerPessoa.setLayoutManager(manager);
    }

    public static class ViewHolder {
        RecyclerView recyclerPessoa;
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Pessoa> pessoaLista = business.listaPessoa();

        PessoaListAdapter adapter = new PessoaListAdapter(pessoaLista,pessoaListener);
        viewHolder.recyclerPessoa.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}

