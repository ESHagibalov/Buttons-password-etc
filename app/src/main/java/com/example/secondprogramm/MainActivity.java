package com.example.secondprogramm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText pass;//переменная пароля
    private Button button;//кнопка показать пароль
    private Button otherBtn;//кнопка выхода
    private RatingBar rating;//рейтинг
    private TextView text_show;//текстовое поле, которое показывает рейтинг
    private Button act_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        pass = (EditText)findViewById(R.id.editText);//привязали пароль к id
        button = (Button)findViewById(R.id.button);//привязали кнпкнопку пароля
        otherBtn = (Button)findViewById(R.id.allert);//привязали кнопку закрытия
        act_change = (Button)findViewById(R.id.atchange);
        button.setOnClickListener(//обработка нажатия кнопки
                new View.OnClickListener(){
                @Override
                public void onClick (View v){
                    button.setText("DONE");//при нажатии меняем текст
                    //button.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    Toast.makeText(MainActivity.this, pass.getText(), Toast.LENGTH_SHORT).show();//при нажатии вылезает окошко подсказка с паролем
                }
            }
        );

        act_change.setOnClickListener(
              new View.OnClickListener(){
                  @Override
                  public void onClick(View v){
                      Intent intent = new Intent(".SecondActivity");
                      startActivity(intent);
                  }
              }
        );

        rating = (RatingBar)findViewById(R.id.ratingBar);//привязка рейтинга к id
        text_show = (TextView)findViewById(R.id.textView);//привязка текстового поля, в котором отображается к id

        rating.setOnRatingBarChangeListener(//как обработчик кнопки, только обработка изменения рейтинга
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean fromUser) {
                        text_show.setText("Значение: " + String.valueOf(v));//при изменении в текстовое поле со значением рейтинга выводится рейтинг
                    }
                }
        );

        otherBtn.setOnClickListener(//обработчик нажатия на кропку выхода
                new View.OnClickListener(){
                    @Override
                    public void onClick (View v){
//                        otherBtn.setText("Текст изменен");
//                        button.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//                        Toast.makeText(MainActivity.this, pass.getText(), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);//билдер всплывающего окна
                        a_builder.setMessage("Вы хотите закрыть приложение?")//текст всплывающего окна
                                .setCancelable(false)//нет кнопки закрыть окно
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {//установка кнопки ДА и обработка ее нажатия
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        finish();//закрыть приложение
                                    }
                                })
                                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {//устнаовка кнопки НЕТ и обработка ее нажатия
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        dialogInterface.cancel();//закрытие всплывшего окна
                                    }
                                });
                        AlertDialog alert = a_builder.create();//объект алерт. все берется из его билдера(выше)
                        alert.setTitle("Закрытие приложения");//заголовок всплывшего окна
                        alert.show();//показ всплывающего окна
                    }
                }
        );
    }


}
