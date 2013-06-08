package org.example.mini;

import java.util.Calendar;
import java.util.regex.Pattern;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnClickListener;
import android.widget.*;


public class InputManually extends Activity implements OnFocusChangeListener, OnClickListener {
	 TextView tv;
	 EditText noEditText,nameEditText,regEditText,buttEditText;
	 Button gen,dobButton,doeButton,viewButton,okButton,backButton,infoButton;
	 Spinner rankSpinner,tradeSpinner,sunitSpinner,wpnSpinner;
	 ArrayAdapter<CharSequence> adapter1=null,adapter2,adapter3=null,adapter4;
	 static final int DATE_DIALOG_ID=100,NO_DIALOG_ID=101,EMPTY_DIALOG_ID=102,KEY_DIALOG_ID=103,CONFIRM_DIALOG_ID=104;
	 private int year,month,day;
	 String no,rank,name,trade,subunit,dob,doe,wpn;
	 int regno,buttno;
	 private ArmyDB objDB;
	 long ins;
	 int counter=0,dbclose=1,confirm;
	 String months[]={"Jan","Feb","Mar","Apr","June","July","Aug","Sep","Oct","Nov","Dec"}; 
	 Pattern p;
	 
	 
  public void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.input_manually);
	  
	  final Calendar c = Calendar.getInstance();
      year = c.get(Calendar.YEAR);
      month = c.get(Calendar.MONTH);
      day = c.get(Calendar.DAY_OF_MONTH);
      
      no=new String();
      rank=new String();
      name=new String();
      trade=new String();
      subunit=new String();
      dob=new String();
      doe=new String();
      wpn=new String();
	  
      tv = (TextView) findViewById(R.id.counter_label);
      noEditText = (EditText) findViewById(R.id.notextbox_label);
	  noEditText.setOnFocusChangeListener(this);
	  nameEditText= (EditText) findViewById(R.id.nametextbox_label);
	  regEditText= (EditText) findViewById(R.id.regtextbox_label);
	  buttEditText= (EditText) findViewById(R.id.butttextbox_label);
	  
	  dobButton = (Button) findViewById(R.id.dobbutton_label);
	  dobButton.setOnClickListener(this);
	  dobButton.setText(day+"-"+months[month]+"-"+year);
	  doeButton = (Button) findViewById(R.id.doebutton_label);
	  doeButton.setOnClickListener(this);
	  doeButton.setText(day+"-"+months[month]+"-"+year);
	  viewButton = (Button) findViewById(R.id.viewlist_label);
	  viewButton.setOnClickListener(this);
	  okButton = (Button) findViewById(R.id.ok_label);
	  okButton.setOnClickListener(this);
	  backButton = (Button) findViewById(R.id.back_label);
	  backButton.setOnClickListener(this);
	  infoButton = (Button) findViewById(R.id.info_label);
	  infoButton.setOnClickListener(this);
	  
	  rankSpinner= (Spinner) findViewById(R.id.rankspinner_label);
	  
	  tradeSpinner= (Spinner) findViewById(R.id.tradespinner_label);
	  
	  
	  wpnSpinner= (Spinner) findViewById(R.id.wpnspinner_label);
	  adapter2 = ArrayAdapter.createFromResource(this,R.array.weapon_array,android.R.layout.simple_spinner_item);
	  adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  wpnSpinner.setAdapter(adapter2);
	 
	  
	  sunitSpinner= (Spinner) findViewById(R.id.sunitspinner_label);
	  adapter4 = ArrayAdapter.createFromResource(this,R.array.subunit_array,android.R.layout.simple_spinner_item);
	  adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  sunitSpinner.setAdapter(adapter4);
	  
	  p=Pattern.compile("-");
	  objDB=new ArmyDB(this);
	  
	  
  }
  public void onFocusChange(View v,boolean b)
  {
	  switch(v.getId())
	  {
	  case R.id.notextbox_label: 
	  if(!b)
	  {
		  adapter1=adapter3=null;
		  	  
		  SpannableStringBuilder ssb=(SpannableStringBuilder)noEditText.getText();
		  String s=ssb.toString();
		  s=s.trim();
		  if(s.matches("((IC|SS|RC|SL|SC)( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|(JC( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|" +
		  		"((\\d\\d\\d\\d\\d\\d\\d\\d)( )?[A-Z])"))
		  {
		   if(s.startsWith("JC"))
		   {
			  adapter1 = ArrayAdapter.createFromResource(this,R.array.rank_array2,android.R.layout.simple_spinner_item);
			  adapter3 = ArrayAdapter.createFromResource(this,R.array.trade_array,android.R.layout.simple_spinner_item);
				 		  
		   }
		   else if(s.startsWith("IC")||s.startsWith("SS")||s.startsWith("RC")||s.startsWith("SL")||s.startsWith("SC"))
		   {
			  adapter1 = ArrayAdapter.createFromResource(this,R.array.rank_array1,android.R.layout.simple_spinner_item); 
		   }
		   else if(s.charAt(0)>='0'&&s.charAt(0)<='9')
			  {
				  adapter1 = ArrayAdapter.createFromResource(this,R.array.rank_array3,android.R.layout.simple_spinner_item);
				  adapter3 = ArrayAdapter.createFromResource(this,R.array.trade_array,android.R.layout.simple_spinner_item);
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
		  if(adapter1!=null)
		  {
		   adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		   rankSpinner.setAdapter(adapter1);
		  }
		  if(adapter3!=null)
		  {
		   adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		   tradeSpinner.setAdapter(adapter3);
		  }
			  
		  
		  
	  }
	  break;
	 }
  }
  public void updateDate()
  {
	  gen.setText(new StringBuilder().append(day).append("-").append(months[month]).append("-").append(year));
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
		   builder.setMessage("Are you sure you want to insert the details into the database?")
		          .setCancelable(false)
		          .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
		        	  @Override
					public void onClick(DialogInterface dialog, int which) {
						
		        		confirm=1; 
		        		insert();
						dialog.cancel();
					}
				})
				  .setNegativeButton("No", new DialogInterface.OnClickListener() {
		        	  @Override
						public void onClick(DialogInterface dialog, int which) {
							
		        		confirm=0;  
						dialog.cancel();
					}
				});
		   alert=builder.create();
		   return alert;
	   	   
		   
	   case KEY_DIALOG_ID:
		   builder=new AlertDialog.Builder(this);
		   builder.setMessage("Army Number and Register Number must be unique. Entered number already exists. Enter a new one.")
		          .setCancelable(false)
		          .setNeutralButton("OK",new DialogInterface.OnClickListener() {
		        	  @Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
					}
				});
		   alert=builder.create();
		   return alert;
	   
	   
	  }
	  return null;
  }
  public int checkEmpty()
  {
	  int f=1;
	  if(noEditText.getText().toString().equals("")||nameEditText.getText().toString().equals("")||
			  regEditText.getText().toString().equals("")||buttEditText.getText().toString().equals("")||
			  dobButton.getText().toString().equals("")||doeButton.getText().toString().equals(""))
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
	  tmp=noEditText.getText().toString();
	  tmp=tmp.trim();
	  if(!tmp.matches("((IC|SS|RC|SL|SC)( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|(JC( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|" +
		  		"((\\d\\d\\d\\d\\d\\d\\d\\d)( )?[A-Z])"))
	  {//code to show error
		  f=0;
		  Toast.makeText(getBaseContext(), "Error in No.", Toast.LENGTH_LONG).show();
	  }
	  
	  tmp=buttEditText.getText().toString();
	  tmp=tmp.trim();
	  buttEditText.setText(tmp);
	  if(!tmp.matches("[0-9]+"))
	  {//code to show error
		  f=0;
		  Toast.makeText(getBaseContext(), "Error in Butt No.", Toast.LENGTH_LONG).show();
	  }
	  tmp=regEditText.getText().toString();
	  tmp=tmp.trim();
	  regEditText.setText(tmp);
	  if(!tmp.matches("[0-9A-Z]+"))
	  {//code to show error
		  f=0;
		  Toast.makeText(getBaseContext(), "Error in Reg No.", Toast.LENGTH_LONG).show();
	  }
	  return f;
  }
  
  public void refresh()
  {
	  adapter1 = ArrayAdapter.createFromResource(this,R.array.rank_no_item,android.R.layout.simple_spinner_item);
	  noEditText.setText("");
	  rankSpinner.setAdapter(adapter1);
	  nameEditText.setText("");
	  tradeSpinner.setAdapter(adapter1);
	  sunitSpinner.setAdapter(adapter4);
	  dobButton.setText(day+"-"+months[month]+"-"+year);
	  doeButton.setText(day+"-"+months[month]+"-"+year);
	  wpnSpinner.setAdapter(adapter2);
	  regEditText.setText("");
	  buttEditText.setText("");
	  counter++;
	  tv.setText(Integer.toString(counter));
  }
  
  public void insert()
  {
	  int i;  
	  String m,d;
	  no=noEditText.getText().toString().trim();
	  rank=rankSpinner.getSelectedItem().toString().trim();
	  name=nameEditText.getText().toString().trim();
	  if(no.startsWith("IC")||no.startsWith("SS")||no.startsWith("RC")||no.startsWith("SL")||no.startsWith("SC"))
		  trade="";
	  else
	      trade=tradeSpinner.getSelectedItem().toString().trim();
	  
	  subunit=sunitSpinner.getSelectedItem().toString().trim();
	  dob=dobButton.getText().toString().trim();
	  String idob[]=p.split(dob);
	  
	  for(i=0;i<months.length;i++)
	  {
		  if(months[i].equals(idob[1]))
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
	  dob=idob[2]+"-"+m+"-"+d;
	  doe=doeButton.getText().toString().trim();
      String idoe[]=p.split(doe);
	  
	  for(i=0;i<months.length;i++)
	  {
		  if(months[i].equals(idoe[1]))
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
	  doe=idoe[2]+"-"+m+"-"+d;
	  
	  wpn=wpnSpinner.getSelectedItem().toString().trim();
	  regno=Integer.parseInt(regEditText.getText().toString().trim());
	  buttno=Integer.parseInt(buttEditText.getText().toString().trim());
	  objDB.open();
	  dbclose=0;
	  ins=objDB.insertDetails(no,rank,name,trade,subunit,dob,doe,wpn,regno,buttno);
	  objDB.close(); 
	  dbclose=1;
	  if(ins==-1) 
	  {
		  showDialog(KEY_DIALOG_ID);
	  }
	  else
	  {
		  refresh();
		  Toast.makeText(getBaseContext(), "Details successfully entered into the database.", Toast.LENGTH_LONG).show();
	  }
	  
  }
  
  public void onClick(View v)
  {
	  Intent i;
	  int chknull=1,val=1;
	  switch(v.getId())
	  {
	  
	   case R.id.dobbutton_label:
		    gen=dobButton;
		    showDialog(DATE_DIALOG_ID);	  
		    break;
	   case R.id.doebutton_label:
		    gen=doeButton;
		    showDialog(DATE_DIALOG_ID);
		    break;
	   case R.id.ok_label:
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
	   case R.id.back_label:
		    if(dbclose!=1)
		    objDB.close();
		    dbclose=1;
		    Toast.makeText(getBaseContext(), "Going back to Input Data page.", Toast.LENGTH_SHORT).show();
		    i=new Intent(this,Input.class);
        	startActivity(i);
       	    break;
	   case R.id.viewlist_label:
		    i=new Intent(this,ViewDetails.class);
		    startActivity(i);
		    break;
	  }
	  
  }
  public void onDestroy()
  {
	  super.onDestroy();
	  if(dbclose==0)
	  {
		  objDB.close();
	  }
  }

}
