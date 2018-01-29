package com.example.ajaysharvesh.firebase;

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

public class Drinks extends AppCompatActivity {

    int quantity = 0;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;
    String customerName = "";
    int price;
    private EditText code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        code = (EditText) findViewById(R.id.txtCustomerName);
        code.getText().toString();

    }

    public void submitOrder(View view) {
        String priceMessage = "Total = $ ";
        hasWhippedCream = CheckForWhippedCream();
        hasChocolate = CheckForChocolate();
        customerName = getCustomerName();
        int price1 = calculatePrice();
        createOrderSummary(price);
    }

    public void increment(View view) {
        if (quantity >= 100) {
            ShowMessage("You can't order more than 100 Drinks");
            return;
        } else {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }

    public void ShowMessage(String messageText) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, messageText, duration);
        toast.show();
    }

    public void decrement(View view) {
        if (quantity <= 1) {
            ShowMessage("You can't order less than 1 Drink");
            return;
        } else {
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
     */
    private int calculatePrice() {
        int price1;
        int topping = 0;
        if (hasChocolate) {
            topping += 2;
        }
        if (hasWhippedCream) {
            topping += 1;
        }
        price1 = (quantity * (price + topping));

        return price1;
    }

    private boolean CheckForWhippedCream() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.hasWhippedCream);
        boolean isChecked = checkBox.isChecked();
        return isChecked;
    }

    private boolean CheckForChocolate() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.hasChocolate);
        boolean isChecked = checkBox.isChecked();
        return isChecked;
    }

    private String getCustomerName() {
        EditText code1 = (EditText) findViewById(R.id.txtCustomerName);
        String code = code1.getText().toString();

        if (code.equals("DP1")) {
            price = 35;
        } else if (code.equals("DP2")) {
            price = 40;
        } else if (code.equals("DP3")) {
            price = 35;
        } else if (code.equals("DP4")) {
            price = 50;
        } else if (code.equals("DP5")) {
            price = 55;
        } else if (code.equals("dp1")) {
            price = 35;
        } else if (code.equals("dp2")) {
            price = 40;
        } else if (code.equals("dp3")) {
            price = 35;
        } else if (code.equals("dp4")) {
            price = 50;
        } else if (code.equals("dp5")) {
            price = 55;
        } else {
            price = 0;
            Toast.makeText(Drinks.this, "Incorrect Code.. Please enter the valid code given below !", Toast.LENGTH_SHORT).show();
        }

        return code;
    }

    /*
    * Return a message with all the information of the order
    * @param price is the price of a cup of coffee
    * */
    private void createOrderSummary(int price) {
        String message = getString(R.string.code) + " " + customerName + " \n";
        message += getString(R.string.order_summary_whipped_cream) + " : " + hasWhippedCream + "\n";
        message += getString(R.string.order_summary_chocolate) + " : " + hasChocolate + "\n";
        message += getString(R.string.order_summary_quantity) + " : " + quantity + "\n";
        message += getString(R.string.order_summary_price) + ": " + price + "\n";
        message += getString(R.string.thank_you);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        String[] addresses = new String[1];
        addresses[0] = "pizzapalsindia@gmail.com";
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject) + " " + customerName);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}



