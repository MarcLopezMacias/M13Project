package cat.itb.m13project.payment;

import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;

import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.config.CheckoutConfig;
import com.paypal.checkout.config.Environment;
import com.paypal.checkout.config.SettingsConfig;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.UserAction;

import cat.itb.m13project.BuildConfig;

import static cat.itb.m13project.ConstantVariables.CLIENT_KEY;

public class PayPalAPI extends Application {

    @Override

    public void onCreate() {
        super.onCreate();
        CheckoutConfig config = new CheckoutConfig(
                this,
                CLIENT_KEY,
                Environment.SANDBOX,
                String.format("%s://paypalpay", BuildConfig.APPLICATION_ID),
                CurrencyCode.EUR,
                UserAction.PAY_NOW,
                new SettingsConfig(
                        true,
                        false
                )
        );
        PayPalCheckout.setConfig(config);
    }
}