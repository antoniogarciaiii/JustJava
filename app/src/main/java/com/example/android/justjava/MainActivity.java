/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //gets you the contents of customer_Name EditText View
        EditText customerNameEntryBox = (EditText) findViewById(R.id.customer_Name);
        String customername = customerNameEntryBox.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream =  whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(customername, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     * @param addWhippedCream is whether or not customer wants whipped cream
     * @param addChocolate is whether or not customer wants chocolate
     * @return  total price of order
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int baseprice = 5;

        if(addWhippedCream) {
            baseprice = baseprice + 1;
        }
        if(addChocolate) {
            baseprice = baseprice + 2;
        }

        return quantity * baseprice;
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100){
            Toast.makeText(getApplicationContext(), "You can't order 100+ coffees",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(getApplicationContext(), "You can't order 0 coffees",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     *  This method displays a summary of the order
     *  @param price of the order
     *  @param nombreDeCliente is the name of the customer.
     *                         no, i couldn't think of any other name for this argument.
     *                         because it is analogous to name, it works for me.
     *                         i don't intend to share this code with anyone.
     *                         i'm bilingual--this works for me.
     *                         don't judge.
     *  @param addWhippedCream is whether or not user wants whipped cream topping
     *  @param addChocolate is whether or not the user wants chocolate topping
     *  @return text summary of order
     */
    private String createOrderSummary(String nombreDeCliente, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + nombreDeCliente;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nquantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

}