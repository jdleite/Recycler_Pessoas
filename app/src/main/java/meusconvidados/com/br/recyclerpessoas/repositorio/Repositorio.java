package meusconvidados.com.br.recyclerpessoas.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import meusconvidados.com.br.recyclerpessoas.banco.MainDb;
import meusconvidados.com.br.recyclerpessoas.constantes.Constantes;
import meusconvidados.com.br.recyclerpessoas.entidade.Pessoa;

public class Repositorio {
    public Boolean inserirPessoa(Pessoa pessoa) {
        try {

            SQLiteDatabase db = MainDb.getInstancia().getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Constantes.PessoaConstantes.PESSOA_NOME, pessoa.getNome());
            cv.put(Constantes.PessoaConstantes.PESSOA_SEXO, pessoa.getSexo());
            cv.put(Constantes.PessoaConstantes.PESSOA_DT_NASC, pessoa.getDt_nasc());

            db.insert(MainDb.TABELA_PESSOA, null, cv);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public List<Pessoa> listaPessoa() {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            SQLiteDatabase db = MainDb.getInstancia().getReadableDatabase();
            String query = "SELECT * FROM " + MainDb.TABELA_PESSOA + " ORDER BY ID DESC";
            Cursor c = db.rawQuery(query, null);

            if (c.moveToFirst()) {
                do {
                    Pessoa p = new Pessoa();

                    p.setId(c.getInt(0));
                    p.setNome(c.getString(1));
                    p.setSexo(c.getString(2));
                    p.setDt_nasc(c.getString(3));

                    pessoas.add(p);

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pessoas;
    }

    public Pessoa carregarPessoa(int id) {
        Pessoa pessoa = new Pessoa();
        try {

            SQLiteDatabase db = MainDb.getInstancia().getReadableDatabase();
            String query = "SELECT * FROM " + MainDb.TABELA_PESSOA + " WHERE ID  = '" + id + "'";
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                pessoa.setId(cursor.getInt(0));
                pessoa.setNome(cursor.getString(1));
                pessoa.setSexo(cursor.getString(2));
                pessoa.setDt_nasc(cursor.getString(3));
            }

            if (cursor != null) {
                cursor.close();
            }


            return pessoa;
        } catch (Exception e) {
            return pessoa;
        }


    }

    public Boolean alterar(Pessoa pessoa) {
        try {
            SQLiteDatabase db = MainDb.getInstancia().getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(Constantes.PessoaConstantes.PESSOA_NOME, pessoa.getNome());
            cv.put(Constantes.PessoaConstantes.PESSOA_SEXO, pessoa.getSexo());
            cv.put(Constantes.PessoaConstantes.PESSOA_DT_NASC, pessoa.getDt_nasc());

            String where = " ID = '" + pessoa.getId() + "'";
            db.update(MainDb.TABELA_PESSOA, cv, where, null);

            return true;


        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }


    }

    public Boolean removerPessoa(int id){
        try {
            SQLiteDatabase sqLiteDatabase = MainDb.getInstancia().getWritableDatabase();

            String whereClause = Constantes.PessoaConstantes.PESSOA_ID + " = ?";

            String[] whereArgs = {String.valueOf(id)};

            sqLiteDatabase.delete(MainDb.TABELA_PESSOA, whereClause, whereArgs);

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
