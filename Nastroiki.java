package kz.mazur.generator;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by user on 04.02.2018.
 */

public class Nastroiki extends Activity {

    SharedPreferences par;
    private static final String VREMA = "16";
    private static final String CHISLO = "9";
    private static final String REC1 ="";
    private static final String REC2 ="";
    private static final String REC3 ="";
    private static final String REC4 ="";
    private static final String REC5 ="";
    private static final String REC6 ="";
    private static final String REC7 ="";
    private static final String REC8 ="";
    private static final String REC9 ="";
    String selected;
    String selected2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nastroiki);
    }


    public void sohranit(View view) {
        //снимаем данные со спинера и сохраняем их
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
         int selected = spinner.getSelectedItemPosition();

        //снимаем данные со спинера и сохраняем их
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
         selected2 = spinner2.getSelectedItem().toString();

        par=getSharedPreferences("par",MODE_PRIVATE);
        SharedPreferences.Editor ed=par.edit();
        ed.putInt(VREMA,selected);
        ed.putString(CHISLO,selected2);
        ed.commit();
        finish();
    }

}
