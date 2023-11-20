package ci.jumia.deals.services.interfaces;

import org.thymeleaf.context.Context;

/**
 * @author Attoungbre Ange François 2023-11-19
 */
public interface EmaillSender {
    void envoyerEmailAvecTemplate(
            String emailDestinataire, String objetMail, String templateName, Context context);
}
