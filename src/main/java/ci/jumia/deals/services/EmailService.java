package ci.jumia.deals.services;

import ci.jumia.deals.exception.EmailException;
import ci.jumia.deals.services.interfaces.EmaillSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.logging.Logger;

/**
 * @author Attoungbre Ange François 2023-11-19
 */
@Service
public class EmailService implements EmaillSender {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final Logger logger = Logger.getLogger(EmailService.class.getName());

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void envoyerEmailAvecTemplate(
            String mailDestinataire, String objetMail,
            String templateName, Context context
    ) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setTo(mailDestinataire);
            helper.setSubject(objetMail);
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            logger.info("Email envoyé à : " + mailDestinataire + " objet : \"" + objetMail + "\"");
        } catch (MessagingException e) {
            String message = "Erreur lors de l'envoi du mail à %s avec pour objet %s";
            throw new EmailException(String.format(message, mailDestinataire, objetMail));
        }

    }
}
