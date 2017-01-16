package test.eynyAPI.Helpers;
import eynyAPI.EynyImp.EynyImp;
import eynyAPI.Helpers.FilterHelper;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
public class FilterHelperTest {

	private EynyImp imp = new EynyImp();
	
	@Test
	public void filterResponseTest() throws IOException{
		String response =FilterHelper.filterResponse(imp.getVideoList("One Piece", 1));
		assertNotEquals("",response);	
	}
	
	@Test
	public void filterVideoLink()
	{
		String title = "ONE-PIECE 海賊王 第771話";
		String href = "/index.php?mod=video&vid=1504106";
		String response = FilterHelper.filterVideoLink(imp.getVideoLink(title, href));
		assertNotEquals("", response);
	}
}

