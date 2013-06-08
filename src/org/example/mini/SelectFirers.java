package org.example.mini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectFirers extends Activity implements OnClickListener{

	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.select_firers);
		 
		  Button arbitraryButton=(Button) findViewById(R.id.arbitrary_label);
		  arbitraryButton.setOnClickListener(this);
		  
		  Button selmanualButton=(Button) findViewById(R.id.selmanual_label);
		  selmanualButton.setOnClickListener(this);
		  
		  Button weakButton=(Button) findViewById(R.id.weak_label);
		  weakButton.setOnClickListener(this);
		  
		  Button balanceButton=(Button) findViewById(R.id.balance_label);
		  balanceButton.setOnClickListener(this);
		  
		  Button homeButton=(Button) findViewById(R.id.firershome_label);
		  homeButton.setOnClickListener(this);
		  
	  }
	  public void onClick(View v)
	  {
	  	Intent i;
	  	switch(v.getId())
	  	{
	  	 case R.id.arbitrary_label:i=new Intent(this,Arbitrary.class);
	  	                           startActivity(i);
	  	                           break;
	  	 case R.id.selmanual_label:i=new Intent(this,SelectManually.class);
	  	                          startActivity(i);
	  	                          break;
	  /*	 case R.id.weak_label:i=new Intent(this,Delete.class);
	                              startActivity(i);
	                              break;
	  	 case R.id.balance_label:i=new Intent(this,SelUpdate.class);
	                               startActivity(i);
	                               break;  */
	  	 case R.id.firershome_label:i=new Intent(this,InputData.class);
	  	                            startActivity(i);
	  	                            break;

	  	}
	  }

}
