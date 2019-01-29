package meusconvidados.com.br.recyclerpessoas.banco;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainDb extends SQLiteOpenHelper {
    private static String NOME_BANCO = "JEFFERSON";
    public static String TABELA_PESSOA = "PESSOA";
    private static int VERSAO = 1;

    private static MainDb instancia;

    public static MainDb getInstancia(){
        if(instancia == null) instancia = new MainDb();

        return instancia;
    }


    public MainDb() {
        super(MyApp.getContext(), NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        instancia = null;
        super.close();
    }
}
