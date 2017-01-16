package eynyAPI.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eynyAPI.EynyImp.EynyImp;
import eynyAPI.Helpers.FilterHelper;

public class EynyController {

	/**
	 * Should return a List of videos if not return empty
	 * @param term
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
    public String search(@RequestParam String term, @RequestParam String page) {
        EynyImp eynyImp = new EynyImp();
        String data = "";
        try {
			String response = eynyImp.getVideoList(term, Integer.parseInt(page));
			if(!response.equals(""))
			{
				data = FilterHelper.filterResponse(response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return data;
    }
	
	/**
	 * Return the Video Link, if not avialable return empty
	 * @param title
	 * @param href
	 * @return
	 */
	@RequestMapping(value="/videoLink", method=RequestMethod.GET)
	public String videoLink(@RequestParam String title, @RequestParam String href)
	{
		EynyImp eynyCont = new EynyImp();
		String data ="";
		String response = eynyCont.getVideoLink(title, href);
		data = FilterHelper.filterVideoLink(response);
				
		return data;
	}
}
