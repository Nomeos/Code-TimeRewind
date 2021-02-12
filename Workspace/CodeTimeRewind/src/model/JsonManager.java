package model;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class JsonManager {
	private Account userAccount = new Account();
	private String[][] jsonData = null;

	public JsonManager() {
	}

	public JsonManager(Account userAccount) {
		this.userAccount = userAccount;
	}

	public void WriteOnJson(Account userAccount) {
		// First Employee
		this.userAccount = userAccount;
		Gson json = new Gson();
		
		
		try (FileWriter file = new FileWriter(System.getProperty("user.dir")+"/save/accounts.json")) {
			 
            file.write(json.toJson(this.userAccount));
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public String ReadOnJson() {

		for (String[] account : jsonData) {
			for (String accountInformation : account) {

			}
		}
		return null;
	}

	public boolean isTheJsonWriteWell() {
		boolean x = true;

		return x;

	}

}
