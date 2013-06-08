package org.example.mini;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.*;

public class DetailSelection extends Activity implements OnClickListener,OnItemSelectedListener{

Spinner visSpinner,wpnSpinner,pracSpinner,typeSpinner,rangeSpinner;
Spinner noSpinner,posnSpinner,rankSpinner,sunitSpinner,tgttypeSpinner;
CheckBox rankCheckBox,sunitCheckBox,tgttypeCheckBox;
Button homeButton,okButton,imButton,rcButton;
ArrayAdapter<CharSequence> adapter1,adapter2,adapter3,adapter4,adapter5;
 ArrayAdapter<CharSequence> adapter6,adapter7,adapter8,adapter9,adapter10;
String vis,wpn,prac,type,posn,rank,sunit,tgttype;
int no,range,check=1;
boolean sFlag=true;
ArrayList<CharSequence> entries;


public void onCreate(Bundle savedInstanceState)
{
 super.onCreate(savedInstanceState);
 setContentView(R.layout.detail_selection);
 

 visSpinner=(Spinner) findViewById(R.id.detselvis_label);
 
 wpnSpinner=(Spinner) findViewById(R.id.detselwpn_label);
 wpnSpinner.setOnItemSelectedListener(this);
 
 noSpinner=(Spinner) findViewById(R.id.detseldetail_label);

 pracSpinner=(Spinner) findViewById(R.id.detselprac_label);
 pracSpinner.setOnItemSelectedListener(this);
 
 typeSpinner=(Spinner) findViewById(R.id.detseltype_label); 
 typeSpinner.setOnItemSelectedListener(this);
 
 rangeSpinner=(Spinner) findViewById(R.id.detselrange_label);
 rangeSpinner.setOnItemSelectedListener(this);
 
 posnSpinner=(Spinner) findViewById(R.id.detselposn_label);
 posnSpinner.setOnItemSelectedListener(this);

 rankCheckBox=(CheckBox) findViewById(R.id.detselrankcbox_label);
 rankCheckBox.setOnClickListener(this); 
 rankSpinner=(Spinner) findViewById(R.id.detselrank_label);

 sunitCheckBox=(CheckBox) findViewById(R.id.detselsunitcbox_label);
 sunitCheckBox.setOnClickListener(this); 
 sunitSpinner=(Spinner) findViewById(R.id.detselsunit_label);

 tgttypeCheckBox=(CheckBox) findViewById(R.id.detseltgttypecbox_label);
 tgttypeCheckBox.setOnClickListener(this); 
 tgttypeSpinner=(Spinner) findViewById(R.id.detseltgttype_label);
 
 adapter1=ArrayAdapter.createFromResource(this, R.array.vis_array,android.R.layout.simple_spinner_item);     
 adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    visSpinner.setAdapter(adapter1);

 adapter2=ArrayAdapter.createFromResource(this, R.array.weapon_array,android.R.layout.simple_spinner_item);     
 adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    wpnSpinner.setAdapter(adapter2);

 adapter3=ArrayAdapter.createFromResource(this, R.array.details_array,android.R.layout.simple_spinner_item);     
 adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    noSpinner.setAdapter(adapter3);

 entries = new ArrayList<CharSequence>(Arrays.asList("Deliberate"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    pracSpinner.setAdapter(adapter4);

 adapter5=ArrayAdapter.createFromResource(this, R.array.typeOfFiring_array,android.R.layout.simple_spinner_item);     
 adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    typeSpinner.setAdapter(adapter5);

  entries = new ArrayList<CharSequence>(Arrays.asList("25"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    rangeSpinner.setAdapter(adapter6);

 adapter7=ArrayAdapter.createFromResource(this, R.array.riflegpposn_array,android.R.layout.simple_spinner_item);     
 adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    posnSpinner.setAdapter(adapter7);

 adapter8=ArrayAdapter.createFromResource(this, R.array.rank_array5,android.R.layout.simple_spinner_item);     
 adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    rankSpinner.setAdapter(adapter8);

 adapter9=ArrayAdapter.createFromResource(this, R.array.subunit_array,android.R.layout.simple_spinner_item);     
 adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    sunitSpinner.setAdapter(adapter9);
 
 entries = new ArrayList<CharSequence>(Arrays.asList("30cm GP tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    tgttypeSpinner.setAdapter(adapter10);

 homeButton=(Button) findViewById(R.id.detselhome_label);
 homeButton.setOnClickListener(this);

 okButton=(Button) findViewById(R.id.detselok_label);
 okButton.setOnClickListener(this);

 imButton=(Button) findViewById(R.id.detselipman_label);
 imButton.setOnClickListener(this);

 rcButton=(Button) findViewById(R.id.detselrgcourse_label);
 rcButton.setOnClickListener(this);

}


public void onItemSelected(AdapterView<?> parent, View v, int pos, long row)
{
	if(sFlag)
	{
 switch(((Spinner)parent).getId())
 {
  case R.id.detselwpn_label:
  case R.id.detseltype_label:
       wpn=wpnSpinner.getSelectedItem().toString().trim();
       type=typeSpinner.getSelectedItem().toString().trim();
       if(wpn.startsWith("RIF")||wpn.startsWith("AK"))
       {
        if(type.equals("GROUPING"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Deliberate"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("25"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.riflegpposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("30cm GP tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else if(type.equals("ZEROING"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Zeroing-Deliberate"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("33"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         entries = new ArrayList<CharSequence>(Arrays.asList("Lying Supported"));
         adapter7=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("120cm zeroing tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else if(type.equals("APLN"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Deliberate"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("100","200"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.rifleapplposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("120cm tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else
        {
         adapter4=ArrayAdapter.createFromResource(
            this, R.array.riflearcprac_array,android.R.layout.simple_spinner_item); 
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("50","100","200"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.riflearcposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("Fig 11","Fig 12"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }

       }
       
       else if(wpn.startsWith("PISTOL"))
       {
        if(type.equals("GROUPING"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Deliberate single shot"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("5","10"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         entries = new ArrayList<CharSequence>(Arrays.asList("Standing"));
         adapter7=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("120cm tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else if(type.equals("ZEROING"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Zeroing-Deliberate"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("33"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         entries = new ArrayList<CharSequence>(Arrays.asList("Lying Supported"));
         adapter7=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("120cm zeroing tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else if(type.equals("APLN"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Deliberate-aimed","Deliberate"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("10","20"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.pistolapplposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("120cm tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else
        {
         adapter4=ArrayAdapter.createFromResource(
            this, R.array.pistolarcprac_array,android.R.layout.simple_spinner_item); 
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("10","15","20","25"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.pistolapplposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("Fig 11","Two Fig 11"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }

       }

       else if(wpn.startsWith("LMG"))
       {
        if(type.equals("GROUPING"))
        {
         adapter4=ArrayAdapter.createFromResource(
            this, R.array.lmggpprac_array,android.R.layout.simple_spinner_item);     
adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("25"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.lmggpposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("30cm GP tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else if(type.equals("ZEROING"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Zeroing-Deliberate"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("33"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         entries = new ArrayList<CharSequence>(Arrays.asList("Lying LMG on bipod"));
         adapter7=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("120cm zeroing tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else if(type.equals("APLN"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Deliberate(Single Shot)"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("100","200","300"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.lmgapplposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("120cm tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else
        {
         adapter4=ArrayAdapter.createFromResource(
            this, R.array.lmgarcprac_array,android.R.layout.simple_spinner_item); 
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("100","200","300"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.lmggpposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("Fig 11"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }

       }

       else if(wpn.startsWith("CMG"))
       {
        if(type.equals("GROUPING"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Deliberate single shot"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("25"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.carbinegpposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("30cm GP tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else if(type.equals("ZEROING"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Zeroing-Deliberate"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         entries = new ArrayList<CharSequence>(Arrays.asList("33"));
         adapter6=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         entries = new ArrayList<CharSequence>(Arrays.asList("Lying Supported"));
         adapter7=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("30cm GP tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else if(type.equals("APLN"))
        {
         entries = new ArrayList<CharSequence>(Arrays.asList("Deliberate(Single Shot)"));
         adapter4=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         adapter6=ArrayAdapter.createFromResource(
            this, R.array.carbineapplrange_array,android.R.layout.simple_spinner_item);     
adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.carbineapplposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("120cm tgt"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }
        else
        {
         adapter4=ArrayAdapter.createFromResource(
            this, R.array.carbinearcprac_array,android.R.layout.simple_spinner_item); 
         adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         pracSpinner.setAdapter(adapter4);

         adapter6=ArrayAdapter.createFromResource(
            this, R.array.carbineapplrange_array,android.R.layout.simple_spinner_item);     
adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         rangeSpinner.setAdapter(adapter6);

         adapter7=ArrayAdapter.createFromResource(
            this, R.array.carbineapplposn_array,android.R.layout.simple_spinner_item);     
adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         posnSpinner.setAdapter(adapter7);

         entries = new ArrayList<CharSequence>(Arrays.asList("Fig 11"));
         adapter10=new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, entries);
         adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         tgttypeSpinner.setAdapter(adapter10);
        }

       }
       break;
 
 }
	}
}

public void onNothingSelected(AdapterView<?> parent)
{
	
}

public void ipManually()
{
	adapter4=ArrayAdapter.createFromResource(this, R.array.prac_array,android.R.layout.simple_spinner_item);     
    adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    pracSpinner.setAdapter(adapter4);
 
	adapter6=ArrayAdapter.createFromResource(this, R.array.range_array,android.R.layout.simple_spinner_item);     
    adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    rangeSpinner.setAdapter(adapter6);
    
	adapter7=ArrayAdapter.createFromResource(this, R.array.posn_array,android.R.layout.simple_spinner_item);     
    adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    posnSpinner.setAdapter(adapter7);
    
	adapter10=ArrayAdapter.createFromResource(this, R.array.tgttype_array,android.R.layout.simple_spinner_item);     
    adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    tgttypeSpinner.setAdapter(adapter10);
}

public void getSelectedItems()
{
  vis=visSpinner.getSelectedItem().toString().trim();
  wpn=wpnSpinner.getSelectedItem().toString().trim();
  no=Integer.parseInt(noSpinner.getSelectedItem().toString().trim());
  prac=pracSpinner.getSelectedItem().toString().trim();
  type=typeSpinner.getSelectedItem().toString().trim();
  range=Integer.parseInt(rangeSpinner.getSelectedItem().toString().trim());
  posn=posnSpinner.getSelectedItem().toString().trim();
  if(rankCheckBox.isChecked())
  {
   rank=rankSpinner.getSelectedItem().toString().trim();
   check=check|2;
  }
  else
   rank="";
  if(sunitCheckBox.isChecked())
  {
   sunit=sunitSpinner.getSelectedItem().toString().trim();
   check=check|4;
  }
  else
   sunit="";
  tgttype=tgttypeSpinner.getSelectedItem().toString().trim();

}

public void onClick(View v)
{
 Intent i;
 switch(v.getId())
 {
  case R.id.detselrankcbox_label:
	   if(rankCheckBox.isChecked())
		   rankSpinner.setClickable(true);
	   else
		   rankSpinner.setClickable(false);
	   break;
	  
  case R.id.detselsunitcbox_label:
	   if(sunitCheckBox.isChecked())
		   sunitSpinner.setClickable(true);
	   else
		   sunitSpinner.setClickable(false);
	   break;
	   
  case R.id.detseltgttypecbox_label:
	   if(tgttypeCheckBox.isChecked())
		   tgttypeSpinner.setClickable(true);
	   else
		   tgttypeSpinner.setClickable(false);
	   break;	   
	   
  case R.id.detselipman_label:
	   sFlag=false;
	   ipManually();
	   break;
	   
  case R.id.detselrgcourse_label:
	   sFlag=true;
	   break;
	   
  case R.id.detselok_label:
       getSelectedItems();
       if(writeInstanceState(this))
        Toast.makeText(getBaseContext(), "Stored to Shared Preferences.",Toast.LENGTH_SHORT).show();
        i=new Intent(this,FiringDetail.class);
       startActivity(i);
       break;

  case R.id.detselhome_label:
       i=new Intent(this,InputData.class);
       startActivity(i);
       break;
 }
 
}

public void callRead()
{
	readInstanceState(this);
	 Toast.makeText(getBaseContext(), "Retrieved from Shared Preferences.",Toast.LENGTH_SHORT).show();
}

public boolean writeInstanceState(Context c) {

        SharedPreferences p =
                c.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);


        SharedPreferences.Editor e = p.edit();

        
        e.putString("Vis", vis);
        e.putString("Wpn", wpn);
        e.putInt("No",no);
        e.putString("Prac", prac);
        e.putString("Type", type);
        e.putInt("Range", range);
        e.putString("Posn", posn);
        e.putString("Rank", rank);
        e.putString("Subunit", sunit);
        e.putString("TgtType", tgttype);
        e.putInt("Check", check);
        e.putInt("DetailFlag", 0);

        return (e.commit());

    }

public void readInstanceState(Context c) {

   SharedPreferences p = c.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);
        
        vis = p.getString("Vis", "");
        wpn = p.getString("Wpn", "");
        no = p.getInt("No", 5);
        prac = p.getString("Prac", "");
        type = p.getString("Type", "");
        range = p.getInt("Range", 5);
        posn = p.getString("Posn", "");
        rank = p.getString("Rank", "");
        sunit = p.getString("Subunit", "");
        tgttype = p.getString("TgtType", "");
        check = p.getInt("Check", 1);
 }


}
