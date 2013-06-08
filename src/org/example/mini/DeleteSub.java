package org.example.mini;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class DeleteSub extends Activity implements OnClickListener{
	
	ArmyDB objDB;
	private Button okButton,infoButton,backButton;
        private CheckBox hq,p,q,r;
        private int DELETE_DIALOG=101;
        
		
	
   public void onCreate(Bundle savedInstanceState)
   {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.delete_subunit);

    okButton = (Button)findViewById(R.id.deletesubokbutton);
    infoButton = (Button)findViewById(R.id.deletesubinfobutton);
    backButton = (Button)findViewById(R.id.deletesubbackbutton);    hq=(CheckBox)findViewById(R.id.deletehq);
    p=(CheckBox)findViewById(R.id.deletep);
    q=(CheckBox)findViewById(R.id.deleteq);
    r=(CheckBox)findViewById(R.id.deleter);                       
    okButton.setOnClickListener( this);
    backButton.setOnClickListener( this);
    infoButton.setOnClickListener( this);
	      
    objDB=new ArmyDB(this);
   }



 public Dialog onCreateDialog(int id)
 {
   AlertDialog.Builder builder = new AlertDialog.Builder(this);
   builder.setMessage("Data of the selected subunits will be erased. Do you want to continue ?")
          .setCancelable(false)
          .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                  public void onClick(DialogInterface dialog, int id) {
                	    objDB.open();
                                if(hq.isChecked())
                                 objDB.deleteSubunitFromDetails("HQ");
                                if(p.isChecked())
                                  objDB.deleteSubunitFromDetails("P");
           				      if(q.isChecked())
                                objDB.deleteSubunitFromDetails("Q");
                                      
                               if(r.isChecked())
                                objDB.deleteSubunitFromDetails("R");
                               objDB.close();
                              	refresh();		            	
                                 dialog.cancel();
                            }})
          .setNegativeButton("No", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id)  {
              dialog.cancel();
             } } );
                AlertDialog alert = builder.create();
                return alert;
 }

  void refresh()
 {
	hq.setChecked(false);
	p.setChecked(false);
	q.setChecked(false);
	r.setChecked(false);
 }
	
 
	 
 public void onClick(View v)
 {	 
  switch(v.getId())
  {
   case R.id.deletesubokbutton:
        if(hq.isChecked()||p.isChecked()||q.isChecked()||r.isChecked())
	  showDialog(DELETE_DIALOG);
        else
          Toast.makeText(getBaseContext(), "Atleast one option must be selected.", Toast.LENGTH_LONG).show();
        break;
			 
   case R.id.deletesubbackbutton:
        Intent back=new Intent(this,Delete.class);        startActivity(back);        break;

   case R.id.deletesubinfobutton:
        break;
			
  }
 }
}
