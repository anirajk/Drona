package org.example.mini;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.*;

public class Delete extends Activity implements OnClickListener {
	
	private Button ok,info,back;
	private RadioButton indl,subunit,all;
	 ArmyDB objDB;
        int DELETE_DIALOG_ID;
        
        
	
	public void onCreate(Bundle savedInstanceState)
        {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.delete);
		
	  DELETE_DIALOG_ID=100;
		 
		  
	 indl=(RadioButton)findViewById(R.id.indlradio_label);
	 subunit=(RadioButton)findViewById(R.id.subunitradio_label);
         all=(RadioButton)findViewById(R.id.allradio_label);
		  
	 ok=(Button)findViewById(R.id.deleteokbutton);
         ok.setOnClickListener(this);
	 info=(Button)findViewById(R.id.deleteinfobutton);
         info.setOnClickListener(this);
	 back=(Button)findViewById(R.id.deletebackbutton);
         back.setOnClickListener(this);
		 
         objDB=new ArmyDB(this); 
	 
         
      }
	

 public Dialog onCreateDialog(int id)
 {
   AlertDialog.Builder builder = new AlertDialog.Builder(this);
   builder.setMessage("All data will be erased. Do you want to continue ?")
          .setCancelable(false)
          .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
             objDB.open();                                                  
             objDB.deleteAllDetails();
             objDB.close();
                                        dialog.cancel();
                                                	  													
                     }})
         .setNegativeButton("No", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id)
          {
           dialog.cancel();
          } } );
          AlertDialog alert = builder.create();
          return alert;
 }


 public void onClick(View v)
 {
  Intent i;
  switch(v.getId())
  {
   case R.id.deleteokbutton:
        if(indl.isChecked())
        {
         i= new Intent(this,DeleteIndl.class);
         startActivity(i);
        }  
        else if(subunit.isChecked())
        {
         i= new Intent(this,DeleteSub.class);
         startActivity(i);
        }
        else if(all.isChecked())
        {
          showDialog(DELETE_DIALOG_ID);
					
        }	
        break;
		 
   case R.id.deletebackbutton:
        i=new Intent(this,Input.class);
        startActivity(i);
        break;
		 
   case R.id.deleteinfobutton:
        break;
		
  }
 }
}


