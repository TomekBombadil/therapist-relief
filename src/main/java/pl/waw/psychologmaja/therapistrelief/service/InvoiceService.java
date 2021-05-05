package pl.waw.psychologmaja.therapistrelief.service;

import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;
import pl.waw.psychologmaja.therapistrelief.entity.Session;
import pl.waw.psychologmaja.therapistrelief.entity.Invoice;
import pl.waw.psychologmaja.therapistrelief.repository.InvoiceRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void getInvoice(Session session, HttpServletResponse response) throws DocumentException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDateTime = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Faktura_" + String.join("_", session.getPatients()
                .stream().map(patient -> patient.getFirstName() + "_" + patient.getLastName())
                .collect(Collectors.toSet())) + "_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Invoice invoice = new Invoice(session);
        invoiceRepository.save(invoice);
        invoice.get(response);
    }
}
