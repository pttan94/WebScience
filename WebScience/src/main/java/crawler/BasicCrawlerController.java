package crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class BasicCrawlerController {
	public static void main(String[] args) throws Exception {

		String crawlStorageFolder = "/data/crawl/root";
		int numberOfCrawlers = 7;
		//int numberOfPages = 100;

		CrawlConfig config = new CrawlConfig();
		
		//config.setMaxPagesToFetch(numberOfPages);

		config.setCrawlStorageFolder(crawlStorageFolder);

		String[] crawlDomains = { "http://tiki.vn/" };

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */

		for (String domain : crawlDomains) {
			controller.addSeed(domain);
		}

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(BasicCrawler.class, numberOfCrawlers);

		System.out.println("Done!");
	}
}
