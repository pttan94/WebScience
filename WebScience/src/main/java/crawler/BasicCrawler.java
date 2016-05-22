package crawler;

import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


public class BasicCrawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp3|zip|gz))$");
	//private boolean test = true;
	//private int num = 1;
	/**
	 * This method receives two parameters. The first parameter is the page in
	 * which we have discovered this new url and the second parameter is the new
	 * url. You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic). In this example,
	 * we are instructing the crawler to ignore urls that have css, js, git, ...
	 * extensions and to only accept urls that start with
	 * "http://www.ics.uci.edu/". In this case, we didn't need the referringPage
	 * parameter to make the decision.
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		
		return !FILTERS.matcher(href).matches()
				&& href.startsWith("http://tiki.vn/");
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		
		//if(num == 100)
		//{
		//	return;
		//}
		//else num++;
		
		//page.setContentCharset("UTF-8");
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);
		//System.out.println("Num: " + num);
		System.out.println("=========");
		//num++;
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			
			String html = htmlParseData.getHtml();
			
			//System.out.println("Html: " + html);
			//System.out.println("Html length: " + html.length());

			Document doc = Jsoup.parse(html);
			Element element = doc.getElementById("review-favorite");
			if(element != null)
			{
				Elements es = element.getElementsByClass("item");
				
				for (Element e : es) {
					  String name  = e.getElementsByClass("name").text();
					  System.out.println(name);
					}
			}
			
			//System.out.println(element.html());

			
		}
	}
}
