package com.techgiants.recyclervieweragain;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<ContactModel> arrcontact=new ArrayList<>();
FloatingActionButton buttonopen;
RecyclerContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recycleContact=findViewById(R.id.recycont);
        recycleContact.setLayoutManager(new LinearLayoutManager(this));
        arrcontact.add(new ContactModel(R.drawable.img,"Akash","9528914540"));
        arrcontact.add(new ContactModel(R.drawable.img,"Amit","4328914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Aman","9328914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Nikhil","5428914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Naman","5428914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Manish","2128914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Love","4328914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Ram","5428914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Atharv","6528914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Aviral","4128914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Mukesh","6228914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Akash","9528914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Amit","4328914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Aman","9328914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Nikhil","5428914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Naman","5428914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Manish","2128914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Love","4328914140"));
        arrcontact.add(new ContactModel(R.drawable.img,"Ram","5428914140"));
buttonopen=findViewById(R.id.opendialouge);
buttonopen.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Dialog dial=new Dialog(MainActivity.this);
        dial.setContentView(R.layout.dialougebox);
        EditText addName=dial.findViewById(R.id.addName);
        EditText addNumber=dial.findViewById(R.id.addNumber);
        Button btn=dial.findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name="",number="";
                if(!addName.getText().toString().isEmpty()){
                 name=addName.getText().toString();
                }else{
                    Toast.makeText(MainActivity.this,"Please Enter Name",Toast.LENGTH_SHORT).show();
                }
                if(!addNumber.getText().toString().isEmpty()){
                    number=addNumber.getText().toString();
                }else{
                    Toast.makeText(MainActivity.this,"Please Enter Number",Toast.LENGTH_SHORT).show();
                }
                if(!addNumber.getText().toString().isEmpty()) {
                    arrcontact.add(new ContactModel(R.drawable.con1,name, number));
                    adapter.notifyItemInserted(arrcontact.size() - 1);
                    recycleContact.scrollToPosition(arrcontact.size() - 1);
                    dial.dismiss();
                }
            }
        });
        dial.show();
    }
});
    adapter=new RecyclerContactAdapter(this,arrcontact);
    recycleContact.setAdapter(adapter);


    }
}