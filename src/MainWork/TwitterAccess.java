package MainWork;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.swing.DefaultListModel;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public final class TwitterAccess  {

	Twitter twitter = TwitterFactory.getSingleton();
	List<Status> timelineStatuses = new ArrayList<Status>();
	private static String estado = "O projeto de ES é interessante";

	public static void main(String[] args) {
		TwitterAccess t = new TwitterAccess();
		t.autenticacao();
	}


	public void autenticacao () {	
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			.setOAuthConsumerKey("omTUULTL1Pd2i4XfVORW2Smzf")
			.setOAuthConsumerSecret("RatuUpSGLl0LLoajnBw3IojT6fF9vDbC7yJ19FpCav89g65Acj")
			.setOAuthAccessToken("727114274489913345-4ax0nVCGOl5qUa2dL4StFQzyxUd06sE")
			.setOAuthAccessTokenSecret("BoRBgd7Pn9eaPDdfHx5AFBpbsY9Q8apNuLxtqXhwRYzzW");
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter = tf.getInstance();
			timelineStatuses = twitter.getHomeTimeline();
		}catch (Exception e) { 
			System.out.println(e.getMessage()); 
		}
	}


	public void getTimeline(String procurar,  DefaultListModel<String> t){

		try{
			t.addElement("|||||||||||||||||||||||| VER TIMELINE ||||||||||||||||||||||||");
			int counter=0;
			int counterTotal = 0;
			for (Status status : timelineStatuses) {
				// Filters only tweets from user "catarina"
				if (status.getUser().getName() != null) {
					t.addElement(status.getId() + "    " + status.getCreatedAt() + " - " + "@" + status.getUser().getName() + ":" + status.getText());
					t.addElement("____________________________________________________________");
					counter++;
				}
				counterTotal++;
			}


			t.addElement("-------------\nNº of Results: " + counter+"/"+counterTotal);
		} catch (Exception e) { 
			t.addElement(e.getMessage() + " - deu erro"); }
	}

	public void updateStatus(String estado) {
		Status status = null;
		try {
			status = twitter.updateStatus(estado);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}

}
