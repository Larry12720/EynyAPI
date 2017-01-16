package eynyAPI.Model;

public class Video {
	private String title;
	private String href;
	private String videoLink;
	
	public Video(){
		
	}
	public Video(String title, String href, String videoLink)
	{
		setTitle(title);
		setHref(href);
		setVideoLink(videoLink);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	
	
}
