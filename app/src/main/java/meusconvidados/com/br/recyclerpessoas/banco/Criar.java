package meusconvidados.com.br.recyclerpessoas.banco;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Criar {

    public void criarBanco(){
        try {
            SQLiteDatabase db = MainDb.getInstancia().getWritableDatabase();
            String colunas = "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NOME VARCHAR,SEXO VARCHAR,DT_NASC VARCHAR)";
            String query = "CREATE TABLE IF NOT EXISTS " + MainDb.TABELA_PESSOA + colunas;
            db.execSQL(query);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
