package com.example.lowse.justjava;

/**
* Add your package below. Package name can be found in the project's AndroidManifest.xml file.
* This is the package name our example uses:
*
* package com.example.android.justjava;
*/

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    int quantity = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox cbCoffee = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox cbChocolate = (CheckBox) findViewById(R.id.chcolate_coffee);
        EditText etName = (EditText) findViewById(R.id.name_field);

        String priceMessage = "Name: " +  etName.getText().toString()
                + "\nAdd whipped cream?" + cbCoffee.isChecked()
                + "\nAdd chocolate?" + cbChocolate.isChecked()
                + "\nQuantity:" + quantity
                + "\nTotal:" + calculatePrice(quantity)
                + "\nTHANK YOU";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java order by " + etName.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else {
            Toast.makeText(this, "nope", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int price = 5;
        return price;
    }
    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity, int price) {
        return price * quantity;
    }
    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        return price;
    }
}