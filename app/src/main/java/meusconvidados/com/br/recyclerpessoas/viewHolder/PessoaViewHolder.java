package meusconvidados.com.br.recyclerpessoas.viewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import meusconvidados.com.br.recyclerpessoas.R;
import meusconvidados.com.br.recyclerpessoas.entidade.Pessoa;
import meusconvidados.com.br.recyclerpessoas.listener.PessoaListener;

public class PessoaViewHolder extends RecyclerView.ViewHolder {
    TextView txt_nome;
    ImageView img_excluir;
    private Context mContext;
    public PessoaViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        txt_nome = itemView.findViewById(R.id.txtNomePessoa);
        img_excluir = itemView.findViewById(R.id.image_remove);

        mContext = context;
    }

    public void bindData(final Pessoa pessoa, final PessoaListener listener) {
        txt_nome.setText(pessoa.getNome());

        txt_nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listener.onListClick(pessoa.getId());
            }
        });

        img_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle(mContext.getString(R.string.remocao_convidado))
                        .setMessage(mContext.getString(R.string.deseja_remover))
                        .setIcon(R.drawable.remove)
                        .setPositiveButton(mContext.getString(R.string.sim), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                listener.onDeleteClick(pessoa.getId());

                            }
                        })
                        .setNeutralButton(mContext.getString(R.string.nao), null)
                        .show();
            }
        });
    }
}
