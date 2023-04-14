package Controller;

import java.util.ArrayList;

import DB.DB_Conn;
import DataClass.storeData;
import DataClass.tagData;

public class searchResult {
	DB_Conn db;
	ArrayList<storeData> storeInfo;
	ArrayList<tagData> tagInfo;

	String[] space = { "          ", "         ", "        ", "       ", "      ", "     ", "    ", "   ", "  " };

	public searchResult(String query) {
		db = new DB_Conn();

		// 검색어 글자수 제한 10
		// query 를 재가공한다. 쓸데없는 공백 제거

		query = query.trim();

		for (int i = 10; i >= 2; i--) {
			query = query.replace(space[10 - i], " ");
		}

		calcStoreInfo(query);
		calcTagInfo(query);
	}

	public void calcStoreInfo(String query) {
		storeInfo = db.getStoreInfo(query);
		ArrayList<storeData> tmp = db.getMenuInfo(query);

		for (storeData sd : tmp) {
			storeInfo.add(sd);
		}
		
		storeInfo = (ArrayList<storeData>) DeduplicationUtils.deduplication(storeInfo, storeData::getStoreCode);
	}

	public void calcTagInfo(String query) {
		tagInfo = db.getTagInfo(query);
	}
}