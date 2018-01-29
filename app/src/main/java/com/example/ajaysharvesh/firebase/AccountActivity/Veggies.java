package com.example.ajaysharvesh.firebase.AccountActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ajaysharvesh.firebase.R;

public class Veggies extends AppCompatActivity {

    int quantity=0;
    boolean hasWhippedCream=false;
    boolean hasChocolate=false;
    String customerName="";
    int price;
    private EditText code1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veggies);

        code1 = (EditText) findViewById(R.id.txtCustomerName);
        code1.getText().toString();

    }

    public void submitOrder(View view) {
        String priceMessage = "Total = Rs. ";
        hasWhippedCream= CheckForWhippedCream();
        hasChocolate=CheckForChocolate();
        customerName=getCustomerName();
        int price1 = calculatePrice();
        createOrderSummary(price1);
    }

    public void increment(View view) {
        if(quantity>=100) {
            ShowMessage("You can't order more than 100 Pizzas");
            return;
        }
        else{
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }

    public void ShowMessage(String messageText)
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,messageText,duration);
        toast.show();
    }

    public void decrement(View view) {
        if(quantity<=1) {
            ShowMessage("You can't order less than 1 Pizza");
            return;
        }
        else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int price1;
        int topping=0;
        if(hasChocolate){
            topping+=29;
        }
        if(hasWhippedCream){
            topping+=39;
        }
        price1 = (quantity*(price+topping));

        return price1;
    }

    private boolean CheckForWhippedCream()
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.hasWhippedCream);
        boolean isChecked = checkBox.isChecked();
        return isChecked;
    }

    private boolean CheckForChocolate()
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.hasChocolate);
        boolean isChecked = checkBox.isChecked();
        return isChecked;
    }

    private String getCustomerName()
    {
        EditText txtCustomerName = (EditText)findViewById(R.id.txtCustomerName);
        String code1 = txtCustomerName.getText().toString();

        if(code1.equals("V01S")) { price = 199; }
        else if (code1.equals("V01L")) { price = 459; }
        else if (code1.equals("V02M")) { price = 399; }
        else if (code1.equals("V03S")) { price = 159; }
        else if (code1.equals("V04S")) { price = 129; }
        else if (code1.equals("V03L")) { price = 549; }
        else if (code1.equals("V05M")) { price = 349; }
        else if (code1.equals("V04M")) { price = 389; }
        else if (code1.equals("V06L")) { price = 649; }
        else if (code1.equals("V07S")) { price = 269; }
        else if (code1.equals("V07M")) { price = 589; }
        else if (code1.equals("V07L")) { price = 799; }
        else if (code1.equals("V08L")) { price = 829; }
        else if (code1.equals("V09S")) { price = 329; }
        else if (code1.equals("V02S") || code1.equals("V06S")) { price = 249; }
        else if (code1.equals("V10L") || code1.equals("V11L")) { price = 929; }
        else if (code1.equals("V01M") || code1.equals("V05S")  || code1.equals("V08S")) { price = 299; }
        else if (code1.equals("V02L") || code1.equals("V04L")  || code1.equals("V08M")) { price = 599; }
        else if (code1.equals("V03M") || code1.equals("V10S")  || code1.equals("V11S")) { price = 289; }
        else if (code1.equals("V05L") || code1.equals("V06M")  || code1.equals("V09M")) { price = 499; }
        else if (code1.equals("V09L") || code1.equals("V10M")  || code1.equals("V11M")) { price = 689; }
        else {
            price = 0;
            Toast.makeText(Veggies.this, "Incorrect Code.. Please enter the valid code given below !", Toast.LENGTH_SHORT).show();
        }
        return code1;
    }

    /*
    * Return a message with all the information of the order
    * @param price is the price of a cup of coffee
    * */
    private void createOrderSummary(int price){
        String message = getString(R.string.code)+" "+customerName+" \n";
        message+=getString(R.string.order_summary_whipped_cream)+" : "+hasWhippedCream+"\n";
        message+=getString(R.string.order_summary_chocolate)+" : "+hasChocolate+"\n";
        message+=getString(R.string.order_summary_quantity)+" : "+quantity+"\n";
        message+=getString(R.string.order_summary_price)+": "+price+"\n";
        message+=getString(R.string.thank_you);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        String[] addresses = new String[1];
        addresses[0]="pizzapalsindia@gmail.com";
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject)+ " "+customerName);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
