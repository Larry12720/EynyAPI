package eynyAPI.Helpers;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.*; 
import java.util.stream.Stream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import eynyAPI.Model.Video;
/**
 * 
 * @author Long Chang Liang
 *
 */
public class FilterHelper {

	/**
	 * Filter the result of the response to form a list of videos and covert it to Json for frontend use
	 * @param response
	 * @return
	 */
	public static String filterResponse(String response){
		List<Video> videoList = new ArrayList<>();
		if(response != null && !response.isEmpty())
		{
			Document doc = Jsoup.parse(response);
			Elements elements = doc.select("table").select("tr").select("td").select("center").select("div")
			.select("p.channel-video-title").select("a");
			for(Element ele : elements)
			{
				Video video = new Video();
				video.setHref(ele.attr("href"));
				video.setTitle(ele.attr("title"));
				video.setVideoLink("");
				videoList.add(video);
			}
		}
		
		//COnvert it to json object
		Gson gson = new Gson();
		String list = gson.toJson(videoList);
		return list;
	}
	
	/**
	 * Getting the video links from Eyny and form a JSON for front end.
	 * @param response
	 * @return
	 */
	public static String filterVideoLink(String response)
	{
		String pattern ="'http://.*\\.mp4'";
		Pattern r = Pattern.compile(pattern);
		String newVideoLink = Stream.of(response).filter(r.asPredicate()).findFirst().get();
		String videoLink = null;
		String parseVideo = null;
		if(response !=null && !response.isEmpty())
		{
			 // Now create matcher object.
		      Matcher m = r.matcher(response);
		      if(m.find())
		      {
		    	  videoLink = m.group(0);
		      }
		}
		parseVideo = videoLink.replace("'", "");
		//video.setVideoLink(parseVideo);
		//COnvert it to json object
		Gson gson = new Gson();
		String list = gson.toJson(parseVideo);
		return list;
	}
}
