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

	public JsonManager() {
		file = new File(saveDirectoryPath);
		this.listOfAccount = new ArrayList<Account>();
	}

	public boolean RegisterAccount(Account userAccount) {
		if (IsTheFileAlreadyExist(this.file)) {
			if (IsTheUserAlreadyExist(userAccount)) {
				return false;
			} else {
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

	public boolean LoginAccount(Account userAccount) {
		if (IsTheFileAlreadyExist(this.file)) {
			if (IsTheUserAlreadyExist(userAccount)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public File GetFile() {
		return this.file;
	}

	public List<Account> ListOfAccount() {
		return this.listOfAccount;
	}

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

	public void AddTheAccountOnTheList(Account userAccount) {
		this.listOfAccount.add(userAccount);
	}

	public boolean IsTheUserAlreadyExist(Account userAccount) {
		boolean result = false;
		for (Account account : listOfAccount) {
			if (account.getUsername() == userAccount.getUsername()) {
				if (account.areThePasswordEquals(userAccount.getPasswordHash())) {
					result = true;
					break;
				} else {
					result = false;
					break;
				}
			}
			result = false;
		}
		return result;

	}

	public boolean IsTheFileAlreadyExist(File file) {
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public String UpdateUserAccount(Account oldAccount) {

		for (Account currentAccount : listOfAccount) {
			if (oldAccount.getUsername() == currentAccount.getUsername()) {
				currentAccount.setAccount_Level(oldAccount.getAccount_Level());
			}
		}
		return null;
	}

}
