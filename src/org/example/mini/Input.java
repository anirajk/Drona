package org.example.mini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class Input extends Activity implements OnClickListener{
  public void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.input_data);
	 
	  Button inputManuallyButton=(Button) findViewById(R.id.inputmanual_label);
	  inputManuallyButton.setOnClickListener(this);
	  
	  Button loadButton=(Button) findViewById(R.id.loaddb_label);
	  loadButton.setOnClickListener(this);
	  
	  Button deleteButton=(Button) findViewById(R.id.delete_label);
	  deleteButton.setOnClickListener(this);
	  
	  Button updateButton=(Button) findViewById(R.id.selupdate_label);
	  updateButton.setOnClickListener(this);
	  
	  Button homeButton=(Button) findViewById(R.id.inputhome_label);
	  homeButton.setOnClickListener(this);
	  
  }
  public void onClick(View v)
  {
  	Intent i;
  	switch(v.getId())
  	{
  	 case R.id.inputmanual_label:i=new Intent(this,InputManually.class);
  	                               startActivity(i);
  	                               break;
  	 case R.id.loaddb_label:i=new Intent(this,LoadDatabase.class);
  	                          startActivity(i);
  	                          break;
  	 case R.id.delete_label:i=new Intent(this,Delete.class);
                              startActivity(i);
                              break;
  	 case R.id.selupdate_label:i=new Intent(this,SelUpdate.class);
                               startActivity(i);
                               break;
  	 case R.id.inputhome_label:i=new Intent(this,InputData.class);
  	                             startActivity(i);
  	                             break;

  	}
  }

}
