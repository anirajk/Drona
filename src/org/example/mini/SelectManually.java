package org.example.mini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class SelectManually extends Activity implements OnClickListener{
 CheckBox wpnCheckBox,noCheckBox,rankCheckBox,tradeCheckBox;
 CheckBox sunitCheckBox,dofCheckBox,percentCheckBox;
 Spinner wpnSpinner,noSpinner,rankSpinner,tradeSpinner;
 Spinner sunitSpinner,dofSpinner,percentSpinner;
 Button okButton,backButton,infoButton;
 ArrayAdapter<CharSequence> adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7;
 ArmyDB objDB;
 String wpn,no,rank,trade,sunit,dof,percent;
 boolean val;
 Intent i;
 int chkd=0;

 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.select_manually);

  wpnCheckBox=(CheckBox) findViewById(R.id.selmanwpncbox_label);
  wpnCheckBox.setOnClickListener(this);
  noCheckBox=(CheckBox) findViewById(R.id.selmannocbox_label);
  noCheckBox.setOnClickListener(this);
  rankCheckBox=(CheckBox) findViewById(R.id.selmanrankcbox_label);
  rankCheckBox.setOnClickListener(this);
  tradeCheckBox=(CheckBox) findViewById(R.id.selmantradecbox_label);
  tradeCheckBox.setOnClickListener(this);
  sunitCheckBox=(CheckBox) findViewById(R.id.selmansunitcbox_label);
  sunitCheckBox.setOnClickListener(this);
  dofCheckBox=(CheckBox) findViewById(R.id.selmandofcbox_label);
  dofCheckBox.setOnClickListener(this);
  percentCheckBox=(CheckBox) findViewById(R.id.selmanpercentcbox_label);
  percentCheckBox.setOnClickListener(this);
  
  wpnSpinner=(Spinner) findViewById(R.id.selmanwpn_label);
  adapter1 = ArrayAdapter.createFromResource(this, R.array.weapon_array, android.R.layout.simple_spinner_item);
  adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  wpnSpinner.setAdapter(adapter1);
  // adapter1.add("ALL");
  // adapter1.setNotifyOnChange(true);
   
 
  noSpinner=(Spinner) findViewById(R.id.selmanno_label);
  adapter2 = ArrayAdapter.createFromResource(this, R.array.nooffirers_array, android.R.layout.simple_spinner_item);
  adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  noSpinner.setAdapter(adapter2);

  rankSpinner=(Spinner) findViewById(R.id.selmanrank_label);
  adapter3 = ArrayAdapter.createFromResource(this, R.array.rank_array5, android.R.layout.simple_spinner_item);
  adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  rankSpinner.setAdapter(adapter3);
  
  tradeSpinner=(Spinner) findViewById(R.id.selmantrade_label);
  adapter4 = ArrayAdapter.createFromResource(this, R.array.trade_array, android.R.layout.simple_spinner_item);
  //adapter4.insert("ALL",15);
  adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  tradeSpinner.setAdapter(adapter4);

  sunitSpinner=(Spinner) findViewById(R.id.selmansunit_label);
  adapter5 = ArrayAdapter.createFromResource(this, R.array.subunit_array, android.R.layout.simple_spinner_item);
 // adapter5.insert("ALL",4);
  adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  sunitSpinner.setAdapter(adapter5);

  dofSpinner=(Spinner) findViewById(R.id.selmandof_label);
  adapter6 = ArrayAdapter.createFromResource(this, R.array.noofbalancefirers_array, android.R.layout.simple_spinner_item);
  adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  dofSpinner.setAdapter(adapter6);

  percentSpinner=(Spinner) findViewById(R.id.selmanpercent_label);
  adapter7 = ArrayAdapter.createFromResource(this, R.array.percentage_array, android.R.layout.simple_spinner_item);
  adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  percentSpinner.setAdapter(adapter7); 

  okButton=(Button) findViewById(R.id.selectmanok_label);
  okButton.setOnClickListener(this);
  backButton=(Button) findViewById(R.id.selectmanback_label);
  backButton.setOnClickListener(this); 
  infoButton=(Button) findViewById(R.id.selectmaninfo_label);
  infoButton.setOnClickListener(this);
 
  objDB=new ArmyDB(this);

 }
  

 public void selectFirers()
 {
  objDB.open();
  objDB.insertFirers(null);
  
  if(wpnCheckBox.isChecked())
  {
   wpn=wpnSpinner.getSelectedItem().toString();
   if(!wpn.equals("ALL"))
   {
    objDB.deleteFirersNotSelected("WPN", wpn);
   }
   chkd=chkd|1;
   i.putExtra("org.example.mini.Wpn",wpn);

 
  }
  if(noCheckBox.isChecked())
  {
    no=noSpinner.getSelectedItem().toString();
    chkd=chkd|2;
    i.putExtra("org.example.mini.No",no);
  }
  if(rankCheckBox.isChecked())
  { 
   rank=rankSpinner.getSelectedItem().toString();
   objDB.deleteFirersNotSelected("Rank", rank);
  }
  if(tradeCheckBox.isChecked())
  {
   trade=tradeSpinner.getSelectedItem().toString();
   if(!sunit.equals("ALL"))
   {
   objDB.deleteFirersNotSelected("Trade", trade);
   }
   chkd=chkd|4;
   i.putExtra("org.example.mini.Trade",trade);
   
  }
  if(sunitCheckBox.isChecked())
  {
   sunit=sunitSpinner.getSelectedItem().toString();
   if(!sunit.equals("ALL"))
   {
    objDB.deleteFirersNotSelected("Subunit", sunit);
   }
   chkd=chkd|8;
   i.putExtra("org.example.mini.Sunit",sunit);
   
  }
  if(dofCheckBox.isChecked())
  {
   dof=dofSpinner.getSelectedItem().toString();
  
   
  }
  objDB.close();  
}
   

 public void onClick(View v)
 {
  
  switch(v.getId())
  {
   case R.id.selmanwpncbox_label:
        if(wpnCheckBox.isChecked())
        {
         wpnSpinner.setClickable(true);
        }
        else
        {
         wpnSpinner.setClickable(false);     
        }
        break;
   case R.id.selmannocbox_label:
        if(noCheckBox.isChecked())
        {
         noSpinner.setClickable(true);
        }
        else
        {
         noSpinner.setClickable(false);     
        }
        break;
   case R.id.selmanrankcbox_label:
        if(rankCheckBox.isChecked())
        {
         rankSpinner.setClickable(true);
        }
        else
        {
         rankSpinner.setClickable(false);     
        }
        break;
   case R.id.selmantradecbox_label:
        if(tradeCheckBox.isChecked())
        {
         tradeSpinner.setClickable(true);
        }
        else
        {
         tradeSpinner.setClickable(false);     
        }
        break;
   case R.id.selmansunitcbox_label:
        if(sunitCheckBox.isChecked())
        {
         sunitSpinner.setClickable(true);
        }
        else
        {
         sunitSpinner.setClickable(false);     
        }
        break;
   case R.id.selmandofcbox_label:
        if(dofCheckBox.isChecked())
        {
         dofSpinner.setClickable(true);
        }
        else
        {
         dofSpinner.setClickable(false);     
        }
        break;
   case R.id.selmanpercentcbox_label:
        if(percentCheckBox.isChecked())
        {
         percentSpinner.setClickable(true);
        }
        else
        {
         percentSpinner.setClickable(false);     
        } 
        break;
   case R.id.selectmanok_label:
        if(!wpnCheckBox.isChecked()&&!noCheckBox.isChecked()&&!rankCheckBox.isChecked()&&!tradeCheckBox.isChecked()&&!sunitCheckBox.isChecked()&&!dofCheckBox.isChecked()&&!percentCheckBox.isChecked())  
        {
         Toast.makeText(getBaseContext(),"Atleast one of the checkboxes must be checked.",Toast.LENGTH_LONG);
        }
        else
        {
         i=new Intent(this,SelFirerManually.class);
         selectFirers();
         i.putExtra("org.example.mini.Checked", chkd);
         startActivity(i);
        }
        break;
   case R.id.selectmanback_label:
        i=new Intent(this,SelectFirers.class);
        startActivity(i);
        break;
   case R.id.selectmaninfo_label:
        break;

   }
  }
}
   


