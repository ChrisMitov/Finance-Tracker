import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.finance.tracker.model.Currency;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConvertCurrency {
	private static final int SUCCESS_CODE = 200;
	private static final String MAIN_URL = "http://api.fixer.io/latest?base=";

	public static int convert(int sum,Currency currency, Currency newCurrency){

		if (currency.equals(newCurrency)) {
			return sum;
		}

		String url = MAIN_URL + currency + "&symbols=" + newCurrency;

		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");

			if (con.getResponseCode() == SUCCESS_CODE) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = reader.readLine();

				Gson gson = new Gson();
				JsonObject jsonObj = gson.fromJson(line, JsonObject.class);
				System.out.println(jsonObj);
				float rate = jsonObj.getAsJsonObject("rates").get(newCurrency.toString()).getAsFloat();
				return Math.round(sum * rate);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}
}
