package com.ericjimenez.andrevinav2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int intentos = 0;
    private int numeros = 0;
    private TextView logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //PlayGameDialog playagain = new PlayGameDialog().onCreateDialog();
        Random rand = new Random();
        // Conseguir un numero entre 0 y 100
        numeros = rand.nextInt(101);
        //Getting logs text view
        logs = (TextView) findViewById(R.id.textView2);
        logs.setMovementMethod(new ScrollingMovementMethod());

        //Getting the button by ID
        Button button = (Button) findViewById(R.id.button);
        //Getting userInput EditText
        EditText userinput = (EditText) findViewById(R.id.inputNumber1);
        //Setting onclicklistener
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                int inputNumber = 0;
                CharSequence text = "";
                new PlayGameDialog();
                // Getting user input
                inputNumber = Integer.valueOf(userinput.getText().toString());

                if (inputNumber == numeros){
                    text = "Que bueno eres! El numero acertante era el "+numeros;
                    showDialog();
                }
                else if (inputNumber<numeros){
                    intentos++;
                    logs.append("El numero es mas grande bestia, Number: "+inputNumber+", Intentos: "+intentos+"\n");
                    text = "Error :( The number is bigger";
                }
                else if (inputNumber>numeros){
                    intentos++;
                    logs.append("El numero es mas pequeño animal, Number: "+inputNumber+", Intentos: "+intentos+"\n");
                    text = "Error :( The number is lower";
                }

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });
    }
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Has ganado con "+intentos+" intentos!!! Eres una maquina, ¿Quieres volver a jugar?")
                .setPositiveButton("Jugar otra vez", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        playAgain();
                    }
                })
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Crea el AlertDialog y lo devuelve
        builder.create();
        builder.show();
    }
    public void playAgain(){
        Random rand = new Random();
        intentos=0;
        // Crea un numero entre 0 y 100 cuando le das a jugar otra vez
        numeros = rand.nextInt(101);
        logs.setText("");
    }
    public class PlayGameDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Has ganado!!! ¿Quieres volver a jugar?")
                    .setPositiveButton("Juega otra vez", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            playAgain();
                        }
                    })
                    .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    public class StartGameDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("test")
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // START THE GAME!
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}