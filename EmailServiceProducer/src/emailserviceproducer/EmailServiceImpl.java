package emailserviceproducer;

public class EmailServiceImpl implements EmailService {

	@Override
	public void sendEmail(String to, String subject, String body) {
		System.out.println("Sending email to " + to + ": " + subject + " - " + body);
	}
}
