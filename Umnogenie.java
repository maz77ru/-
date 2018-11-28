package kz.mazur.generator;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by user on 30.01.2018.
 */

public class Umnogenie extends FragmentActivity {
    private Klava klava;
    private android.support.v4.app.FragmentManager manager;
    private FragmentTransaction transaction;
    private AdView mAdView;
    SharedPreferences par;
    private static final String VREMA = "16";
    private static final String CHISLO = "9";
    private static final String RE1 ="0";
    int num1,num2 ,result, pravda,prav,net;
    int mnogit,ret, vrem;
    TextView mnog1,mnog2,vremia,pravilno,neprav,otvet;
    Button button;
    CountDownTimer timer;
    long vre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umnogenie);
        MobileAds.initialize(this, "ca-app-pub-6235348522304098~2247377017");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // находим элементы
        mnog1 = (TextView) findViewById(R.id.mnog1);
        mnog2 = (TextView) findViewById(R.id.mnog2);
        otvet = (TextView) findViewById(R.id.otvet);
        button=(Button)findViewById(R.id.button);
        vremia=(TextView)findViewById(R.id.vremia);
        pravilno=(TextView)findViewById(R.id.pravilno);
        neprav=(TextView)findViewById(R.id.neprav);
        klava=new Klava();
        manager =getSupportFragmentManager();
        par = getSharedPreferences("par", MODE_PRIVATE);
        mnogit = Integer.parseInt( par.getString(CHISLO, "9"));
         ret=par.getInt(VREMA,0);
         //выставляем счетчики времени в зависимости от данных
         if (ret==0){vrem=60000;}
         if (ret==1){vrem=120000;}
//перехватываем нажатие конпки энтер
        otvet.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER))
                        || (i == EditorInfo.IME_ACTION_DONE)) {
                    //сделать, что нужно по нажатию на Done
                    if (TextUtils.isEmpty(mnog1.getText().toString())){
                        return false;
                    }
                  proverka();
                }
                return false;
            }
        });

    }

    public void start(View view) {
       if (TextUtils.isEmpty(mnog1.getText().toString())){
           button.setBackgroundColor(Color.YELLOW);
          button.setText(R.string.proverit);//меняем название кнопки на проверить
           TextView textView2=(TextView)findViewById(R.id.textView2);
           TextView textView3=(TextView)findViewById(R.id.textView3);
           textView2.setText("*");
           textView3.setText("=");
           otvet.setVisibility(View.VISIBLE);
           generacia(); //вызываем функцию генерирующую вопрос
          if (ret!=2){// сли задано то делаем таймер обратного отсчета
           //Создаем таймер обратного отсчета на n секунд с шагом отсчета
           //в 1 секунду (задаем значения в миллисекундах):
          timer= new CountDownTimer(vrem,1000) {
               @Override
               public void onTick(long l) {
                   vremia.setText(""+l/1000);
                   vre=l;
               }

               @Override
               public void onFinish() {//что то делаем когда кончилось время
                  Recor();
                   // отображаем сообщение пользователю
                   final AlertDialog.Builder builder=new AlertDialog.Builder(Umnogenie.this);
                   builder.setTitle(R.string.message);
                   builder.setMessage("Верно :"+prav+"\n Неверно :"+net);
                   builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           otvet.setText("");
                           mnog1.setText("");
                           mnog2.setText("");
                           prav=0;
                           pravilno.setText("");
                           net=0;
                           neprav.setText("");
                           button.setText("старт");
                           dialogInterface.cancel();
                       }
                   });
                   AlertDialog alert=builder.create();
                   alert.show();
               }
           }.start();
           return;
          }
       }
       //сюда ставим проверку на время и если задано устанавливаем нужное время и после его окончания выводим результат
     proverka();
    }

    private void Recor() {
        //проверяем на рекорд если нет ошибки
        if(net==0){
            par=getSharedPreferences("par",MODE_PRIVATE);
            boolean rd1=par.contains(RE1);
            SharedPreferences.Editor ed=par.edit();
            if(rd1==false){
                ed.putInt(VREMA,prav);
                ed.commit();
            }else {
                if(prav>par.getInt(RE1,0)){
                    ed.putInt(VREMA,prav);
                    ed.commit();
                }
            }

        }
    }


    private void proverka(){
        //  П р о в е р я е м   п о л я   н а   п у с т о т у
        if (TextUtils.isEmpty(otvet.getText().toString())){
            Toast.makeText(getApplicationContext(), "где ответ!", Toast.LENGTH_SHORT).show();
            return;
        }
        //   ч и т а е м   E d i t T e x t   и   з а п о л н я е м   п е р е м е н н ы е   ч и с л а м и
        result = Integer.parseInt(otvet.getText().toString());
        num1 = Integer.parseInt(mnog1.getText().toString());
        num2 = Integer.parseInt(mnog2.getText().toString());
        pravda=num1*num2;
        if(result==pravda){
            prav=prav +1;
            pravilno.setText(""+prav);
            otvet.setText( "" );
            generacia();
        }
        if(result!=pravda) {
            net=net+1;
            neprav.setText(""+net);
            if (ret==2){ otvet.setText( "" );
            }
            else { otvet.setText( "" );
                generacia();}
            }
    }
    private void generacia(){
        // формируем строку вывода
        num1= (int) (Math.random()*8 )+2;//генерируем случайное число в диапазоне от 2 до 9
        num2= (int) (Math.random()*mnogit )+1;//генерируем случайное число в диапазоне от 1 до 9
        mnog1.setText( ""+ num1 );
        mnog2.setText( ""+ num2 );
    }
    @Override
    public void onBackPressed(){
        if (vre>0){timer.cancel();}
        mAdView.destroy();
        finish();
    }

}
