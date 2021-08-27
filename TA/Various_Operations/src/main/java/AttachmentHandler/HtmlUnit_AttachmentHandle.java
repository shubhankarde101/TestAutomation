package AttachmentHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.attachment.AttachmentHandler;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/*AttachmentHandler
If you don't like the default behavior, you can implement your own way of processing. This is done by registering your own implementation of the AttachmentHandler interface in the WebClient.

The AttachmentHandler based download support works in addition to the default UnexpectedPage based support. This means, if your AttachmentHandler does not handle the content, the webClient will fall back to the default solution and place an UnexpectedPage inside the window. You can overwrite the method isAttachment() in your AttachmentHandler to only handle dedicated responses - the default implementation only detects responses having a content-disposition header of type 'attachment'.

The method

boolean handleAttachment(final WebResponse response)
is called if the response was detected as attachment (see above isAttachment()). You can process the attachment in your implementation (e.g. by saving it to a file) or simply return false. Based on the result of the call, the AttachmentHandler supports two operation modes.
true signals the rest of the code the response is handled by your code; there is NO replacement of the current page with an UnexpectedPage and also the method
void handleAttachment(Page page)
from you AttachmentHandler is NOT called.
By returning false, the response will be processed further like this:
at first a new window will be created,
next the UnexpectedPage will be build,
then the method
void handleAttachment(Page page)
from you AttachmentHandler is called and
finally the UnexpectedPage will be placed inside the new window.*/


public class HtmlUnit_AttachmentHandle {

	@SuppressWarnings("serial")
	@Test
	public void attachmentHandle() throws NoSuchFieldException, IllegalAccessException, FailingHttpStatusCodeException,
			MalformedURLException, IOException {

		// usage of Functional Interface in driver method
		WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		WebClient client = (WebClient) fetch(driver, "Using Functional Interface");
		System.out.println(client.getBrowserVersion().isChrome());

		// Attachment handle using Default method		
		HtmlPage page1 = client.getPage("https://file-examples.com/index.php/sample-documents-download/sample-pdf-download/");
		WebResponse downloadpage = page1.getAnchorByText("Download sample pdf file").click().getWebResponse();
		System.out.println("pdf Element Clicked..");
		try {
			IOUtils.copy(downloadpage.getContentAsStream(), new FileOutputStream("File1.pdf"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Attachment handle using Attachment Handler method
		client.setAttachmentHandler(new AttachmentHandler() {
			@Override
			public boolean handleAttachment(final WebResponse response) {
				// TODO Auto-generated method stub
				System.out.println("Handling attachment using Attachment Handler");
				System.out.println(response.getContentType());
				if(response.getContentType().equalsIgnoreCase("application/vmd.ms-excel"))
				{
					try {
						IOUtils.copy(downloadpage.getContentAsStream(), new FileOutputStream("File2.xls"));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return true;

			}			
			@Override
			public void handleAttachment(final Page page) {
				// TODO Auto-generated method stub
				System.out.println("This method is not called as the other overloaded method returns true");

			}

		});
		HtmlPage page2 = client.getPage("https://file-examples.com/index.php/sample-documents-download/sample-xls-download/");
        page2.getAnchorByText("Download sample xls file").click();
        System.out.println("xls Element Clicked..");
		
		

	}

	private static Object fetch(Object o1, Object o2) throws NoSuchFieldException, IllegalAccessException {
		Demo d = (obj1, obj2) -> {

			Field f = obj1.getClass().getDeclaredField("webClient");
			f.setAccessible(true);
			System.out.println(obj2);
			return f.get(obj1);

		};

		return d.get(o1, o2);

	}

}

