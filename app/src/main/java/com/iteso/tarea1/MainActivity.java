package com.iteso.tarea1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameEt;
    EditText phoneEt;
    Spinner escolaridadSpinner;
    Button cleanButton;
    RadioGroup radioGroup;
    AutoCompleteTextView bookTextView;
    Student student;
    CheckBox checkBox;
    RadioButton selectedRadio;

    AlertDialog.Builder alertDialogBuilder;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_item:
                Save();
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEt = findViewById(R.id.name_et);
        phoneEt = findViewById(R.id.phone_et);
        radioGroup = findViewById(R.id.radioGender);
        escolaridadSpinner = findViewById(R.id.escolaridad_spinner);
        cleanButton = findViewById(R.id.clean_button);
        bookTextView = findViewById(R.id.book_autocomplete);
        checkBox = findViewById(R.id.checkbox_sport);
        // Get the string array
        String[] books = getResources().getStringArray(R.array.books_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
        bookTextView.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,R.array.escolaridad_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        escolaridadSpinner.setAdapter(adapterSpinner);
        CreateDialog();
        cleanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void CleanFields(){
        nameEt.setText("");
        phoneEt.setText("");
        escolaridadSpinner.setSelection(0);
        bookTextView.setText("");
        checkBox.setSelected(false);
    }

    private void Save(){
        Context context = getApplicationContext();
        student= new Student();
        student.Name(nameEt.getText().toString());
        student.Phone(phoneEt.getText().toString());
        int selectedId = radioGroup.getCheckedRadioButtonId();
        selectedRadio = findViewById(selectedId);
        student.Gender(selectedRadio.getText().toString());
        student.SchoolLevek(escolaridadSpinner.getSelectedItem().toString());
        student.Sports(checkBox.isChecked());
        student.FavoriteBook(bookTextView.getText().toString());
        CharSequence text = student.ToString();
        int duration=Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

    private void CreateDialog(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Seguro quieres borrar?");
        alertDialogBuilder.setPositiveButton("Sí",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(MainActivity.this,"Eliminado",Toast.LENGTH_LONG).show();
                    CleanFields();
                }
            });
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this,"Cancelado",Toast.LENGTH_LONG).show();
                }
            });
    }
}
