package org.example.mini;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SelUpdate extends Activity implements OnClickListener{
    
	InputManually im;
	CheckBox noCheckBox,rankCheckBox,nameCheckBox,tradeCheckBox,sunitCheckBox,dobCheckBox,doeCheckBox,wpnCheckBox,
	         regCheckBox,buttCheckBox;
	EditText enoEditText,enameEditText,eregEditText,ebuttEditText;
	Button gen,unoOKButton;
	Cursor cursor;
	static final int DATE_DIALOG_ID=100,NO_DIALOG_ID=101,EMPTY_DIALOG_ID=102,CONFIRM_DIALOG_ID=103;
	private int year,month,day;
	ArmyDB objDB;
	TextView tv1,tv2;
	int nof=0,namef=0,regf=0,buttf=0;
	String oldNO;
	boolean boolup,confirm;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sel_update);
        
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        
        
       im=new InputManually();
       
       noCheckBox=(CheckBox)findViewById(R.id.uno_label);
       noCheckBox.setOnClickListener(this);
       rankCheckBox=(CheckBox)findViewById(R.id.urank_label);
       rankCheckBox.setOnClickListener(this);
       nameCheckBox=(CheckBox)findViewById(R.id.uname_label);
       nameCheckBox.setOnClickListener(this);
       tradeCheckBox=(CheckBox)findViewById(R.id.utrade_label);
       tradeCheckBox.setOnClickListener(this);
       sunitCheckBox=(CheckBox)findViewById(R.id.usubunit_label);
       sunitCheckBox.setOnClickListener(this);
       dobCheckBox=(CheckBox)findViewById(R.id.udob_label);
       dobCheckBox.setOnClickListener(this);
       doeCheckBox=(CheckBox)findViewById(R.id.udoe_label);
       doeCheckBox.setOnClickListener(this);
       wpnCheckBox=(CheckBox)findViewById(R.id.uwpn_label);
       wpnCheckBox.setOnClickListener(this);
       regCheckBox=(CheckBox)findViewById(R.id.ureg_label);
       regCheckBox.setOnClickListener(this);
       buttCheckBox=(CheckBox)findViewById(R.id.ubutt_label);
       buttCheckBox.setOnClickListener(this);
       
      tv1= (TextView) findViewById(R.id.notext_label);
      tv2= (TextView) findViewById(R.id.oldno_label);
      im.noEditText=(EditText)findViewById(R.id.unotextbox_label);
      im.noEditText.setFocusable(false);
      im.nameEditText=(EditText)findViewById(R.id.unametextbox_label);
      im.nameEditText.setFocusable(false);
      im.regEditText=(EditText)findViewById(R.id.uregtextbox_label);
      im.regEditText.setFocusable(false);
      im.buttEditText=(EditText)findViewById(R.id.ubutttextbox_label);
      im.buttEditText.setFocusable(false);
      
      enoEditText=(EditText)findViewById(R.id.eunotextbox_label);
      enameEditText=(EditText)findViewById(R.id.eunametextbox_label);   
      eregEditText=(EditText)findViewById(R.id.euregtextbox_label);
      ebuttEditText=(EditText)findViewById(R.id.eubutttextbox_label);
      
      
      im.dobButton=(Button)findViewById(R.id.udobbutton_label);
      im.dobButton.setOnClickListener(this);
      im.dobButton.setClickable(false);
      im.doeButton=(Button)findViewById(R.id.udoebutton_label);
      im.doeButton.setOnClickListener(this);
      im.doeButton.setClickable(false);
      im.okButton=(Button)findViewById(R.id.uok_label);
      im.okButton.setOnClickListener(this);
      im.backButton=(Button)findViewById(R.id.uback_label);
      im.backButton.setOnClickListener(this);
      im.infoButton=(Button)findViewById(R.id.uinfo_label);
      im.infoButton.setOnClickListener(this);
      unoOKButton=(Button)findViewById(R.id.unook_label);
      unoOKButton.setOnClickListener(this);
     
      im.rankSpinner= (Spinner) findViewById(R.id.urankspinner_label);
      im.rankSpinner.setClickable(false);
  	  im.adapter1=null;
      
  	  im.wpnSpinner= (Spinner) findViewById(R.id.uwpnspinner_label);
  	  im.wpnSpinner.setClickable(false);
  	  im.adapter2=null;
  	  
  	  im.tradeSpinner= (Spinner) findViewById(R.id.utradespinner_label);
  	  im.tradeSpinner.setClickable(false);
  	  im.adapter3=null;
  	  
  	  im.sunitSpinner= (Spinner) findViewById(R.id.usunitspinner_label);
  	  im.sunitSpinner.setClickable(false);
  	  im.adapter4=null;
  	  
  	  objDB=new ArmyDB(this);
	}
	
	public void updateDate()
	  {
		  gen.setText(new StringBuilder().append(day).append("-").append(month+1).append("-").append(year));
	  }
	  private DatePickerDialog.OnDateSetListener dDatePickerDialog=new DatePickerDialog.OnDateSetListener(){
		   public void onDateSet(DatePicker dp,int y,int m,int d)
		   {
			   year=y;
			   month=m;
			   day=d;
			   updateDate();
		   }
	  };
	                           
	  protected Dialog onCreateDialog(int id)
	  {
		  AlertDialog alert;
		  AlertDialog.Builder builder;
		  switch(id)
		  {
		   case DATE_DIALOG_ID: return new DatePickerDialog(this,dDatePickerDialog,year,month,day);
		   case NO_DIALOG_ID:
			   builder=new AlertDialog.Builder(this);
			   builder.setMessage("Invalid Army Number.Enter a valid one.")
			          .setCancelable(false)
			          .setNeutralButton("OK",new DialogInterface.OnClickListener() {
			        	  @Override
						public void onClick(DialogInterface dialog, int which) {
							
							dialog.cancel();
						}
					});
			   alert=builder.create();
			   return alert;
		   case EMPTY_DIALOG_ID:
			   builder=new AlertDialog.Builder(this);
			   builder.setMessage("All fields are to be filled mandatorily.")
			          .setCancelable(false)
			          .setNeutralButton("OK",new DialogInterface.OnClickListener() {
			        	  @Override
						public void onClick(DialogInterface dialog, int which) {
							
							dialog.cancel();
						}
					});
			   alert=builder.create();
			   return alert;
		   
		   case CONFIRM_DIALOG_ID:
			   builder=new AlertDialog.Builder(this);
			   builder.setMessage("Are you sure you want to update the details in the database?")
			          .setCancelable(false)
			          .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			        	  @Override
						public void onClick(DialogInterface dialog, int which) {
							
			        		confirm=true; 
			        		update();
							dialog.cancel();
						}
					})
					  .setNegativeButton("No", new DialogInterface.OnClickListener() {
			        	  @Override
							public void onClick(DialogInterface dialog, int which) {
								
			        		confirm=false;  
							dialog.cancel();
						}
					});
			   alert=builder.create();
			   return alert;
		   	
			
		  }
		  return null;
	  }
		
	public void detailsOfId()
	{
	 	int i;
	 	String s;
		cursor.moveToFirst();
		im.no=enoEditText.getText().toString();
		enoEditText.setVisibility(View.GONE);
		im.noEditText.setVisibility(View.VISIBLE);
		im.noEditText.setText(im.no);
		tv1.setVisibility(View.GONE);
		noCheckBox.setVisibility(View.VISIBLE);
		
		for(i=0;i<im.adapter1.getCount();i++)
		{
			if(im.rankSpinner.getItemAtPosition(i).toString().equals(cursor.getString(cursor.getColumnIndex("Rank"))))
			{
				break;
			}
		}
		im.rankSpinner.setSelection(i, true);
		im.nameEditText.setText(cursor.getString(cursor.getColumnIndex("Name")));
		  
		if(!(im.no.startsWith("IC")||im.no.startsWith("SS")||im.no.startsWith("RC")||im.no.startsWith("SL")||im.no.startsWith("SC")))
		{
		   for(i=0;i<im.adapter3.getCount();i++)
		   {
			if(im.tradeSpinner.getItemAtPosition(i).toString().equals(cursor.getString(cursor.getColumnIndex("Trade"))))
			{
				break;
			}
		   }
		}
		im.tradeSpinner.setSelection(i, true);
		for(i=0;i<im.adapter4.getCount();i++)
		{
			if(im.sunitSpinner.getItemAtPosition(i).toString().equals(cursor.getString(cursor.getColumnIndex("Subunit"))))
			{
				break;
			}
		}
		im.sunitSpinner.setSelection(i, true);
		im.dobButton.setClickable(true);
		s=cursor.getString(cursor.getColumnIndex("DOB"));
		String idob[]=s.split("-");
		String m=im.months[Integer.parseInt(idob[1])-1];
        im.dobButton.setText(idob[2]+"-"+m+"-"+idob[0]);
        im.dobButton.setClickable(false);
        im.doeButton.setClickable(true);
        s=cursor.getString(cursor.getColumnIndex("DOE"));
		String idoe[]=s.split("-");
	    m=im.months[Integer.parseInt(idoe[1])-1];
        
        im.doeButton.setText(idoe[2]+"-"+m+"-"+idoe[0]);
        im.doeButton.setClickable(false);
        for(i=0;i<im.adapter2.getCount();i++)
		{
			if(im.wpnSpinner.getItemAtPosition(i).toString().equals(cursor.getString(cursor.getColumnIndex("WPN"))))
			{
				break;
			}
		}
		im.wpnSpinner.setSelection(i, true);
		im.regEditText.setText(cursor.getString(cursor.getColumnIndex("Regno")));
		im.buttEditText.setText(cursor.getString(cursor.getColumnIndex("Buttno")));
		
		
	}
	
	public int checkEmpty()
	{
		int f=1;
		String s1,s2;
		if(nof==0)
		 im.no=im.noEditText.getText().toString();
		else
		 im.no=enoEditText.getText().toString();
		
		if(namef==0)
		 im.name=im.nameEditText.getText().toString();
		else
		 im.name=enameEditText.getText().toString();
		
		if(regf==0)
	     s1=im.regEditText.getText().toString();
		else
		 s1=eregEditText.getText().toString();
		
		if(buttf==0)
		  s2=im.buttEditText.getText().toString();
		else
		  s2=ebuttEditText.getText().toString();
			
		  if(im.no.equals("")||im.name.equals("")|| s1.equals("")||s2.equals("")||
				  im.dobButton.getText().toString().equals("")||im.doeButton.getText().toString().equals(""))
		  {
			  f=0;
			  showDialog(EMPTY_DIALOG_ID);
		  }
		  return f;
	}
	
	public int validate()
	  {
		  int f=1;
		  String tmp;
		  if(nof==0)
				 tmp=im.noEditText.getText().toString();
		  else
				 tmp=enoEditText.getText().toString();
				
		  tmp=tmp.trim();
		  if(!tmp.matches("((IC|SS|RC|SL|SC)( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|(JC( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|" +
		  		"((\\d\\d\\d\\d\\d\\d\\d\\d)( )?[A-Z])"))
		  {//code to show error
			  f=0;
			  Toast.makeText(getBaseContext(), "Error in No.", Toast.LENGTH_LONG).show();
		  }
		  
		  if(buttf==0)
			  tmp=im.buttEditText.getText().toString();
			else
			  tmp=ebuttEditText.getText().toString();
			
		  tmp=tmp.trim();
		
		  if(!tmp.matches("[0-9]+"))
		  {//code to show error
			  f=0;
			  Toast.makeText(getBaseContext(), "Error in Butt No.", Toast.LENGTH_LONG).show();
		  }
		  if(regf==0)
			     tmp=im.regEditText.getText().toString();
		  else
				 tmp=eregEditText.getText().toString();
				
		  tmp=tmp.trim();

		  if(!tmp.matches("[0-9A-Z]+"))
		  {//code to show error
			  f=0;
			  Toast.makeText(getBaseContext(), "Error in Reg No.", Toast.LENGTH_LONG).show();
		  }
		  return f;
	  }
	
	public void refresh()
	{
	    noCheckBox.setChecked(false);	
	    nameCheckBox.setChecked(false);
	    rankCheckBox.setChecked(false);
	    tradeCheckBox.setChecked(false);
	    sunitCheckBox.setChecked(false);
	    dobCheckBox.setChecked(false);
	    doeCheckBox.setChecked(false);
	    wpnCheckBox.setChecked(false);
	    regCheckBox.setChecked(false);
	    buttCheckBox.setChecked(false);
	    tv2.setVisibility(View.GONE);
		noCheckBox.setVisibility(View.GONE);
		tv1.setVisibility(View.VISIBLE);
		im.noEditText.setVisibility(View.GONE);
		enoEditText.setVisibility(View.VISIBLE);
		enoEditText.setText("");
		im.adapter1 = ArrayAdapter.createFromResource(this,R.array.rank_no_item,android.R.layout.simple_spinner_item);
        im.rankSpinner.setAdapter(im.adapter1);
        enameEditText.setVisibility(View.GONE);
        im.nameEditText.setVisibility(View.VISIBLE);
        im.nameEditText.setText("");
        im.tradeSpinner.setAdapter(im.adapter1);
        im.sunitSpinner.setAdapter(im.adapter1);
        im.dobButton.setText("");
        im.doeButton.setText("");
        im.wpnSpinner.setAdapter(im.adapter1);
        eregEditText.setVisibility(View.GONE);
        im.regEditText.setVisibility(View.VISIBLE);
        im.regEditText.setText("");
        ebuttEditText.setVisibility(View.GONE);
        im.buttEditText.setVisibility(View.VISIBLE);
        im.buttEditText.setText("");
        
		
	}
	  
	public void update()
	{
		String m,d;
		if(nof==0)
			 im.no=im.noEditText.getText().toString().trim();
			else
			 im.no=enoEditText.getText().toString().trim();
	
		im.rank=im.rankSpinner.getSelectedItem().toString().trim();
		
		if(namef==0)
			 im.name=im.nameEditText.getText().toString().trim();
			else
			 im.name=enameEditText.getText().toString().trim();
		
		if(im.no.startsWith("IC")||im.no.startsWith("SS")||im.no.startsWith("RC")||im.no.startsWith("SL")||im.no.startsWith("SC"))
			  im.trade="";
		else
		    im.trade=im.tradeSpinner.getSelectedItem().toString().trim();
		
		im.subunit=im.sunitSpinner.getSelectedItem().toString().trim();
	    im.dob=im.dobButton.getText().toString().trim();
	    String idob[]=im.dob.split("-");
		  int i;
		  for(i=0;i<im.months.length;i++)
		  {
			  if(im.months[i].equals(idob[1]))
				  break;
		  }
		  if((i+1)<=9)
		    m="0"+(i+1);
		  else
			  m=Integer.toString(i+1);
		  
		  if(Integer.parseInt(idob[0])<=9)
			  d="0"+idob[0];
		  else
			  d=idob[0];
		  im.dob=idob[2]+"-"+m+"-"+d;
	    
	    im.doe=im.doeButton.getText().toString().trim();
	    String idoe[]=im.doe.split("-");
		  
		  for(i=0;i<im.months.length;i++)
		  {
			  if(im.months[i].equals(idoe[1]))
				  break;
		  }
		  if((i+1)<=9)
		    m="0"+(i+1);
		  else
			  m=Integer.toString(i+1);
		  
		  if(Integer.parseInt(idoe[0])<=9)
			  d="0"+idoe[0];
		  else
			  d=idoe[0];
		  im.doe=idoe[2]+"-"+m+"-"+d;
		  
	    
	    im.wpn=im.wpnSpinner.getSelectedItem().toString().trim();
	    
	    if(regf==0)
		  im.regno=Integer.parseInt(im.regEditText.getText().toString().trim());
	    else
	    	im.regno=Integer.parseInt(eregEditText.getText().toString().trim());
	    if(buttf==0)
		  im.buttno=Integer.parseInt(im.buttEditText.getText().toString().trim());
	    else
	    	im.buttno=Integer.parseInt(ebuttEditText.getText().toString().trim());
		  objDB.open();
		  boolup=objDB.update(oldNO, im.no, im.rank, im.name, im.trade, im.subunit, im.dob, im.doe, im.wpn, im.regno, im.buttno);
		  objDB.close();  
		  if(!boolup)
		  {
			  Toast.makeText(getBaseContext(), "Updation unsuccessful.", Toast.LENGTH_LONG).show();
		  }
		  else
		  {
			  refresh();
			  Toast.makeText(getBaseContext(), "Details successfully updated into the database.", Toast.LENGTH_LONG).show();
		  }
		  
	}
	  
	public void onClick(View v)
	{
		Intent i;
		int chknull=1,val=1;
		switch(v.getId())
		{
		 case R.id.uno_label:
			  if(noCheckBox.isChecked())
			  {
				  nof=1;
				  Toast.makeText(getBaseContext(), "No checked", Toast.LENGTH_SHORT).show();
				  im.no=im.noEditText.getText().toString();
				  im.noEditText.setVisibility(View.GONE);
				  enoEditText.setVisibility(View.VISIBLE);
				  enoEditText.setText(im.no);
				  tv2.setText("The old Army No is "+im.no);
			  }
			  else
			  {
				  nof=0;
				  im.no=enoEditText.getText().toString();
				  enoEditText.setVisibility(View.GONE);
				  im.noEditText.setVisibility(View.VISIBLE);
				  im.noEditText.setText(im.no);
				  tv2.setText("");
			  }
			  break;
		 case R.id.urank_label:
			  if(rankCheckBox.isChecked())
			  {
				  Toast.makeText(getBaseContext(), "Rank checked", Toast.LENGTH_SHORT).show();
				  im.rankSpinner.setClickable(true);
			  }
			  else
			  {
				  im.rankSpinner.setClickable(false);
			  }
			  break;
		 case R.id.uname_label:
			  if(nameCheckBox.isChecked())
			  {
				  namef=1;
				  Toast.makeText(getBaseContext(), "Name checked", Toast.LENGTH_SHORT).show();
				  im.name=im.nameEditText.getText().toString();
				  im.nameEditText.setVisibility(View.GONE);
				  enameEditText.setVisibility(View.VISIBLE);
				  enameEditText.setText(im.name);
			  }
			  else
			  {
				  namef=0;
				  im.name=enameEditText.getText().toString();
				  enameEditText.setVisibility(View.GONE);
				  im.nameEditText.setVisibility(View.VISIBLE);
				  im.nameEditText.setText(im.name);
				  
			  }
			  break;
		 case R.id.utrade_label:
			  if(tradeCheckBox.isChecked())
			  {
				  Toast.makeText(getBaseContext(), "Trade checked", Toast.LENGTH_SHORT).show();
				  im.tradeSpinner.setClickable(true);
			  }
			  else
			  {
				  im.tradeSpinner.setClickable(false);
			  }
			  break;
		 case R.id.usubunit_label:
			  if(sunitCheckBox.isChecked())
			  {
				  Toast.makeText(getBaseContext(), "Subunit checked", Toast.LENGTH_SHORT).show();
				  im.sunitSpinner.setClickable(true);
			  }
			  else
			  {
				  im.sunitSpinner.setClickable(false);
			  }
			  break;
	
		 case R.id.udob_label:
			  if(dobCheckBox.isChecked())
			  {
				  Toast.makeText(getBaseContext(), "DOB checked", Toast.LENGTH_SHORT).show();
				  im.dobButton.setClickable(true);
			  }
			  else
			  {
				  im.dobButton.setClickable(false);
			  }
			  break;
		 case R.id.udoe_label:
			  if(doeCheckBox.isChecked())
			  {
				  Toast.makeText(getBaseContext(), "DOE checked", Toast.LENGTH_SHORT).show();
				  im.doeButton.setClickable(true);
			  }
			  else
			  {
				  im.doeButton.setClickable(false);
			  }
			  break;
	     
		 case R.id.uwpn_label:	  
			 if(wpnCheckBox.isChecked())
			  {
				  Toast.makeText(getBaseContext(), "Weapon checked", Toast.LENGTH_SHORT).show();
				  im.wpnSpinner.setClickable(true);
			  }
			  else
			  {
				  im.wpnSpinner.setClickable(false);
			  }
			  break;
		 case R.id.ureg_label:
			  if(regCheckBox.isChecked())
			  {
				  regf=1;
				  Toast.makeText(getBaseContext(), "Register No checked", Toast.LENGTH_SHORT).show();
				  im.regno=Integer.parseInt(im.regEditText.getText().toString());
				  im.regEditText.setVisibility(View.GONE);
				  eregEditText.setVisibility(View.VISIBLE);
				  eregEditText.setText(im.regno);
			  }
			  else
			  {
				  regf=0;
				  im.regno=Integer.parseInt(eregEditText.getText().toString());
				  eregEditText.setVisibility(View.GONE);
				  im.regEditText.setVisibility(View.VISIBLE);
				  im.regEditText.setText(im.regno);
			  }
			  break;
		 case R.id.ubutt_label:
			  if(buttCheckBox.isChecked())
			  {
				  buttf=1;
				  Toast.makeText(getBaseContext(), "Butt No checked", Toast.LENGTH_SHORT).show();
				  im.buttno=Integer.parseInt(im.buttEditText.getText().toString());
				  im.buttEditText.setVisibility(View.GONE);
				  ebuttEditText.setVisibility(View.VISIBLE);
				  ebuttEditText.setText(im.buttno);
			  }
			  else
			  {
				  buttf=0;
				  im.buttno=Integer.parseInt(ebuttEditText.getText().toString());
				  ebuttEditText.setVisibility(View.GONE);
				  im.buttEditText.setVisibility(View.VISIBLE);
				  im.buttEditText.setText(im.buttno);
			  }
			  break;
		 case R.id.udobbutton_label:
			  gen=im.dobButton;
			  showDialog(DATE_DIALOG_ID);	  
		      break;
		 case R.id.udoebutton_label:
			  gen=im.doeButton;
			  showDialog(DATE_DIALOG_ID);
		      break;
		 case R.id.unook_label:
			  int f=1;
			  String s;
			  s=enoEditText.getText().toString();
			  s=s.trim();
			  if(!s.matches("((IC|SS|RC|SL|SC)( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|(JC( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|" +
		  		"((\\d\\d\\d\\d\\d\\d\\d\\d)( )?[A-Z])"))
			  {//code to show error
				  f=0;
				  Toast.makeText(getBaseContext(), "Error in No.", Toast.LENGTH_LONG).show();
			  }
			  if(f==1)
			  {
				objDB.open();
				cursor= objDB.getOneSoldierDetail(s);
				objDB.close();
				oldNO=s;
			//	cursor.moveToFirst();
				if(cursor.getString(0).equals("0"))
				{
					Toast.makeText(getBaseContext(), "Entered Army No doesnt exist in the database.", Toast.LENGTH_LONG).show();
				}
				else
				{
					im.rankSpinner.setClickable(true);
					im.tradeSpinner.setClickable(true);
					im.sunitSpinner.setClickable(true);
					im.wpnSpinner.setClickable(true);
					
					im.adapter2 = ArrayAdapter.createFromResource(this,R.array.weapon_array,android.R.layout.simple_spinner_item);
				  	im.adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				    im.wpnSpinner.setAdapter(im.adapter2);
				  	  
				    im.adapter4 = ArrayAdapter.createFromResource(this,R.array.subunit_array,android.R.layout.simple_spinner_item);
				    im.adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				  	im.sunitSpinner.setAdapter(im.adapter4);
				       
				  	  if(s.matches("((IC|SS|RC|SL|SC)( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|(JC( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|" +
		  		"((\\d\\d\\d\\d\\d\\d\\d\\d)( )?[A-Z])"))
					  {
					   if(s.startsWith("JC"))
					   {
						  im.adapter1 = ArrayAdapter.createFromResource(this,R.array.rank_array2,android.R.layout.simple_spinner_item);
						  im.adapter3 = ArrayAdapter.createFromResource(this,R.array.trade_array,android.R.layout.simple_spinner_item);
							 		  
					   }
					   else if(s.startsWith("IC")||s.startsWith("SS")||s.startsWith("RC")||s.startsWith("SL")||s.startsWith("SC"))
					   {
						  im.adapter1 = ArrayAdapter.createFromResource(this,R.array.rank_array1,android.R.layout.simple_spinner_item); 
					   }
					   else if(s.charAt(0)>='0'&&s.charAt(0)<='9')
						  {
							  im.adapter1 = ArrayAdapter.createFromResource(this,R.array.rank_array4,android.R.layout.simple_spinner_item);
							  im.adapter3 = ArrayAdapter.createFromResource(this,R.array.trade_array,android.R.layout.simple_spinner_item);
						  }
					   else
					   {
						  showDialog(NO_DIALOG_ID);
				   	   }
						
					  }
					  
					  else
					  {
						  showDialog(NO_DIALOG_ID);
					  }
					  if(im.adapter1!=null)
					  {
					   im.adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					   im.rankSpinner.setAdapter(im.adapter1);
					  }
					  if(im.adapter3!=null)
					  {
					   im.adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					   im.tradeSpinner.setAdapter(im.adapter3);
					  }
					
				  	
					detailsOfId();
					//cursor.close();
					
					im.rankSpinner.setClickable(false);
					im.tradeSpinner.setClickable(false);
					im.sunitSpinner.setClickable(false);
					im.wpnSpinner.setClickable(false);
					
				}
			  }
			  break;
		 case R.id.uok_label:
			 chknull=checkEmpty();
			    if(chknull==1)
			    {
			    	val=validate();
			    }
			    if(chknull==1&&val==1)
			    {
			    	showDialog(CONFIRM_DIALOG_ID);
			    		
			    }
			    break;
		 case R.id.uback_label:
			  Toast.makeText(getBaseContext(), "Going back to Input Data page.", Toast.LENGTH_SHORT).show();
			    i=new Intent(this,Input.class);
	        	startActivity(i);
	       	    break;
		}
	}
}
