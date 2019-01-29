package meusconvidados.com.br.recyclerpessoas.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

import meusconvidados.com.br.recyclerpessoas.R;
import meusconvidados.com.br.recyclerpessoas.business.Business;
import meusconvidados.com.br.recyclerpessoas.constantes.Constantes;
import meusconvidados.com.br.recyclerpessoas.entidade.Pessoa;
import meusconvidados.com.br.recyclerpessoas.repositorio.Repositorio;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder viewHolder = new ViewHolder();
    private int pessoaId = 0;
    private Repositorio repositorio;
    private Business business;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewHolder.btn_Cadastrar = findViewById(R.id.btnCadastrar);
        viewHolder.edt_Nome = findViewById(R.id.edtNome);
        viewHolder.radioGroup = findViewById(R.id.rdg_Sexo);
        viewHolder.rdb_masc = findViewById(R.id.rdbMasc);
        viewHolder.rdb_fem = findViewById(R.id.rdbFemin);
        viewHolder.edt_Dt_Nasc = findViewById(R.id.edtDataNasc);
        viewHolder.btn_Listar = findViewById(R.id.btnListar);

        repositorio = new Repositorio();
        business = new Business();


        viewHolder.edt_Dt_Nasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirData();
            }
        });


        listener();
        carregarDados();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCadastrar) {
            cadastrarPessoa();


        } else if (v.getId() == R.id.btnListar) {

            startActivity(new Intent(MainActivity.this, ListaPessoaActivity.class));

        }

    }


    private static class ViewHolder {
        Button btn_Cadastrar, btn_Listar;
        EditText edt_Nome, edt_Dt_Nasc;
        RadioGroup radioGroup;
        RadioButton rdb_masc, rdb_fem, radio_Sexo;
    }


    private void listener() {

        viewHolder.btn_Cadastrar.setOnClickListener(this);
        viewHolder.btn_Listar.setOnClickListener(this);
    }

    public Boolean verificarCampos() {
        if (viewHolder.edt_Nome.getText().toString().trim().isEmpty()) {
            viewHolder.edt_Nome.setError(getString(R.string.nome_obrigatorio));
            return false;
        } else if (viewHolder.edt_Dt_Nasc.getText().toString().trim().isEmpty()) {
            viewHolder.edt_Dt_Nasc.setError(getString(R.string.data_obrigatorio));
            return false;
        }

        if (viewHolder.radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), getString(R.string.radio_obrigatorio), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void cadastrarPessoa() {
        if (!verificarCampos()) {
            return;
        } else {
            Pessoa p = new Pessoa();

            int id = viewHolder.radioGroup.getCheckedRadioButtonId();

            viewHolder.radio_Sexo = findViewById(id);

            p.setNome(viewHolder.edt_Nome.getText().toString());
            p.setSexo(viewHolder.radio_Sexo.getText().toString());
            p.setDt_nasc(viewHolder.edt_Dt_Nasc.getText().toString());

            if (pessoaId == 0) {
                if (business.inserirPessoa(p)) {

                    Toast.makeText(getApplicationContext(), getString(R.string.salvo_sucesso), Toast.LENGTH_SHORT).show();


                } else {

                    Toast.makeText(getApplicationContext(), getString(R.string.erro_salvar), Toast.LENGTH_SHORT).show();

                }


            } else {
                p.setId(pessoaId);


                if (business.alterar(p)) {

                    Toast.makeText(getApplicationContext(), getString(R.string.alterado_sucesso), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.erro_alterar), Toast.LENGTH_SHORT).show();

                }


            }


        }

        limparCampo();
        pessoaId = 0;


    }

    private void inserirData() {
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);

        DatePickerDialog d = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                viewHolder.edt_Dt_Nasc.setText(dayOfMonth + "/" + month + "/" + year);
            }
        }, ano, dia, mes);
        d.show();
    }

    private void limparCampo() {

        viewHolder.edt_Nome.setText("");
        viewHolder.radioGroup.clearCheck();
        viewHolder.edt_Dt_Nasc.setText("");
    }

    private void carregarDados() {
        Bundle b = getIntent().getExtras();

        if (b != null) {

            pessoaId = b.getInt(Constantes.BundleConstantes.PESSOA_ID);

            Pessoa pessoa = repositorio.carregarPessoa(pessoaId);

            viewHolder.edt_Nome.setText(pessoa.getNome());
            viewHolder.edt_Dt_Nasc.setText(pessoa.getDt_nasc());


            if (pessoa.getSexo().equalsIgnoreCase("masculino")) {

                viewHolder.rdb_masc.setChecked(true);


            } else {
                viewHolder.rdb_fem.setChecked(true);
            }


        }
    }
}



