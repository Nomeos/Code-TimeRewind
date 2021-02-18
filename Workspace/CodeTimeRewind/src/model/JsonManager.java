package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonManager {

	private List<Account> listOfAccount = null;
	private Gson json = null;
	private String saveDirectoryPath = System.getProperty("user.dir") + "\\save\\accounts.json";
	private File file;
	private FileReader fileReader;
	private FileWriter fileWriter;
	private boolean isRegister = false;

	//Constructor of the JsonManager class
	public JsonManager() {
		file = new File(saveDirectoryPath);
		this.listOfAccount = new ArrayList<Account>();
	}
	//Principal method for check if the user can register
	public boolean RegisterAccount(Account userAccount) {
		if (IsTheFileAlreadyExist(this.file)) {
			this.isRegister = true;
			if (IsTheUserAlreadyExist(userAccount)) {
				return false;
			} else {
				userAccount.setAccount_Level(1);
				AddTheAccountOnTheList(userAccount);
				WriteOnJson();
				return true;
			}
		} else {
			AddTheAccountOnTheList(userAccount);
			WriteOnJson();
			return true;
		}
	}
	//Principal method for check if the user can login
	public boolean LoginAccount(Account userAccount) {
		if (IsTheFileAlreadyExist(this.file)) {
			this.isRegister = false;
			if (IsTheUserAlreadyExist(userAccount)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	// Get feature for the list of account
	public List<Account> ListOfAccount() {
		return this.listOfAccount;
	}
	//Write the list of account object directly on the json
	public void WriteOnJson() {
		try {
			json = new Gson();
			this.fileWriter = new FileWriter(saveDirectoryPath);
			this.fileWriter.write(json.toJson(this.listOfAccount));
			this.fileWriter.flush();
			this.fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Retrieve all data from the json and return a list of account
	public List<Account> GetAllDataFromJson() {

		try {
			this.fileReader = new FileReader(saveDirectoryPath);
			BufferedReader br = new BufferedReader(fileReader);
			String s;
			System.out.println(saveDirectoryPath);
			while ((s = br.readLine()) != null) {

				System.out.println(s);
				json = new Gson();
				this.listOfAccount = json.fromJson(s, new TypeToken<List<Account>>() {
				}.getType());

			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.listOfAccount;
	}
	//Add the user account in the list of account.
	public void AddTheAccountOnTheList(Account userAccount) {
		this.listOfAccount.add(userAccount);
	}
	//Check if the user already exist in the json, it returns a boolean (works with login and register).
	public boolean IsTheUserAlreadyExist(Account userAccount) {
		boolean result = false;
		if (isRegister) {
			for (Account account : GetAllDataFromJson()) {
				if (!account.getUsername().equals(userAccount.getUsername())) {
					result = false;
				} else {
					result = true;
					break;
				}

			}

		} else {
			for (Account account : GetAllDataFromJson()) {
				if (account.getUsername().equals(userAccount.getUsername())) {
					if (account.areThePasswordEquals(userAccount.getPassword())) {
						result = true;
						break;
					} else {
						result = false;
					}
				}
			}
		}
		return result;

	}
	//Check if the file already exist and return a boolean.
	public boolean IsTheFileAlreadyExist(File file) {
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}
	//Update the user account with the new information.
	public String UpdateUserAccount(Account oldAccount) {

		for (Account currentAccount : listOfAccount) {
			if (oldAccount.getUsername() == currentAccount.getUsername()) {
				currentAccount.setAccount_Level(oldAccount.getAccount_Level());
			}
		}
		return null;
	}
	public File GetFile() {
		return this.file;
	}

}
