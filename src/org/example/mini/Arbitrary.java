
package org.example.mini;



import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Arbitrary extends Activity implements OnClickListener{
 Spinner wpnSpinner,noSpinner,balanceFirersSpinner;
 ArrayAdapter<CharSequence> adapter1,adapter2,adapter3;
 CheckBox hqCheckBox,pCheckBox,qCheckBox,rCheckBox,balanceCheckBox;
 ArmyDB objDB;
 String wpn,no;
 Button okButton,backButton,infoButton;
 int chkdsunit=15;

 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.arbitrary);
 
  wpnSpinner=(Spinner) findViewById(R.id.arbwpnspinner_label);
  adapter1 = ArrayAdapter.createFromResource(this,R.array.weapon_array,android.R.layout.simple_spinner_item);     
  adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  wpnSpinner.setAdapter(adapter1);

 
  noSpinner=(Spinner) findViewById(R.id.arbnooffirersspinner_label);
  adapter2 = ArrayAdapter.createFromResource(this, R.array.nooffirers_array,android.R.layout.simple_spinner_item);     
  adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  noSpinner.setAdapter(adapter2);
  
  balanceFirersSpinner=(Spinner) findViewById(R.id.arbbalfirersspinner_label);

  hqCheckBox=(CheckBox) findViewById(R.id.sunithqcbox_label);
  pCheckBox=(CheckBox) findViewById(R.id.sunitpcbox_label);
  qCheckBox=(CheckBox) findViewById(R.id.sunitqcbox_label);
  rCheckBox=(CheckBox) findViewById(R.id.sunitrcbox_label);

  balanceCheckBox=(CheckBox) findViewById(R.id.arbbalancecbox_label);
  balanceCheckBox.setOnClickListener(this);  

  okButton=(Button) findViewById(R.id.arbok_label);
  okButton.setOnClickListener(this);
  backButton=(Button) findViewById(R.id.arbback_label);
  backButton.setOnClickListener(this);
  infoButton=(Button) findViewById(R.id.arbinfo_label);
  infoButton.setOnClickListener(this); 

  objDB=new ArmyDB(this);
 }

 public int checkSunit()
 {
  int f=1;
  if(!hqCheckBox.isChecked()&&!pCheckBox.isChecked()&&!qCheckBox.isChecked()&&!rCheckBox.isChecked())
  {
   f=0;
   Toast.makeText(getBaseContext(), "Atleast one subunit must be checked.", Toast.LENGTH_LONG).show();
  }
 return f;
 }

 public void selectFirers()
 {
   wpn=wpnSpinner.getSelectedItem().toString();
  // String s="INSERT INTO Firer_Selection SELECT Armyno,Subunit FROM "+
   //          "Indl_Details WHERE WPN="+wpn;
   
   no=noSpinner.getSelectedItem().toString();
   objDB.open();
   objDB.insertFirers(wpn);
   if(!hqCheckBox.isChecked())
   {
    chkdsunit=chkdsunit&14;
    objDB.deleteSunitFromFirers("HQ");   
   }
   if(!pCheckBox.isChecked())
   {
    chkdsunit=chkdsunit&13;
    objDB.deleteSunitFromFirers("P");   
   }
   if(!qCheckBox.isChecked())
   {
    chkdsunit=chkdsunit&11;
    objDB.deleteSunitFromFirers("Q");   
   }
   if(!rCheckBox.isChecked())
   {
    chkdsunit=chkdsunit&7;
    objDB.deleteSunitFromFirers("R");   
   }
   objDB.close();

 }
 public void onClick(View v)
 {
  int f;
  Intent i;
  switch(v.getId())
  {
   case R.id.arbbalancecbox_label:
        if(balanceCheckBox.isChecked())
        {
        	adapter3 = ArrayAdapter.createFromResource(this,R.array.noofbalancefirers_array,android.R.layout.simple_spinner_item);
			  adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			  balanceFirersSpinner.setAdapter(adapter3);
			   
		  }
		  else
		  {
			  adapter3 = ArrayAdapter.createFromResource(this,R.array.rank_no_item,android.R.layout.simple_spinner_item);
		      balanceFirersSpinner.setAdapter(adapter3);
		  }
        break;
   case R.id.arbok_label:
        f=checkSunit();
        if(f==1)
        {
         selectFirers();
         i=new Intent(this,SelectAny.class);
         i.putExtra("org.example.mini.Sunits",chkdsunit);
         i.putExtra("org.example.mini.Wpn",wpn);
         i.putExtra("org.example.mini.NoOfFirers",no);
         startActivity(i);
        }
        break;
        
   case R.id.arbback_label:   
	    i=new Intent(this,SelectFirers.class);
	    startActivity(i);
	    break;
  }
 }
 }
