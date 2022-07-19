package controller;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.MMusicDAO;
import model.MMusicVO;

public class Crawling {

	public static void sample() {
		final String url="https://www.melon.com/chart/index.htm";
		Document doc = null;
		try {
			doc=Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MMusicDAO mDAO=new MMusicDAO();
		Elements e1=doc.select(".ellipsis.rank01");
		Elements e2=doc.select(".checkEllipsis");
		Iterator<Element> itr1 = e1.iterator();
		Iterator<Element> itr2 = e2.iterator();
		
		while(itr1.hasNext()) {
			MMusicVO mVO=new MMusicVO();
			String str1=itr1.next().text(); // title
			String str2=itr2.next().text(); // artist
	
			mVO.setmTitle(str1);
			mVO.setmArtist(str2);		
			
			mDAO.insert(mVO);	
		}	
		System.out.println("로그: 샘플데이터 저장완료!");
	}

}
