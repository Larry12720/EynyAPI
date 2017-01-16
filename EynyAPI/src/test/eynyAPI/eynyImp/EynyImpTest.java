package test.eynyAPI.eynyImp;
import eynyAPI.EynyImp.EynyImp;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;


public class EynyImpTest {

	EynyImp imp = new EynyImp();
	@Test
	public void videoListResponseTest() throws IOException{
		String param = "One Piece";
		String response ="";
		response = imp.getVideoList(param, 1);
		assertNotEquals(response,"");
	}
	
	@Test
	public void getVideoLinkNotEmpty() throws IOException
	{
		String title = "ONE-PIECE 海賊王 第771話";
		String href = "/index.php?mod=video&vid=1504106";
		String reponse = imp.getVideoLink(title, href);
		assertNotEquals(reponse,"");
	}
}
