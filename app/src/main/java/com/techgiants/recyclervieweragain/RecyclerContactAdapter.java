package com.techgiants.recyclervieweragain;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.Viewholder> {
    Context context;
    int lastposn=-1;
    ArrayList<ContactModel> arrContacts;
    RecyclerContactAdapter(Context context,ArrayList<ContactModel> arrContacts){
        this.context=context;
        this.arrContacts=arrContacts;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.contactrow,parent,false);
       Viewholder viewHold=new Viewholder(v);
       return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.img.setImageResource(arrContacts.get(position).img);
        holder.txtName.setText(arrContacts.get(position).name);
        holder.txtNumber.setText(arrContacts.get(position).number);
        holder.setAmimation(holder.itemView,position);
     holder.ll.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Dialog dial=new Dialog(context);
             dial.setContentView(R.layout.dialougebox);
             EditText edtname=dial.findViewById(R.id.addName);
             EditText edtnumber=dial.findViewById(R.id.addNumber);
             Button btn=dial.findViewById(R.id.btnAdd);
             btn.setText("update");
             TextView txt=dial.findViewById(R.id.txtheading);
             txt.setText("Update");
             edtname.setText((arrContacts.get(position).name));
             edtnumber.setText((arrContacts.get(position).number));
             btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     String name="",number="";
                     if(!edtname.getText().toString().isEmpty()){
                         name=edtname.getText().toString();
                     }else{
                         Toast.makeText(context,"Please Enter Name",Toast.LENGTH_SHORT).show();
                     }
                     if(!edtnumber.getText().toString().isEmpty()){
                         number=edtnumber.getText().toString();
                     }else{
                         Toast.makeText(context,"Please Enter Number",Toast.LENGTH_SHORT).show();
                     }
                    arrContacts.set(position,new ContactModel(arrContacts.get(position).img,name,number));
                     notifyItemChanged(position);
                     dial.dismiss();
                     }
             });
    dial.show();
         }
     });
     holder.ll.setOnLongClickListener(new View.OnLongClickListener() {
         @Override
         public boolean onLongClick(View v) {
             AlertDialog.Builder builder=new AlertDialog.Builder(context);
             builder.setTitle("Delete Contact");
             builder.setIcon(R.drawable.delete);
             builder.setMessage("Are You Sure to delete");
             builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                   arrContacts.remove(position);
                   notifyItemRemoved(position);
                 }
             }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             });
             builder.show();
             return true;
         }
     });
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView txtName,txtNumber;
        ImageView img;
        LinearLayout ll;
      public Viewholder(View itemview){
          super(itemview);
          txtName=itemview.findViewById(R.id.contactname);
          txtNumber=itemview.findViewById(R.id.contactnumber);
          img=itemview.findViewById(R.id.contactimg);
          ll=itemview.findViewById(R.id.contactrowlayout);
      }
      private void setAmimation(View viewtoanimate,int position){
          if(position>lastposn){
              Animation slidein= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
              viewtoanimate.startAnimation(slidein);
              lastposn=position;
          }
      }

  }
}
