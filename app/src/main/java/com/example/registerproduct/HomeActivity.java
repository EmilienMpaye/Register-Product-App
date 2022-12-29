package com.example.registerproduct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

//reference to the buttons and other controls in layout
        EditText product_name,product_quantity,product_pric;
        Button btn_Add;
        ListView lv_customerList;
        ArrayAdapter productArrayAdapter;
        ProductDatabaseHelper productDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Product View");

        product_name = findViewById(R.id.product_name);
        product_quantity = findViewById(R.id.product_quantity);
        product_pric = findViewById(R.id.product_price);
        btn_Add = findViewById(R.id.btn_Add);
        lv_customerList = findViewById(R.id.lv_customerList);
        productDatabaseHelper=new ProductDatabaseHelper(HomeActivity.this);

        
        showCustomerInListView(productDatabaseHelper);

        //button listeners for the add and view all buttons

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new instance of our class using constructor

                ProductModel productModel;
                try {
                    productModel = new ProductModel(-1, product_name.getText().toString(),product_quantity.getText().toString(),
                            Integer.parseInt(product_pric.getText().toString()));
                    Toast.makeText(HomeActivity.this, productModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(HomeActivity.this, "Error occurred creating product", Toast.LENGTH_SHORT).show();

                    productModel = new ProductModel(-1,"error","No kg",1);
                }
                ProductDatabaseHelper dataBaseHelper = new ProductDatabaseHelper(HomeActivity.this);
                boolean success = dataBaseHelper.addOne(productModel);
                Toast.makeText(HomeActivity.this, "Success=" +success, Toast.LENGTH_SHORT).show();
                showCustomerInListView(dataBaseHelper);

            }
        });


        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductModel clickedProduct = (ProductModel) adapterView.getItemAtPosition(i);
                //productDatabaseHelper.deleteOne(clickedCustomer);
                showCustomerInListView(productDatabaseHelper);
               Toast.makeText(HomeActivity.this, "details" + clickedProduct.toString() ,Toast.LENGTH_SHORT).show();

               //Product detail
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("product details");
                builder.setMessage(clickedProduct.toString());
                builder.show();

            }

        });

    }
    private void showCustomerInListView(ProductDatabaseHelper dataBaseHelper2) {
        productArrayAdapter = new ArrayAdapter<ProductModel>(HomeActivity.this,
                android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        lv_customerList.setAdapter(productArrayAdapter);

    }

}
