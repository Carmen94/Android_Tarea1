package com.iteso.tarea1;

import android.content.Context;
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
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameEt;
    EditText phoneEt;
    Spinner spinner;
    Button cleanButton;
    RadioButton radioButton;
    AutoCompleteTextView bookTextView;
    Student student;
    CheckBox checkBox;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_item:
                Toast.makeText(this, "Clicked Menu 1", Toast.LENGTH_SHORT).show();
                CleanFields();
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
        radioButton=findViewById(R.id.radioFemale);

        phoneEt = findViewById(R.id.phone_et);
        spinner = findViewById(R.id.escolaridad_spinner);
        cleanButton = findViewById(R.id.clean_button);
        bookTextView = findViewById(R.id.book_autocomplete);

        // Get the string array
        String[] books = getResources().getStringArray(R.array.books_array);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
        bookTextView.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,R.array.escolaridad_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        cleanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CleanFields();
            }
        });

    }

    private void CleanFields(){
        nameEt.setText("");
        phoneEt.setText("");
        spinner.setSelection(0);
        bookTextView.setText("");
        checkBox.setSelected(false);
    }

    private void Save(){
        Context context = getApplicationContext();
        student=new Student();
        student.Name(nameEt.getText().toString());
        student.Phone(phoneEt.getText().toString());
        student.Gender();

        CharSequence text =student.GetStudentInformation();
        int duration=Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,text,duration);
    }
}
