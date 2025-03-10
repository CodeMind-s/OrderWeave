package emailserviceproducer;

public class EmailServiceImpl implements EmailService {

	@Override
	public void sendEmail(String to, String subject, String body) {
		System.out.println("\nSending email\n");
		System.out.println("\n\tTo: " + to );
		System.out.println("\tSubject: " + subject);
		System.out.println("\tBody: " + body + "\n");
	}
}
